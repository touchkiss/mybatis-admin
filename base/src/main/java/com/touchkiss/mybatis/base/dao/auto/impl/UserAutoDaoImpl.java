package com.touchkiss.mybatis.base.dao.auto.impl;

import com.touchkiss.mybatis.sqlbuild.Handle;
import com.touchkiss.mybatis.sqlbuild.condition.ManyCondition;
import com.touchkiss.mybatis.sqlbuild.condition.original.ICondition;
import com.touchkiss.mybatis.sqlbuild.dao.impl.BaseAudoDaoImpl;
import com.touchkiss.mybatis.sqlbuild.keyword.Sort;
import com.touchkiss.mybatis.sqlbuild.query.QColumn;
import com.touchkiss.mybatis.sqlbuild.selector.SelectMetadata;
import com.touchkiss.mybatis.sqlbuild.selector.SelectResolver;
import com.touchkiss.mybatis.sqlbuild.selector.Selector;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.touchkiss.mybatis.base.bean.User;
import com.touchkiss.mybatis.base.dao.auto.UserAutoDao;
import com.touchkiss.mybatis.base.mapper.auto.UserAutoMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;


/**
 * .dao层接口实现
 *
 * @author Touchkiss
 **/
@Component
@SuppressWarnings({ "unchecked", "rawtypes" })
public class UserAutoDaoImpl extends BaseAudoDaoImpl<User> implements UserAutoDao {
    @Resource(name = "userAutoMapper")
    private UserAutoMapper mapper;

    /**
     * 根据条件查询
     *
     * @param conditions
     * @return
     */
    @Override
    public List<User> select(ICondition... conditions) {
        ManyCondition manyCondition = new ManyCondition();
        for (int i = 0; i < conditions.length; i++) {
            manyCondition.add(conditions[i]);
        }
        return this.mapper.select(new SelectResolver(manyCondition, UserAutoDao.TABLE, UserAutoDao.ALL_FIELDS).toMetadata());
    }

    @Override
    public List<User> select(Selector<User> selector) {
        SelectResolver resolver = new SelectResolver(selector, UserAutoDao.TABLE, UserAutoDao.ALL_FIELDS);
        SelectMetadata metadata = resolver.toMetadata();
        return this.mapper.select(metadata);
    }

    @Override
    public List<User> select(User bean) {
        ManyCondition conditions = UserAutoDao.SELECT_WHERE_HANDLE.handle(bean);
        SelectResolver resolver = new SelectResolver(conditions, UserAutoDao.TABLE, UserAutoDao.ALL_FIELDS);
        SelectMetadata metadata = resolver.toMetadata();
        return this.mapper.select(metadata);
    }

    @Override
    public List<User> select(Map<String, String[]> map) {
        Selector<User> selector = new Selector<>();
        for (QColumn<User, Object> field : UserAutoDao.ALL_FIELDS) {
            if (map.containsKey(field.getColumnName())) {
                selector.where(field, map.get(field.getColumnName())[0]);
            }
        }
        return this.select(selector);
    }


    @Override
    public List<User> selectById(String uid) {
        return select(UserAutoDao.uid.toEqCondition(Integer.parseInt(uid)));
    }

    @Override
    public <Output> List<Output> select(Selector<User> selector, Handle<Map, Output> handle) {
        SelectResolver resolver = new SelectResolver(selector, UserAutoDao.TABLE, UserAutoDao.ALL_FIELDS);
        List maps = this.mapper.selectForListMap(resolver.toMetadata());
        List<Output> ret = new ArrayList<>(maps.size());
        for (int i = 0; i < maps.size(); i++) {
            Output out = handle.handle((Map) maps.get(i));
            ret.add(out);
        }
        return ret;
    }

    @Override
    public List<User> selectAll(QColumn column, Sort sort){
        Selector selector = new Selector();
        selector.order(column,sort);
        SelectResolver resolver = new SelectResolver(selector, UserAutoDao.TABLE, UserAutoDao.ALL_FIELDS);
        SelectMetadata metadata = resolver.toMetadata();
        return this.mapper.select(metadata);
    }

    @Override
    public <Output> Output selectRow(Selector<User> selector, Handle<Map, Output> handle) {
        SelectResolver resolver = new SelectResolver(selector, UserAutoDao.TABLE, UserAutoDao.ALL_FIELDS);
        Map map = this.mapper.selectForMap(resolver.toMetadata());
        return handle.handle(map);
    }

    @Override
    public Long selectCount(ICondition... condition) {
        Selector<User> selector = new Selector<>();
        selector.addStringField(UserAutoDao.COUNT_FIELD);
        selector.where(condition);
        SelectResolver resolver = new SelectResolver(selector, UserAutoDao.TABLE, UserAutoDao.ALL_FIELDS);
        List list = this.mapper.selectForListMap(resolver.toMetadata());
        if (list.size() > 0) {
            return UserAutoDao.COUNT_HANDLE.handle((Map) list.get(0));
        }
        return 0L;
    }

    @Override
    public Long selectCount(User bean) {
        ManyCondition conditions = UserAutoDao.SELECT_WHERE_HANDLE.handle(bean);
        return selectCount(conditions);
    }

    @Override
    public <Output> Page<Output> selectPage(Selector<User> selector, Handle<Map, Output> handle, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        SelectResolver resolver = new SelectResolver(selector, UserAutoDao.TABLE, UserAutoDao.ALL_FIELDS);
        Page maps = this.mapper.selectPageForListMap(resolver.toMetadata());
        Page<Output> ret = new Page<>();
        for (int i = 0; i < maps.size(); i++) {
            Output out = handle.handle((Map) maps.get(i));
            ret.add(out);
        }
        return ret;
    }

    @Override
    public Page<User> selectPage(Map<String, String[]> map, int pageNo, int pageSize) {
        Selector<User> selector = new Selector<>();
        for (QColumn<User, Object> field : UserAutoDao.ALL_FIELDS) {
            if (map.containsKey(field.getColumnName())) {
                selector.where(field, map.get(field.getColumnName())[0]);
            }
        }
        return selectPage(selector, pageNo, pageSize);
    }

    @Override
    public Page<User> selectPage(int pageNum, int pageSize, ICondition... condition) {
        PageHelper.startPage(pageNum, pageSize);
        Selector<User> selector = new Selector<>();
        selector.where(condition);
        return this.selectPage(selector, pageNum, pageSize);
    }

    @Override
    public Page<User> selectPage(Selector<User> selector, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        SelectResolver resolver = new SelectResolver(selector, UserAutoDao.TABLE, UserAutoDao.ALL_FIELDS);
        return this.mapper.selectPage(resolver.toMetadata());
    }

    @Override
    public Page<User> selectPage(User bean, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        SelectResolver resolver = new SelectResolver(UserAutoDao.SELECT_WHERE_HANDLE.handle(bean), UserAutoDao.TABLE, UserAutoDao.ALL_FIELDS);
        return this.mapper.selectPage(resolver.toMetadata());
    }
    //endregion

    //region write data

    @Override
    public int updateById(User bean) {
        return this.mapper.update(bean, UserAutoDao.uid.toEqCondition(bean.getUid()).prepare());
    }

    @Override
    public int updateSelectiveById(User bean) {
        return this.mapper.updateSelective(bean, UserAutoDao.uid.toEqCondition(bean.getUid()).prepare());
    }


    @Override
    public int deleteById(String uid) {
        return this.mapper.delete(UserAutoDao.uid.toEqCondition(Integer.parseInt(uid)).prepare());
    }


    @Override
    public int update(User bean, ICondition... condition) {
        ManyCondition conditions = new ManyCondition();
        for (int i = 0; i < condition.length; i++) {
            conditions.add(condition[i]);
        }
        return this.mapper.update(bean, conditions.prepare());
    }

    @Override
    public int updateSelective(User bean, ICondition... condition) {
        ManyCondition conditions = new ManyCondition();
        for (int i = 0; i < condition.length; i++) {
            conditions.add(condition[i]);
        }
        return this.mapper.updateSelective(bean, conditions.prepare());
    }

    @Override
    public int delete(ICondition... condition) {
        ManyCondition conditions = new ManyCondition();
        for (int i = 0; i < condition.length; i++) {
            conditions.add(condition[i]);
        }
        return this.mapper.delete(conditions.prepare());
    }
    //endregion
}