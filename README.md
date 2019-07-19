# mybatis-admin-demo
A toy project for mybatis generator and admin.实现部分django admin的功能，仅需少量代码即可对数据库元数据进行管理。

使用Generator自动生成数据库表对应的bean、mapper、dao、service和mapper.xml文件，然后再向AdminConfig里面注册需要管理的类名，启动即可实现对数据库原始数据的管理。搜索条件支持空格（and）、or（或者）和括号进行高级搜索。
即使你不使用该admin示例项目，也可以使用其中的mybatis代码生成工具，该工具能够生成selectById、selectAll、selectPage、select、selectForMap、selectForListMapo、selectPageForListMap、insert、insertSelective、update、updateSelective、delete等方法。

演示地址  http://mybatis.touchkiss.com/admin

# 使用步骤
1. git clone https://github.com/touchkiss/mybatis-admin
2. 使用intellij idea打开此项目，
3. mvn install安装此项目
4. 创建一个自己的maven项目，继承


 	<parent>
        <groupId>com.touchkiss.mybatis</groupId>
        <artifactId>mybatis-demo</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <relativePath/> 
    </parent>
5. dependencies添加


 	<dependency>
        <groupId>com.touchkiss.mybatis</groupId>
        <artifactId>generator</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>
    <dependency>
        <groupId>com.touchkiss.mybatis</groupId>
        <artifactId>sqlbuilder</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>
    <dependency>
        <groupId>com.touchkiss.mybatis</groupId>
        <artifactId>admin</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>

6. 参考/base/src/test/java/Generator.java创建自己的mybatis文件
7. 参考/demo/src/main/java/com/touchkiss/mybatis/demo/Config配置自己的注册文件
8. Application启动类添加注解@ComponentScan(basePackages = {"com.touchkiss.mybatis.admin","你自己的项目包名"})