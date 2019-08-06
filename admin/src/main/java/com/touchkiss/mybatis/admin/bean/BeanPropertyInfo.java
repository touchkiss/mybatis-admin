package com.touchkiss.mybatis.admin.bean;

/**
 * @Author Touchkiss
 * @create: 2019-06-25 15:03
 */
public class BeanPropertyInfo {
    private Object value;
    private String propertyName;
    private String propertyShowName;
    private String propertyType;
    private boolean required;
    private boolean order;

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

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
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
}
