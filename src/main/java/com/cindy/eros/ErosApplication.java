package com.cindy.eros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//扫描mybatis mapper包路径
@MapperScan(basePackages = "com.cindy.eros.admin.dao")
//扫描所有需要的包
@ComponentScan(basePackages = {"com.cindy.eros","org.n3r.idworker"})
public class ErosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErosApplication.class, args);
    }
}
