import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.touchkiss.mybatis.generator.MyBatisGenerator;
import com.touchkiss.mybatis.generator.internal.GeneratorContext;
import com.touchkiss.mybatis.generator.internal.JDBCConnectionConfiguration;
import com.touchkiss.mybatis.generator.model.Cache;
import com.touchkiss.mybatis.generator.model.TableConfig;
import com.touchkiss.mybatis.generator.template.impl.DaoGenerator;
import com.touchkiss.mybatis.generator.template.impl.MappingGenerator;
import com.touchkiss.mybatis.generator.template.impl.ServiceGenerator;
import com.touchkiss.mybatis.generator.template.impl.XmlGenerator;

import java.io.File;
import java.util.List;
import java.util.Map;

public class Generator {
    public static void main(String[] args) {
        DevGenerator();
    }

    public static void DevGenerator() {
        String root = "D:\\documents\\java\\mybatis-admin\\base\\src\\main";
        //工程java根目录
        String rootPath = root + File.separator + "java";
        //工程resources根目录
        String xmlPath = root + File.separator + "resources";

        //初始化数据库连接
        JDBCConnectionConfiguration dataConfig = new JDBCConnectionConfiguration();
        dataConfig.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataConfig.setUrl("jdbc:mysql://127.0.0.1:3306/ai?characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&nullCatalogMeansCurrent=true");
        dataConfig.setUsername("root");
        dataConfig.setPassword("mouse");
        dataConfig.getProperties().put("remarks", "true");
        dataConfig.getProperties().put("useInformationSchema", "true");

        //自动生成文件Map对象
        Map<String, String> packages = Maps.newHashMap();
        packages.put(DaoGenerator.ROOTPATH, rootPath);
//        //service业务层接口
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

//        tableConfigs.add(new TableConfig("d_table").overwrite(true).supportSerialize(true).useGeneratedKeys(true).cache(new Cache()));
        tableConfigs.add(new TableConfig("category").overwrite(true).supportSerialize(true).useGeneratedKeys(true).cache(new Cache()));
//        tableConfigs.add(new TableConfig("test_pri").overwrite(true).supportSerialize(true).useGeneratedKeys(true).cache(new Cache()));
        tableConfigs.add(new TableConfig("user").overwrite(true).supportSerialize(true).useGeneratedKeys(true).cache(new Cache()));
//        tableConfigs.add(new TableConfig("user_group").overwrite(true).supportSerialize(true).useGeneratedKeys(true).cache(new Cache()));
//        tableConfigs.add(new TableConfig("user").overwrite(true).supportSerialize(true).useGeneratedKeys(true).cache(new Cache()));
        /*tableConfigs.add(new TableConfig("zq_user_account_today").overwrite(true).supportSerialize(true).useGeneratedKeys(true).cache(new Cache()));*/
        //  tableConfigs.add(new TableConfig("zq_article_context").overwrite(true).supportSerialize(true).useGeneratedKeys(true).cache(new Cache()));
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(dataConfig, tableConfigs.toArray(new TableConfig[tableConfigs.size()]), context);


        myBatisGenerator.generator();
    }

}
