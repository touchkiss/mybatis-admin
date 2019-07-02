package com.touchkiss.mybatis.generator.template;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import com.google.common.collect.Lists;
import com.touchkiss.mybatis.generator.internal.FullyQualifiedTable;
import com.touchkiss.mybatis.generator.internal.GeneratorContext;
import com.touchkiss.mybatis.generator.model.Column;
import com.touchkiss.mybatis.generator.model.TableConfig;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class GeneratorBase implements Generator {
    protected TableConfig tableConfig;
    protected GeneratorContext context;
    protected FullyQualifiedTable fullyQualifiedTable;

    public GeneratorBase() {
    }

    @Override
    public void setTableConfig(TableConfig tableConfig) {
        this.tableConfig = tableConfig;
    }

    @Override
    public void setContext(GeneratorContext context) {
        this.context = context;
    }

    @Override
    public void setFullyQualifiedTable(FullyQualifiedTable fullyQualifiedTable) {
        this.fullyQualifiedTable = fullyQualifiedTable;
    }

    protected File getFilePath(String packageName, String fileName) {
        return this.getFilePath((String)null, packageName, fileName);
    }

    protected File getFilePath(String rootPath, String packageName, String fileName) {
        if (StringUtils.isBlank(rootPath)) {
            rootPath = this.context.getGeneratorRootPath();
        }

        if (!StringUtils.isNotBlank(packageName)) {
            File file = new File(rootPath, fileName);
            return file;
        } else {
            StringBuilder sb = new StringBuilder();
            String[] packages = packageName.split("\\.");

            for(int i = 0; i < packages.length; ++i) {
                sb.append(File.separator);
                sb.append(packages[i].trim());
            }

            sb.append(File.separator);
            sb.append(fileName);
            File file = new File(rootPath, sb.toString());
            return file;
        }
    }

    protected List<Column> getPrimaryKeyColumn(List<Column> columns) {
        List<Column> primaryKeyColumns = new ArrayList();
        Iterator var3;
        if (this.tableConfig.getColumnKeys() != null && !this.tableConfig.getColumnKeys().isEmpty()) {
            var3 = this.tableConfig.getColumnKeys().iterator();

            while(var3.hasNext()) {
                String columnKey = (String)var3.next();
                Column column = this.fullyQualifiedTable.getColumn(this.tableConfig.getTableName(), columnKey);
                if (column == null) {
                    throw new RuntimeException("Can't find column : " + columnKey);
                }

                primaryKeyColumns.add(column);
            }
        } else {
            var3 = columns.iterator();

            while(var3.hasNext()) {
                Column column = (Column)var3.next();
                if (column.getIsPrimaryKey()) {
                    primaryKeyColumns.add(column);
                }
            }
        }

        return primaryKeyColumns.isEmpty() ? null : primaryKeyColumns;
    }

    protected List<Column> getForeignColumns(List<Column> columns) {
        List<Column> foreignColums = null;
        if (CollectionUtils.isNotEmpty(columns)) {
            foreignColums = (List)columns.stream().filter((t) -> {
                return t.getIsForeignKey();
            }).collect(Collectors.toCollection(ArrayList::new));
            if (foreignColums.size() == 0) {
                foreignColums = null;
            }
        }

        return foreignColums;
    }

    protected List<Column> getFilterColumn() {
        List<Column> columns = Lists.newArrayList(this.fullyQualifiedTable.getColumns(this.tableConfig.getTableName()));
        return (List)(CollectionUtils.isNotEmpty(this.tableConfig.getIgnoreColumns()) ? (List)columns.stream().filter((t) -> {
            Iterator var2 = this.tableConfig.getIgnoreColumns().iterator();

            String c;
            do {
                if (!var2.hasNext()) {
                    return true;
                }

                c = (String)var2.next();
            } while(!t.getColumnName().equalsIgnoreCase(c));

            return false;
        }).collect(Collectors.toCollection(ArrayList::new)) : columns);
    }
}
