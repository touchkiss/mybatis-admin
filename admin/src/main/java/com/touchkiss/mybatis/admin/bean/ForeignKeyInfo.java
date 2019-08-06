package com.touchkiss.mybatis.admin.bean;

import com.touchkiss.mybatis.sqlbuild.selector.Selector;

import java.util.List;

/**
 * @Author Touchkiss
 * @create: 2019-07-05 12:38
 */
public class ForeignKeyInfo {
    /**
     * 外键表名
     */
    private String name;
    /**
     * 键-列名
     */
    private String keyName;
    /**
     * 值-列名
     */
    private String valueName;
    /**
     * 是否可编辑
     */
    private Boolean editable;
    /**
     * 传入selector
     */
    private Selector selector;
    /**
     * 传入option列表
     */
    private List<SelectOption> options;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    public List<SelectOption> getOptions() {
        return options;
    }

    public void setOptions(List<SelectOption> options) {
        this.options = options;
    }
}
