# mybatis-admin-demo
A toy project for mybatis generator and admin.实现部分django admin的功能，仅需少量代码即可对数据库元数据进行管理。

使用Generator自动生成数据库表对应的bean、mapper、dao、service和mapper.xml文件，然后再向AdminConfig里面注册需要管理的类名，启动即可实现对数据库原始数据的管理。搜索条件支持空格（and）、or（或者）和括号进行高级搜索。

演示地址  http://mybatis.touchkiss.com/admin

# 使用步骤
1. 删除/base/src/main/目录下的内容
2. 配置/base/src/test/java/Generator.java

`
 String root = "D:\\document\\java\\mybatis-demo\\base\\src\\main";
        //工程java根目录
        String rootPath = root + File.separator + "java";
        //工程resources根目录
        String xmlPath = root + File.separator + "resources";

        //初始化数据库连接
        JDBCConnectionConfiguration dataConfig = new JDBCConnectionConfiguration();
        dataConfig.setDriverClass("com.mysql.jdbc.Driver");
        dataConfig.setUrl("jdbc:mysql://127.0.0.1:3306/ai");
        dataConfig.setUsername("root");
        dataConfig.setPassword("mouse");
        dataConfig.getProperties().put("remarks", "true");
        dataConfig.getProperties().put("useInformationSchema", "true");

        //自动生成文件Map对象
        Map<String, String> packages = Maps.newHashMap();
        packages.put(DaoGenerator.ROOTPATH, rootPath);
        //service业务层接口
        packages.put(ServiceGenerator.PACKAGE, "com.touchkiss.mybatis.base.service");
        //service业务层接口实现
        packages.put(ServiceGenerator.PACKAGEIMPL, "com.touchkiss.mybatis.base.service.impl");
        //dao层接口
        packages.put(DaoGenerator.PACKAGE, "com.touchkiss.mybatis.base.dao.auto");
        //dao层接口实现
        packages.put(DaoGenerator.PACKAGEIMPL, "com.touchkiss.mybatis.base.dao.auto.impl");
        //mapper层根目录
        packages.put(MappingGenerator.ROOTPATH, rootPath);
        //mapper层接口
        packages.put(MappingGenerator.PACKAGE, "com.touchkiss.mybatis.base.mapper.auto");
        //XML文件根目录
        packages.put(XmlGenerator.ROOTPATH, xmlPath);
        //XML文件
        packages.put(XmlGenerator.PACKAGE, "mapper.auto");

        GeneratorContext context = GeneratorContext.createContext(
                //自动生成的根目录
                rootPath,
                //自动生成的Bean
                "com.touchkiss.mybatis.base.bean",
                //需要生成的Map对象
                packages);
        context.setForceBigDecimals(false);
        context.setUseMark(false);
        List<TableConfig> tableConfigs = Lists.newArrayList();

        tableConfigs.add(new TableConfig("d_table").overwrite(true).supportSerialize(true).useGeneratedKeys(true).cache(new Cache()));
        tableConfigs.add(new TableConfig("category").overwrite(true).supportSerialize(true).useGeneratedKeys(true).cache(new Cache()));
        tableConfigs.add(new TableConfig("t_user").overwrite(true).supportSerialize(true).useGeneratedKeys(true).cache(new Cache()));
        tableConfigs.add(new TableConfig("user_group").overwrite(true).supportSerialize(true).useGeneratedKeys(true).cache(new Cache()));
        tableConfigs.add(new TableConfig("user").overwrite(true).supportSerialize(true).useGeneratedKeys(true).cache(new Cache()));
        /*tableConfigs.add(new TableConfig("zq_user_account_today").overwrite(true).supportSerialize(true).useGeneratedKeys(true).cache(new Cache()));*/
        //  tableConfigs.add(new TableConfig("zq_article_context").overwrite(true).supportSerialize(true).useGeneratedKeys(true).cache(new Cache()));
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(dataConfig, tableConfigs.toArray(new TableConfig[tableConfigs.size()]), context);


        myBatisGenerator.generator();

使用该文件自动生成mybatis的bean、mapper、dao、service和xml文件。

3. 配置/demo/src/main/java/com/touchkiss/mybatis/demo/Config，将需要管理的类加入

`
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
        registerInfoMap.put("category", new RegisterInfo("categorys", "类目", "category", "分类信息", Category.class, CategoryServiceImpl.class));
        registerInfoMap.put("userGroup", new RegisterInfo("users", "用户", "user_group", "用户组信息", UserGroup.class, UserGroupServiceImpl.class));
        RegisterInfo user2RegisgerInfo = new RegisterInfo("users", "用户", "user", "用户信息", User.class, UserServiceImpl.class);
        user2RegisgerInfo.getBeanInfo().setIdColumnName("uid");
        registerInfoMap.put("user2", user2RegisgerInfo);
`

4. 修改application.properties运行DemoApplication.java即可使用。