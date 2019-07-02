package com.touchkiss.mybatis.base.mapper.auto;

import com.touchkiss.mybatis.sqlbuild.mapper.BaseMapper;
import com.touchkiss.mybatis.base.bean.UserGroup;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户组.mapper层接口
 *
 * @author Touchkiss
 **/
@Mapper
public interface UserGroupAutoMapper extends BaseMapper<UserGroup> {
}