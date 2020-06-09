package com.gm.pm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Jason
 */
@SpringBootApplication
@EnableConfigurationProperties
@MapperScan(basePackages = "com.gm.pm.mapper")
@PropertySource(value={"file:application.properties"})
public class PmApp {

    public static void main(String[] args) {
        SpringApplication.run(PmApp.class, args);
    }
}
