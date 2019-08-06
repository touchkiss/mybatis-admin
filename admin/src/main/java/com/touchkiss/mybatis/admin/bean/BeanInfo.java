package com.touchkiss.mybatis.admin.bean;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @Author Touchkiss
 * @create: 2019-06-22 22:37
 */
public class BeanInfo {
    private String idColumnName;
    private BeanPropertyInfo[] beanPropertyInfos;
    private Map<String, BeanPropertyInfo> beanPropertyInfoMap;
    private String[] showFields;
    private String[] filterFields;
    private String[] searchFields;
    private Set<String> filterFieldSet;
    private Set<String> searchFieldSet;

    public BeanInfo(String idColumnName, BeanPropertyInfo[] beanPropertyInfos, String[] showFields, String[] filterFields, String[] searchFields, Set<String> filterFieldSet, Set<String> searchFieldSet) {
        this.idColumnName = idColumnName;
        this.beanPropertyInfos = beanPropertyInfos;
        this.beanPropertyInfoMap = new HashMap<>();
        for (BeanPropertyInfo beanPropertyInfo : beanPropertyInfos) {
            this.beanPropertyInfoMap.put(beanPropertyInfo.getPropertyName(), beanPropertyInfo);
        }
        this.showFields = showFields;
        this.filterFields = filterFields;
        this.searchFields = searchFields;
        this.filterFieldSet = filterFieldSet;
        this.searchFieldSet = searchFieldSet;
    }

    public BeanInfo() {
    }

    public BeanInfo(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        int length = fields.length;
        this.beanPropertyInfos = new BeanPropertyInfo[length];
        this.beanPropertyInfoMap = new HashMap<>();
        this.showFields = new String[length];
        this.searchFields = new String[length];
        this.searchFieldSet = new HashSet<>(length * 2);
        this.filterFields = new String[length];
        this.filterFieldSet = new HashSet<>(length * 2);
        if (length > 0) {
            this.idColumnName = fields[0].getName();
            for (int i = 0; i < length; i++) {
                Field field = fields[i];
                String fieldName = field.getName();
                BeanPropertyInfo beanPropertyInfo = new BeanPropertyInfo(fieldName, field.getType().getName());
                this.beanPropertyInfoMap.put(fieldName, beanPropertyInfo);
                this.beanPropertyInfos[i] = beanPropertyInfo;
                this.showFields[i] = fieldName;
                this.searchFields[i] = fieldName;
                this.searchFieldSet.add(fieldName);
                this.filterFields[i] = fieldName;
                this.filterFieldSet.add(fieldName);
            }
        }
    }

    public void setBeanPropertyInfos(BeanPropertyInfo[] beanPropertyInfos) {
        this.beanPropertyInfos = beanPropertyInfos;
        if (this.beanPropertyInfoMap == null) {
            this.beanPropertyInfoMap = new HashMap<>();
        }
        this.beanPropertyInfoMap.clear();
        for (BeanPropertyInfo beanPropertyInfo : beanPropertyInfos) {
            this.beanPropertyInfoMap.put(beanPropertyInfo.getPropertyName(), beanPropertyInfo);
        }
    }

    public void setShowFields(String[] showFields) {
        this.showFields = showFields;
    }

    public void showAllFields() {
        this.showFields = new String[this.beanPropertyInfos.length];
        for (int i = 0; i < this.beanPropertyInfos.length; i++) {
            this.showFields[i] = this.beanPropertyInfos[i].getPropertyName();
        }
    }

    public void setFilterFields(String[] filterFields) {
        this.filterFields = filterFields;
        this.filterFieldSet.addAll(Arrays.asList(filterFields));
    }

    public void setSearchFields(String[] searchFields) {
        this.searchFields = searchFields;
        this.searchFieldSet.addAll(Arrays.asList(searchFields));
    }

    public String getIdColumnName() {
        return idColumnName;
    }

    public void setIdColumnName(String idColumnName) {
        this.idColumnName = idColumnName;
    }

    public BeanPropertyInfo[] getBeanPropertyInfos() {
        return beanPropertyInfos;
    }

    public Map<String, BeanPropertyInfo> getBeanPropertyInfoMap() {
        return beanPropertyInfoMap;
    }

    public void setBeanPropertyInfoMap(Map<String, BeanPropertyInfo> beanPropertyInfoMap) {
        this.beanPropertyInfoMap = beanPropertyInfoMap;
    }

    public String[] getShowFields() {
        return showFields;
    }

    public String[] getFilterFields() {
        return filterFields;
    }

    public String[] getSearchFields() {
        return searchFields;
    }

    public Set<String> getFilterFieldSet() {
        return filterFieldSet;
    }

    public void setFilterFieldSet(Set<String> filterFieldSet) {
        this.filterFieldSet = filterFieldSet;
    }

    public Set<String> getSearchFieldSet() {
        return searchFieldSet;
    }

    public void setSearchFieldSet(Set<String> searchFieldSet) {
        this.searchFieldSet = searchFieldSet;
    }
}
