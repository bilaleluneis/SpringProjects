package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Bilal El Uneis
 * @since July 2018
 * Sample controller for spring boot with thymeleaf and Bootstrap
 */

@Controller
public class WelcomeToBootStrapSpringController {
	
	/**
	 * gets called when hitting localhost:8080/ and getting that page
	 * @param model
	 * @return src/main/resources/templates/views/welcome.html page
	 */
	@RequestMapping( value="/" , method = RequestMethod.GET )
	public String index ( Model model ) {
		model.addAttribute("message", "Configuration (thymeleaf, bootstrap and jQuery) looks all good!");
		return "views/welcome";
	}

}
