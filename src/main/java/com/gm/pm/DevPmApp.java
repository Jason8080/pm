package com.gm.pm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author Jason
 */
@SpringBootApplication
@EnableConfigurationProperties
@MapperScan(basePackages = "com.gm.pm.mapper")
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE, classes = {
                        PmApp.class
                }
        )
)
public class DevPmApp {

    public static void main(String[] args) {
        SpringApplication.run(DevPmApp.class, args);
    }
}
