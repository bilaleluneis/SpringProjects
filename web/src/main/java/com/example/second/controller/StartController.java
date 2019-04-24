package com.example.second.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.model.Page;
import com.example.model.Person;
import com.example.servlet.SecondDispatcherServlet;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since April 2019
 */

@Controller
public final class StartController extends BaseController {
	
	private StartController() {
		super();
	}
	
	@RequestMapping(value= {"/welcome", "/controllerPage*"}, params= {"start"}, method=RequestMethod.POST)
	View startPost(	@ModelAttribute(Person.MODEL) 
					Person person, 
					BindingResult errors,
					RedirectAttributes redirect, 
					HttpServletRequest request ) {
		log.info("In Start button Post Handler Method ...");
		String urlCurrntlyDisplayed = request.getRequestURL().toString();
		Page page = getCurrentPage();
		RedirectView rv = new RedirectView();
		rv.setContextRelative(true);
		rv.setExposeModelAttributes(false);
		Integer currentPageId = page.getPageId();
		log.info("Current page ID = " + currentPageId);
		if(urlCurrntlyDisplayed.contains("/welcome"))
			page = pageMap.get(1);
		else
			page = pageMap.get(0);
		log.info("Start button Post Handler Method setting page Id to " + page.getPageId());
		session.setAttribute(Page.MODEL, page);
		rv.setUrl(SecondDispatcherServlet.ROOT_CONTEXT + "/" + page.getUrl());
		redirect.addFlashAttribute(Person.MODEL, person);
		session.setAttribute(Person.MODEL, person);
		log.info("Start button Post Handler Method redirecting to /" + page.getUrl());
		log.info("Exiting Start button Post Handler Method ...");
		return rv;
	}

}
