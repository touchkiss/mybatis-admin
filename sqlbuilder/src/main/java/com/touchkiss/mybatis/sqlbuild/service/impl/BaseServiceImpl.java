package com.touchkiss.mybatis.sqlbuild.service.impl;

import com.github.pagehelper.Page;
import com.touchkiss.mybatis.sqlbuild.dao.ReadBaseDao;
import com.touchkiss.mybatis.sqlbuild.dao.WriteBaseDao;
import com.touchkiss.mybatis.sqlbuild.keyword.Sort;
import com.touchkiss.mybatis.sqlbuild.query.QColumn;
import com.touchkiss.mybatis.sqlbuild.selector.Selector;
import com.touchkiss.mybatis.sqlbuild.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @Author Touchkiss
 * @create: 2019-06-20 17:50
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    @Autowired
    private ReadBaseDao<T> readBaseDao;
    @Autowired
    private WriteBaseDao<T> writeBaseDao;

    @Override
    public int insert(T bean) {
        return writeBaseDao.insert(bean);
    }

    @Override
    public int insertSelective(T bean) {
        return writeBaseDao.insertSelective(bean);
    }

    @Override
    public List<T> selectById(String id) {
        return readBaseDao.selectById(id);
    }

    @Override
    public List<T> selectAll() {
        return readBaseDao.selectAll();
    }

    @Override
    public List<T> selectAll(QColumn column, Sort sort) {
        return readBaseDao.selectAll(column, sort);
    }

    @Override
    public List<T> select(Selector<T> selector) {
        return readBaseDao.select(selector);
    }

    @Override
    public List<T> select(T bean) {
        return readBaseDao.select(bean);
    }

    @Override
    public List<T> select(Map<String, String[]> map) {
        return readBaseDao.select(map);
    }

//    @Override
//    public T selectRow(T bean) {
//        return readBaseDao.selectRow(bean);
//    }

//    @Override
//    public T selectRow(Selector<T> selector) {
//        return readBaseDao.selectRow(selector);
//    }

    @Override
    public Page<T> selectPage(Selector<T> selector, int pageNo, int pageSize) {
        return readBaseDao.selectPage(selector, pageNo, pageSize);
    }

    @Override
    public Page<T> selectPage(Map<String, String[]> map, int pageNo, int pageSize) {
        return readBaseDao.selectPage(map, pageNo, pageSize);
    }

    @Override
    public Page<T> selectPage(T bean, int pageNo, int pageSize) {
        return readBaseDao.selectPage(bean, pageNo, pageSize);
    }

    @Override
    public int updateById(T bean) {
        return writeBaseDao.updateById(bean);
    }

    @Override
    public int updateSelectiveById(T bean) {
        return writeBaseDao.updateSelectiveById(bean);
    }

    @Override
    public int deleteById(String id) {
        return writeBaseDao.deleteById(id);
    }
}
