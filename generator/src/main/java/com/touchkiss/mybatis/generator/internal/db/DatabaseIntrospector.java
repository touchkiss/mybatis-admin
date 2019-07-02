package com.touchkiss.mybatis.generator.internal.db;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import com.google.common.collect.Lists;
import com.touchkiss.mybatis.generator.internal.FullyQualifiedTable;
import com.touchkiss.mybatis.generator.internal.GeneratorContext;
import com.touchkiss.mybatis.generator.internal.type.JavaTypeResolver;
import com.touchkiss.mybatis.generator.model.Column;
import com.touchkiss.mybatis.generator.model.ForeignKey;
import com.touchkiss.mybatis.generator.model.Table;
import com.touchkiss.mybatis.generator.model.TableConfig;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseIntrospector {
    private static final Logger log = LoggerFactory.getLogger(DatabaseIntrospector.class);
    private DatabaseMetaData databaseMetaData;
    private String[] tableType;
    private GeneratorContext context;
    private String catalog;
    private String schema;

    public DatabaseIntrospector(DatabaseMetaData databaseMetaData, GeneratorContext context, String[] tableType, String catalog, String schema) {
        this.databaseMetaData = databaseMetaData;
        this.tableType = tableType;
        this.context = context;
        this.catalog = catalog;
        this.schema = schema;
    }

    public boolean calculateTable(TableConfig tableConfig, FullyQualifiedTable fullyQualifiedTable) {
        Table table = this.getTable((String)null, tableConfig.getSchema(), tableConfig.getTableName());
        if (table != null) {
            Column[] columns = this.getColumns((String)null, tableConfig.getSchema(), table);
            if (ArrayUtils.isNotEmpty(columns)) {
                fullyQualifiedTable.addTable(table, tableConfig, columns);
                return true;
            }
        }

        return false;
    }

    public boolean calculatePrimaryKey(TableConfig tableConfig, FullyQualifiedTable fullyQualifiedTable) {
        ResultSet rs = null;

        try {
            rs = this.databaseMetaData.getPrimaryKeys(this.catalog, StringUtils.isEmpty(tableConfig.getSchema()) ? this.schema : tableConfig.getSchema(), tableConfig.getTableName());
            boolean b = false;

            while(rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                String tableName = rs.getString("TABLE_NAME");
                Column column = fullyQualifiedTable.getColumn(tableName, columnName);
                if (null != column) {
                    column.setPrimaryKey(true);
                    b = true;
                }
            }

            boolean var13 = b;
            return var13;
        } catch (SQLException var11) {
            log.error("calculate PrimaryKey ：", var11);
        } finally {
            this.closeResultSet(rs);
        }

        return false;
    }

    public void calculateForeignKey(FullyQualifiedTable fullyQualifiedTable, JavaTypeResolver javaTypeResolver) {
        Table[] tables = fullyQualifiedTable.getTables();

        for(int i = 0; i < tables.length; ++i) {
            ResultSet rs = null;

            try {
                TableConfig curTableConfig = fullyQualifiedTable.getTableConfig(tables[i].getTableName());
                rs = this.databaseMetaData.getImportedKeys(this.catalog, StringUtils.isEmpty(curTableConfig.getSchema()) ? this.schema : curTableConfig.getSchema(), tables[i].getTableName());

                while(rs.next()) {
                    ForeignKey foreignKey = new ForeignKey();
                    foreignKey.setFkcolumnName(rs.getString("FKCOLUMN_NAME"));
                    foreignKey.setFktableName(rs.getString("FKTABLE_NAME"));
                    foreignKey.setPktableName(rs.getString("PKTABLE_NAME"));
                    foreignKey.setPkcolumnName(rs.getString("PKCOLUMN_NAME"));
                    Column column = fullyQualifiedTable.getColumn(foreignKey.getFktableName(), foreignKey.getFkcolumnName());
                    column.setForeignKey(foreignKey);
                    Column pkColumn = fullyQualifiedTable.getColumn(foreignKey.getPktableName(), foreignKey.getPkcolumnName());
                    if (pkColumn == null) {
                        TableConfig tableConfig = new TableConfig(foreignKey.getPktableName());
                        tableConfig.generator(false);
                        if (StringUtils.isBlank(tableConfig.getPackageName())) {
                            tableConfig.packageName(this.context.getDefaultEntityPackage());
                        }

                        javaTypeResolver.addDefinedType(tableConfig);
                        if (this.calculateTable(tableConfig, fullyQualifiedTable)) {
                            this.calculatePrimaryKey(tableConfig, fullyQualifiedTable);
                            Column pkColumn1 = fullyQualifiedTable.getColumn(foreignKey.getPktableName(), foreignKey.getPkcolumnName());
                            foreignKey.setPkColumn(pkColumn1);
                        }
                    } else {
                        foreignKey.setPkColumn(pkColumn);
                    }
                }
            } catch (SQLException var15) {
                log.error("calculate ForeignKey ：", var15);
            } finally {
                this.closeResultSet(rs);
            }
        }

    }

    private Table getTable(String catalog, String schema, String tableName) {
        ResultSet rs = null;

        Table var6;
        try {
            rs = this.databaseMetaData.getTables(StringUtils.isEmpty(catalog) ? this.catalog : catalog, StringUtils.isEmpty(schema) ? this.schema : schema, tableName, this.tableType);
            if (!rs.next()) {
                return null;
            }

            Table table = new Table();
            table.setTableName(rs.getString("TABLE_NAME"));
            table.setRemarks(rs.getString("REMARKS"));
            table.setTableType(rs.getString("TABLE_TYPE"));
            var6 = table;
        } catch (SQLException var10) {
            log.error("calculateTable：", var10);
            return null;
        } finally {
            this.closeResultSet(rs);
        }

        return var6;
    }

    private Column[] getColumns(String catalog, String schema, Table table) {
        ResultSet rs = null;

        try {
            rs = this.databaseMetaData.getColumns(StringUtils.isEmpty(catalog) ? this.catalog : catalog, StringUtils.isEmpty(schema) ? this.schema : schema, table.getTableName(), (String)null);
            ArrayList columns = Lists.newArrayList();

            while(rs.next()) {
                Column column = new Column();
                column.setColumnName(rs.getString("COLUMN_NAME"));
                column.setDataType(rs.getInt("DATA_TYPE"));
                column.setTypeName(rs.getString("TYPE_NAME"));
                column.setColumnSize(rs.getInt("COLUMN_SIZE"));
                column.setDecimalDigits(rs.getInt("DECIMAL_DIGITS"));
                column.setRemarks(rs.getString("REMARKS"));
                column.setIsNullable(rs.getString("IS_NULLABLE"));
                if (this.context.getDatabaseDialect() == DatabaseDialects.MYSQL) {
                    column.setIsAutoincrement(rs.getString("IS_AUTOINCREMENT"));
                    column.setIsGeneratedcolumn(rs.getString("IS_GENERATEDCOLUMN"));
                }

                column.setTable(table);
                columns.add(column);
            }

            Column[] var12 = (Column[])columns.toArray(new Column[columns.size()]);
            return var12;
        } catch (SQLException var10) {
            log.error("calculateColumns：", var10);
        } finally {
            this.closeResultSet(rs);
        }

        return null;
    }

    private void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException var3) {
                ;
            }
        }

    }
}
