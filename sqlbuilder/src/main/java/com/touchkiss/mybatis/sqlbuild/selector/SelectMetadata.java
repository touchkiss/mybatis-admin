package com.touchkiss.mybatis.sqlbuild.selector;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import com.touchkiss.mybatis.sqlbuild.condition.original.PreparedCondition;

import java.util.List;

public class SelectMetadata {
    protected Boolean distinct;
    protected List<Field> fields;
    protected Table from;
    protected List<JoinTable> joinTable;
    protected List<PreparedCondition> wheres;
    protected List<SortField> sortFields;
    protected List<GroupField> groupFields;

    public SelectMetadata() {
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    public List<Field> getFields() {
        return this.fields;
    }

    public Table getFrom() {
        return this.from;
    }

    public List<JoinTable> getJoinTable() {
        return this.joinTable;
    }

    public List<PreparedCondition> getWheres() {
        return this.wheres;
    }

    public List<SortField> getSortFields() {
        return this.sortFields;
    }

    public List<GroupField> getGroupFields() {
        return this.groupFields;
    }
}