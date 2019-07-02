package com.touchkiss.mybatis.sqlbuild.query;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import com.touchkiss.mybatis.sqlbuild.condition.SingleCondition;
import com.touchkiss.mybatis.sqlbuild.keyword.CompareOperator;


public class QColumn<TABLE, TYPE> {
    private QTable table;
    private String columnName;

    public QColumn(QTable<TABLE> table, String columnName) {
        this.table = table;
        this.columnName = columnName;
    }

    public QTable getTable() {
        return this.table;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public SingleCondition toEqCondition(TYPE... value) {
        return new SingleCondition(this, CompareOperator.EQUAL, value);
    }
}
