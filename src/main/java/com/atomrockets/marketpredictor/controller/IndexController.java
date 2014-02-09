package com.atomrockets.marketpredictor.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * Controllers are annotated with @Controller. It is found by Spring 
 * 		because of @ComponentScan annotation in AppConfig. The method will 
 * 		intercept GET request to "/" to which the response will be sent. 
 * 		@ResponseBody indicates that whatever this method returns will be 
 * 		response body, and in this case it’s just a "Hello world" String.
 */
@Controller
class IndexController {

	//This value is coming from the properties resources. The dev or prod files depends on the environment.
	 @Value("${example.message}")
	 private String message;
	 
    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String showIndex() {
        return message + "\n Hello world";
    }

}