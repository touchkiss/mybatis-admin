package com.touchkiss.mybatis.generator.internal.type;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import com.google.common.collect.Maps;
import com.touchkiss.mybatis.generator.internal.FullyQualifiedTable;
import com.touchkiss.mybatis.generator.internal.GeneratorContext;
import com.touchkiss.mybatis.generator.internal.db.DatabaseDialects;
import com.touchkiss.mybatis.generator.model.Column;
import com.touchkiss.mybatis.generator.model.TableConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Map;

public class JavaTypeResolver extends CustomTypeProvide {
    protected Map<Integer, JdbcTypeInformation> typeMap = Maps.newHashMap();
    protected Map<String, FullyQualifiedJavaType> definedTypeMap = Maps.newHashMap();

    public JavaTypeResolver(GeneratorContext context) {
        super(context);
        this.typeMap.put(2003, new JavaTypeResolver.JdbcTypeInformation("ARRAY", new FullyQualifiedJavaType(Object.class.getName())));
        this.typeMap.put(-5, new JavaTypeResolver.JdbcTypeInformation("BIGINT", new FullyQualifiedJavaType(Long.class.getName())));
        this.typeMap.put(-2, new JavaTypeResolver.JdbcTypeInformation("BINARY", new FullyQualifiedJavaType("byte[]")));
        this.typeMap.put(-7, new JavaTypeResolver.JdbcTypeInformation("BIT", new FullyQualifiedJavaType(Boolean.class.getName())));
        this.typeMap.put(2004, new JavaTypeResolver.JdbcTypeInformation("BLOB", new FullyQualifiedJavaType("byte[]")));
        this.typeMap.put(16, new JavaTypeResolver.JdbcTypeInformation("BOOLEAN", new FullyQualifiedJavaType(Boolean.class.getName())));
        this.typeMap.put(1, new JavaTypeResolver.JdbcTypeInformation("CHAR", new FullyQualifiedJavaType(String.class.getName())));
        this.typeMap.put(2005, new JavaTypeResolver.JdbcTypeInformation("CLOB", new FullyQualifiedJavaType(String.class.getName())));
        this.typeMap.put(70, new JavaTypeResolver.JdbcTypeInformation("DATALINK", new FullyQualifiedJavaType(Object.class.getName())));
        this.typeMap.put(91, new JavaTypeResolver.JdbcTypeInformation("DATE", new FullyQualifiedJavaType(Date.class.getName())));
        this.typeMap.put(2001, new JavaTypeResolver.JdbcTypeInformation("DISTINCT", new FullyQualifiedJavaType(Object.class.getName())));
        this.typeMap.put(8, new JavaTypeResolver.JdbcTypeInformation("DOUBLE", new FullyQualifiedJavaType(Double.class.getName())));
        this.typeMap.put(6, new JavaTypeResolver.JdbcTypeInformation("FLOAT", new FullyQualifiedJavaType(Double.class.getName())));
        this.typeMap.put(4, new JavaTypeResolver.JdbcTypeInformation("INTEGER", new FullyQualifiedJavaType(Integer.class.getName())));
        this.typeMap.put(2000, new JavaTypeResolver.JdbcTypeInformation("JAVA_OBJECT", new FullyQualifiedJavaType(Object.class.getName())));
        this.typeMap.put(-16, new JavaTypeResolver.JdbcTypeInformation("LONGNVARCHAR", new FullyQualifiedJavaType(String.class.getName())));
        this.typeMap.put(-4, new JavaTypeResolver.JdbcTypeInformation("LONGVARBINARY", new FullyQualifiedJavaType("byte[]")));
        this.typeMap.put(-1, new JavaTypeResolver.JdbcTypeInformation("LONGVARCHAR", new FullyQualifiedJavaType(String.class.getName())));
        this.typeMap.put(-15, new JavaTypeResolver.JdbcTypeInformation("NCHAR", new FullyQualifiedJavaType(String.class.getName())));
        this.typeMap.put(2011, new JavaTypeResolver.JdbcTypeInformation("NCLOB", new FullyQualifiedJavaType(String.class.getName())));
        this.typeMap.put(-9, new JavaTypeResolver.JdbcTypeInformation("NVARCHAR", new FullyQualifiedJavaType(String.class.getName())));
        this.typeMap.put(0, new JavaTypeResolver.JdbcTypeInformation("NULL", new FullyQualifiedJavaType(Object.class.getName())));
        this.typeMap.put(1111, new JavaTypeResolver.JdbcTypeInformation("OTHER", new FullyQualifiedJavaType(Object.class.getName())));
        this.typeMap.put(7, new JavaTypeResolver.JdbcTypeInformation("REAL", new FullyQualifiedJavaType(Float.class.getName())));
        this.typeMap.put(2006, new JavaTypeResolver.JdbcTypeInformation("REF", new FullyQualifiedJavaType(Object.class.getName())));
        this.typeMap.put(5, new JavaTypeResolver.JdbcTypeInformation("SMALLINT", new FullyQualifiedJavaType(Short.class.getName())));
        this.typeMap.put(2002, new JavaTypeResolver.JdbcTypeInformation("STRUCT", new FullyQualifiedJavaType(Object.class.getName())));
        this.typeMap.put(92, new JavaTypeResolver.JdbcTypeInformation("TIME", new FullyQualifiedJavaType(Date.class.getName())));
        this.typeMap.put(93, new JavaTypeResolver.JdbcTypeInformation("TIMESTAMP", new FullyQualifiedJavaType(Date.class.getName())));
        this.typeMap.put(-6, new JavaTypeResolver.JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Byte.class.getName())));
        this.typeMap.put(-3, new JavaTypeResolver.JdbcTypeInformation("VARBINARY", new FullyQualifiedJavaType("byte[]")));
        this.typeMap.put(12, new JavaTypeResolver.JdbcTypeInformation("VARCHAR", new FullyQualifiedJavaType(String.class.getName())));
    }

    public void addDefinedType(TableConfig tableConfig) {
        String tablePath = tableConfig.getPackageName() + "." + tableConfig.getEntityName();
        if (!this.definedTypeMap.containsKey(tablePath.toUpperCase())) {
            this.definedTypeMap.put(tablePath.toUpperCase(), new FullyQualifiedJavaType(tablePath));
        }

    }

    public FullyQualifiedJavaType calculateJavaType(Column column, FullyQualifiedTable fullyQualifiedTable) {
        FullyQualifiedJavaType answer = null;
        if (column.getIsForeignKey()) {
            String pkTabkeName = column.getForeignKey().getPktableName();
            TableConfig table = fullyQualifiedTable.getTableConfig(pkTabkeName);
            if (table != null) {
                String tablePath = (table.getPackageName() + "." + table.getEntityName()).toUpperCase();
                answer = (FullyQualifiedJavaType)this.definedTypeMap.get(tablePath);
                if (answer == null) {
                    answer = FullyQualifiedJavaType.objectJavaType;
                }
            }
        }

        return answer;
    }

    public FullyQualifiedJavaType calculateJavaType(Column column) {
        if (this.context.getDatabaseDialect() == DatabaseDialects.ORACLE) {
            if (column.getDataType() == 1111) {
                if (column.getTypeName().equalsIgnoreCase("NVARCHAR2")) {
                    column.setDataType(-9);
                }
            } else if (column.getTypeName().startsWith("TIMESTAMP")) {
                column.setDataType(93);
            }
        }

        JavaTypeResolver.JdbcTypeInformation jdbcTypeInformation = (JavaTypeResolver.JdbcTypeInformation)this.typeMap.get(column.getDataType());
        FullyQualifiedJavaType answer;
        if (jdbcTypeInformation == null) {
            switch(column.getDataType()) {
            case 2:
            case 3:
                if (column.getDecimalDigits() > 0 && column.getColumnSize() <= 11) {
                    answer = FullyQualifiedJavaType.DoubleJavaType;
                } else if ((column.getDecimalDigits() > 0 || column.getColumnSize() > 18) && this.context.getForceBigDecimals()) {
                    answer = FullyQualifiedJavaType.BigDecimalJavaType;
                } else if (column.getColumnSize() > 11) {
                    answer = FullyQualifiedJavaType.LongJavaType;
                } else if (column.getColumnSize() > 4) {
                    answer = FullyQualifiedJavaType.IntegerJavaType;
                } else {
                    answer = FullyQualifiedJavaType.ShortJavaType;
                }
                break;
            default:
                answer = null;
            }
        } else {
            switch(column.getDataType()) {
            case -6:
                if (column.getColumnName().toUpperCase().startsWith("IS")) {
                    answer = FullyQualifiedJavaType.BooleanJavaType;
                } else {
                    answer = FullyQualifiedJavaType.IntegerJavaType;
                }
                break;
            default:
                answer = jdbcTypeInformation.getFullyQualifiedJavaType();
            }
        }

        if (answer == null) {
            answer = FullyQualifiedJavaType.objectJavaType;
        } else if (this.context.getJavaTypeChange() != null) {
            String type = (String)this.context.getJavaTypeChange().get(answer.getFullTypeSpecification());
            if (StringUtils.isNotBlank(type)) {
                if (FullyQualifiedJavaType.BigDecimalJavaType.getFullTypeSpecification().equals(type)) {
                    answer = FullyQualifiedJavaType.BigDecimalJavaType;
                } else if (FullyQualifiedJavaType.objectJavaType.getFullTypeSpecification().equals(type)) {
                    answer = FullyQualifiedJavaType.objectJavaType;
                } else if (FullyQualifiedJavaType.DoubleJavaType.getFullTypeSpecification().equals(type)) {
                    answer = FullyQualifiedJavaType.DoubleJavaType;
                } else if (FullyQualifiedJavaType.LongJavaType.getFullTypeSpecification().equals(type)) {
                    answer = FullyQualifiedJavaType.LongJavaType;
                } else if (FullyQualifiedJavaType.IntegerJavaType.getFullTypeSpecification().equals(type)) {
                    answer = FullyQualifiedJavaType.IntegerJavaType;
                } else if (FullyQualifiedJavaType.ShortJavaType.getFullTypeSpecification().equals(type)) {
                    answer = FullyQualifiedJavaType.ShortJavaType;
                } else if (FullyQualifiedJavaType.BooleanJavaType.getFullTypeSpecification().equals(type)) {
                    answer = FullyQualifiedJavaType.BooleanJavaType;
                } else {
                    answer = new FullyQualifiedJavaType(type);
                }
            }
        }

        return answer;
    }

    public static class JdbcTypeInformation {
        private String jdbcTypeName;
        private FullyQualifiedJavaType fullyQualifiedJavaType;

        public JdbcTypeInformation(String jdbcTypeName, FullyQualifiedJavaType fullyQualifiedJavaType) {
            this.jdbcTypeName = jdbcTypeName;
            this.fullyQualifiedJavaType = fullyQualifiedJavaType;
        }

        public String getJdbcTypeName() {
            return this.jdbcTypeName;
        }

        public FullyQualifiedJavaType getFullyQualifiedJavaType() {
            return this.fullyQualifiedJavaType;
        }
    }
}