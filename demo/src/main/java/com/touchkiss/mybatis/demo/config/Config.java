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
//        RegisterInfo userRegisterInfo = new RegisterInfo("users", "用户", "t_user", "用户信息", TUser.class, TUserServiceImpl.class);
//        userRegisterInfo.getBeanInfo().setBeanPropertyInfos(new BeanPropertyInfo[]{
//                new BeanPropertyInfo("id", "id", "java.lang.Integer"),
//                new BeanPropertyInfo("groupId", "组id", "java.lang.String"),
//                new BeanPropertyInfo("userName", "姓名", "java.lang.String"),
//                new BeanPropertyInfo("age", "年龄", "java.lang.Integer"),
//                new BeanPropertyInfo("lastModifyTime", "上次修改时间", "java.util.Date"),
//                new BeanPropertyInfo("createTime", "创建时间", "java.util.Date")
//        });
//        userRegisterInfo.getBeanInfo().showAllFields();
//        registerInfoMap.put("user", userRegisterInfo);
//        RegisterInfo catetoryInfo = new RegisterInfo("categorys", "类目", "category", "分类信息", Category.class, CategoryServiceImpl.class);
//        ForeignKeyInfo parentidForkeyInfo = new ForeignKeyInfo();
//        parentidForkeyInfo.setName("category");
//        parentidForkeyInfo.setKeyName("id");
//        parentidForkeyInfo.setValueName("name");
//        Selector selector = new Selector().addField(CategoryAutoDao.id, CategoryAutoDao.name).distinct(true);
//        parentidForkeyInfo.setSelector(selector);
//        parentidForkeyInfo.setEditable(true);
//        catetoryInfo.getForeignKeyInfoMap().put("parentid", parentidForkeyInfo);
//        ForeignKeyInfo topSelectOptions = new ForeignKeyInfo();
//        topSelectOptions.setName("top");
//        topSelectOptions.setOptions(new ArrayList() {{
//            add(new SelectOption("1", "是"));
//            add(new SelectOption("0", "否"));
//        }});
//        topSelectOptions.setEditable(false);
//        catetoryInfo.getForeignKeyInfoMap().put("top", topSelectOptions);
//        registerInfoMap.put("category", catetoryInfo);
        registerInfoMap.put("user", new RegisterInfo("users", "用户", "user", "用户组信息", User.class, UserServiceImpl.class));
        registerInfoMap.put("category", new RegisterInfo("categorys", "分类", "category", "分类", Category.class, CategoryServiceImpl.class));
//        RegisterInfo user2RegisgerInfo = new RegisterInfo("users", "用户", "user", "用户信息", User.class, UserServiceImpl.class);
//        user2RegisgerInfo.getBeanInfo().setIdColumnName("uid");
//        registerInfoMap.put("user2", user2RegisgerInfo);
    }
}
