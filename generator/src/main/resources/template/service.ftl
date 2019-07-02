package ${packageName};

import com.touchkiss.mybatis.sqlbuild.service.BaseService;
import java.util.List;
<#if entityPackage??>import ${entityPackage};</#if>
<#list table.imports as im>import ${im};</#list>

/**
 * ${table.getRemarks()}.service业务层接口
 *
 * @author Touchkiss
 **/
public interface ${tableConfig.getEntityName()}Service extends BaseService<${tableConfig.getEntityName()}>{
<#if primaryKeyColumns??><#if primaryKeyColumns?size gt 0><#list primaryKeyColumns as keyColumn>

  <#if keyColumn_index gt 0>
    /**
     * 根据主键 查询
     */
    List<${tableConfig.getEntityName()}> selectBy<#if keyColumn_index == 0>Id<#elseif keyColumn_index gt 0>${keyColumn.getJavaPropertyFirstUpper()}</#if>(${keyColumn.getJavaType()} ${keyColumn.getJavaProperty()});

    /**
      * 根据主键 更新
      */
    int updateBy${keyColumn.getJavaPropertyFirstUpper()}(${tableConfig.getEntityName()} bean);

    /**
      * 根据主键 更新指定字段
      */
    int updateSelectiveBy<#if keyColumn_index == 0>Id<#elseif keyColumn_index gt 0>${keyColumn.getJavaPropertyFirstUpper()}</#if>(${tableConfig.getEntityName()} bean);

    /**
       * 根据主键 删除
       */
    int deleteBy${keyColumn.getJavaPropertyFirstUpper()}(${keyColumn.getJavaType()} ${keyColumn.getJavaProperty()});
  </#if>
  </#list>
  <#if primaryKeyColumns?size gt 1>

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
  </#if>
}