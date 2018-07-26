package com.example.demo;

import java.util.Arrays;
import java.util.List;

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
	 * gets called when hitting localhost:8080/
	 * @param model
	 * @return src/main/resources/templates/views/welcome.html page
	 */
	@RequestMapping( value="/" , method = RequestMethod.GET )
	public String index ( Model model ) {
		model.addAttribute("message", "Configuration (thymeleaf, bootstrap and jQuery) looks all good!");
		return "views/welcome";
	}
	
	/**
	 * gets called when hitting localhost:8080/collapsables
	 * @param model
	 * @return src/main/resources/templates/views/collapsables.html page
	 */
	@RequestMapping( value="/collapsables" , method = RequestMethod.GET )
	public String collapsablesExamples ( Model model ) {
		
		List<Movie> movieList = Arrays.asList(
					new Movie("theRock","The Rock 1997:","Very Cool Movie about Escaping Alcetraz"),
					new Movie("armagadon","Armagadon 1996:","Very Cool Movie about saving Earth"),
					new Movie("savingRyan","Saving Private Ryan:","Very Cool Movie about saving a Soldier")
					);
		
		model.addAttribute("movieList", movieList);
		
		return "views/collapsables";
	}
	
	/**
	 * gets called when hitting localhost:8080/general
	 * @param model
	 * @return src/main/resources/templates/views/general.html page
	 */
	@RequestMapping( value="/general" , method = RequestMethod.GET )
	public String textExamples ( Model model ) {	
		return "views/general";
	}

}
