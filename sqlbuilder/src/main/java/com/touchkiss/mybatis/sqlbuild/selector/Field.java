package com.touchkiss.mybatis.sqlbuild.selector;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

public class Field implements Serializable {
    private Table table;
    private String field;
    private String tableName;

    public Field(String field) {
        this((Table)null, field);
    }

    public Field(Table table, String field) {
        this.setTable(table);
        this.setField(field);
    }

    public Table getTable() {
        return this.table;
    }

    public void setTable(Table table) {
        this.table = table;
        if (this.table != null) {
            this.tableName = this.table.getTable();
        }

    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public boolean getIsFunction() {
        return StringUtils.isNotBlank(this.field) && (this.field.indexOf("(") != -1 || this.field.indexOf(")") != -1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Field field1;
            label41: {
                field1 = (Field)o;
                if (this.table != null) {
                    if (this.table.equals(field1.table)) {
                        break label41;
                    }
                } else if (field1.table == null) {
                    break label41;
                }

                return false;
            }

            if (this.field != null) {
                if (this.field.equals(field1.field)) {
                    return this.tableName != null ? this.tableName.equals(field1.tableName) : field1.tableName == null;
                }
            } else if (field1.field == null) {
                return this.tableName != null ? this.tableName.equals(field1.tableName) : field1.tableName == null;
            }

            return false;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = this.table != null ? this.table.hashCode() : 0;
        result = 31 * result + (this.field != null ? this.field.hashCode() : 0);
        result = 31 * result + (this.tableName != null ? this.tableName.hashCode() : 0);
        return result;
    }
}
