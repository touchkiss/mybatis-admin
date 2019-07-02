package com.touchkiss.mybatis.generator.model;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import com.touchkiss.mybatis.generator.internal.type.FullyQualifiedJavaType;
import com.touchkiss.mybatis.generator.utils.JavaUtils;
import org.apache.commons.lang3.StringUtils;

public class CustomProperty {
    private String javaName;
    private FullyQualifiedJavaType javaType;
    private String remarks;

    public CustomProperty() {
    }

    public String getJavaName() {
        return JavaUtils.toCamelCase(this.javaName, false);
    }

    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    public FullyQualifiedJavaType getJavaType() {
        return this.javaType == null ? FullyQualifiedJavaType.objectJavaType : this.javaType;
    }

    public String getRemarks() {
        return StringUtils.isBlank(this.remarks) ? this.javaName : this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPropertyUpper() {
        String s1 = JavaUtils.toCamelCase(this.javaName, true);
        return s1;
    }

    public void setJavaType(FullyQualifiedJavaType javaType) {
        this.javaType = javaType;
    }
}
