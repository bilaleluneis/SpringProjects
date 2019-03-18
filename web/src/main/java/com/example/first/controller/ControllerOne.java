package com.example.first.controller;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since March 2019
 */

@Controller
public class ControllerOne {
	
	private static final Logger log = Logger.getLogger(ControllerOne.class);
	
	@Autowired
	private ViewResolver resolver;
	
	@GetMapping("/welcome")
    public String loadForm() throws Exception {
		View view = resolver.resolveViewName("welcome", Locale.US);
		log.debug(view.toString());
		return "welcome";
    }

}
