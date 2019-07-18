package ${implPackageName};

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
<#if entityPackage??>import ${entityPackage};</#if>
<#if packageName!=implPackageName>import ${packageName}.${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao;</#if>
<#if mapperPackage!=implPackageName>import ${mapperPackage}.${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Mapper;</#if>
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
<#list table.imports as im>import ${im};</#list>

/**
 * ${table.getRemarks()}.dao层接口实现
 *
 * @author Touchkiss
 **/
@Component<#if tableConfig.getSchema()??>("${tableConfig.getSchema()}${tableConfig.getEntityName()}${context.getBeanNameSuffix()}DaoImpl")</#if>
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}DaoImpl implements ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao {
    @Resource(name = "${mapperBeanName}")
    private ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Mapper mapper;

    /**
     * 根据条件查询
     *
     * @param conditions
     * @return
     */
    @Override
    public List<${tableConfig.getEntityName()}> select(ICondition... conditions) {
        ManyCondition manyCondition = new ManyCondition();
        for (int i = 0; i < conditions.length; i++) {
            manyCondition.add(conditions[i]);
        }
        return this.mapper.select(new SelectResolver(manyCondition, ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.TABLE, ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.ALL_FIELDS).toMetadata());
    }

    @Override
    public List<${tableConfig.getEntityName()}> select(Selector<${tableConfig.getEntityName()}> selector) {
        SelectResolver resolver = new SelectResolver(selector, ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.TABLE, ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.ALL_FIELDS);
        SelectMetadata metadata = resolver.toMetadata();
        return this.mapper.select(metadata);
    }

    @Override
    public List<${tableConfig.getEntityName()}> select(${tableConfig.getEntityName()} bean) {
        ManyCondition conditions = ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.SELECT_WHERE_HANDLE.handle(bean);
        SelectResolver resolver = new SelectResolver(conditions, ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.TABLE, ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.ALL_FIELDS);
        SelectMetadata metadata = resolver.toMetadata();
        return this.mapper.select(metadata);
    }

    @Override
    public List<${tableConfig.getEntityName()}> select(Map<String, String[]> map) {
        Selector<${tableConfig.getEntityName()}> selector = new Selector<>();
        for (QColumn<${tableConfig.getEntityName()}, Object> field : ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.ALL_FIELDS) {
            if (map.containsKey(field.getColumnName())) {
                selector.where(field, map.get(field.getColumnName())[0]);
            }
        }
        return this.select(selector);
    }

    @Override
    public <Output> List<Output> select(Selector<${tableConfig.getEntityName()}> selector, Handle<Map, Output> handle) {
        SelectResolver resolver = new SelectResolver(selector, ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.TABLE, ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.ALL_FIELDS);
        List maps = this.mapper.selectForListMap(resolver.toMetadata());
        List<Output> ret = new ArrayList<>(maps.size());
        for (int i = 0; i < maps.size(); i++) {
            Output out = handle.handle((Map) maps.get(i));
            ret.add(out);
        }
        return ret;
    }

    @Override
    public List<${tableConfig.getEntityName()}> selectAll() {
        return this.mapper.selectAll();
    }

    @Override
    public List<${tableConfig.getEntityName()}> selectAll(QColumn column, Sort sort){
        Selector selector = new Selector();
        selector.order(column,sort);
        SelectResolver resolver = new SelectResolver(selector, ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.TABLE, ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.ALL_FIELDS);
        SelectMetadata metadata = resolver.toMetadata();
        return this.mapper.select(metadata);
    }
    @Override
    public ${tableConfig.getEntityName()} selectOne(Selector<${tableConfig.getEntityName()}> selector) {
        SelectResolver resolver = new SelectResolver(selector, ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.TABLE, ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.ALL_FIELDS);
        SelectMetadata metadata = resolver.toMetadata();
        return this.mapper.selectOne(metadata);
    }

    @Override
    public Long selectCount(ICondition... condition) {
        Selector<${tableConfig.getEntityName()}> selector = new Selector<>();
        selector.addStringField(${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.COUNT_FIELD);
        selector.where(condition);
        SelectResolver resolver = new SelectResolver(selector, ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.TABLE, ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.ALL_FIELDS);
        List list = this.mapper.selectForListMap(resolver.toMetadata());
        if (list.size() > 0) {
            return ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.COUNT_HANDLE.handle((Map) list.get(0));
        }
        return 0L;
    }

    @Override
    public Long selectCount(${tableConfig.getEntityName()} bean) {
        ManyCondition conditions = ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.SELECT_WHERE_HANDLE.handle(bean);
        return selectCount(conditions);
    }

    @Override
    public <Output> Page<Output> selectPage(Selector<${tableConfig.getEntityName()}> selector, Handle<Map, Output> handle, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        SelectResolver resolver = new SelectResolver(selector, ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.TABLE, ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.ALL_FIELDS);
        Page maps = this.mapper.selectPageForListMap(resolver.toMetadata());
        Page<Output> ret = new Page<>();
        for (int i = 0; i < maps.size(); i++) {
            Output out = handle.handle((Map) maps.get(i));
            ret.add(out);
        }
        return ret;
    }

    @Override
    public Page<${tableConfig.getEntityName()}> selectPage(Map<String, String[]> map, int pageNo, int pageSize) {
        Selector<${tableConfig.getEntityName()}> selector = new Selector<>();
        for (QColumn<${tableConfig.getEntityName()}, Object> field : ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.ALL_FIELDS) {
            if (map.containsKey(field.getColumnName())) {
                selector.where(field, map.get(field.getColumnName())[0]);
            }
        }
        return selectPage(selector, pageNo, pageSize);
    }

    @Override
    public Page<${tableConfig.getEntityName()}> selectPage(int pageNum, int pageSize, ICondition... condition) {
        PageHelper.startPage(pageNum, pageSize);
        Selector<${tableConfig.getEntityName()}> selector = new Selector<>();
        selector.where(condition);
        return this.selectPage(selector, pageNum, pageSize);
    }

    @Override
    public Page<${tableConfig.getEntityName()}> selectPage(Selector<${tableConfig.getEntityName()}> selector, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        SelectResolver resolver = new SelectResolver(selector, ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.TABLE, ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.ALL_FIELDS);
        return this.mapper.selectPage(resolver.toMetadata());
    }

    @Override
    public Page<${tableConfig.getEntityName()}> selectPage(${tableConfig.getEntityName()} bean, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        SelectResolver resolver = new SelectResolver(${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.SELECT_WHERE_HANDLE.handle(bean), ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.TABLE, ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao.ALL_FIELDS);
        return this.mapper.selectPage(resolver.toMetadata());
    }
    //endregion

    //region write data
    <#if primaryKeyColumns??><#if primaryKeyColumns?size gt 0>
    @Override
    public ${tableConfig.getEntityName()} selectOneByID(Object... ids) {
        return this.mapper.selectOneByID(ids);
    }
    </#if></#if>

    @Override
    public int update(${tableConfig.getEntityName()} bean, ICondition... condition) {
        ManyCondition conditions = new ManyCondition();
        for (int i = 0; i < condition.length; i++) {
            conditions.add(condition[i]);
        }
        return this.mapper.update(bean, conditions.prepare());
    }

    @Override
    public int updateSelective(${tableConfig.getEntityName()} bean, ICondition... condition) {
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

    @Override
    public ${tableConfig.getEntityName()} selectOne(${tableConfig.getEntityName()} bean) {
        return this.mapper.selectOne(bean);
    }

    @Override
    public int insert(${tableConfig.getEntityName()} bean) {
        return this.mapper.insert(bean);
    }

    @Override
    public int insertSelective(${tableConfig.getEntityName()} bean) {
        return this.mapper.insertSelective(bean);
    }

    @Override
    public int deleteOneByID(Object... ids) {
        return this.mapper.deleteOneByID(ids);
    }

    @Override
    public int updateOneByID(${tableConfig.getEntityName()} bean) {
        return this.mapper.updateOneByID(bean);
    }

    @Override
    public int updateOneSelectiveByID(${tableConfig.getEntityName()} bean) {
        return this.mapper.updateOneSelectiveByID(bean);
    }
    //endregion
}