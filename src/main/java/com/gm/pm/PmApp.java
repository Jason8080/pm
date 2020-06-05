package com.gm.pm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Jason
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class PmApp {

    public static void main(String[] args) {
        SpringApplication.run(PmApp.class, args);
    }
}
