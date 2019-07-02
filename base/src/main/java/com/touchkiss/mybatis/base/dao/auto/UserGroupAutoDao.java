package com.touchkiss.mybatis.base.dao.auto;

import com.touchkiss.mybatis.sqlbuild.Handle;
import com.touchkiss.mybatis.sqlbuild.condition.ManyCondition;
import com.touchkiss.mybatis.sqlbuild.dao.ReadBaseDao;
import com.touchkiss.mybatis.sqlbuild.dao.WriteBaseDao;
import com.touchkiss.mybatis.sqlbuild.query.QColumn;
import com.touchkiss.mybatis.sqlbuild.query.QTable;
import com.touchkiss.mybatis.base.bean.UserGroup;
import java.util.List;
import java.util.Date;

/**
 * 用户组.dao层接口
 *
 * @author Touchkiss
 **/
public interface UserGroupAutoDao extends ReadBaseDao<UserGroup>, WriteBaseDao<UserGroup> {
    QTable<UserGroup> TABLE = new QTable<>("user_group");   //用户组
    //region filds
    QColumn<UserGroup, Long> id = new QColumn<>(TABLE, "id");    //id
    QColumn<UserGroup, String> groupName = new QColumn<>(TABLE, "group_name");    //组名
    QColumn<UserGroup, Date> lastModifyTime = new QColumn<>(TABLE, "last_modify_time");    //修改时间
    QColumn<UserGroup, Date> createTime = new QColumn<>(TABLE, "create_time");    //创建时间
    //所有字段
    QColumn<UserGroup, Object>[] ALL_FIELDS = new QColumn[]{ id , groupName , lastModifyTime , createTime};
    //endregion

    //查询处理 handle
    Handle<UserGroup, ManyCondition> SELECT_WHERE_HANDLE = (bean) -> {
        ManyCondition conditions = new ManyCondition();
        if (bean.getId() != null) {
            conditions.add(id.toEqCondition(bean.getId()));
        }
        if (bean.getGroupName() != null) {
            conditions.add(groupName.toEqCondition(bean.getGroupName()));
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