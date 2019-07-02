package com.touchkiss.mybatis.base.service.impl;

import com.touchkiss.mybatis.base.bean.User;
import com.touchkiss.mybatis.base.service.UserService;
import com.touchkiss.mybatis.base.dao.auto.UserAutoDao;
import com.touchkiss.mybatis.sqlbuild.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * .service业务层实现
 *
 * @author Touchkiss
 **/
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Autowired
    private UserAutoDao dao;
}