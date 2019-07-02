package com.touchkiss.mybatis.generator.model;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import com.touchkiss.mybatis.generator.internal.type.JdbcTypeNameTranslator;
import com.touchkiss.mybatis.generator.utils.JavaUtils;

public class Column {
    private String columnName;
    private String typeName;
    private Integer dataType;
    private Integer columnSize;
    private Integer decimalDigits;
    private String isNullable;
    private String remarks;
    private String isAutoincrement;
    private String isGeneratedcolumn;
    private Table table;
    private boolean isPrimaryKey = false;
    private ForeignKey foreignKey;
    private String javaType;
    private String fullJavaType;

    public boolean getIsPrimaryKey() {
        return this.isPrimaryKey;
    }

    public String getJdbcType() {
        return JdbcTypeNameTranslator.getJdbcTypeName(this.dataType);
    }

    public String getJavaProperty() {
        return JavaUtils.toCamelCase(this.columnName, false);
    }

    public String getJavaPropertyFirstUpper() {
        return JavaUtils.toCamelCase(this.columnName, true);
    }

    public boolean getIsForeignKey() {
        return this.foreignKey != null;
    }

    public Column() {
    }

    public String getColumnName() {
        return this.columnName;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public Integer getDataType() {
        return this.dataType;
    }

    public Integer getColumnSize() {
        return this.columnSize;
    }

    public Integer getDecimalDigits() {
        return this.decimalDigits;
    }

    public String getIsNullable() {
        return this.isNullable;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public String getIsAutoincrement() {
        return this.isAutoincrement;
    }

    public String getIsGeneratedcolumn() {
        return this.isGeneratedcolumn;
    }

    public Table getTable() {
        return this.table;
    }

    public ForeignKey getForeignKey() {
        return this.foreignKey;
    }

    public String getJavaType() {
        return this.javaType;
    }

    public String getFullJavaType() {
        return this.fullJavaType;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public void setColumnSize(Integer columnSize) {
        this.columnSize = columnSize;
    }

    public void setDecimalDigits(Integer decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setIsAutoincrement(String isAutoincrement) {
        this.isAutoincrement = isAutoincrement;
    }

    public void setIsGeneratedcolumn(String isGeneratedcolumn) {
        this.isGeneratedcolumn = isGeneratedcolumn;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public void setPrimaryKey(boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    public void setForeignKey(ForeignKey foreignKey) {
        this.foreignKey = foreignKey;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public void setFullJavaType(String fullJavaType) {
        this.fullJavaType = fullJavaType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Column)) {
            return false;
        } else {
            Column other = (Column)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label171: {
                    Object this$columnName = this.getColumnName();
                    Object other$columnName = other.getColumnName();
                    if (this$columnName == null) {
                        if (other$columnName == null) {
                            break label171;
                        }
                    } else if (this$columnName.equals(other$columnName)) {
                        break label171;
                    }

                    return false;
                }

                Object this$typeName = this.getTypeName();
                Object other$typeName = other.getTypeName();
                if (this$typeName == null) {
                    if (other$typeName != null) {
                        return false;
                    }
                } else if (!this$typeName.equals(other$typeName)) {
                    return false;
                }

                Object this$dataType = this.getDataType();
                Object other$dataType = other.getDataType();
                if (this$dataType == null) {
                    if (other$dataType != null) {
                        return false;
                    }
                } else if (!this$dataType.equals(other$dataType)) {
                    return false;
                }

                label150: {
                    Object this$columnSize = this.getColumnSize();
                    Object other$columnSize = other.getColumnSize();
                    if (this$columnSize == null) {
                        if (other$columnSize == null) {
                            break label150;
                        }
                    } else if (this$columnSize.equals(other$columnSize)) {
                        break label150;
                    }

                    return false;
                }

                label143: {
                    Object this$decimalDigits = this.getDecimalDigits();
                    Object other$decimalDigits = other.getDecimalDigits();
                    if (this$decimalDigits == null) {
                        if (other$decimalDigits == null) {
                            break label143;
                        }
                    } else if (this$decimalDigits.equals(other$decimalDigits)) {
                        break label143;
                    }

                    return false;
                }

                label136: {
                    Object this$isNullable = this.getIsNullable();
                    Object other$isNullable = other.getIsNullable();
                    if (this$isNullable == null) {
                        if (other$isNullable == null) {
                            break label136;
                        }
                    } else if (this$isNullable.equals(other$isNullable)) {
                        break label136;
                    }

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

                label122: {
                    Object this$isAutoincrement = this.getIsAutoincrement();
                    Object other$isAutoincrement = other.getIsAutoincrement();
                    if (this$isAutoincrement == null) {
                        if (other$isAutoincrement == null) {
                            break label122;
                        }
                    } else if (this$isAutoincrement.equals(other$isAutoincrement)) {
                        break label122;
                    }

                    return false;
                }

                Object this$isGeneratedcolumn = this.getIsGeneratedcolumn();
                Object other$isGeneratedcolumn = other.getIsGeneratedcolumn();
                if (this$isGeneratedcolumn == null) {
                    if (other$isGeneratedcolumn != null) {
                        return false;
                    }
                } else if (!this$isGeneratedcolumn.equals(other$isGeneratedcolumn)) {
                    return false;
                }

                label108: {
                    Object this$table = this.getTable();
                    Object other$table = other.getTable();
                    if (this$table == null) {
                        if (other$table == null) {
                            break label108;
                        }
                    } else if (this$table.equals(other$table)) {
                        break label108;
                    }

                    return false;
                }

                if (this.getIsPrimaryKey() != other.getIsPrimaryKey()) {
                    return false;
                } else {
                    label100: {
                        Object this$foreignKey = this.getForeignKey();
                        Object other$foreignKey = other.getForeignKey();
                        if (this$foreignKey == null) {
                            if (other$foreignKey == null) {
                                break label100;
                            }
                        } else if (this$foreignKey.equals(other$foreignKey)) {
                            break label100;
                        }

                        return false;
                    }

                    Object this$javaType = this.getJavaType();
                    Object other$javaType = other.getJavaType();
                    if (this$javaType == null) {
                        if (other$javaType != null) {
                            return false;
                        }
                    } else if (!this$javaType.equals(other$javaType)) {
                        return false;
                    }

                    Object this$fullJavaType = this.getFullJavaType();
                    Object other$fullJavaType = other.getFullJavaType();
                    if (this$fullJavaType == null) {
                        if (other$fullJavaType != null) {
                            return false;
                        }
                    } else if (!this$fullJavaType.equals(other$fullJavaType)) {
                        return false;
                    }

                    return true;
                }
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Column;
    }

    @Override
    public int hashCode() {
//        int PRIME = true;
        int result = 1;
        Object $columnName = this.getColumnName();
        result = result * 59 + ($columnName == null ? 43 : $columnName.hashCode());
        Object $typeName = this.getTypeName();
        result = result * 59 + ($typeName == null ? 43 : $typeName.hashCode());
        Object $dataType = this.getDataType();
        result = result * 59 + ($dataType == null ? 43 : $dataType.hashCode());
        Object $columnSize = this.getColumnSize();
        result = result * 59 + ($columnSize == null ? 43 : $columnSize.hashCode());
        Object $decimalDigits = this.getDecimalDigits();
        result = result * 59 + ($decimalDigits == null ? 43 : $decimalDigits.hashCode());
        Object $isNullable = this.getIsNullable();
        result = result * 59 + ($isNullable == null ? 43 : $isNullable.hashCode());
        Object $remarks = this.getRemarks();
        result = result * 59 + ($remarks == null ? 43 : $remarks.hashCode());
        Object $isAutoincrement = this.getIsAutoincrement();
        result = result * 59 + ($isAutoincrement == null ? 43 : $isAutoincrement.hashCode());
        Object $isGeneratedcolumn = this.getIsGeneratedcolumn();
        result = result * 59 + ($isGeneratedcolumn == null ? 43 : $isGeneratedcolumn.hashCode());
        Object $table = this.getTable();
        result = result * 59 + ($table == null ? 43 : $table.hashCode());
        result = result * 59 + (this.getIsPrimaryKey() ? 79 : 97);
        Object $foreignKey = this.getForeignKey();
        result = result * 59 + ($foreignKey == null ? 43 : $foreignKey.hashCode());
        Object $javaType = this.getJavaType();
        result = result * 59 + ($javaType == null ? 43 : $javaType.hashCode());
        Object $fullJavaType = this.getFullJavaType();
        result = result * 59 + ($fullJavaType == null ? 43 : $fullJavaType.hashCode());
        return result;
    }

    public String toString() {
        return "Column(columnName=" + this.getColumnName() + ", typeName=" + this.getTypeName() + ", dataType=" + this.getDataType() + ", columnSize=" + this.getColumnSize() + ", decimalDigits=" + this.getDecimalDigits() + ", isNullable=" + this.getIsNullable() + ", remarks=" + this.getRemarks() + ", isAutoincrement=" + this.getIsAutoincrement() + ", isGeneratedcolumn=" + this.getIsGeneratedcolumn() + ", table=" + this.getTable() + ", isPrimaryKey=" + this.getIsPrimaryKey() + ", foreignKey=" + this.getForeignKey() + ", javaType=" + this.getJavaType() + ", fullJavaType=" + this.getFullJavaType() + ")";
    }
}