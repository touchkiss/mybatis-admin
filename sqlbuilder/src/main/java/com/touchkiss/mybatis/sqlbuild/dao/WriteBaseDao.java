package com.touchkiss.mybatis.sqlbuild.dao;

import com.touchkiss.mybatis.sqlbuild.condition.original.ICondition;

import java.util.Map;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

public interface WriteBaseDao<T> {
    int insert(T bean);

    int insertSelective(T bean);

    int update(T bean, ICondition... iConditions);

    int updateSelective(T bean, ICondition... iConditions);

    int delete(ICondition... iConditions);

    int deleteOneByID(Object...ids);

    int updateOneByID(T bean);

    int updateOneSelectiveByID(T bean);
}
