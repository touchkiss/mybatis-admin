package com.touchkiss.mybatis.sqlbuild.query;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

public class QTable<TABLE> {
    private final String tableName;
    private final String schema;

    public QTable(String schema, String tableName) {
        this.schema = schema;
        this.tableName = tableName;
    }

    public QTable(String tableName) {
        this((String)null, tableName);
    }

    public String getTableName() {
        return this.tableName;
    }

    public String getSchema() {
        return this.schema;
    }
}
