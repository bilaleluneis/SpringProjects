package com.one.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
//@ComponentScan(basePackages="com.one")
public class ControllerOne {
	
	@Autowired
	@Qualifier("resolverOne")
	private ViewResolver resolver;
	
	@GetMapping("/one")
    public ModelAndView loadForm(Model m) throws Exception {
		View view = resolver.resolveViewName("welcome", Locale.US);
		return new ModelAndView(view);
    }

}
