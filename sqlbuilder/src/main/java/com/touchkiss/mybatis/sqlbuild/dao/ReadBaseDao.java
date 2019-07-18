package com.touchkiss.mybatis.sqlbuild.dao;

import com.github.pagehelper.Page;
import com.touchkiss.mybatis.sqlbuild.Handle;
import com.touchkiss.mybatis.sqlbuild.condition.original.ICondition;
import com.touchkiss.mybatis.sqlbuild.keyword.Sort;
import com.touchkiss.mybatis.sqlbuild.query.QColumn;
import com.touchkiss.mybatis.sqlbuild.selector.Selector;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

public interface ReadBaseDao<T> {
    String COUNT_FIELD = "COUNT(1) AS ROW_COUNT";
    Handle<Map, Long> COUNT_HANDLE = new Handle<Map, Long>() {
        @Override
        public Long handle(Map map) {
            if (map.isEmpty()) {
                return 0L;
            } else {
                Iterator it = map.values().iterator();
                if (it.hasNext()) {
                    Object obj = it.next();
                    if (obj == null) {
                        return 0L;
                    } else if (obj instanceof Long) {
                        return (Long) obj;
                    } else {
                        return obj instanceof Integer ? ((Integer) obj).longValue() : Long.parseLong(obj.toString());
                    }
                } else {
                    return 0L;
                }
            }
        }
    };

    T selectOneByID(Object...ids);

    List<T> selectAll();

    List<T> selectAll(QColumn column, Sort sort);

    List<T> select(ICondition... iConditions);

    List<T> select(Selector<T> selector);

    List<T> select(T bean);

    List<T> select(Map<String, String[]> map);

    <Output> List<Output> select(Selector<T> selector, Handle<Map, Output> handle);

    <Output> Page<Output> selectPage(Selector<T> selector, Handle<Map, Output> handle, int pageNo, int pageSize);

    Long selectCount(ICondition... iConditions);

    Long selectCount(T bean);

    Page<T> selectPage(int pageNo, int pageSize, ICondition... iConditions);

    Page<T> selectPage(Selector<T> selector, int pageNo, int pageSize);

    Page<T> selectPage(T bean, int pageNo, int pageSize);

    Page<T> selectPage(Map<String, String[]> map, int pageNo, int pageSize);

    T selectOne(T bean);

    T selectOne(Selector<T> selector);
}