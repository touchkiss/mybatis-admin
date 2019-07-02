package com.touchkiss.mybatis.sqlbuild.selector;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import com.touchkiss.mybatis.sqlbuild.keyword.Join;
import com.touchkiss.mybatis.sqlbuild.keyword.SqlLogic;

import java.util.Arrays;

public class JoinTable extends Table {
    private Join join;
    private SqlLogic sqlLogic;
    private JoinCondition[] conditions;

    public JoinTable() {
        this.join = Join.INNER;
        this.sqlLogic = SqlLogic.AND;
    }

    public Join getJoin() {
        return this.join;
    }

    public void setJoin(Join join) {
        this.join = join;
    }

    public JoinCondition[] getConditions() {
        return this.conditions != null && this.conditions.length > 0 ? this.conditions : null;
    }

    public void setConditions(JoinCondition[] conditions) {
        this.conditions = conditions;
    }

    public String getSqlLogic() {
        return this.sqlLogic.name();
    }

    public void setSqlLogic(SqlLogic sqlLogic) {
        this.sqlLogic = sqlLogic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            if (!super.equals(o)) {
                return false;
            } else {
                JoinTable joinTable = (JoinTable)o;
                if (this.join != joinTable.join) {
                    return false;
                } else {
                    return this.sqlLogic != joinTable.sqlLogic ? false : Arrays.equals(this.conditions, joinTable.conditions);
                }
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (this.join != null ? this.join.hashCode() : 0);
        result = 31 * result + (this.sqlLogic != null ? this.sqlLogic.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(this.conditions);
        return result;
    }
}