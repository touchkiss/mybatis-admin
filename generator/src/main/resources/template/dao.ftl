package ${packageName};

import com.touchkiss.mybatis.sqlbuild.Handle;
import com.touchkiss.mybatis.sqlbuild.condition.ManyCondition;
import com.touchkiss.mybatis.sqlbuild.dao.ReadBaseDao;
import com.touchkiss.mybatis.sqlbuild.dao.WriteBaseDao;
import com.touchkiss.mybatis.sqlbuild.query.QColumn;
import com.touchkiss.mybatis.sqlbuild.query.QTable;
<#if entityPackage??>import ${entityPackage};</#if>
<#if primaryKeyColumns?? && primaryKeyColumns?size gt 0>import java.util.List;</#if>
<#list table.imports as im>import ${im};
</#list>

/**
 * ${table.getRemarks()}.dao层接口
 *
 * @author Touchkiss
 **/
public interface ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao extends ReadBaseDao<${tableConfig.getEntityName()}>, WriteBaseDao<${tableConfig.getEntityName()}> {
    QTable<${tableConfig.getEntityName()}> TABLE = new QTable<>(<#if tableConfig.getSchema()??>"${tableConfig.getSchema()}", </#if>"${table.getTableName()}");   //${table.getRemarks()}
    //region filds
<#list columns as column>
    QColumn<${tableConfig.getEntityName()}, ${column.getJavaType()}> ${column.getJavaProperty()} = new QColumn<>(TABLE, "${column.getColumnName()}");    //${column.getRemarks()}
</#list>
    //所有字段
    QColumn<${tableConfig.getEntityName()}, Object>[] ALL_FIELDS = new QColumn[]{<#list columns as column><#if column_index != 0> ,</#if> ${column.getJavaProperty()}</#list>};
    //endregion

    //查询处理 handle
    Handle<${tableConfig.getEntityName()}, ManyCondition> SELECT_WHERE_HANDLE = (bean) -> {
        ManyCondition conditions = new ManyCondition();
<#list columns as column>
        if (bean.get${column.getJavaPropertyFirstUpper()}() != null) {
            conditions.add(${column.getJavaProperty()}.toEqCondition(bean.get${column.getJavaPropertyFirstUpper()}()));
        }
</#list>
        return conditions;
    };

    <#if primaryKeyColumns??><#if primaryKeyColumns?size gt 1>
    <#list primaryKeyColumns as keyColumn>
    <#if keyColumn_index gt 0>

    /**
    * 根据主键 查询
    */
    List<${tableConfig.getEntityName()}> selectBy${keyColumn.getJavaPropertyFirstUpper()}(${keyColumn.getJavaType()} ${keyColumn.getJavaProperty()});

    /**
    * 根据主键 更新
    */
    int updateBy${keyColumn.getJavaPropertyFirstUpper()}(${tableConfig.getEntityName()} bean);

    /**
    * 根据主键 更新指定字段
    */
    int updateSelectiveBy${keyColumn.getJavaPropertyFirstUpper()}(${tableConfig.getEntityName()} bean);

    /**
    * 根据主键 删除
    */
    int deleteBy${keyColumn.getJavaPropertyFirstUpper()}(<#if keyColumn_index == 0>String<#elseif keyColumn_index gt 0>${keyColumn.getJavaType()}</#if> ${keyColumn.getJavaProperty()});
    </#if>
    </#list>

    /**
    * 根据主键 查询
    */
    List<${tableConfig.getEntityName()}> selectBy<#list primaryKeyColumns as keyColumn><#if keyColumn_index gt 0>And</#if>${keyColumn.getJavaPropertyFirstUpper()}</#list>(<#list primaryKeyColumns as keyColumn><#if keyColumn_index gt 0>, </#if>${keyColumn.getJavaType()} ${keyColumn.getJavaProperty()}</#list>);

    /**
    * 根据主键 更新
    */
    int updateBy<#list primaryKeyColumns as keyColumn><#if keyColumn_index gt 0>And</#if>${keyColumn.getJavaPropertyFirstUpper()}</#list>(${tableConfig.getEntityName()} bean);

    /**
    * 根据主键 更新指定字段
    */
    int updateSelectiveBy<#list primaryKeyColumns as keyColumn><#if keyColumn_index gt 0>And</#if>${keyColumn.getJavaPropertyFirstUpper()}</#list>(${tableConfig.getEntityName()} bean);

    /**
    * 根据主键 删除
    */
    int deleteBy<#list primaryKeyColumns as keyColumn><#if keyColumn_index gt 0>And</#if>${keyColumn.getJavaPropertyFirstUpper()}</#list>(<#list primaryKeyColumns as keyColumn><#if keyColumn_index gt 0>, </#if>${keyColumn.getJavaType()} ${keyColumn.getJavaProperty()}</#list>);
    </#if>
    </#if>
}