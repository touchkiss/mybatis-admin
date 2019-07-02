package com.touchkiss.mybatis.base.dao.auto;

import com.touchkiss.mybatis.sqlbuild.Handle;
import com.touchkiss.mybatis.sqlbuild.condition.ManyCondition;
import com.touchkiss.mybatis.sqlbuild.dao.ReadBaseDao;
import com.touchkiss.mybatis.sqlbuild.dao.WriteBaseDao;
import com.touchkiss.mybatis.sqlbuild.query.QColumn;
import com.touchkiss.mybatis.sqlbuild.query.QTable;
import com.touchkiss.mybatis.base.bean.Category;
import java.util.List;

/**
 * .dao层接口
 *
 * @author Touchkiss
 **/
public interface CategoryAutoDao extends ReadBaseDao<Category>, WriteBaseDao<Category> {
    QTable<Category> TABLE = new QTable<>("category");   //
    //region filds
    QColumn<Category, Integer> id = new QColumn<>(TABLE, "id");    //
    QColumn<Category, Integer> parentid = new QColumn<>(TABLE, "parentid");    //
    QColumn<Category, String> name = new QColumn<>(TABLE, "name");    //
    QColumn<Category, Boolean> top = new QColumn<>(TABLE, "top");    //
    //所有字段
    QColumn<Category, Object>[] ALL_FIELDS = new QColumn[]{ id , parentid , name , top};
    //endregion

    //查询处理 handle
    Handle<Category, ManyCondition> SELECT_WHERE_HANDLE = (bean) -> {
        ManyCondition conditions = new ManyCondition();
        if (bean.getId() != null) {
            conditions.add(id.toEqCondition(bean.getId()));
        }
        if (bean.getParentid() != null) {
            conditions.add(parentid.toEqCondition(bean.getParentid()));
        }
        if (bean.getName() != null) {
            conditions.add(name.toEqCondition(bean.getName()));
        }
        if (bean.getTop() != null) {
            conditions.add(top.toEqCondition(bean.getTop()));
        }
        return conditions;
    };

}