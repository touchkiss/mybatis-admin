package com.touchkiss.mybatis.generator.model;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

public class SelectKey {
    private String columnName;
    private String order = "BEFORE";
    private String statementType = "PREPARED";
    private String statement;
    private Column column;

    public SelectKey(String columnName, String statement) {
        this.columnName = columnName;
        this.statement = statement;
    }

    public SelectKey(String columnName, String statementType, String order, String statement) {
        this.columnName = columnName;
        this.statementType = statementType;
        this.order = order;
        this.statement = statement;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public String getResultType() {
        return this.column != null ? this.column.getFullJavaType() : null;
    }

    public String getJavaProperty() {
        return this.column.getJavaProperty();
    }

    public String getOrder() {
        return this.order;
    }

    public String getStatementType() {
        return this.statementType;
    }

    public String getStatement() {
        return this.statement;
    }

    public boolean getExistColumn() {
        return this.column != null;
    }
}
