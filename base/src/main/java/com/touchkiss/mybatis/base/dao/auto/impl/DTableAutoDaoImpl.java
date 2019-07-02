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
import com.touchkiss.mybatis.base.bean.DTable;
import com.touchkiss.mybatis.base.dao.auto.DTableAutoDao;
import com.touchkiss.mybatis.base.mapper.auto.DTableAutoMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.Date;

/**
 * .dao层接口实现
 *
 * @author Touchkiss
 **/
@Component
@SuppressWarnings({ "unchecked", "rawtypes" })
public class DTableAutoDaoImpl extends BaseAudoDaoImpl<DTable> implements DTableAutoDao {
    @Resource(name = "DTableAutoMapper")
    private DTableAutoMapper mapper;

    /**
     * 根据条件查询
     *
     * @param conditions
     * @return
     */
    @Override
    public List<DTable> select(ICondition... conditions) {
        ManyCondition manyCondition = new ManyCondition();
        for (int i = 0; i < conditions.length; i++) {
            manyCondition.add(conditions[i]);
        }
        return this.mapper.select(new SelectResolver(manyCondition, DTableAutoDao.TABLE, DTableAutoDao.ALL_FIELDS).toMetadata());
    }

    @Override
    public List<DTable> select(Selector<DTable> selector) {
        SelectResolver resolver = new SelectResolver(selector, DTableAutoDao.TABLE, DTableAutoDao.ALL_FIELDS);
        SelectMetadata metadata = resolver.toMetadata();
        return this.mapper.select(metadata);
    }

    @Override
    public List<DTable> select(DTable bean) {
        ManyCondition conditions = DTableAutoDao.SELECT_WHERE_HANDLE.handle(bean);
        SelectResolver resolver = new SelectResolver(conditions, DTableAutoDao.TABLE, DTableAutoDao.ALL_FIELDS);
        SelectMetadata metadata = resolver.toMetadata();
        return this.mapper.select(metadata);
    }

    @Override
    public List<DTable> select(Map<String, String[]> map) {
        Selector<DTable> selector = new Selector<>();
        for (QColumn<DTable, Object> field : DTableAutoDao.ALL_FIELDS) {
            if (map.containsKey(field.getColumnName())) {
                selector.where(field, map.get(field.getColumnName())[0]);
            }
        }
        return this.select(selector);
    }


    @Override
    public List<DTable> selectById(String id) {
        return select(DTableAutoDao.id.toEqCondition(Integer.parseInt(id)));
    }

    @Override
    public List<DTable> selectByPid(Date pid) {
        return select(DTableAutoDao.pid.toEqCondition(pid));
    }
    @Override
    public List<DTable> selectByIdAndPid(Integer id, Date pid) {
        ManyCondition manyCondition = new ManyCondition();
        manyCondition.add(DTableAutoDao.id.toEqCondition(id));
        manyCondition.add(DTableAutoDao.pid.toEqCondition(pid));
        return this.mapper.select(new SelectResolver(manyCondition, DTableAutoDao.TABLE, DTableAutoDao.ALL_FIELDS).toMetadata());
    }

    @Override
    public <Output> List<Output> select(Selector<DTable> selector, Handle<Map, Output> handle) {
        SelectResolver resolver = new SelectResolver(selector, DTableAutoDao.TABLE, DTableAutoDao.ALL_FIELDS);
        List maps = this.mapper.selectForListMap(resolver.toMetadata());
        List<Output> ret = new ArrayList<>(maps.size());
        for (int i = 0; i < maps.size(); i++) {
            Output out = handle.handle((Map) maps.get(i));
            ret.add(out);
        }
        return ret;
    }

    @Override
    public List<DTable> selectAll(QColumn column, Sort sort){
        Selector selector = new Selector();
        selector.order(column,sort);
        SelectResolver resolver = new SelectResolver(selector, DTableAutoDao.TABLE, DTableAutoDao.ALL_FIELDS);
        SelectMetadata metadata = resolver.toMetadata();
        return this.mapper.select(metadata);
    }

    @Override
    public <Output> Output selectRow(Selector<DTable> selector, Handle<Map, Output> handle) {
        SelectResolver resolver = new SelectResolver(selector, DTableAutoDao.TABLE, DTableAutoDao.ALL_FIELDS);
        Map map = this.mapper.selectForMap(resolver.toMetadata());
        return handle.handle(map);
    }

    @Override
    public Long selectCount(ICondition... condition) {
        Selector<DTable> selector = new Selector<>();
        selector.addStringField(DTableAutoDao.COUNT_FIELD);
        selector.where(condition);
        SelectResolver resolver = new SelectResolver(selector, DTableAutoDao.TABLE, DTableAutoDao.ALL_FIELDS);
        List list = this.mapper.selectForListMap(resolver.toMetadata());
        if (list.size() > 0) {
            return DTableAutoDao.COUNT_HANDLE.handle((Map) list.get(0));
        }
        return 0L;
    }

    @Override
    public Long selectCount(DTable bean) {
        ManyCondition conditions = DTableAutoDao.SELECT_WHERE_HANDLE.handle(bean);
        return selectCount(conditions);
    }

    @Override
    public <Output> Page<Output> selectPage(Selector<DTable> selector, Handle<Map, Output> handle, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        SelectResolver resolver = new SelectResolver(selector, DTableAutoDao.TABLE, DTableAutoDao.ALL_FIELDS);
        Page maps = this.mapper.selectPageForListMap(resolver.toMetadata());
        Page<Output> ret = new Page<>();
        for (int i = 0; i < maps.size(); i++) {
            Output out = handle.handle((Map) maps.get(i));
            ret.add(out);
        }
        return ret;
    }

    @Override
    public Page<DTable> selectPage(Map<String, String[]> map, int pageNo, int pageSize) {
        Selector<DTable> selector = new Selector<>();
        for (QColumn<DTable, Object> field : DTableAutoDao.ALL_FIELDS) {
            if (map.containsKey(field.getColumnName())) {
                selector.where(field, map.get(field.getColumnName())[0]);
            }
        }
        return selectPage(selector, pageNo, pageSize);
    }

    @Override
    public Page<DTable> selectPage(int pageNum, int pageSize, ICondition... condition) {
        PageHelper.startPage(pageNum, pageSize);
        Selector<DTable> selector = new Selector<>();
        selector.where(condition);
        return this.selectPage(selector, pageNum, pageSize);
    }

    @Override
    public Page<DTable> selectPage(Selector<DTable> selector, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        SelectResolver resolver = new SelectResolver(selector, DTableAutoDao.TABLE, DTableAutoDao.ALL_FIELDS);
        return this.mapper.selectPage(resolver.toMetadata());
    }

    @Override
    public Page<DTable> selectPage(DTable bean, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        SelectResolver resolver = new SelectResolver(DTableAutoDao.SELECT_WHERE_HANDLE.handle(bean), DTableAutoDao.TABLE, DTableAutoDao.ALL_FIELDS);
        return this.mapper.selectPage(resolver.toMetadata());
    }
    //endregion

    //region write data

    @Override
    public int updateById(DTable bean) {
        return this.mapper.update(bean, DTableAutoDao.id.toEqCondition(bean.getId()).prepare());
    }

    @Override
    public int updateSelectiveById(DTable bean) {
        return this.mapper.updateSelective(bean, DTableAutoDao.id.toEqCondition(bean.getId()).prepare());
    }

    @Override
    public int updateByPid(DTable bean) {
        return this.mapper.update(bean, DTableAutoDao.pid.toEqCondition(bean.getPid()).prepare());
    }

    @Override
    public int updateSelectiveByPid(DTable bean) {
        return this.mapper.updateSelective(bean, DTableAutoDao.pid.toEqCondition(bean.getPid()).prepare());
    }

    @Override
    public int updateByIdAndPid(DTable bean) {
        ManyCondition conditions = new ManyCondition();
        conditions.add(DTableAutoDao.id
            .toEqCondition(bean.getId()));
        conditions.add(DTableAutoDao.pid
            .toEqCondition(bean.getPid()));
        return this.mapper.update(bean, conditions.prepare());
    }

    @Override
    public int updateSelectiveByIdAndPid(DTable bean) {
        ManyCondition conditions = new ManyCondition();
        conditions.add(DTableAutoDao.id
            .toEqCondition(bean.getId()));
        conditions.add(DTableAutoDao.pid
            .toEqCondition(bean.getPid()));
        return this.mapper.updateSelective(bean, conditions.prepare());
    }

    @Override
    public int deleteById(String id) {
        return this.mapper.delete(DTableAutoDao.id.toEqCondition(Integer.parseInt(id)).prepare());
    }

    @Override
    public int deleteByPid(Date pid) {
        return this.mapper.delete(DTableAutoDao.pid.toEqCondition(pid).prepare());
    }

    @Override
    public int deleteByIdAndPid(Integer id, Date pid) {
        ManyCondition conditions = new ManyCondition();
        conditions.add(DTableAutoDao.id
            .toEqCondition(id));
        conditions.add(DTableAutoDao.pid
            .toEqCondition(pid));
        return this.mapper.delete(conditions.prepare());
    }

    @Override
    public int update(DTable bean, ICondition... condition) {
        ManyCondition conditions = new ManyCondition();
        for (int i = 0; i < condition.length; i++) {
            conditions.add(condition[i]);
        }
        return this.mapper.update(bean, conditions.prepare());
    }

    @Override
    public int updateSelective(DTable bean, ICondition... condition) {
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