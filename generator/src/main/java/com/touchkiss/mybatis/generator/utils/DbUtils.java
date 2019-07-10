package com.touchkiss.mybatis.generator.utils;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import com.google.common.collect.Lists;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

public class DbUtils {
    public DbUtils() {
    }

    public static void printResultSet(ResultSet resultSet) {
        try {
            System.out.println("---------------------------begin-------------------------------");
            List<MetaProperties> metaProperties = getResultSetMeta(resultSet);
            metaProperties.stream().forEach((t) -> {
                System.out.println(t);
            });

            while (resultSet.next()) {
                System.out.println("--------------------------------row-------------------------------------");

                for (int i = 0; i < metaProperties.size(); ++i) {
                    System.out.println(((DbUtils.MetaProperties) metaProperties.get(i)).getColumnName() + ":" + resultSet.getString(i + 1));
                }
            }

            System.out.println("---------------------------end-------------------------------");
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }

    public static List<MetaProperties> getResultSetMeta(ResultSet resultSet) {
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            List<MetaProperties> metaProperties = Lists.newArrayList();
            int i = 1;

            for (int j = metaData.getColumnCount(); i <= j; ++i) {
                DbUtils.MetaProperties meta = new DbUtils.MetaProperties();
                meta.setColumnName(metaData.getColumnName(i));
                meta.setColumnType(metaData.getColumnType(i));
                meta.setColumnTypeName(metaData.getColumnTypeName(i));
                meta.setColumnDisplaySize(metaData.getColumnDisplaySize(i));
                meta.setColumnLabel(metaData.getColumnLabel(i));
                meta.setPrecision(metaData.getPrecision(i));
                meta.setScale(metaData.getScale(i));
                meta.setTableName(metaData.getTableName(i));
                metaProperties.add(meta);
            }

            return metaProperties;
        } catch (SQLException var6) {
            var6.printStackTrace();
            return null;
        }
    }

    public static class MetaProperties {
        private String columnName;
        private Integer columnType;
        private String columnTypeName;
        private Integer columnDisplaySize;
        private String columnLabel;
        private Integer precision;
        private Integer scale;
        private String tableName;

        public MetaProperties() {
        }

        public String getColumnName() {
            return this.columnName;
        }

        public Integer getColumnType() {
            return this.columnType;
        }

        public String getColumnTypeName() {
            return this.columnTypeName;
        }

        public Integer getColumnDisplaySize() {
            return this.columnDisplaySize;
        }

        public String getColumnLabel() {
            return this.columnLabel;
        }

        public Integer getPrecision() {
            return this.precision;
        }

        public Integer getScale() {
            return this.scale;
        }

        public String getTableName() {
            return this.tableName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public void setColumnType(Integer columnType) {
            this.columnType = columnType;
        }

        public void setColumnTypeName(String columnTypeName) {
            this.columnTypeName = columnTypeName;
        }

        public void setColumnDisplaySize(Integer columnDisplaySize) {
            this.columnDisplaySize = columnDisplaySize;
        }

        public void setColumnLabel(String columnLabel) {
            this.columnLabel = columnLabel;
        }

        public void setPrecision(Integer precision) {
            this.precision = precision;
        }

        public void setScale(Integer scale) {
            this.scale = scale;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof DbUtils.MetaProperties)) {
                return false;
            } else {
                DbUtils.MetaProperties other = (DbUtils.MetaProperties) o;
                if (!other.canEqual(this)) {
                    return false;
                } else {
                    label107:
                    {
                        Object this$columnName = this.getColumnName();
                        Object other$columnName = other.getColumnName();
                        if (this$columnName == null) {
                            if (other$columnName == null) {
                                break label107;
                            }
                        } else if (this$columnName.equals(other$columnName)) {
                            break label107;
                        }

                        return false;
                    }

                    Object this$columnType = this.getColumnType();
                    Object other$columnType = other.getColumnType();
                    if (this$columnType == null) {
                        if (other$columnType != null) {
                            return false;
                        }
                    } else if (!this$columnType.equals(other$columnType)) {
                        return false;
                    }

                    Object this$columnTypeName = this.getColumnTypeName();
                    Object other$columnTypeName = other.getColumnTypeName();
                    if (this$columnTypeName == null) {
                        if (other$columnTypeName != null) {
                            return false;
                        }
                    } else if (!this$columnTypeName.equals(other$columnTypeName)) {
                        return false;
                    }

                    label86:
                    {
                        Object this$columnDisplaySize = this.getColumnDisplaySize();
                        Object other$columnDisplaySize = other.getColumnDisplaySize();
                        if (this$columnDisplaySize == null) {
                            if (other$columnDisplaySize == null) {
                                break label86;
                            }
                        } else if (this$columnDisplaySize.equals(other$columnDisplaySize)) {
                            break label86;
                        }

                        return false;
                    }

                    label79:
                    {
                        Object this$columnLabel = this.getColumnLabel();
                        Object other$columnLabel = other.getColumnLabel();
                        if (this$columnLabel == null) {
                            if (other$columnLabel == null) {
                                break label79;
                            }
                        } else if (this$columnLabel.equals(other$columnLabel)) {
                            break label79;
                        }

                        return false;
                    }

                    label72:
                    {
                        Object this$precision = this.getPrecision();
                        Object other$precision = other.getPrecision();
                        if (this$precision == null) {
                            if (other$precision == null) {
                                break label72;
                            }
                        } else if (this$precision.equals(other$precision)) {
                            break label72;
                        }

                        return false;
                    }

                    Object this$scale = this.getScale();
                    Object other$scale = other.getScale();
                    if (this$scale == null) {
                        if (other$scale != null) {
                            return false;
                        }
                    } else if (!this$scale.equals(other$scale)) {
                        return false;
                    }

                    Object this$tableName = this.getTableName();
                    Object other$tableName = other.getTableName();
                    if (this$tableName == null) {
                        if (other$tableName != null) {
                            return false;
                        }
                    } else if (!this$tableName.equals(other$tableName)) {
                        return false;
                    }

                    return true;
                }
            }
        }

        protected boolean canEqual(Object other) {
            return other instanceof DbUtils.MetaProperties;
        }

        @Override
        public int hashCode() {
//            int PRIME = true;
            int result = 1;
            Object $columnName = this.getColumnName();
            result = result * 59 + ($columnName == null ? 43 : $columnName.hashCode());
            Object $columnType = this.getColumnType();
            result = result * 59 + ($columnType == null ? 43 : $columnType.hashCode());
            Object $columnTypeName = this.getColumnTypeName();
            result = result * 59 + ($columnTypeName == null ? 43 : $columnTypeName.hashCode());
            Object $columnDisplaySize = this.getColumnDisplaySize();
            result = result * 59 + ($columnDisplaySize == null ? 43 : $columnDisplaySize.hashCode());
            Object $columnLabel = this.getColumnLabel();
            result = result * 59 + ($columnLabel == null ? 43 : $columnLabel.hashCode());
            Object $precision = this.getPrecision();
            result = result * 59 + ($precision == null ? 43 : $precision.hashCode());
            Object $scale = this.getScale();
            result = result * 59 + ($scale == null ? 43 : $scale.hashCode());
            Object $tableName = this.getTableName();
            result = result * 59 + ($tableName == null ? 43 : $tableName.hashCode());
            return result;
        }

        @Override
        public String toString() {
            return "DbUtils.MetaProperties(columnName=" + this.getColumnName() + ", columnType=" + this.getColumnType() + ", columnTypeName=" + this.getColumnTypeName() + ", columnDisplaySize=" + this.getColumnDisplaySize() + ", columnLabel=" + this.getColumnLabel() + ", precision=" + this.getPrecision() + ", scale=" + this.getScale() + ", name=" + this.getTableName() + ")";
        }
    }
}