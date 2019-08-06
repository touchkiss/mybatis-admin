<#setting classic_compatible=true />
package ${tableConfig.getPackageName()};

<#list table.getImports() as im>import ${im};
</#list>

/**
 * ${table.getRemarks()}.Bean对象
 *
 * @author Touchkiss
 */
public class ${tableConfig.getEntityName()}<#if tableConfig.getSupportSerialize()> implements java.io.Serializable</#if> {
<#list columns as column>
    /**
     * ${column.getRemarks()}
     */
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