package com.two.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since March 2019
 */

@Controller
//@ComponentScan(basePackages="com.two")
public class ControllerTwo {
	
	@Autowired
	private ViewResolver resolver;
	
	@GetMapping("/two")
    public ModelAndView loadForm(Model m) throws Exception {
		View view = resolver.resolveViewName("welcome", Locale.US);
        return new ModelAndView(view);
    }

}
