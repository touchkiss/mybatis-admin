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
import com.touchkiss.mybatis.base.bean.Category;
import com.touchkiss.mybatis.base.dao.auto.CategoryAutoDao;
import com.touchkiss.mybatis.base.mapper.auto.CategoryAutoMapper;
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
public class CategoryAutoDaoImpl extends BaseAudoDaoImpl<Category> implements CategoryAutoDao {
    @Resource(name = "categoryAutoMapper")
    private CategoryAutoMapper mapper;

    /**
     * 根据条件查询
     *
     * @param conditions
     * @return
     */
    @Override
    public List<Category> select(ICondition... conditions) {
        ManyCondition manyCondition = new ManyCondition();
        for (int i = 0; i < conditions.length; i++) {
            manyCondition.add(conditions[i]);
        }
        return this.mapper.select(new SelectResolver(manyCondition, CategoryAutoDao.TABLE, CategoryAutoDao.ALL_FIELDS).toMetadata());
    }

    @Override
    public List<Category> select(Selector<Category> selector) {
        SelectResolver resolver = new SelectResolver(selector, CategoryAutoDao.TABLE, CategoryAutoDao.ALL_FIELDS);
        SelectMetadata metadata = resolver.toMetadata();
        return this.mapper.select(metadata);
    }

    @Override
    public List<Category> select(Category bean) {
        ManyCondition conditions = CategoryAutoDao.SELECT_WHERE_HANDLE.handle(bean);
        SelectResolver resolver = new SelectResolver(conditions, CategoryAutoDao.TABLE, CategoryAutoDao.ALL_FIELDS);
        SelectMetadata metadata = resolver.toMetadata();
        return this.mapper.select(metadata);
    }

    @Override
    public List<Category> select(Map<String, String[]> map) {
        Selector<Category> selector = new Selector<>();
        for (QColumn<Category, Object> field : CategoryAutoDao.ALL_FIELDS) {
            if (map.containsKey(field.getColumnName())) {
                selector.where(field, map.get(field.getColumnName())[0]);
            }
        }
        return this.select(selector);
    }


    @Override
    public List<Category> selectById(String id) {
        return select(CategoryAutoDao.id.toEqCondition(Integer.parseInt(id)));
    }

    @Override
    public <Output> List<Output> select(Selector<Category> selector, Handle<Map, Output> handle) {
        SelectResolver resolver = new SelectResolver(selector, CategoryAutoDao.TABLE, CategoryAutoDao.ALL_FIELDS);
        List maps = this.mapper.selectForListMap(resolver.toMetadata());
        List<Output> ret = new ArrayList<>(maps.size());
        for (int i = 0; i < maps.size(); i++) {
            Output out = handle.handle((Map) maps.get(i));
            ret.add(out);
        }
        return ret;
    }

    @Override
    public List<Category> selectAll(QColumn column, Sort sort){
        Selector selector = new Selector();
        selector.order(column,sort);
        SelectResolver resolver = new SelectResolver(selector, CategoryAutoDao.TABLE, CategoryAutoDao.ALL_FIELDS);
        SelectMetadata metadata = resolver.toMetadata();
        return this.mapper.select(metadata);
    }

    @Override
    public <Output> Output selectRow(Selector<Category> selector, Handle<Map, Output> handle) {
        SelectResolver resolver = new SelectResolver(selector, CategoryAutoDao.TABLE, CategoryAutoDao.ALL_FIELDS);
        Map map = this.mapper.selectForMap(resolver.toMetadata());
        return handle.handle(map);
    }

    @Override
    public Long selectCount(ICondition... condition) {
        Selector<Category> selector = new Selector<>();
        selector.addStringField(CategoryAutoDao.COUNT_FIELD);
        selector.where(condition);
        SelectResolver resolver = new SelectResolver(selector, CategoryAutoDao.TABLE, CategoryAutoDao.ALL_FIELDS);
        List list = this.mapper.selectForListMap(resolver.toMetadata());
        if (list.size() > 0) {
            return CategoryAutoDao.COUNT_HANDLE.handle((Map) list.get(0));
        }
        return 0L;
    }

    @Override
    public Long selectCount(Category bean) {
        ManyCondition conditions = CategoryAutoDao.SELECT_WHERE_HANDLE.handle(bean);
        return selectCount(conditions);
    }

    @Override
    public <Output> Page<Output> selectPage(Selector<Category> selector, Handle<Map, Output> handle, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        SelectResolver resolver = new SelectResolver(selector, CategoryAutoDao.TABLE, CategoryAutoDao.ALL_FIELDS);
        Page maps = this.mapper.selectPageForListMap(resolver.toMetadata());
        Page<Output> ret = new Page<>();
        for (int i = 0; i < maps.size(); i++) {
            Output out = handle.handle((Map) maps.get(i));
            ret.add(out);
        }
        return ret;
    }

    @Override
    public Page<Category> selectPage(Map<String, String[]> map, int pageNo, int pageSize) {
        Selector<Category> selector = new Selector<>();
        for (QColumn<Category, Object> field : CategoryAutoDao.ALL_FIELDS) {
            if (map.containsKey(field.getColumnName())) {
                selector.where(field, map.get(field.getColumnName())[0]);
            }
        }
        return selectPage(selector, pageNo, pageSize);
    }

    @Override
    public Page<Category> selectPage(int pageNum, int pageSize, ICondition... condition) {
        PageHelper.startPage(pageNum, pageSize);
        Selector<Category> selector = new Selector<>();
        selector.where(condition);
        return this.selectPage(selector, pageNum, pageSize);
    }

    @Override
    public Page<Category> selectPage(Selector<Category> selector, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        SelectResolver resolver = new SelectResolver(selector, CategoryAutoDao.TABLE, CategoryAutoDao.ALL_FIELDS);
        return this.mapper.selectPage(resolver.toMetadata());
    }

    @Override
    public Page<Category> selectPage(Category bean, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        SelectResolver resolver = new SelectResolver(CategoryAutoDao.SELECT_WHERE_HANDLE.handle(bean), CategoryAutoDao.TABLE, CategoryAutoDao.ALL_FIELDS);
        return this.mapper.selectPage(resolver.toMetadata());
    }
    //endregion

    //region write data

    @Override
    public int updateById(Category bean) {
        return this.mapper.update(bean, CategoryAutoDao.id.toEqCondition(bean.getId()).prepare());
    }

    @Override
    public int updateSelectiveById(Category bean) {
        return this.mapper.updateSelective(bean, CategoryAutoDao.id.toEqCondition(bean.getId()).prepare());
    }


    @Override
    public int deleteById(String id) {
        return this.mapper.delete(CategoryAutoDao.id.toEqCondition(Integer.parseInt(id)).prepare());
    }


    @Override
    public int update(Category bean, ICondition... condition) {
        ManyCondition conditions = new ManyCondition();
        for (int i = 0; i < condition.length; i++) {
            conditions.add(condition[i]);
        }
        return this.mapper.update(bean, conditions.prepare());
    }

    @Override
    public int updateSelective(Category bean, ICondition... condition) {
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