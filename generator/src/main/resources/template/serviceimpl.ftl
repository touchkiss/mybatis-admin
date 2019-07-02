package ${implPackageName};

<#if entityPackage??>import ${entityPackage};</#if>
<#if packageName!=implPackageName>
import ${packageName}.${tableConfig.getEntityName()}Service;
import ${daoPackageName}.${tableConfig.getEntityName()}AutoDao;
</#if>
import com.touchkiss.mybatis.sqlbuild.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
<#list table.imports as im>import ${im};</#list>

/**
 * ${table.getRemarks()}.service业务层实现
 *
 * @author Touchkiss
 **/
@Service
public class ${tableConfig.getEntityName()}ServiceImpl extends BaseServiceImpl<${tableConfig.getEntityName()}> implements ${tableConfig.getEntityName()}Service {
<#if primaryKeyColumns??><#if primaryKeyColumns?size gt 0>
    @Autowired
    private ${tableConfig.getEntityName()}AutoDao dao;
  <#list primaryKeyColumns as keyColumn>
    <#if keyColumn_index gt 0>

    /**
     * 根据主键 查询
     */
    @Override
    public List<${tableConfig.getEntityName()}> selectBy${keyColumn.getJavaPropertyFirstUpper()}(${keyColumn.getJavaType()} ${keyColumn.getJavaProperty()}){
        return dao.selectBy${keyColumn.getJavaPropertyFirstUpper()}(${keyColumn.getJavaProperty()});
    }

    /**
     * 根据主键 更新
     */
    @Override
    public int updateBy${keyColumn.getJavaPropertyFirstUpper()}(${tableConfig.getEntityName()} bean){
        return dao.updateBy${keyColumn.getJavaPropertyFirstUpper()}(bean);
    }

    /**
     * 根据主键 更新指定字段
     */
    @Override
    public int updateSelectiveBy${keyColumn.getJavaPropertyFirstUpper()}(${tableConfig.getEntityName()} bean){
        return dao.updateSelectiveBy${keyColumn.getJavaPropertyFirstUpper()}(bean);
    }

    /**
     * 根据主键 删除
     */
    @Override
    public int deleteBy${keyColumn.getJavaPropertyFirstUpper()}(${keyColumn.getJavaType()} ${keyColumn.getJavaProperty()}){
        return dao.deleteBy${keyColumn.getJavaPropertyFirstUpper()}(${keyColumn.getJavaProperty()});
    }
    </#if>
  </#list>
  <#if primaryKeyColumns?size gt 1>

    /**
     * 根据主键 查询
     */
    @Override
    public List<${tableConfig.getEntityName()}> selectBy<#list primaryKeyColumns as keyColumn><#if keyColumn_index gt 0>And</#if>${keyColumn.getJavaPropertyFirstUpper()}</#list>(<#list primaryKeyColumns as keyColumn><#if keyColumn_index gt 0>, </#if>${keyColumn.getJavaType()} ${keyColumn.getJavaProperty()}</#list>){
        return dao.selectBy<#list primaryKeyColumns as keyColumn><#if keyColumn_index gt 0>And</#if>${keyColumn.getJavaPropertyFirstUpper()}</#list>(<#list primaryKeyColumns as keyColumn><#if keyColumn_index gt 0>, </#if>${keyColumn.getJavaProperty()}</#list>);
    }

    /**
     * 根据主键 更新
     */
    @Override
    public int updateBy<#list primaryKeyColumns as keyColumn><#if keyColumn_index gt 0>And</#if>${keyColumn.getJavaPropertyFirstUpper()}</#list>(${tableConfig.getEntityName()} bean){
        return dao.updateBy<#list primaryKeyColumns as keyColumn><#if keyColumn_index gt 0>And</#if>${keyColumn.getJavaPropertyFirstUpper()}</#list>(bean);
    }

    /**
     * 根据主键 更新指定字段
     */
    @Override
    public int updateSelectiveBy<#list primaryKeyColumns as keyColumn><#if keyColumn_index gt 0>And</#if>${keyColumn.getJavaPropertyFirstUpper()}</#list>(${tableConfig.getEntityName()} bean){
        return dao.updateSelectiveBy<#list primaryKeyColumns as keyColumn><#if keyColumn_index gt 0>And</#if>${keyColumn.getJavaPropertyFirstUpper()}</#list>(bean);
    }

    /**
     * 根据主键 删除
     */
    @Override
    public int deleteBy<#list primaryKeyColumns as keyColumn><#if keyColumn_index gt 0>And</#if>${keyColumn.getJavaPropertyFirstUpper()}</#list>(<#list primaryKeyColumns as keyColumn><#if keyColumn_index gt 0>, </#if>${keyColumn.getJavaType()} ${keyColumn.getJavaProperty()}</#list>){
        return dao.deleteBy<#list primaryKeyColumns as keyColumn><#if keyColumn_index gt 0>And</#if>${keyColumn.getJavaPropertyFirstUpper()}</#list>(<#list primaryKeyColumns as keyColumn><#if keyColumn_index gt 0>, </#if>${keyColumn.getJavaProperty()}</#list>);
    }
  </#if>
  </#if>
  </#if>
}