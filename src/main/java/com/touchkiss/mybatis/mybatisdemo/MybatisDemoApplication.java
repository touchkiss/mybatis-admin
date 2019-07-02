package com.touchkiss.mybatis.mybatisdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

@SpringBootApplication
public class MybatisDemoApplication {
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    public static void main(String[] args) {
        SpringApplication.run(MybatisDemoApplication.class, args);
    }
    public void add(){
//        Method getMappingForMethod = ReflectionUtils.findMethod(RequestMappingHandlerMapping.class, "getMappingForMethod", Method.class, Class.class);
//        getMappingForMethod.setAccessible(true);
//        RequestMappingInfo mapping_info = (RequestMappingInfo) getMappingForMethod.invoke(requestMappingHandlerMapping, m_d,entry.getValue());
    }


}
