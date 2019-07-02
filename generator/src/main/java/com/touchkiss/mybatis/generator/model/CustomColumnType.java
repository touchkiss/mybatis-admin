package com.touchkiss.mybatis.generator.model;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import com.touchkiss.mybatis.generator.internal.type.FullyQualifiedJavaType;

public class CustomColumnType {
    private String columnName;
    private FullyQualifiedJavaType javaType;
    private Integer dataType;

    public CustomColumnType() {
    }

    public String getColumnName() {
        return this.columnName;
    }

    public FullyQualifiedJavaType getJavaType() {
        return this.javaType;
    }

    public Integer getDataType() {
        return this.dataType;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setJavaType(FullyQualifiedJavaType javaType) {
        this.javaType = javaType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CustomColumnType)) {
            return false;
        } else {
            CustomColumnType other = (CustomColumnType)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$columnName = this.getColumnName();
                    Object other$columnName = other.getColumnName();
                    if (this$columnName == null) {
                        if (other$columnName == null) {
                            break label47;
                        }
                    } else if (this$columnName.equals(other$columnName)) {
                        break label47;
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

                Object this$dataType = this.getDataType();
                Object other$dataType = other.getDataType();
                if (this$dataType == null) {
                    if (other$dataType != null) {
                        return false;
                    }
                } else if (!this$dataType.equals(other$dataType)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof CustomColumnType;
    }

    @Override
    public int hashCode() {
//        int PRIME = true;
        int result = 1;
        Object $columnName = this.getColumnName();
        result = result * 59 + ($columnName == null ? 43 : $columnName.hashCode());
        Object $javaType = this.getJavaType();
        result = result * 59 + ($javaType == null ? 43 : $javaType.hashCode());
        Object $dataType = this.getDataType();
        result = result * 59 + ($dataType == null ? 43 : $dataType.hashCode());
        return result;
    }

    public String toString() {
        return "CustomColumnType(columnName=" + this.getColumnName() + ", javaType=" + this.getJavaType() + ", dataType=" + this.getDataType() + ")";
    }
}
