package com.touchkiss.mybatis.sqlbuild.service;

import com.github.pagehelper.Page;
import com.touchkiss.mybatis.sqlbuild.keyword.Sort;
import com.touchkiss.mybatis.sqlbuild.query.QColumn;
import com.touchkiss.mybatis.sqlbuild.selector.Selector;

import java.util.List;
import java.util.Map;

/**
 * @Author Touchkiss
 * @create: 2019-06-20 17:08
 */
public interface BaseService<T> {
    /**
     * 根据主键查询一条
     */
    T selectOneByID(Object... ids);

    /**
     * 根据主键删除一条
     */
    int deleteOneByID(Object... ids);

    /**
     * 根据主键更新一条
     */
    int updateOneByID(T bean, Object... ids);

    /**
     * 根据主键更新一条记录的非空字段
     */
    int updateOneSelectiveByID(T bean, Object... ids);

    /**
     * 全部插入
     */
    int insert(T bean);

    /**
     * 指定插入
     */
    int insertSelective(T bean);

    /**
     * 查询所有
     */
    List<T> selectAll();

    /**
     * 查询所有 - 支持排序
     */
    List<T> selectAll(QColumn column, Sort sort);

    /**
     * 根据Selector查询多条
     */
    List<T> select(Selector<T> selector);

    /**
     * 根据bean查询多条
     */
    List<T> select(T bean);

    /**
     * 根据map查询多条
     */
    List<T> select(Map<String, String[]> map);

    /**
     * 根据bean查询单条
     */
    T selectOne(T bean);

    /**
     * 根据Selector查询单条
     */
    T selectOne(Selector<T> selector);

    /**
     * 分页查询 - 适用于自动生成配置方法
     */
    Page<T> selectPage(Selector<T> selector, int pageNo, int pageSize);

    Page<Map> selectPageForMap(Selector<T> selector, int pageNo, int pageSize);

    /**
     * 分页查询 - 适用于手动添加查询方法
     */
    Page<T> selectPage(Map<String, String[]> map, int pageNo, int pageSize);

    /**
     * 分页查询 - 适用于手动添加查询方法
     */
    Page<T> selectPage(T bean, int pageNo, int pageSize);
}

