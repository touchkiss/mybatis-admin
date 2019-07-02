package com.touchkiss.mybatis.sqlbuild.condition;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import com.touchkiss.mybatis.sqlbuild.condition.original.ICondition;
import com.touchkiss.mybatis.sqlbuild.condition.original.PreparedCondition;
import com.touchkiss.mybatis.sqlbuild.keyword.SqlLogic;

import java.util.ArrayList;
import java.util.List;

public class ManyCondition extends ArrayList<ICondition> implements ICondition {
    private static final long serialVersionUID = 916105996074806911L;
    private SqlLogic separator;

    public ManyCondition() {
        this.separator = SqlLogic.AND;
    }

    public SqlLogic getSeparator() {
        return this.separator;
    }

    public void setSeparator(SqlLogic separator) {
        this.separator = separator;
    }

    @Override
    public List<PreparedCondition> prepare() {
        List<PreparedCondition> ret = new ArrayList();
        int j = this.size();
        if (j > 0) {
            List<PreparedCondition> firstList = ((ICondition)this.get(0)).prepare();
            int firstSize = firstList.size();
            if (firstSize > 1) {
                PreparedCondition first = (PreparedCondition)firstList.get(0);
                first.appendPrefix(" (");
                ((PreparedCondition)firstList.get(firstSize - 1)).appendSuffix(")");
            }

            ret.addAll(firstList);

            for(int i = 1; i < j; ++i) {
                List<PreparedCondition> list = ((ICondition)this.get(i)).prepare();
                int size = list.size();
                if (size > 0) {
                    PreparedCondition first = (PreparedCondition)list.get(0);
                    if (size > 1) {
                        first.appendPrefix(" " + this.separator.name() + " (");
                        PreparedCondition last = (PreparedCondition)list.get(size - 1);
                        last.appendSuffix(")");
                    } else {
                        first.appendPrefix(" " + this.separator.name() + " ");
                    }

                    ret.addAll(list);
                }
            }
        }

        return ret;
    }
}
