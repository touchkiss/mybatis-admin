package com.touchkiss.mybatis.admin.bean;

import lombok.Data;

/**
 * @Author Touchkiss
 * @create: 2019-06-25 15:03
 */
@Data
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
}
