package com.example.demo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Bilal El Uneis
 * @since July 2018
 * Sample controller for spring boot with thymeleaf application
 */

@Controller
public class SkeletonController {
	
	/**
	 * gets called when hitting localhost:8080/ and getting that page
	 * @param model
	 * @return src/main/resources/templates/views/welcome.html page
	 */
	@RequestMapping( value="/" , method = RequestMethod.GET )
	public String index ( Model model ) {
		return "views/welcome";
	}
	
	/**
	 * gets called when hitting localhost:8080/welcome
	 * @param model
	 * @return src/main/resources/templates/views/welcome.html page
	 */
	@RequestMapping( value="/welcome" , method = RequestMethod.GET )
	public String onGetWelcome ( Model model ) {
		return "views/welcome";
	}
	
	/**
	 * gets called when user submits form on localhost:8080/welcome
	 * @param model
	 * @return src/main/resources/templates/views/welcome.html page
	 */
	@RequestMapping( value="/welcome" , method = RequestMethod.POST )
	public String onPostWelcome ( HttpServletRequest request, Model model ) {
		
		String userName = request.getParameter("user_name");
		model.addAttribute("message", "Hello " + userName);
		return "views/result";
		
	}

}
