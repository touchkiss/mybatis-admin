package ${implPackageName};

<#if entityPackage??>import ${entityPackage};</#if>
<#if packageName!=implPackageName>
import com.github.pagehelper.Page;
import ${packageName}.${tableConfig.getEntityName()}Service;
import ${daoPackageName}.${tableConfig.getEntityName()}AutoDao;
import com.touchkiss.mybatis.sqlbuild.keyword.Sort;
import com.touchkiss.mybatis.sqlbuild.query.QColumn;
import com.touchkiss.mybatis.sqlbuild.selector.Selector;
</#if>
<#if primaryKeyColumns??><#if primaryKeyColumns?size gt 0><#else>import com.touchkiss.mybatis.sqlbuild.exceptions.NoPrimaryKeyException;</#if><#else>import com.touchkiss.mybatis.sqlbuild.exceptions.NoPrimaryKeyException;</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;<#if context.isUseLombok()>
import lombok.AllArgsConstructor;</#if>

import java.util.List;
import java.util.Map;
<#list table.imports as im>import ${im};</#list>

/**
 * ${table.getRemarks()}.service业务层实现
 *
 * @author ${tableConfig.getEntityName()}ouchkiss
 */
@Service<#if context.isUseLombok()>
@AllArgsConstructor</#if>
public class ${tableConfig.getEntityName()}ServiceImpl implements ${tableConfig.getEntityName()}Service {<#if !context.isUseLombok()>
    @Autowired</#if>
    private ${tableConfig.getEntityName()}${context.getBeanNameSuffix()}Dao dao;

	@Override
	public ${tableConfig.getEntityName()} selectOneByID(Object... ids) {
		<#if primaryKeyColumns??><#if primaryKeyColumns?size gt 0>return this.dao.selectOneByID(ids);<#else>throw new NoPrimaryKeyException("该表无主键！");
		return null;</#if><#else>throw new NoPrimaryKeyException("该表无主键！");
        return null;</#if>
	}

	@Override
	public int deleteOneByID(Object... ids) {
		<#if primaryKeyColumns??><#if primaryKeyColumns?size gt 0>return this.dao.deleteOneByID(ids);<#else>throw new NoPrimaryKeyException("该表无主键！");
		return 0;</#if><#else>throw new NoPrimaryKeyException("该表无主键！");
        return 0;</#if>
	}

	@Override
	public int updateOneByID(${tableConfig.getEntityName()} bean, Object... ids) {
		<#if primaryKeyColumns??><#if primaryKeyColumns?size gt 0>return this.dao.updateOneByID(bean, ids);<#else>throw new NoPrimaryKeyException("该表无主键！");
        return 0;</#if><#else>throw new NoPrimaryKeyException("该表无主键！");
        return 0;</#if>
	}

	@Override
	public int updateOneSelectiveByID(${tableConfig.getEntityName()} bean, Object... ids) {
		<#if primaryKeyColumns??><#if primaryKeyColumns?size gt 0>return this.dao.updateOneSelectiveByID(bean, ids);<#else>throw new NoPrimaryKeyException("该表无主键！");
		return 0;</#if><#else>throw new NoPrimaryKeyException("该表无主键！");
		return 0;</#if>
	}

	@Override
	public int insert(${tableConfig.getEntityName()} bean) {
		return this.dao.insert(bean);
	}

	@Override
	public int insertSelective(${tableConfig.getEntityName()} bean) {
		return this.dao.insertSelective(bean);
	}

	@Override
	public List<${tableConfig.getEntityName()}> selectAll() {
		return this.dao.selectAll();
	}

	@Override
	public List<${tableConfig.getEntityName()}> selectAll(QColumn column, Sort sort) {
		return this.dao.selectAll(column, sort);
	}

	@Override
	public List<${tableConfig.getEntityName()}> select(Selector<${tableConfig.getEntityName()}> selector) {
		return this.dao.select(selector);
	}

	@Override
	public List<${tableConfig.getEntityName()}> select(${tableConfig.getEntityName()} bean) {
		return this.dao.select(bean);
	}

	@Override
	public List<${tableConfig.getEntityName()}> select(Map<String, String[]> map) {
		return this.dao.select(map);
	}

	@Override
	public ${tableConfig.getEntityName()} selectOne(${tableConfig.getEntityName()} bean) {
		return this.dao.selectOne(bean);
	}

	@Override
	public ${tableConfig.getEntityName()} selectOne(Selector<${tableConfig.getEntityName()}> selector) {
		return this.dao.selectOne(selector);
	}

	@Override
	public Page<${tableConfig.getEntityName()}> selectPage(Selector<${tableConfig.getEntityName()}> selector, int pageNum, int pageSize) {
		return this.dao.selectPage(selector, pageNum, pageSize);
	}

	@Override
	public Page<Map> selectPageForMap(Selector<${tableConfig.getEntityName()}> selector, int pageNum, int pageSize) {
		return this.dao.selectPageForMap(selector, pageNum, pageSize);
	}

	@Override
	public Page<${tableConfig.getEntityName()}> selectPage(Map<String, String[]> map, int pageNum, int pageSize) {
		return this.dao.selectPage(map, pageNum, pageSize);
	}

	@Override
	public Page<${tableConfig.getEntityName()}> selectPage(${tableConfig.getEntityName()} bean, int pageNum, int pageSize) {
		return this.dao.selectPage(bean, pageNum, pageSize);
	}
}