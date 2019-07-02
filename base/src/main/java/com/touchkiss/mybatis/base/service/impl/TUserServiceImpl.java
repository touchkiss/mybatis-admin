package com.touchkiss.mybatis.base.service.impl;

import com.touchkiss.mybatis.base.bean.TUser;
import com.touchkiss.mybatis.base.service.TUserService;
import com.touchkiss.mybatis.base.dao.auto.TUserAutoDao;
import com.touchkiss.mybatis.sqlbuild.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;

/**
 * 用户表.service业务层实现
 *
 * @author Touchkiss
 **/
@Service
public class TUserServiceImpl extends BaseServiceImpl<TUser> implements TUserService {
    @Autowired
    private TUserAutoDao dao;
}