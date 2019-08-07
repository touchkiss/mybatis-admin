<#setting classic_compatible=true />
<#setting number_format="0" />
package ${tableConfig.getPackageName()};

import com.touchkiss.mybatis.admin.annotation.AdminBean;
import com.touchkiss.mybatis.admin.annotation.AdminColumn;

<#list table.getImports() as im>import ${im};
</#list>

/**
 * ${table.getRemarks()}.Bean对象
 *
 * @author Touchkiss
 */
@AdminBean("${table.getRemarks()}")
public class ${tableConfig.getEntityName()}<#if tableConfig.getSupportSerialize()> implements java.io.Serializable</#if> {
<#list columns as column>
    /**
     * ${column.getRemarks()}
     */
    @AdminColumn(columnName = "${column.getColumnName()}", isPrimaryKey = <#if column.getIsPrimaryKey()==true>true<#else>false</#if>, remarks = "${column.getRemarks()}", length = ${column.getColumnSize()}, jdbctype = "${column.getJdbcType()}", nullable = <#if column.getIsNullable()=='YES'>true<#else>false</#if>, autoIncrement = <#if column.getIsAutoincrement()=='YES'>true<#else>false</#if>)
    private ${column.getJavaType()} ${column.getJavaProperty()};
</#list>
<#list tableConfig.getCustomProperties() as customProperty>
    /**
     * ${customProperty.getRemarks()} 自定义属性
     */
    private ${customProperty.getJavaType().getTypeName()} ${customProperty.getJavaName()};
</#list>
<#list columns as column>

    /**
     * ${column.getRemarks()}
     */
    public void set${column.getJavaPropertyFirstUpper()}(${column.getJavaType()} ${column.getJavaProperty()}) {
        this.${column.javaProperty} = ${column.javaProperty};
    }

    /**
     * ${column.getRemarks()}
     */
    public ${column.getJavaType()} get${column.getJavaPropertyFirstUpper()}() {
        return this.${column.getJavaProperty()};
    }
</#list>
<#list tableConfig.getCustomProperties() as customProperty>

    /**
     * ${customProperty.getRemarks()} 自定义属性
     */
    public void set${customProperty.getPropertyUpper()}(${customProperty.getJavaType().getTypeName()} ${customProperty.getJavaName()}) {
        this.${customProperty.getJavaName()} = ${customProperty.getJavaName()};
    }

    /**
     * ${customProperty.getRemarks()} 自定义属性
     */
    public ${customProperty.getJavaType().getTypeName()} get${customProperty.getPropertyUpper()}() {
        return this.${customProperty.getJavaName()};
    }
</#list>
}