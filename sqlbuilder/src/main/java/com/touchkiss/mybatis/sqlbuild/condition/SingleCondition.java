package com.touchkiss.mybatis.sqlbuild.condition;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import com.touchkiss.mybatis.sqlbuild.GlobalConfig;
import com.touchkiss.mybatis.sqlbuild.condition.original.ICondition;
import com.touchkiss.mybatis.sqlbuild.condition.original.PreparedCondition;
import com.touchkiss.mybatis.sqlbuild.exceptions.NotSuperOperatorException;
import com.touchkiss.mybatis.sqlbuild.keyword.CompareOperator;
import com.touchkiss.mybatis.sqlbuild.query.QColumn;
import com.touchkiss.mybatis.sqlbuild.selector.Field;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SingleCondition implements ICondition {
    private Field field;
    private CompareOperator operator;
    private Object[] values;

    public SingleCondition(QColumn<?, ?> field, Object values) {
        this.operator = CompareOperator.EQUAL;
        this.setField(field);
        this.setValues(values);
    }

    public SingleCondition(QColumn<?, ?> field, CompareOperator operator) {
        this.operator = CompareOperator.EQUAL;
        if (operator != CompareOperator.IS_NOT_NULL && operator != CompareOperator.IS_NULL) {
            throw new NotSuperOperatorException("not super " + operator.name() + "operator");
        } else {
            this.setField(field);
            this.operator = operator;
        }
    }

    public SingleCondition(QColumn<?, ?> field, CompareOperator operator, Object values) {
        this.operator = CompareOperator.EQUAL;
        if (values == null && operator != CompareOperator.IS_NOT_NULL && operator != CompareOperator.IS_NULL) {
            throw new NotSuperOperatorException("values is null, not super " + operator.name() + "operator");
        } else {
            this.setField(field);
            this.setValues(values);
            this.operator = operator;
        }
    }

    @Override
    public List<PreparedCondition> prepare() {
        List<PreparedCondition> ret = new ArrayList();
        CompareOperator operator = this.operator;
        if (this.values != null && this.values.length > 0) {
            int j = this.values.length;
            if (j > 0) {
                PreparedCondition pc = new PreparedCondition();
                boolean bracket = false;
                switch(operator) {
                case IN:
                    if (j == 1) {
                        operator = CompareOperator.EQUAL;
                    } else {
                        bracket = true;
                    }
                    break;
                case NOT_IN:
                    if (j == 1) {
                        operator = CompareOperator.NOT_EQUAL;
                    } else {
                        bracket = true;
                    }
                }

                ret.add(pc.setPrefix(this.fieldToString(this.field) + operator).setValue(this.values[0]));
                if (bracket) {
                    pc.setPrefix(pc.getPrefix() + "(");

                    for(int i = 1; i < j; ++i) {
                        pc = new PreparedCondition();
                        ret.add(pc.setPrefix(",").setValue(this.values[i]));
                    }

                    pc.setSuffix(")");
                }
            }
        } else if (CompareOperator.IS_NULL.equals(operator) || CompareOperator.IS_NOT_NULL.equals(operator)) {
            ret.add((new PreparedCondition()).setPrefix(this.fieldToString(this.field) + operator));
        }

        return ret;
    }

    public final Field getField() {
        return this.field;
    }

    private final SingleCondition setField(QColumn<?, ?> column) {
        this.field = new Field(column.getColumnName());
        this.field.setTableName(column.getTable().getTableName());
        return this;
    }

    private final String fieldToString(Field field) {
        StringBuilder sBuff = new StringBuilder(50);
        if (!field.getIsFunction() && field.getTable() != null && StringUtils.isNotEmpty(field.getTable().getAliasTable())) {
            sBuff.append(field.getTable().getAliasTable());
            sBuff.append(".");
        }

        if (GlobalConfig.isUseQuotes()) {
            sBuff.append("\"").append(field.getField()).append("\"");
        } else {
            sBuff.append(field.getField());
        }

        return sBuff.toString();
    }

    private final SingleCondition setValues(Object value) {
        if (value != null && value.getClass().isArray()) {
            this.values = (Object[])((Object[])value);
        } else if (value != null && value instanceof Collection) {
            this.values = ((Collection)value).toArray();
        } else if (value != null) {
            this.values = new Object[]{value};
        }

        return this;
    }
}