package com.touchkiss.mybatis.admin.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Touchkiss
 * @create: 2019-06-22 22:15
 */
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroupShowName() {
        return groupShowName;
    }

    public void setGroupShowName(String groupShowName) {
        this.groupShowName = groupShowName;
    }

    public String getGroupIconClass() {
        return groupIconClass;
    }

    public void setGroupIconClass(String groupIconClass) {
        this.groupIconClass = groupIconClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getNameIconClass() {
        return nameIconClass;
    }

    public void setNameIconClass(String nameIconClass) {
        this.nameIconClass = nameIconClass;
    }

    public Class getBeanClazz() {
        return beanClazz;
    }

    public void setBeanClazz(Class beanClazz) {
        this.beanClazz = beanClazz;
    }

    public Class getServiceClazz() {
        return serviceClazz;
    }

    public void setServiceClazz(Class serviceClazz) {
        this.serviceClazz = serviceClazz;
    }

    public BeanInfo getBeanInfo() {
        return beanInfo;
    }

    public void setBeanInfo(BeanInfo beanInfo) {
        this.beanInfo = beanInfo;
    }

    public Map<String, ForeignKeyInfo> getForeignKeyInfoMap() {
        return foreignKeyInfoMap;
    }

    public void setForeignKeyInfoMap(Map<String, ForeignKeyInfo> foreignKeyInfoMap) {
        this.foreignKeyInfoMap = foreignKeyInfoMap;
    }
}
