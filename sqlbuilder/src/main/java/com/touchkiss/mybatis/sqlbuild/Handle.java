package com.touchkiss.mybatis.sqlbuild;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

@FunctionalInterface
public interface Handle<Input, Output> {
    Output handle(Input var1);
}
