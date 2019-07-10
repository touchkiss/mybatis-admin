package com.touchkiss.mybatis.base.mapper.auto;

import com.touchkiss.mybatis.sqlbuild.mapper.BaseMapper;
import com.touchkiss.mybatis.base.bean.TUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表.mapper层接口
 *
 * @author Touchkiss
 **/
@Mapper
public interface TUserAutoMapper extends BaseMapper<TUser> {
}