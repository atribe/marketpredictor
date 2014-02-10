package com.atomrockets.marketpredictor.intializer;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.atomrockets.marketpredictor.config.WebMvcConfig;

/*
 * This is from http://kielczewski.eu/2013/11/spring-mvc-without-web-xml-using-webapplicationinitializer/
 * 
 * Several things happen here: 
 * 1. AnnotationConfigWebApplicationContext is created. It�s WebApplicationContext implementation
 * 		 that looks for Spring configuration in classes annotated with @Configuration annotation. setConfigLocation() method gets hint in which package(s) to look for them.
 * 2. ContextLoaderListener is added to ServletContext � the purpose of this is to �glue� 
 * 		WebApplicationContext to the lifecycle of ServletContext.
 * 3. DispatcherServlet is created and initialized with WebApplicationContext we have created, and
 * 		it�s mapped to "/*" URLs and set to eagerly load on application startup.
 */
public class AppInitializer implements WebApplicationInitializer {
	 
	private static final String CONFIG_LOCATION = "com.atomrockets.marketpredictor.config";
    private static final String MAPPING_URL = "/";
    
    private static final String SPRING_PROPERTIES_FILE_NAME = "spring.properties";
    private static final String ACTIVE_PROFILE_PROPERTY_NAME = "spring.profiles.active";
    private static final String DEFAULT_PROFILE = "dev";
    private static final PropertiesLoader propertiesLoader = new PropertiesLoader();
    
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
    	
    	// Create ApplicationContext. Gets its context from the getContext() method below.
    	WebApplicationContext context = getContext();
        //servletContext.addListener(new ContextLoaderListener(context));
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("Dispatcher_Servlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping(MAPPING_URL);
    }
 
    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(CONFIG_LOCATION);
        
        Properties prop = propertiesLoader.load(SPRING_PROPERTIES_FILE_NAME);
        context.getEnvironment().setActiveProfiles(prop.getProperty(ACTIVE_PROFILE_PROPERTY_NAME, DEFAULT_PROFILE));
        return context;
    }
 
}
