package com.gm.pm.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.http.HttpStatus.*;

/**
 * @author Jason
 */
@Configuration
public class ErrorPageConfig {
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return (factory -> {
            ErrorPage e400 = new ErrorPage(BAD_REQUEST, "/error/" + 400);
            ErrorPage e404 = new ErrorPage(NOT_FOUND, "/error/" + 404);
            ErrorPage e500 = new ErrorPage(INTERNAL_SERVER_ERROR, "/error/" + 500);
            factory.addErrorPages(e400,e404,e500);
        });
    }

}