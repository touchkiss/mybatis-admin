<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperPackage}">
    <#if tableConfig.getCache()??>
    <cache<#if tableConfig.getCache().getType()??>
                type="${tableConfig.getCache().getType()}"</#if><#if tableConfig.getCache().getEviction()??>
                eviction="${tableConfig.getCache().getEviction()}"</#if><#if tableConfig.getCache().getFlushInterval()??>
                flushInterval="${tableConfig.getCache().getFlushInterval()?c}"</#if><#if tableConfig.getCache().getReadOnly()??>
                readOnly="${tableConfig.getCache().getReadOnly()}"</#if><#if tableConfig.getCache().getSize()??>
                size="${tableConfig.getCache().getSize()?c}"</#if> /></#if>
    <resultMap id="baseResultMap" type="${entityPackage}">
    <#list columns as column>
      <#if column.getIsPrimaryKey()>
        <id column="${column.getColumnName()}" property="${column.getJavaProperty()}" jdbcType="${column.getJdbcType()}"/>
      </#if>
    </#list>
    <#list columns as column>
      <#if !column.getIsPrimaryKey()>
        <result column="${column.getColumnName()}" property="${column.getJavaProperty()}" jdbcType="${column.getJdbcType()}"/>
      </#if>
    </#list>
    <#list tableConfig.customProperties as customProperty>
        <!-- ${customProperty.getRemarks()} 自定义属性 映射 -->
        <result column="${customProperty.getJavaName()}" property="${customProperty.getJavaName()}"/>
    </#list>
    </resultMap>

    <sql id="selectSql">
        select
        <if test="meta.distinct != null and meta.distinct == true"> distinct </if>
        <foreach collection="meta.fields" item="item" index="index" separator=",">
            <if test="item.table.aliasTable != null and item.isFunction == false">${r'$'}{item.table.aliasTable}.
            </if><#if context.getUseMark()>
            <choose>
                <when test="item.isFunction == false">"${r'$'}{item.field}"</when>
                <otherwise>${r'$'}{item.field}</otherwise>
            </choose><#else>${r'$'}{item.field}</#if> </foreach>
        from
        <if test="meta.from.getSchema() != null"><#if context.getUseMark()>"</#if>${r'$'}{meta.from.getSchema()}<#if context.getUseMark()>"</#if>.</if><#if context.getUseMark()>"</#if>${r'$'}{meta.from.table}<#if context.getUseMark()>"</#if> <if test="meta.from.aliasTable != null">${r'$'}{meta.from.aliasTable}</if>
        <if test="meta.joinTable != null">
            <foreach collection="meta.joinTable" item="item" index="index" separator=" ">
            ${r'$'}{item.join} join
                <if test="item.getSchema() != null"><#if context.getUseMark()>"</#if>${r'$'}{item.getSchema()}<#if context.getUseMark()>"</#if>.</if><#if context.getUseMark()>"</#if>${r'$'}{item.table}<#if context.getUseMark()>"</#if> ${r'$'}{item.aliasTable}
                <if test="item.conditions != null">on
                    <foreach collection="item.conditions" item="condition" index="i">
                        <if test="i &gt; 0">${r'$'}{item.sqlLogic}</if>
                        <if test="condition.isRelateField == true">
                        ${r'$'}{item.aliasTable}.<#if context.getUseMark()>"</#if>${r'$'}{condition.joinField}<#if context.getUseMark()>"</#if> ${r'$'}{condition.operator} ${r'$'}{condition.relateTableAlias}.<#if context.getUseMark()>"</#if>${r'$'}{condition.relateField}<#if context.getUseMark()>"</#if>
                        </if>
                        <if test="condition.isRelateField == false">
                        ${r'$'}{item.aliasTable}.<#if context.getUseMark()>"</#if>${r'$'}{condition.joinField}<#if context.getUseMark()>"</#if> ${r'$'}{condition.operator} ${r'#'}{condition.relateValue}
                        </if>
                    </foreach>
                </if>
            </foreach>
        </if>
        <if test="meta.wheres != null">
            WHERE
            <foreach collection="meta.wheres" item="item" index="index" separator=" ">
                <if test="item.prefix != null">${r'$'}{item.prefix}</if>
                <if test="item.value != null">${r'#'}{item.value}</if>
                <if test="item.suffix != null">${r'$'}{item.suffix}</if>
            </foreach>
        </if>
        <if test="meta.groupFields != null">
            group by
            <foreach collection="meta.groupFields" item="item" index="index" separator=",">
                <if test="item.table.aliasTable != null and item.isFunction == false">${r'$'}{item.table.aliasTable}.
                </if><#if context.getUseMark()>"</#if>${r'$'}{item.field}<#if context.getUseMark()>"</#if>
            </foreach>
        </if>
        <if test="meta.sortFields != null">
            order by
            <foreach collection="meta.sortFields" item="item" index="index" separator=",">
                <if test="item.table.aliasTable != null and item.isFunction == false">${r'$'}{item.table.aliasTable}.
                </if><#if context.getUseMark()>"</#if>${r'$'}{item.field}<#if context.getUseMark()>"</#if> ${r'$'}{item.order}
            </foreach>
        </if>
    </sql>

    <sql id="whereSql">
        <if test="wheres != null">
            WHERE
            <foreach collection="wheres" item="item" index="index" separator=" ">
                <if test="item.prefix != null">${r'$'}{item.prefix}</if>
                <if test="item.value != null">${r'#'}{item.value}</if>
                <if test="item.suffix != null">${r'$'}{item.suffix}</if>
            </foreach>
        </if>
    </sql>

    <select id="selectAll"
            resultMap="baseResultMap"<#if tableConfig.getCache()?? && tableConfig.getCache().getSelectUseCache()>
            useCache="true"</#if>>
        SELECT
        <include refid="allFields"/>
        FROM <#if tableConfig.getSchema()??><#if context.getUseMark()>
        "</#if>${tableConfig.getSchema()}<#if context.getUseMark()>"</#if>.</#if><#if context.getUseMark()>
        "</#if>${table.getTableName()}<#if context.getUseMark()>"</#if>
    </select>

    <select id="select" parameterType="com.touchkiss.mybatis.sqlbuild.selector.SelectMetadata"
            resultMap="baseResultMap" <#if tableConfig.getCache()?? && tableConfig.getCache().getSelectUseCache()>
            useCache="true"</#if>>
        <include refid="selectSql"/>
    </select>

    <select id="selectPage" parameterType="com.touchkiss.mybatis.sqlbuild.selector.SelectMetadata"
            resultMap="baseResultMap" <#if tableConfig.getCache()?? && tableConfig.getCache().getSelectUseCache()>
            useCache="true"</#if>>
        <include refid="selectSql"/>
    </select>

    <select id="selectOne" parameterType="com.touchkiss.mybatis.sqlbuild.selector.SelectMetadata"
            resultMap="baseResultMap" <#if tableConfig.getCache()?? && tableConfig.getCache().getSelectUseCache()>
            useCache="true"</#if>>
        <include refid="selectSql"/>
    </select>

    <select id="selectForMap" parameterType="com.touchkiss.mybatis.sqlbuild.selector.SelectMetadata"
            resultType="map" <#if tableConfig.getCache()?? && tableConfig.getCache().getSelectUseCache()>
            useCache="true"</#if>>
        <include refid="selectSql"/>
    </select>

    <select id="selectForListMap" resultType="map"
            parameterType="com.touchkiss.mybatis.sqlbuild.selector.SelectMetadata" <#if tableConfig.getCache()?? && tableConfig.getCache().getSelectUseCache()>
            useCache="true"</#if>>
        <include refid="selectSql"/>
    </select>

    <select id="selectPageForListMap" resultType="map"
            parameterType="com.touchkiss.mybatis.sqlbuild.selector.SelectMetadata" <#if tableConfig.getCache()?? && tableConfig.getCache().getSelectUseCache()>
            useCache="true"</#if>>
        <include refid="selectSql"/>
    </select>

    <sql id="allFields">
        <#list columns as column><#if column_index gt 0>,</#if><#if context.getUseMark()>
            "</#if>${column.getColumnName()}<#if context.getUseMark()>"</#if></#list>
    </sql>

    <insert id="insert"
            parameterType="${entityPackage}"<#if tableConfig.getCache()?? && tableConfig.getCache().getInsertFlushCache()>
            flushCache="true"</#if><#if primaryKeyColumns?? && primaryKeyColumns?size gt 0 && tableConfig.getUseGeneratedKeys()>
            useGeneratedKeys="true"
            keyColumn="<#list primaryKeyColumns as col><#if col_index gt 0>, </#if>${col.getColumnName()}</#list>"
            keyProperty="<#list primaryKeyColumns as col><#if col_index gt 0>, </#if>${col.getJavaProperty()}</#list>"</#if>>
        <#if tableConfig.selectKeys??><#list tableConfig.getSelectKeys() as selectKey><#if selectKey.getExistColumn()>
        <selectKey keyProperty="${selectKey.getJavaProperty()}" order="${selectKey.getOrder()}"
                   resultType="${selectKey.getResultType()}"
                   statementType="${selectKey.getStatementType()}">${selectKey.getStatement()}</selectKey>
        </#if></#list></#if>
        insert into <#if tableConfig.getSchema()??><#if context.getUseMark()>
        "</#if>${tableConfig.getSchema()}<#if context.getUseMark()>"</#if>.</#if><#if context.getUseMark()>
        "</#if>${table.getTableName()}<#if context.getUseMark()>"</#if> (<#list columns as column><#if column_index gt 0>,</#if><#if context.getUseMark()>"</#if>${column.getColumnName()}<#if context.getUseMark()>"</#if></#list>)
        values (<#list columns as column><#if column_index gt 0>,</#if><if test="record.${column.getJavaProperty()} != null">${r'#'}{record.${column.getJavaProperty()},jdbcType=${column.getJdbcType()}}</if><if test="record.${column.getJavaProperty()} == null">null</if></#list>)
    </insert>

    <insert id="insertSelective"
            parameterType="${entityPackage}"<#if tableConfig.getCache()?? && tableConfig.getCache().getInsertFlushCache()>
            flushCache="true"</#if><#if primaryKeyColumns?? && primaryKeyColumns?size gt 0 && tableConfig.getUseGeneratedKeys() == true>
            useGeneratedKeys="true"
            keyColumn="<#list primaryKeyColumns as col><#if col_index gt 0>, </#if>${col.getColumnName()}</#list>"
            keyProperty="<#list primaryKeyColumns as col><#if col_index gt 0>, </#if>${col.getJavaProperty()}</#list>"</#if>>
        <#if tableConfig.selectKeys??><#list tableConfig.getSelectKeys() as selectKey><#if selectKey.getExistColumn()>
        <selectKey keyProperty="${selectKey.getJavaProperty()}" order="${selectKey.getOrder()}"
                   resultType="${selectKey.getResultType()}"
                   statementType="${selectKey.getStatementType()}">${selectKey.getStatement()}</selectKey>
        </#if></#list></#if>
        insert into <#if tableConfig.getSchema()??><#if context.getUseMark()>
        "</#if>${tableConfig.getSchema()}<#if context.getUseMark()>"</#if>.</#if><#if context.getUseMark()>
        "</#if>${table.getTableName()}<#if context.getUseMark()>"</#if>
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list columns as column>
            <if test="record.${column.getJavaProperty()} != null">${column.getColumnName()},</if>
        </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
        <#list columns as column>
            <if test="record.${column.getJavaProperty()} != null">${r'#'}{record.${column.getJavaProperty()},jdbcType=${column.getJdbcType()}},</if>
        </#list>
        </trim>
    </insert>

    <update id="update" parameterType="map"<#if tableConfig.getCache()?? && tableConfig.getCache().updateFlushCache>
            flushCache="true"</#if>>
        UPDATE <#if tableConfig.getSchema()??><#if context.getUseMark()>
        "</#if>${tableConfig.getSchema()}<#if context.getUseMark()>"</#if>.</#if><#if context.getUseMark()>
        "</#if>${table.getTableName()}<#if context.getUseMark()>"</#if> SET
        <#list columns as column>
          <#if column_index gt 0>,</#if><#if context.getUseMark()>
            "</#if>${column.getColumnName()}<#if context.getUseMark()>"</#if> = <if test="record.${column.getJavaProperty()} != null">${r'#'}{record.${column.getJavaProperty()},jdbcType=${column.getJdbcType()}}</if><if test="record.${column.getJavaProperty()} == null">null</if>
        </#list>
        <include refid="whereSql"/>
    </update>

    <update id="updateSelective"
            parameterType="map"<#if tableConfig.getCache()?? && tableConfig.getCache().updateFlushCache>
            flushCache="true"</#if>>
        UPDATE <#if tableConfig.getSchema()??><#if context.getUseMark()>
        "</#if>${tableConfig.getSchema()}<#if context.getUseMark()>"</#if>.</#if><#if context.getUseMark()>
        "</#if>${table.getTableName()}<#if context.getUseMark()>"</#if> SET
        <set>
        <#list columns as column>
            <if test="record.${column.getJavaProperty()} != null">
            <#if context.getUseMark()>"</#if>${column.getColumnName()}<#if context.getUseMark()>"</#if> = ${r'#'}{record.${column.getJavaProperty()},jdbcType=${column.getJdbcType()}},
            </if>
        </#list>
        </set>
        <include refid="whereSql"/>
    </update>

    <delete id="delete" parameterType="map"<#if tableConfig.getCache()?? && tableConfig.getCache().deleteFlushCache>
            flushCache="true"</#if>>
        DELETE FROM <#if tableConfig.getSchema()??><#if context.getUseMark()>
        "</#if>${tableConfig.getSchema()}<#if context.getUseMark()>"</#if>.</#if><#if context.getUseMark()>
        "</#if>${table.getTableName()}<#if context.getUseMark()>"</#if>
        <include refid="whereSql"/>
    </delete>
    <#if primaryKeyColumns??><#if primaryKeyColumns?size gt 0>

    <sql id="idWhere">
        WHERE<#list primaryKeyColumns as keyColumn><#if keyColumn_index gt 0> AND</#if> ${keyColumn.getColumnName()} <if test="ids.length > ${keyColumn_index} and ids[${keyColumn_index}] != null">= ${r'#'}{ids[${keyColumn_index}]}</if><if test="ids == null or ids.length == ${keyColumn_index} or ids[${keyColumn_index}] == null">IS NULL</if></#list>
    </sql>

    <select id="selectOneByID" resultMap="baseResultMap" <#if tableConfig.getCache()?? && tableConfig.getCache().getSelectUseCache()>
            useCache="true"</#if>>
        SELECT <include refid="allFields"/>
        FROM <#if tableConfig.getSchema()??><#if context.getUseMark()>
        "</#if>${tableConfig.getSchema()}<#if context.getUseMark()>"</#if>.</#if><#if context.getUseMark()>
        "</#if>${table.getTableName()}<#if context.getUseMark()>"</#if>
        <include refid="idWhere"/>
    </select>
    
    <delete id="deleteOneByID"<#if tableConfig.getCache()?? && tableConfig.getCache().deleteFlushCache>
            flushCache="true"</#if>>
        DELETE FROM <#if tableConfig.getSchema()??><#if context.getUseMark()>
        "</#if>${tableConfig.getSchema()}<#if context.getUseMark()>"</#if>.</#if><#if context.getUseMark()>
        "</#if>${table.getTableName()}<#if context.getUseMark()>"</#if>
        <include refid="idWhere"/>
    </delete>
    
    <update id="updateOneSelectiveByID"<#if tableConfig.getCache()?? && tableConfig.getCache().deleteFlushCache>
            flushCache="true"</#if>>
        UPDATE <#if tableConfig.getSchema()??><#if context.getUseMark()>
        "</#if>${tableConfig.getSchema()}<#if context.getUseMark()>"</#if>.</#if><#if context.getUseMark()>
        "</#if>${table.getTableName()}<#if context.getUseMark()>"</#if> SET
        <#list columns as column>
            <if test="record.${column.getJavaProperty()} != null">
            <#if column_index gt 0>, </#if><#if context.getUseMark()>"</#if>${column.getColumnName()}<#if context.getUseMark()>"</#if> = ${r'#'}{record.${column.getJavaProperty()},jdbcType=${column.getJdbcType()}}
            </if>
        </#list>
        WHERE<#list primaryKeyColumns as keyColumn><#if keyColumn_index gt 0> AND</#if> ${keyColumn.getColumnName()} <if test="record.${keyColumn.getJavaProperty()} != null">= ${r'#'}{record.${keyColumn.getJavaProperty()},jdbcType=${keyColumn.getJdbcType()}}</if><if test="record.${keyColumn.getJavaProperty()} == null"> IS NULL</if> </#list>
    </update>

    <update id="updateOneByID"<#if tableConfig.getCache()?? && tableConfig.getCache().deleteFlushCache>
            flushCache="true"</#if>>
        UPDATE <#if tableConfig.getSchema()??><#if context.getUseMark()>
        "</#if>${tableConfig.getSchema()}<#if context.getUseMark()>"</#if>.</#if><#if context.getUseMark()>
        "</#if>${table.getTableName()}<#if context.getUseMark()>"</#if> SET
        <#list columns as column>
          <#if column_index gt 0>,</#if><#if context.getUseMark()>
            "</#if>${column.getColumnName()}<#if context.getUseMark()>"</#if> = <if test="record.${column.getJavaProperty()} != null">${r'#'}{record.${column.getJavaProperty()},jdbcType=${column.getJdbcType()}}</if><if test="record.${column.getJavaProperty()} == null">null</if>
        </#list>
        WHERE<#list primaryKeyColumns as keyColumn><#if keyColumn_index gt 0> AND</#if> ${keyColumn.getColumnName()} <if test="record.${keyColumn.getJavaProperty()} != null">= ${r'#'}{record.${keyColumn.getJavaProperty()},jdbcType=${keyColumn.getJdbcType()}}</if><if test="record.${keyColumn.getJavaProperty()} == null"> IS NULL</if> </#list>
    </update>
    </#if></#if>
</mapper>