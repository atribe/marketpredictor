package com.atomrockets.marketpredictor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/*
 * This is from http://kielczewski.eu/2013/11/spring-mvc-without-web-xml-using-webapplicationinitializer/
 * 
 * WebMvcConfig class enables Spring MVC with @EnableWebMvc annotation. It 
 * 		extends WebMvcConfigurerAdapter, which provides empty methods that 
 * 		can be overridden to customize default configuration of Spring MVC.
 * 		We will stick to default configuration at this time, but it�s 
 * 		advised for you to see what the possibilities are.
 */
@EnableAsync
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.atomrockets.marketpredictor.controller")
@SuppressWarnings("UnusedDeclaration")
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	@Bean
	 public InternalResourceViewResolver configureInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".jsp");
		
		return resolver;
	}
	
	/*
	 * I don't know if I need this or not.
	 * 
	 * I think I do, it got rid of the error: org.springframework.web.servlet.DispatcherServlet noHandlerFound
	 * But now it I get a 404 error saying The requested resource (/marketpredictor/WEB-INF/pages/index.jsp) is not available.
	 * Maybe it isn't fully configured yet?
	 * 
	 * This enables the use of css and stuff.
	 */
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
	 @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926);
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    }
}