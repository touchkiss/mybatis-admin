package com.touchkiss.mybatis.generator.model;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import com.touchkiss.mybatis.generator.internal.type.FullyQualifiedJavaType;
import com.touchkiss.mybatis.generator.utils.JavaUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableConfig {
    private String tableName;
    private String entityName;
    private String packageName;
    private boolean overwrite;
    private boolean generator;
    private boolean supportSerialize;
    private boolean useGeneratedKeys;
    private String schema;
    private Cache cache;
    private final List<String> ignoreColumns;
    private final List<String> columnKeys;
    private final List<SelectKey> selectKeys;
    private final List<CustomProperty> customProperties;
    private final Map<String, CustomColumnType> customColumnTypeMap;

    public TableConfig schema(String schema) {
        this.schema = schema;
        return this;
    }

    public TableConfig cache(Cache cache) {
        this.cache = cache;
        return this;
    }

    public TableConfig addSelectKey(SelectKey selectKey) {
        this.selectKeys.add(selectKey);
        return this;
    }

    public TableConfig addCustomColumnType(String columnName, Class<?> javaType, Integer jdbcType) {
        CustomColumnType customColumnType = new CustomColumnType();
        customColumnType.setColumnName(columnName);
        customColumnType.setJavaType(new FullyQualifiedJavaType(javaType.getTypeName()));
        customColumnType.setDataType(jdbcType);
        this.customColumnTypeMap.put(columnName, customColumnType);
        return this;
    }

    public TableConfig addCustomProperty(String javaName, Class<?> javaType, String remarks) {
        CustomProperty customProperty = new CustomProperty();
        customProperty.setJavaName(javaName);
        customProperty.setJavaType(new FullyQualifiedJavaType(javaType.getTypeName()));
        customProperty.setRemarks(remarks);
        this.customProperties.add(customProperty);
        return this;
    }

    public TableConfig addIgnoreColumn(String columnName) {
        this.ignoreColumns.add(columnName);
        return this;
    }

    public TableConfig addPrimaryKeys(String columnKey) {
        this.columnKeys.add(columnKey);
        return this;
    }

    public TableConfig tableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public TableConfig entityName(String entityName) {
        this.entityName = entityName;
        return this;
    }

    public TableConfig packageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public TableConfig overwrite(boolean overwrite) {
        this.overwrite = overwrite;
        return this;
    }

    public TableConfig supportSerialize(boolean supportSerialize) {
        this.supportSerialize = supportSerialize;
        return this;
    }

    public TableConfig generator(boolean generator) {
        this.generator = generator;
        return this;
    }

    public TableConfig useGeneratedKeys(boolean useGeneratedKeys) {
        this.useGeneratedKeys = useGeneratedKeys;
        return this;
    }

    public TableConfig(String tableName) {
        this(tableName, (String)null, (String)null, false, true);
    }

    public TableConfig(String tableName, String entityName) {
        this(tableName, entityName, (String)null, false, true);
    }

    public TableConfig(String tableName, String entityName, String packageName) {
        this(tableName, entityName, packageName, false, true);
    }

    public TableConfig(String tableName, String entityName, String packageName, boolean overwrite) {
        this(tableName, entityName, packageName, overwrite, true);
    }

    public TableConfig(String tableName, String entityName, String packageName, boolean overwrite, boolean generator) {
        this.overwrite = false;
        this.generator = false;
        this.supportSerialize = false;
        this.useGeneratedKeys = false;
        this.ignoreColumns = new ArrayList();
        this.columnKeys = new ArrayList();
        this.selectKeys = new ArrayList();
        this.customProperties = new ArrayList();
        this.customColumnTypeMap = new HashMap();
        this.tableName = tableName;
        this.entityName = entityName;
        this.packageName = packageName;
        this.overwrite = overwrite;
        this.generator = generator;
    }

    public String getEntityName() {
        return StringUtils.isBlank(this.entityName) ? JavaUtils.toCamelCase(this.tableName, true) : this.entityName;
    }

    public boolean getUseGeneratedKeys() {
        return this.useGeneratedKeys;
    }

    public String getFirstLowerEntityName() {
        if (StringUtils.isBlank(this.entityName)) {
            return JavaUtils.toCamelCase(this.tableName, false);
        } else if (this.entityName.length() > 0) {
            String e = this.entityName.substring(0, 1).toLowerCase();
            if (this.entityName.length() > 1) {
                e = e + this.entityName.substring(1);
            }

            return e;
        } else {
            return this.entityName;
        }
    }

    public String getTableName() {
        return this.tableName;
    }

    public boolean getOverwrite() {
        return this.overwrite;
    }

    public boolean getGenerator() {
        return this.generator;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public boolean getSupportSerialize() {
        return this.supportSerialize;
    }

    public CustomColumnType getCustomColumnType(String columnName) {
        return (CustomColumnType)this.customColumnTypeMap.get(columnName);
    }

    public List<CustomProperty> getCustomProperties() {
        return this.customProperties;
    }

    public List<String> getColumnKeys() {
        return this.columnKeys;
    }

    public List<String> getIgnoreColumns() {
        return this.ignoreColumns;
    }

    public List<SelectKey> getSelectKeys() {
        return this.selectKeys;
    }

    public Cache getCache() {
        return this.cache;
    }

    public String getSchema() {
        return StringUtils.isNotBlank(this.schema) ? this.schema : null;
    }
}
