package com.example.first.controller;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.View;

import com.example.model.Person;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since March 2019
 */

@Controller
public class ControllerOne extends BaseController {
	
	private static final Logger log = Logger.getLogger(ControllerOne.class);
	
	@GetMapping("/welcome")
    public String loadForm() throws Exception {
		View view = resolver.resolveViewName("welcome", Locale.US);
		log.debug(view.toString());
		return "welcome";
    }
	
	@PostMapping("/welcome")
	public String postForm(	@Validated
							@ModelAttribute("person") 
							Person person,
							BindingResult errors) {
		if(errors.hasErrors()) {
			log.error("Validation Failed for postForm()");
			return "error";
		}
		return "welcome";
	}

}
