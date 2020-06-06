package com.gm.pm.config;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.validation.Validator;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
 
/**
 * @author Jason
 */
@Configuration
public class DateConfig{
 
    @Bean
    public ConfigurableWebBindingInitializer configurableWebBindingInitializer(
            FormattingConversionService mvcConversionService, Validator mvcValidator
    ) {
        ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
        initializer.setConversionService(mvcConversionService);
        initializer.setValidator(mvcValidator);
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        //装配自定义属性编辑器
        initializer.setPropertyEditorRegistrar(propertyEditorRegistry -> {
            propertyEditorRegistry.registerCustomEditor(Date.class,
                    new CustomDateEditor(dateFormat, true)
            );
        });
        return initializer;
    }
}