package com.touchkiss.mybatis.generator.internal.type;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import java.math.BigDecimal;

public class FullyQualifiedJavaType {
    private String fullTypeSpecification;
    private static final String LANG_HOME = "java.lang";
    private static final String[] BASIC_DATA_TYPE = new String[]{"int", "double", "byte", "short", "long", "float", "char", "boolean"};
    public static final FullyQualifiedJavaType objectJavaType = new FullyQualifiedJavaType("java.lang.Object");
    public static final FullyQualifiedJavaType DoubleJavaType = new FullyQualifiedJavaType(Double.class.getName());
    public static final FullyQualifiedJavaType BigDecimalJavaType = new FullyQualifiedJavaType(BigDecimal.class.getName());
    public static final FullyQualifiedJavaType LongJavaType = new FullyQualifiedJavaType(Long.class.getName());
    public static final FullyQualifiedJavaType IntegerJavaType = new FullyQualifiedJavaType(Integer.class.getName());
    public static final FullyQualifiedJavaType ShortJavaType = new FullyQualifiedJavaType(Short.class.getName());
    public static final FullyQualifiedJavaType BooleanJavaType = new FullyQualifiedJavaType(Boolean.class.getName());

    public FullyQualifiedJavaType(String fullTypeSpecification) {
        this.fullTypeSpecification = fullTypeSpecification;
    }

    public String getTypeName() {
        int index = this.fullTypeSpecification.lastIndexOf(".");
        return index == -1 ? this.fullTypeSpecification : this.fullTypeSpecification.substring(index + 1);
    }

    public String getFullTypeName() {
        return this.fullTypeSpecification;
    }

    public String getFullTypeSpecification() {
        return this.fullTypeSpecification;
    }

    private String getTypePackageName() {
        int index = this.fullTypeSpecification.lastIndexOf(".");
        return index != -1 && !this.fullTypeSpecification.contains("java.lang") ? this.fullTypeSpecification.substring(0, index) : "";
    }

    public boolean equalsPackage(String packageName) {
        return this.getTypePackageName().equals(packageName);
    }

    public String getImport() {
        return !this.fullTypeSpecification.contains("java.lang") && !this.isBasicDataType() ? this.fullTypeSpecification : "";
    }

    private boolean isBasicDataType() {
        for(int i = 0; i < BASIC_DATA_TYPE.length; ++i) {
            if (this.fullTypeSpecification.equalsIgnoreCase(BASIC_DATA_TYPE[i]) || this.fullTypeSpecification.equalsIgnoreCase(BASIC_DATA_TYPE[i] + "[]")) {
                return true;
            }
        }

        return false;
    }
}
