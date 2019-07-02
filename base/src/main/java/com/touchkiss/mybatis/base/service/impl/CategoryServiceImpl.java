package com.touchkiss.mybatis.base.service.impl;

import com.touchkiss.mybatis.base.bean.Category;
import com.touchkiss.mybatis.base.service.CategoryService;
import com.touchkiss.mybatis.base.dao.auto.CategoryAutoDao;
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
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {
    @Autowired
    private CategoryAutoDao dao;
}