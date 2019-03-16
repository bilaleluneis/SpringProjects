package com.example.second.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since March 2019
 */

@Controller
public class ControllerOne {
	
	@GetMapping("/welcome")
    public String loadForm(){
		return "welcome";
    }

}
