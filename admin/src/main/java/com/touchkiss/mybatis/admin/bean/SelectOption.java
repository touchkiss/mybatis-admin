package com.touchkiss.mybatis.admin.bean;

import lombok.Data;

/**
 * @Author Touchkiss
 * @create: 2019-07-05 17:24
 */
@Data
public class SelectOption {
    private String value;
    private String text;

    public SelectOption(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public SelectOption() {
    }
}
