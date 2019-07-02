package com.touchkiss.mybatis.sqlbuild.condition.original;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import java.util.List;

public interface ICondition {
    List<PreparedCondition> prepare();
}
