package com.atomrockets.marketpredictor.controller;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/*
 * Controllers are annotated with @Controller. It is found by Spring 
 * 		because of @ComponentScan annotation in AppConfig. The method will 
 * 		intercept GET request to "/" to which the response will be sent. 
 * 		@ResponseBody indicates that whatever this method returns will be 
 * 		response body, and in this case it�s just a "Hello world" String.
 */
@Controller
class IndexController {

	//This value is coming from the properties resources. The dev or prod files depends on the environment.
	 @Value("${example.message}")
	 private String message;
	 
    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(Model model) {
    	model.addAttribute("wisdom", "Goodbye XML");
    	model.addAttribute("message", message);
    	model.addAttribute("dateOut", new LocalDateTime().toString());
    	return "index";
    }
    /*
     * This method is from the tutorial, I'm replacing it with the above from Shakeel's code
    @ResponseBody
    public String showIndex() {
        return message + "\n Hello world";
    }
    */
}