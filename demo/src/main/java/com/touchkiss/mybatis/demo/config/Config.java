package com.touchkiss.mybatis.demo.config;

import com.touchkiss.mybatis.admin.bean.BeanPropertyInfo;
import com.touchkiss.mybatis.admin.bean.RegisterInfo;
import com.touchkiss.mybatis.base.bean.Category;
import com.touchkiss.mybatis.base.bean.TUser;
import com.touchkiss.mybatis.base.bean.User;
import com.touchkiss.mybatis.base.bean.UserGroup;
import com.touchkiss.mybatis.base.service.impl.CategoryServiceImpl;
import com.touchkiss.mybatis.base.service.impl.TUserServiceImpl;
import com.touchkiss.mybatis.base.service.impl.UserGroupServiceImpl;
import com.touchkiss.mybatis.base.service.impl.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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
        RegisterInfo userRegisterInfo = new RegisterInfo("users", "用户", "t_user", "用户信息", TUser.class, TUserServiceImpl.class);
        userRegisterInfo.getBeanInfo().setBeanPropertyInfos(new BeanPropertyInfo[]{
                new BeanPropertyInfo("id", "id", "java.lang.Integer"),
                new BeanPropertyInfo("groupId", "组id", "java.lang.String"),
                new BeanPropertyInfo("userName", "姓名", "java.lang.String"),
                new BeanPropertyInfo("age", "年龄", "java.lang.Integer"),
                new BeanPropertyInfo("lastModifyTime", "上次修改时间", "java.util.Date"),
                new BeanPropertyInfo("createTime", "创建时间", "java.util.Date")
        });
        userRegisterInfo.getBeanInfo().showAllFields();
        registerInfoMap.put("user", userRegisterInfo);
//        registerInfoMap.put("user", new RegisterInfo("users", "t_user", "用户信息", TUser.class, TUserServiceImpl.class));
        registerInfoMap.put("category", new RegisterInfo("categorys", "类目", "category", "分类信息", Category.class, CategoryServiceImpl.class));
        registerInfoMap.put("userGroup", new RegisterInfo("users", "用户", "user_group", "用户组信息", UserGroup.class, UserGroupServiceImpl.class));
//        registerInfoMap.put("dtable", new RegisterInfo("dtable", "测试", "d_table", DTable.class, DTableServiceImpl.class));
        RegisterInfo user2RegisgerInfo = new RegisterInfo("users", "用户", "user", "用户信息", User.class, UserServiceImpl.class);
        user2RegisgerInfo.getBeanInfo().setIdColumnName("uid");
        registerInfoMap.put("user2", user2RegisgerInfo);
    }
}
