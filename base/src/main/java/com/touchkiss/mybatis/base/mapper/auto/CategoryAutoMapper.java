package com.touchkiss.mybatis.base.mapper.auto;

import com.touchkiss.mybatis.sqlbuild.mapper.BaseMapper;
import com.touchkiss.mybatis.base.bean.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * .mapper层接口
 *
 * @author Touchkiss
 **/
@Mapper
public interface CategoryAutoMapper extends BaseMapper<Category> {
}