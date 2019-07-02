package com.touchkiss.mybatis.sqlbuild.selector;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

public class GroupField extends Field {
    public GroupField(Table table, String field) {
        super(table, field);
    }

    public GroupField(String field) {
        super(field);
    }
}
