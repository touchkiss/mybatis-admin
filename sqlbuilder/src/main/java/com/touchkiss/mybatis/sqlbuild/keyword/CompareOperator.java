package com.touchkiss.mybatis.sqlbuild.keyword;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

public enum CompareOperator {
    EQUAL("="),
    AND("&"),
    NOT_EQUAL("!="),
    GT(">"),
    LT("<"),
    GET(">="),
    LET("<="),
    LIKE(" LIKE "),
    NOT_LIKE(" NOT LIKE "),
    IN(" IN "),
    NOT_IN(" NOT IN "),
    IS_NULL(" IS NULL "),
    IS_NOT_NULL(" IS NOT NULL "),
    REGEXP(" REGEXP ");

    String s;

    private CompareOperator(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return this.s;
    }
}
