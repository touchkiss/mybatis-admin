package com.touchkiss.mybatis.sqlbuild.dao.impl;

import com.touchkiss.mybatis.sqlbuild.dao.ReadBaseDao;
import com.touchkiss.mybatis.sqlbuild.dao.WriteBaseDao;
import com.touchkiss.mybatis.sqlbuild.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author Touchkiss
 * @create: 2019-06-20 18:26
 */
public abstract class BaseAudoDaoImpl<T> implements ReadBaseDao<T>, WriteBaseDao<T> {
    @Autowired
    private BaseMapper<T> mapper;

    @Override
    public int insert(T bean) {
        return this.mapper.insert(bean);
    }

    @Override
    public int insertSelective(T bean) {
        return this.mapper.insertSelective(bean);
    }

    @Override
    public List<T> selectAll() {
        return this.mapper.selectAll();
    }
}
