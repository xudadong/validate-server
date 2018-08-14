package com.xudd.asj.validate.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动
 */
@SpringBootApplication
@MapperScan("com.xudd.asj.validate.server.mapper")
public class ValidateServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ValidateServerApplication.class, args);
    }
}
