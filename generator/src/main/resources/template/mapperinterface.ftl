package ${packageName};

import com.touchkiss.mybatis.sqlbuild.mapper.BaseMapper;
<#if entityPackage??>import ${entityPackage};</#if>
<#if annotationPackages??><#list annotationPackages as packageName>import ${packageName};
</#list></#if>

/**
 * ${table.getRemarks()}.mapper层接口
 *
 * @author Touchkiss
 */
<#if annotations??><#list annotations as annoName>@${annoName}
</#list></#if>
public interface ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Mapper extends BaseMapper<${tableConfig.getEntityName()}> {
}