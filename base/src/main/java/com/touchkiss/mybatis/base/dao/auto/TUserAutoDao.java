package com.touchkiss.mybatis.base.dao.auto;

import com.touchkiss.mybatis.sqlbuild.Handle;
import com.touchkiss.mybatis.sqlbuild.condition.ManyCondition;
import com.touchkiss.mybatis.sqlbuild.dao.ReadBaseDao;
import com.touchkiss.mybatis.sqlbuild.dao.WriteBaseDao;
import com.touchkiss.mybatis.sqlbuild.query.QColumn;
import com.touchkiss.mybatis.sqlbuild.query.QTable;
import com.touchkiss.mybatis.base.bean.TUser;
import java.util.List;
import java.util.Date;

/**
 * 用户表.dao层接口
 *
 * @author Touchkiss
 **/
public interface TUserAutoDao extends ReadBaseDao<TUser>, WriteBaseDao<TUser> {
    QTable<TUser> TABLE = new QTable<>("t_user");   //用户表
    //region filds
    QColumn<TUser, Long> id = new QColumn<>(TABLE, "id");    //
    QColumn<TUser, Long> groupId = new QColumn<>(TABLE, "group_id");    //
    QColumn<TUser, String> userName = new QColumn<>(TABLE, "user_name");    //
    QColumn<TUser, Integer> age = new QColumn<>(TABLE, "age");    //
    QColumn<TUser, Date> lastModifyTime = new QColumn<>(TABLE, "last_modify_time");    //
    QColumn<TUser, Date> createTime = new QColumn<>(TABLE, "create_time");    //
    //所有字段
    QColumn<TUser, Object>[] ALL_FIELDS = new QColumn[]{ id , groupId , userName , age , lastModifyTime , createTime};
    //endregion

    //查询处理 handle
    Handle<TUser, ManyCondition> SELECT_WHERE_HANDLE = (bean) -> {
        ManyCondition conditions = new ManyCondition();
        if (bean.getId() != null) {
            conditions.add(id.toEqCondition(bean.getId()));
        }
        if (bean.getGroupId() != null) {
            conditions.add(groupId.toEqCondition(bean.getGroupId()));
        }
        if (bean.getUserName() != null) {
            conditions.add(userName.toEqCondition(bean.getUserName()));
        }
        if (bean.getAge() != null) {
            conditions.add(age.toEqCondition(bean.getAge()));
        }
        if (bean.getLastModifyTime() != null) {
            conditions.add(lastModifyTime.toEqCondition(bean.getLastModifyTime()));
        }
        if (bean.getCreateTime() != null) {
            conditions.add(createTime.toEqCondition(bean.getCreateTime()));
        }
        return conditions;
    };

}