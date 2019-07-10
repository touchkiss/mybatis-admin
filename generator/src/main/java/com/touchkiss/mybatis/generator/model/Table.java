package com.touchkiss.mybatis.generator.model;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import java.util.Arrays;

public class Table {
    private String tableName;
    private String tableType;
    private String remarks;
    private String[] imports;
    private String[] javaForeignTypeImports;
    private String[] foreignTypeImportsAll;

    public Table() {
    }

    public String getTableName() {
        return this.tableName;
    }

    public String getTableType() {
        return this.tableType;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public String[] getImports() {
        return this.imports;
    }

    public String[] getJavaForeignTypeImports() {
        return this.javaForeignTypeImports;
    }

    public String[] getForeignTypeImportsAll() {
        return this.foreignTypeImportsAll;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setImports(String[] imports) {
        this.imports = imports;
    }

    public void setJavaForeignTypeImports(String[] javaForeignTypeImports) {
        this.javaForeignTypeImports = javaForeignTypeImports;
    }

    public void setForeignTypeImportsAll(String[] foreignTypeImportsAll) {
        this.foreignTypeImportsAll = foreignTypeImportsAll;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Table)) {
            return false;
        } else {
            Table other = (Table)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Object this$tableName = this.getTableName();
                    Object other$tableName = other.getTableName();
                    if (this$tableName == null) {
                        if (other$tableName == null) {
                            break label59;
                        }
                    } else if (this$tableName.equals(other$tableName)) {
                        break label59;
                    }

                    return false;
                }

                Object this$tableType = this.getTableType();
                Object other$tableType = other.getTableType();
                if (this$tableType == null) {
                    if (other$tableType != null) {
                        return false;
                    }
                } else if (!this$tableType.equals(other$tableType)) {
                    return false;
                }

                Object this$remarks = this.getRemarks();
                Object other$remarks = other.getRemarks();
                if (this$remarks == null) {
                    if (other$remarks != null) {
                        return false;
                    }
                } else if (!this$remarks.equals(other$remarks)) {
                    return false;
                }

                if (!Arrays.deepEquals(this.getImports(), other.getImports())) {
                    return false;
                } else if (!Arrays.deepEquals(this.getJavaForeignTypeImports(), other.getJavaForeignTypeImports())) {
                    return false;
                } else if (!Arrays.deepEquals(this.getForeignTypeImportsAll(), other.getForeignTypeImportsAll())) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Table;
    }

    @Override
    public int hashCode() {
//        int PRIME = true;
        int result = 1;
        Object $tableName = this.getTableName();
        result = result * 59 + ($tableName == null ? 43 : $tableName.hashCode());
        Object $tableType = this.getTableType();
        result = result * 59 + ($tableType == null ? 43 : $tableType.hashCode());
        Object $remarks = this.getRemarks();
        result = result * 59 + ($remarks == null ? 43 : $remarks.hashCode());
        result = result * 59 + Arrays.deepHashCode(this.getImports());
        result = result * 59 + Arrays.deepHashCode(this.getJavaForeignTypeImports());
        result = result * 59 + Arrays.deepHashCode(this.getForeignTypeImportsAll());
        return result;
    }

    @Override
    public String toString() {
        return "Table(name=" + this.getTableName() + ", tableType=" + this.getTableType() + ", remarks=" + this.getRemarks() + ", imports=" + Arrays.deepToString(this.getImports()) + ", javaForeignTypeImports=" + Arrays.deepToString(this.getJavaForeignTypeImports()) + ", foreignTypeImportsAll=" + Arrays.deepToString(this.getForeignTypeImportsAll()) + ")";
    }
}