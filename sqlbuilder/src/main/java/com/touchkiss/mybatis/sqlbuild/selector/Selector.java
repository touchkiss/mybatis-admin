package com.touchkiss.mybatis.sqlbuild.selector;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import com.touchkiss.mybatis.sqlbuild.condition.ManyCondition;
import com.touchkiss.mybatis.sqlbuild.condition.SingleCondition;
import com.touchkiss.mybatis.sqlbuild.condition.original.ICondition;
import com.touchkiss.mybatis.sqlbuild.keyword.CompareOperator;
import com.touchkiss.mybatis.sqlbuild.keyword.Join;
import com.touchkiss.mybatis.sqlbuild.keyword.Sort;
import com.touchkiss.mybatis.sqlbuild.keyword.SqlLogic;
import com.touchkiss.mybatis.sqlbuild.query.QColumn;
import com.touchkiss.mybatis.sqlbuild.query.QTable;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class Selector<TABLE> {
    private static final int initSpace = 5;
    private final Table table = new Table();
    private Boolean distinct = null;
    private final Set<Field> fields = new LinkedHashSet(5);
    private final List<Field> joinFields = new ArrayList(5);
    private final ManyCondition wheres = new ManyCondition();
    private final List<JoinTable> joinTables = new ArrayList(5);
    private final List<SortField> orderFields = new ArrayList(5);
    private List<GroupField> groupFields = new ArrayList(5);

    public Selector() {
    }

    public Selector<TABLE> setTable(QTable<TABLE> table) {
        this.table.setTable(table.getTableName());
        if (StringUtils.isNotBlank(table.getSchema())) {
            this.table.setSchema(table.getSchema());
        }

        return this;
    }

    public Selector<TABLE> distinct(Boolean distinct) {
        this.distinct = distinct;
        return this;
    }

    public Selector<TABLE> addField(QColumn... fields) {
        for (int i = 0; i < fields.length; ++i) {
            Field field = new Field(this.table, fields[i].getColumnName());
            this.fields.add(field);
        }

        return this;
    }

    public Selector<TABLE> addStringField(String fieldExp) {
        Field field = new Field(fieldExp);
        this.fields.add(field);
        return this;
    }

    public Selector<TABLE> whereSeparator(SqlLogic sqlLogic) {
        this.wheres.setSeparator(sqlLogic);
        return this;
    }

    public <VALUE> Selector<TABLE> where(QColumn<TABLE, VALUE> column, CompareOperator operator, VALUE... value) {
        this.wheres.add(new SingleCondition(column, operator, value));
        return this;
    }

    public <VALUE> Selector<TABLE> where(QColumn<TABLE, VALUE> column, VALUE... value) {
        this.wheres.add(column.toEqCondition(value));
        return this;
    }

    public Selector<TABLE> where(ICondition... condition) {
        for (int i = 0; i < condition.length; ++i) {
            this.wheres.add(condition[i]);
        }

        return this;
    }

    public <JoinTable, VALUE> Selector<TABLE> whereByTable(QColumn<JoinTable, VALUE> joinTableColumn, VALUE... value) {
        this.wheres.add(joinTableColumn.toEqCondition(value));
        return this;
    }

    public <JoinTable, VALUE> Selector<TABLE> whereByTable(QColumn<JoinTable, VALUE> joinTableColumn, CompareOperator operator, VALUE... value) {
        this.wheres.add(new SingleCondition(joinTableColumn, operator, value));
        return this;
    }

    public <JoinTable> Selector<TABLE>.JoinBuilder<JoinTable> join(QTable<JoinTable> joinTable) {
        Selector<TABLE>.JoinBuilder<JoinTable> joinBuilder = new Selector.JoinBuilder(joinTable);
        return joinBuilder;
    }

    public Selector<TABLE> order(QColumn<TABLE, ?> column, Sort sort) {
        SortField sortField = new SortField(column.getColumnName(), sort);
        sortField.setTableName(column.getTable().getTableName());
        this.orderFields.add(sortField);
        return this;
    }

    public <JoinTable> Selector<TABLE> orderByTable(QColumn<JoinTable, ?> column, Sort sort) {
        SortField sortField = new SortField(column.getColumnName(), sort);
        sortField.setTableName(column.getTable().getTableName());
        this.orderFields.add(sortField);
        return this;
    }

    public Selector<TABLE> group(QColumn<TABLE, ?> column) {
        GroupField groupField = new GroupField(column.getColumnName());
        groupField.setTableName(column.getTable().getTableName());
        this.groupFields.add(groupField);
        return this;
    }

    public <JoinTable> Selector<TABLE> groupByTable(QColumn<JoinTable, ?> column) {
        GroupField groupField = new GroupField(column.getColumnName());
        groupField.setTableName(column.getTable().getTableName());
        this.groupFields.add(groupField);
        return this;
    }

    protected Table getTable() {
        return this.table != null && !StringUtils.isBlank(this.table.getTable()) ? this.table : null;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    protected List<Field> getJoinFields() {
        return this.joinFields;
    }

    protected List<JoinTable> getJoinTables() {
        return this.joinTables;
    }

    protected Set<Field> getFields() {
        return this.fields;
    }

    protected ManyCondition getWheres() {
        return this.wheres;
    }

    protected List<SortField> getOrderFields() {
        return this.orderFields;
    }

    protected List<GroupField> getGroupFields() {
        return this.groupFields;
    }

    public class JoinBuilder<JoinQTable> {
        private Join join;
        private SqlLogic sqlLogic;
        private boolean isBuilder;
        private QTable<JoinQTable> joinQTable;
        private List<Field> joinFields;
        private List<Store<JoinQTable>> ons;

        private JoinBuilder(QTable<JoinQTable> joinTableQTable) {
            this.join = Join.INNER;
            this.sqlLogic = SqlLogic.AND;
            this.isBuilder = false;
            this.joinFields = new ArrayList(5);
            this.ons = new ArrayList(5);
            this.joinQTable = joinTableQTable;
        }

        public <TYPE> Selector<TABLE>.JoinBuilder<JoinQTable> on(QColumn<JoinQTable, TYPE> joinTableColumn, CompareOperator operator, QColumn<?, TYPE> targetTableColumn) {
            if (!this.isBuilder) {
                this.ons.add(new Selector.JoinBuilder.Store(joinTableColumn, operator, targetTableColumn));
            }

            return this;
        }

        public <TYPE> Selector<TABLE>.JoinBuilder<JoinQTable> on(QColumn<JoinQTable, TYPE> joinTableColumn, QColumn<?, TYPE> targetTableColumn) {
            return this.on(joinTableColumn, CompareOperator.EQUAL, targetTableColumn);
        }

        public <TYPE> Selector<TABLE>.JoinBuilder<JoinQTable> on(QColumn<JoinQTable, TYPE> joinTableColumn, CompareOperator operator, TYPE value) {
            if (!this.isBuilder) {
                this.ons.add(new Selector.JoinBuilder.Store(joinTableColumn, operator, value));
            }

            return this;
        }

        public <TYPE> Selector<TABLE>.JoinBuilder<JoinQTable> on(QColumn<JoinQTable, TYPE> joinTableColumn, TYPE value) {
            return this.on(joinTableColumn, CompareOperator.EQUAL, value);
        }

        public Selector<TABLE>.JoinBuilder<JoinQTable> addField(QColumn... fields) {
            for (int i = 0; i < fields.length; ++i) {
                Field field = new Field(fields[i].getColumnName());
                this.joinFields.add(field);
            }

            return this;
        }

        public Selector<TABLE>.JoinBuilder<JoinQTable> joinMethod(Join join) {
            if (!this.isBuilder && join != null) {
                this.join = join;
            }

            return this;
        }

        public Selector<TABLE>.JoinBuilder<JoinQTable> sqlLogic(SqlLogic sqlLogic) {
            if (!this.isBuilder && sqlLogic != null) {
                this.sqlLogic = sqlLogic;
            }

            return this;
        }

        public Selector<TABLE> ok() {
            if (!this.isBuilder) {
                this.isBuilder = true;
                JoinTable joinTable = new JoinTable();
                joinTable.setSchema(this.joinQTable.getSchema());
                joinTable.setTable(this.joinQTable.getTableName());
                joinTable.setJoin(this.join);
                joinTable.setSqlLogic(this.sqlLogic);
                JoinCondition[] joinConditions = new JoinCondition[this.ons.size()];

                int i;
                for (i = 0; i < this.ons.size(); ++i) {
                    Selector<TABLE>.JoinBuilder<JoinQTable>.Store<JoinQTable> on = (Selector.JoinBuilder.Store) this.ons.get(i);
                    if (on.value != null) {
                        JoinCondition condition = new JoinCondition(on.joinTableColumn.getColumnName(), on.value);
                        condition.setOperator(on.operator);
                        joinConditions[i] = condition;
                    } else {
                        joinConditions[i] = new JoinCondition(on.joinTableColumn.getColumnName(), on.targetTableColumn.getTable().getTableName(), on.targetTableColumn.getColumnName());
                        joinConditions[i].setOperator(on.operator);
                    }
                }

                joinTable.setConditions(joinConditions);
                Selector.this.joinTables.add(joinTable);

                for (i = 0; i < this.joinFields.size(); ++i) {
                    ((Field) this.joinFields.get(i)).setTable(joinTable);
                    Selector.this.joinFields.add(this.joinFields.get(i));
                }
            }

            return Selector.this;
        }

        private class Store<JoinQTable> {
            private CompareOperator operator;
            private QColumn<JoinQTable, ?> joinTableColumn;
            private QColumn<?, ?> targetTableColumn;
            private Object value;

            private Store(QColumn<JoinQTable, ?> joinTableColumn, CompareOperator operator, QColumn<?, ?> targetTableColumn) {
                this.joinTableColumn = joinTableColumn;
                this.operator = operator;
                this.targetTableColumn = targetTableColumn;
            }

            private Store(QColumn<JoinQTable, ?> joinTableColumn, CompareOperator operator, Object value) {
                this.joinTableColumn = joinTableColumn;
                this.operator = operator;
                this.value = value;
            }
        }
    }
}