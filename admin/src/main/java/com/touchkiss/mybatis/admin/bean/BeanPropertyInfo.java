package com.touchkiss.mybatis.admin.bean;

/**
 * @Author Touchkiss
 * @create: 2019-06-25 15:03
 */
public class BeanPropertyInfo {
    private String columnName;
    private String remarks;
    private int length;
    private String jdbctype;
    private String propertyName;
    private String propertyShowName;
    private String propertyType;
    private boolean required;
    private boolean order;
    private boolean isPrimaryKey;
    private boolean nullable;
    private boolean autoIncrement;

    public BeanPropertyInfo() {
    }

    public BeanPropertyInfo(String propertyName, String propertyType) {
        this.propertyName = propertyName;
        this.propertyShowName=propertyName;
        this.propertyType = propertyType;
        this.required=false;
        this.order = true;
    }

    public BeanPropertyInfo(String propertyName, String propertyShowName, String propertyType) {
        this.propertyName = propertyName;
        this.propertyShowName = propertyShowName;
        this.propertyType = propertyType;
        this.required=false;
        this.order = true;
    }

    public BeanPropertyInfo(String propertyName, String propertyShowName, String propertyType, boolean order) {
        this.propertyName = propertyName;
        this.propertyShowName = propertyShowName;
        this.propertyType = propertyType;
        this.order = order;
        this.required=false;
    }

    public BeanPropertyInfo(String propertyName, String propertyShowName, String propertyType,boolean required, boolean order) {
        this.propertyName = propertyName;
        this.propertyShowName = propertyShowName;
        this.propertyType = propertyType;
        this.required=required;
        this.order = order;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getJdbctype() {
        return jdbctype;
    }

    public void setJdbctype(String jdbctype) {
        this.jdbctype = jdbctype;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyShowName() {
        return propertyShowName;
    }

    public void setPropertyShowName(String propertyShowName) {
        this.propertyShowName = propertyShowName;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isOrder() {
        return order;
    }

    public void setOrder(boolean order) {
        this.order = order;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }
}
