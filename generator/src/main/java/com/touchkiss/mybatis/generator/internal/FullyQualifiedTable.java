package com.touchkiss.mybatis.generator.internal;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import com.google.common.collect.Maps;
import com.touchkiss.mybatis.generator.model.Column;
import com.touchkiss.mybatis.generator.model.Table;
import com.touchkiss.mybatis.generator.model.TableConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class FullyQualifiedTable {
    private Map<String, Table> tableMap = Maps.newTreeMap();
    private Map<String, TableConfig> tableConfigMap = Maps.newHashMap();
    private Map<String, Column[]> columnsIndexMap = Maps.newHashMap();
    private Map<String, Map<String, Column>> columnsMap = Maps.newHashMap();

    public FullyQualifiedTable() {
    }

    public void addTable(Table table, TableConfig tableConfig, Column[] columns) {
        if (StringUtils.isNoneBlank(new CharSequence[]{table.getTableName()})) {
            String tableName = table.getTableName().toUpperCase();
            this.tableMap.put(tableName, table);
            this.tableConfigMap.put(tableName, tableConfig);
            this.columnsIndexMap.put(tableName, columns);
            Map<String, Column> map = Maps.newHashMap();

            for(int i = 0; i < columns.length; ++i) {
                map.put(columns[i].getColumnName().toUpperCase(), columns[i]);
            }

            this.columnsMap.put(tableName, map);
        }

    }

    public TableConfig getTableConfig(String tableName) {
        return (TableConfig)this.tableConfigMap.get(tableName.toUpperCase());
    }

    public TableConfig[] getTableConfigs() {
        return (TableConfig[])this.tableConfigMap.values().toArray(new TableConfig[0]);
    }

    public Table[] getTables() {
        return (Table[])this.tableMap.values().toArray(new Table[0]);
    }

    public Table getTable(String tableName) {
        tableName = tableName.toUpperCase();
        return (Table)this.tableMap.get(tableName);
    }

    public Column[] getColumns(String tableName) {
        tableName = tableName.toUpperCase();
        return (Column[])this.columnsIndexMap.get(tableName);
    }

    public Column getColumn(String tableName, String columnName) {
        tableName = tableName.toUpperCase();
        columnName = columnName.toUpperCase();
        Map<String, Column> m = (Map)this.columnsMap.get(tableName);
        return m != null ? (Column)m.get(columnName) : null;
    }
}