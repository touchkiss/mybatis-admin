package com.touchkiss.mybatis.base.dao.auto;

import com.touchkiss.mybatis.sqlbuild.Handle;
import com.touchkiss.mybatis.sqlbuild.condition.ManyCondition;
import com.touchkiss.mybatis.sqlbuild.dao.ReadBaseDao;
import com.touchkiss.mybatis.sqlbuild.dao.WriteBaseDao;
import com.touchkiss.mybatis.sqlbuild.query.QColumn;
import com.touchkiss.mybatis.sqlbuild.query.QTable;
import com.touchkiss.mybatis.base.bean.User;
import java.util.List;

/**
 * .dao层接口
 *
 * @author Touchkiss
 **/
public interface UserAutoDao extends ReadBaseDao<User>, WriteBaseDao<User> {
    QTable<User> TABLE = new QTable<>("user");   //
    //region filds
    QColumn<User, Integer> uid = new QColumn<>(TABLE, "uid");    //
    QColumn<User, String> username = new QColumn<>(TABLE, "username");    //
    QColumn<User, String> password = new QColumn<>(TABLE, "password");    //
    //所有字段
    QColumn<User, Object>[] ALL_FIELDS = new QColumn[]{ uid , username , password};
    //endregion

    //查询处理 handle
    Handle<User, ManyCondition> SELECT_WHERE_HANDLE = (bean) -> {
        ManyCondition conditions = new ManyCondition();
        if (bean.getUid() != null) {
            conditions.add(uid.toEqCondition(bean.getUid()));
        }
        if (bean.getUsername() != null) {
            conditions.add(username.toEqCondition(bean.getUsername()));
        }
        if (bean.getPassword() != null) {
            conditions.add(password.toEqCondition(bean.getPassword()));
        }
        return conditions;
    };

}