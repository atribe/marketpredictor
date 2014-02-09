package com.atomrockets.marketpredictor.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/*
 * This is from http://kielczewski.eu/2013/11/spring-mvc-without-web-xml-using-webapplicationinitializer/
 * 
 * The main AppConfig configuration class doesn’t do anything but hits 
 * Spring on where to look for its components through @ComponentScan 
 * annotation.
 */
@Configuration
@ComponentScan(basePackages = "com.atomrockets.marketpredictor")
@SuppressWarnings("UnusedDeclaration")
public class AppConfig {
}
