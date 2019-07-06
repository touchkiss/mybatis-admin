package com.touchkiss.mybatis.sqlbuild.selector;

import com.touchkiss.mybatis.sqlbuild.condition.ManyCondition;
import com.touchkiss.mybatis.sqlbuild.condition.SingleCondition;
import com.touchkiss.mybatis.sqlbuild.condition.original.ICondition;
import com.touchkiss.mybatis.sqlbuild.exceptions.ResolverException;
import com.touchkiss.mybatis.sqlbuild.query.QColumn;
import com.touchkiss.mybatis.sqlbuild.query.QTable;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

public class SelectResolver {
    private static final char[] DIC = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'i', 'f', 'g', 'u', 'v', 'w', 'i', 'x', 'z'};
    private int tableAliasIndex = 0;
    private final SelectMetadata selectMetadata = new SelectMetadata();

    public <TABLE> SelectResolver(Selector<TABLE> selector, QTable<TABLE> defaultTable, QColumn<TABLE, ?>[] defaultColumns) {
        this.init(selector, defaultTable, defaultColumns);
    }

    private <TABLE> void init(Selector<TABLE> selector, QTable<TABLE> defaultTable, QColumn<TABLE, ?>[] defaultColumns) {
        if (selector.getTable() == null) {
            selector.setTable(defaultTable);
        }

        if (selector.getFields().isEmpty()) {
            selector.addField(defaultColumns);
        }

        if (!selector.getJoinFields().isEmpty()) {
            for(int i = 0; i < selector.getJoinFields().size(); ++i) {
                selector.getFields().add(selector.getJoinFields().get(i));
            }
        }

        Map<String, Table> tableMap = this.initName(selector);
        this.selectMetadata.from = selector.getTable();
        this.selectMetadata.distinct=selector.getDistinct();
        this.selectMetadata.fields = new ArrayList(selector.getFields());
        List<JoinTable> joinTables = selector.getJoinTables();
        Iterator var6;
        if (!joinTables.isEmpty()) {
            var6 = joinTables.iterator();

            while(var6.hasNext()) {
                JoinTable joinTable = (JoinTable)var6.next();
                JoinCondition[] joinConditions = joinTable.getConditions();

                for(int i = 0; i < joinConditions.length; ++i) {
                    if (joinConditions[i].getIsRelateField()) {
                        String relateTableName = joinConditions[i].getRelateTable();
                        Table relateTable = (Table)tableMap.get(relateTableName);
                        if (relateTable == null) {
                            throw new ResolverException("Can't find the table " + relateTableName + ", Check the selector table join.");
                        }

                        joinConditions[i].setRelateTableAlias(relateTable.getAliasTable());
                    }
                }
            }

            this.selectMetadata.joinTable = joinTables;
        }

        if (!selector.getWheres().isEmpty()) {
            this.recurCondition(selector.getWheres(), tableMap);
            this.selectMetadata.wheres = selector.getWheres().prepare();
            if (this.selectMetadata.wheres.isEmpty()) {
                this.selectMetadata.wheres = null;
            }
        }

        Table table;
        if (!selector.getOrderFields().isEmpty()) {
            var6 = selector.getOrderFields().iterator();

            while(var6.hasNext()) {
                SortField sortField = (SortField)var6.next();
                if (sortField.getTable() == null && StringUtils.isNotEmpty(sortField.getTableName())) {
                    table = (Table)tableMap.get(sortField.getTableName());
                    if (table == null) {
                        throw new ResolverException("Can't find the table " + sortField.getTableName() + ", sort field is invalid.");
                    }

                    sortField.setTable(table);
                }
            }

            this.selectMetadata.sortFields = selector.getOrderFields();
        }

        if (!selector.getGroupFields().isEmpty()) {
            var6 = selector.getGroupFields().iterator();

            while(var6.hasNext()) {
                GroupField groupField = (GroupField)var6.next();
                if (groupField.getTable() == null && StringUtils.isNotEmpty(groupField.getTableName())) {
                    table = (Table)tableMap.get(groupField.getTableName());
                    if (table == null) {
                        throw new ResolverException("Can't find the table " + groupField.getTableName() + ", group field is invalid.");
                    }

                    groupField.setTable(table);
                }
            }

            this.selectMetadata.groupFields = selector.getGroupFields();
        }

    }

    private void recurCondition(ManyCondition conditions, Map<String, Table> tableMap) {
        Iterator var3 = conditions.iterator();

        while(var3.hasNext()) {
            ICondition condition = (ICondition)var3.next();
            if (condition instanceof ManyCondition) {
                this.recurCondition((ManyCondition)condition, tableMap);
            } else if (condition instanceof SingleCondition) {
                SingleCondition singleCondition = (SingleCondition)condition;
                Field field = singleCondition.getField();
                if (field.getTable() == null && StringUtils.isNotEmpty(field.getTableName())) {
                    Table table = (Table)tableMap.get(field.getTableName());
                    if (table == null) {
                        throw new ResolverException("Can't find the table " + field.getTableName() + ", field is invalid.");
                    }

                    field.setTable(table);
                }
            }
        }

    }

    private <TABLE> Map<String, Table> initName(Selector<TABLE> selector) {
        Map<String, Table> tableMap = new LinkedHashMap();
        Table fromTable = selector.getTable();
        if (StringUtils.isEmpty(fromTable.getAliasTable())) {
            fromTable.setAliasTable(this.getTableAliasName());
        }

        tableMap.put(fromTable.getTable(), fromTable);
        List<JoinTable> joinTables = selector.getJoinTables();

        for(int i = 0; i < joinTables.size(); ++i) {
            JoinTable joinTable = (JoinTable)joinTables.get(i);
            if (StringUtils.isEmpty(joinTable.getAliasTable())) {
                joinTable.setAliasTable(this.getTableAliasName());
            }

            tableMap.put(joinTable.getTable(), joinTable);
        }

        Set<Field> fields = selector.getFields();
        Iterator var10 = fields.iterator();

        while(true) {
            while(var10.hasNext()) {
                Field field = (Field)var10.next();
                if (field.getTable() == null && StringUtils.isNotEmpty(field.getTableName())) {
                    Table table = (Table)tableMap.get(field.getTableName());
                    field.setTable(table);
                } else if (field.getTable() == null && StringUtils.isEmpty(field.getTableName())) {
                    field.setTable(fromTable);
                    field.setTableName(fromTable.getTable());
                }
            }

            return tableMap;
        }
    }

    public <TABLE> SelectResolver(ICondition condition, QTable<TABLE> defaultTable, QColumn<TABLE, ?>[] defaultColumns) {
        this.selectMetadata.from = new Table();
        this.selectMetadata.from.setTable(defaultTable.getTableName());
        this.selectMetadata.from.setSchema(defaultTable.getSchema());
        this.selectMetadata.fields = new ArrayList();

        for(int i = 0; i < defaultColumns.length; ++i) {
            Field field = new Field(this.selectMetadata.from, defaultColumns[i].getColumnName());
            this.selectMetadata.fields.add(field);
        }

        this.selectMetadata.wheres = condition.prepare();
        if (this.selectMetadata.wheres.isEmpty()) {
            this.selectMetadata.wheres = null;
        }

    }

    public SelectMetadata toMetadata() {
        return this.selectMetadata;
    }

    private String getTableAliasName() {
        String ret;
        if (this.tableAliasIndex > DIC.length - 1) {
            ret = "t" + (this.tableAliasIndex - DIC.length - 1);
        } else {
            ret = String.valueOf(DIC[this.tableAliasIndex]);
        }

        ++this.tableAliasIndex;
        return ret;
    }
}
