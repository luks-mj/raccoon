package com.mujun.mng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;




@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableSwagger2
@MapperScan(basePackages="com.mujun.mng.dao")
public class MunJunServiceApplication   extends SpringBootServletInitializer
{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(MunJunServiceApplication.class);
    }

    public static void main(String[] args) {
        try {
            SpringApplication.run(MunJunServiceApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
