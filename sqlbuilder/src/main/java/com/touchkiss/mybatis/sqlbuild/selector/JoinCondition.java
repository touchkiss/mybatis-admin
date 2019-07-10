package com.touchkiss.mybatis.sqlbuild.selector;

import com.touchkiss.mybatis.sqlbuild.keyword.CompareOperator;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

public class JoinCondition {
    private String relateTable;
    private String relateTableAlias;
    private String relateField;
    private Object relateValue;
    private String joinField;
    private final boolean isRelateField;
    private CompareOperator operator;

    public JoinCondition(String joinField, Object relateValue) {
        this.operator = CompareOperator.EQUAL;
        this.joinField = joinField;
        this.relateValue = relateValue;
        this.isRelateField = false;
    }

    protected void setOperator(CompareOperator operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return this.operator.toString();
    }

    public JoinCondition(String joinField, String relateTable, String relateField) {
        this.operator = CompareOperator.EQUAL;
        this.relateTable = relateTable;
        this.relateField = relateField;
        this.joinField = joinField;
        this.isRelateField = true;
    }

    public String getRelateTable() {
        return this.relateTable;
    }

    public String getRelateField() {
        return this.relateField;
    }

    public String getJoinField() {
        return this.joinField;
    }

    public Object getRelateValue() {
        return this.relateValue;
    }

    public String getRelateTableAlias() {
        return this.relateTableAlias;
    }

    public boolean getIsRelateField() {
        return this.isRelateField;
    }

    protected void setRelateTableAlias(String relateTableAlias) {
        this.relateTableAlias = relateTableAlias;
    }
}
