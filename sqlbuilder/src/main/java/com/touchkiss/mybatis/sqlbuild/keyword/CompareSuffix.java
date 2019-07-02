package com.touchkiss.mybatis.sqlbuild.keyword;

/**
 * @Author Touchkiss
 * @create: 2019-06-25 19:10
 */
public enum CompareSuffix {
    eq("EQUAL"),
    cn("LIKE"),
    ne("NOT_EQUAL"),
    gt("GT"),
    lt("LT"),
    get("GET"),
    let("LET"),
    nc("NOT_LIKE"),
    in("IN"),
    ni("NOT_IN"),
    sn("IS_NULL"),
    ns("IS_NOT_NULL"),
    re("REGEXP");

    String s;
    private CompareSuffix(String s){this.s=s;}

    @Override
    public java.lang.String toString() {
        return this.s;
    }
}
