package ${packageName};

import com.touchkiss.mybatis.sqlbuild.service.BaseService;
import java.util.List;
<#if entityPackage??>import ${entityPackage};</#if>
<#list table.imports as im>import ${im};</#list>

/**
 * ${table.getRemarks()}.service业务层接口
 *
 * @author Touchkiss
 */
public interface ${tableConfig.getEntityName()}Service extends BaseService<${tableConfig.getEntityName()}>{
}