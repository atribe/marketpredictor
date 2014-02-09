package com.atomrockets.marketpredictor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
 * This is from http://kielczewski.eu/2013/11/spring-mvc-without-web-xml-using-webapplicationinitializer/
 * 
 * WebMvcConfig class enables Spring MVC with @EnableWebMvc annotation. It 
 * 		extends WebMvcConfigurerAdapter, which provides empty methods that 
 * 		can be overridden to customize default configuration of Spring MVC.
 * 		We will stick to default configuration at this time, but it’s 
 * 		advised for you to see what the possibilities are.
 */
@EnableWebMvc
@Configuration
@SuppressWarnings("UnusedDeclaration")
public class WebMvcConfig extends WebMvcConfigurerAdapter {
}