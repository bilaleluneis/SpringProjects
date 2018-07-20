package com.example.demo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Bilal El Uneis
 * @since July 2018
 * Sample controller for spring boot with thymeleaf application
 */

@Controller
public class SkeletonController {
	
	
	@RequestMapping(value="/")
	public String welcome(Map<String, Object> model) {
		model.put("title", "Bilal's Spring Boot with Thymeleaf Title");
		model.put("header", "Bilal's page Header");
		model.put("message", "Hello Bilal");
		return "views/welcome";
	}

	
}
