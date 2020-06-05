package com.gm.pm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jason
 */
@SpringBootApplication
@EnableConfigurationProperties
@MapperScan(basePackages = "com.gm.pm.mapper")
public class PmApp {

    public static void main(String[] args) {
        SpringApplication.run(PmApp.class, args);
    }
}
