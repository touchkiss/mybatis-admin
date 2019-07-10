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
     * 全部插入
     */
    int insert(T bean);

    /**
     * 指定插入
     */
    int insertSelective(T bean);

    /**
     * 按id查询
     */
    List<T> selectById(String id);

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
//    T selectRow(T bean);

    /**
     * 根据Selector查询单条
     */
//    T selectRow(Selector<T> selector);

    /**
     * 分页查询 - 适用于自动生成配置方法
     */
    Page<T> selectPage(Selector<T> selector, int pageNo, int pageSize);

    /**
     * 分页查询 - 适用于手动添加查询方法
     */
    Page<T> selectPage(Map<String, String[]> map, int pageNo, int pageSize);

    /**
     * 分页查询 - 适用于手动添加查询方法
     */
    Page<T> selectPage(T bean, int pageNo, int pageSize);

    /**
     * 根据id更新所有字段
     */
    int updateById(T bean);

    /**
     * 根据id更新非空字段
     */
    int updateSelectiveById(T bean);

    /**
     * 根据id删除
     */
    int deleteById(String id);
}