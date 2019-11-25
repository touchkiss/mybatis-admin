package com.touchkiss.mybatis.demo.config;

import com.touchkiss.mybatis.admin.bean.RegisterInfo;
import com.touchkiss.mybatis.base.bean.Category;
import com.touchkiss.mybatis.base.bean.User;
import com.touchkiss.mybatis.base.service.impl.CategoryServiceImpl;
import com.touchkiss.mybatis.base.service.impl.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.touchkiss.mybatis.admin.config.AdminConfig.registerInfoMap;

/**
 * @Author Touchkiss
 * @create: 2019-06-22 22:15
 */
@Component
@Scope("singleton")
public class Config implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        registerInfoMap.put("user", new RegisterInfo("users", "用户", "user", "用户组信息", User.class, UserServiceImpl.class));
        registerInfoMap.put("category", new RegisterInfo("categorys", "分类", "category", "分类", Category.class, CategoryServiceImpl.class));
    }
}
