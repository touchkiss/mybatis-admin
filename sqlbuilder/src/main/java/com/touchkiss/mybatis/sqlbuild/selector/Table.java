package com.touchkiss.mybatis.sqlbuild.selector;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

public class Table {
    private String table;
    private String aliasTable;
    private String schema;

    public Table() {
    }

    public String getTable() {
        return this.table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getAliasTable() {
        return StringUtils.isNotBlank(this.aliasTable) ? this.aliasTable : null;
    }

    public void setAliasTable(String aliasTable) {
        this.aliasTable = aliasTable;
    }

    public String getSchema() {
        return StringUtils.isNotBlank(this.schema) ? this.schema : null;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Table table1;
            label41: {
                table1 = (Table)o;
                if (this.table != null) {
                    if (this.table.equals(table1.table)) {
                        break label41;
                    }
                } else if (table1.table == null) {
                    break label41;
                }

                return false;
            }

            if (this.aliasTable != null) {
                if (this.aliasTable.equals(table1.aliasTable)) {
                    return this.schema != null ? this.schema.equals(table1.schema) : table1.schema == null;
                }
            } else if (table1.aliasTable == null) {
                return this.schema != null ? this.schema.equals(table1.schema) : table1.schema == null;
            }

            return false;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = this.table != null ? this.table.hashCode() : 0;
        result = 31 * result + (this.aliasTable != null ? this.aliasTable.hashCode() : 0);
        result = 31 * result + (this.schema != null ? this.schema.hashCode() : 0);
        return result;
    }
}
