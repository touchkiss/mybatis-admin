package com.touchkiss.mybatis.base.service.impl;

import com.touchkiss.mybatis.base.bean.UserGroup;
import com.touchkiss.mybatis.base.service.UserGroupService;
import com.touchkiss.mybatis.base.dao.auto.UserGroupAutoDao;
import com.touchkiss.mybatis.sqlbuild.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;

/**
 * 用户组.service业务层实现
 *
 * @author Touchkiss
 **/
@Service
public class UserGroupServiceImpl extends BaseServiceImpl<UserGroup> implements UserGroupService {
    @Autowired
    private UserGroupAutoDao dao;
}