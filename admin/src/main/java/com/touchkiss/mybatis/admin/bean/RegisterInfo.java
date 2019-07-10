package com.touchkiss.mybatis.admin.bean;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Touchkiss
 * @create: 2019-06-22 22:15
 */
@Data
public class RegisterInfo {
    private String group;
    private String groupShowName ;
    private String groupIconClass = null;
    private String name;
    private String showName;
    private String nameIconClass = null;
    private Class beanClazz;
    private Class serviceClazz;
    private BeanInfo beanInfo;
    private Map<String,ForeignKeyInfo> foreignKeyInfoMap;

    public RegisterInfo(String group, String name, Class beanClazz, Class serviceClazz) {
        this.group = group;
        this.groupShowName=group;
        this.name = name;
        this.showName = name;
        this.beanClazz = beanClazz;
        this.serviceClazz = serviceClazz;
        this.beanInfo = new BeanInfo(beanClazz);
        this.foreignKeyInfoMap=new HashMap<>();
    }

    public RegisterInfo(String group, String name, String showName, Class beanClazz, Class serviceClazz) {
        this.group = group;
        this.groupShowName=group;
        this.name = name;
        this.showName = showName;
        this.beanClazz = beanClazz;
        this.serviceClazz = serviceClazz;
        this.beanInfo = new BeanInfo(beanClazz);
        this.foreignKeyInfoMap=new HashMap<>();
    }

    public RegisterInfo(String group, String groupShowName, String name, String showName, Class beanClazz, Class serviceClazz) {
        this.group = group;
        this.groupShowName = groupShowName;
        this.name = name;
        this.showName = showName;
        this.beanClazz = beanClazz;
        this.serviceClazz = serviceClazz;
        this.beanInfo = new BeanInfo(beanClazz);
        this.foreignKeyInfoMap=new HashMap<>();
    }

    public RegisterInfo(String group, String groupShowName, String name, String showName, Class beanClazz, Class serviceClazz, BeanInfo beanInfo) {
        this.group = group;
        this.groupShowName = groupShowName;
        this.name = name;
        this.showName = showName;
        this.beanClazz = beanClazz;
        this.serviceClazz = serviceClazz;
        this.beanInfo = beanInfo;
        this.foreignKeyInfoMap=new HashMap<>();
    }

    public RegisterInfo(String group, String groupShowName, String groupIconClass, String name, String showName, String nameIconClass, Class beanClazz, Class serviceClazz, BeanInfo beanInfo) {
        this.group = group;
        this.groupShowName = groupShowName;
        this.groupIconClass = groupIconClass;
        this.name = name;
        this.showName = showName;
        this.nameIconClass = nameIconClass;
        this.beanClazz = beanClazz;
        this.serviceClazz = serviceClazz;
        this.beanInfo = beanInfo;
        this.foreignKeyInfoMap=new HashMap<>();
    }
}
