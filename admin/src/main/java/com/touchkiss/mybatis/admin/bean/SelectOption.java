package com.touchkiss.mybatis.admin.bean;

/**
 * @Author Touchkiss
 * @create: 2019-07-05 17:24
 */
public class SelectOption {
    private String value;
    private String text;

    public SelectOption(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public SelectOption() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
