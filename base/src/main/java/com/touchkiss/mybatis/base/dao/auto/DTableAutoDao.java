package com.touchkiss.mybatis.base.dao.auto;

import com.touchkiss.mybatis.sqlbuild.Handle;
import com.touchkiss.mybatis.sqlbuild.condition.ManyCondition;
import com.touchkiss.mybatis.sqlbuild.dao.ReadBaseDao;
import com.touchkiss.mybatis.sqlbuild.dao.WriteBaseDao;
import com.touchkiss.mybatis.sqlbuild.query.QColumn;
import com.touchkiss.mybatis.sqlbuild.query.QTable;
import com.touchkiss.mybatis.base.bean.DTable;
import java.util.List;
import java.util.Date;

/**
 * .dao层接口
 *
 * @author Touchkiss
 **/
public interface DTableAutoDao extends ReadBaseDao<DTable>, WriteBaseDao<DTable> {
    QTable<DTable> TABLE = new QTable<>("d_table");   //
    //region filds
    QColumn<DTable, Integer> id = new QColumn<>(TABLE, "id");    //
    QColumn<DTable, Date> pid = new QColumn<>(TABLE, "pid");    //
    QColumn<DTable, String> name = new QColumn<>(TABLE, "name");    //
    //所有字段
    QColumn<DTable, Object>[] ALL_FIELDS = new QColumn[]{ id , pid , name};
    //endregion

    //查询处理 handle
    Handle<DTable, ManyCondition> SELECT_WHERE_HANDLE = (bean) -> {
        ManyCondition conditions = new ManyCondition();
        if (bean.getId() != null) {
            conditions.add(id.toEqCondition(bean.getId()));
        }
        if (bean.getPid() != null) {
            conditions.add(pid.toEqCondition(bean.getPid()));
        }
        if (bean.getName() != null) {
            conditions.add(name.toEqCondition(bean.getName()));
        }
        return conditions;
    };


    /**
    * 根据主键 查询
    */
    List<DTable> selectByPid(Date pid);

    /**
    * 根据主键 更新
    */
    int updateByPid(DTable bean);

    /**
    * 根据主键 更新指定字段
    */
    int updateSelectiveByPid(DTable bean);

    /**
    * 根据主键 删除
    */
    int deleteByPid(Date pid);

    /**
    * 根据主键 查询
    */
    List<DTable> selectByIdAndPid(Integer id, Date pid);

    /**
    * 根据主键 更新
    */
    int updateByIdAndPid(DTable bean);

    /**
    * 根据主键 更新指定字段
    */
    int updateSelectiveByIdAndPid(DTable bean);

    /**
    * 根据主键 删除
    */
    int deleteByIdAndPid(Integer id, Date pid);
}