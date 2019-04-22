package com.example.second.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.example.model.Page;
import com.example.model.Person;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since April 2019
 */

public abstract class BaseController {
	
	protected static final Logger log = Logger.getLogger(BaseController.class);
	protected static final String ERRORS_MODEL_KEY = BindingResult.MODEL_KEY_PREFIX + Person.MODEL;
	
	@Autowired
	@Qualifier("secondValidator")
	protected Validator validator;
	
	@Autowired
	@Qualifier("pageMap")
	protected Map<Integer, Page> pageMap;
	
	@Autowired
	protected HttpSession session;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@ModelAttribute(Person.MODEL)
	protected Person getPerson(Model model) {
		log.info("inside getPerson()");
		Person person = (Person)model.asMap().get(Person.MODEL);
		if(person == null) {
			log.info("person Obtained from Model is empty.. attempt to get from session");
			person = (Person)session.getAttribute(Person.MODEL);
		}
		if(person ==  null) {
			log.info("person Obtained from session is empty.. creating new Person()");
			person = new Person();
		}
		model.addAttribute(Person.MODEL, person);
		session.setAttribute(Person.MODEL, person);
		log.info("exiting getPerson()");
		return person;
	}
	
	protected Page getCurrentPage() {
		Page currentPage = (Page)session.getAttribute(Page.MODEL);
		if(currentPage == null)
			currentPage = pageMap.get(0);
		return currentPage;
	}
	
	@GetMapping
	protected String loadPage(Model model, HttpServletRequest request) {
		Page page = (Page) session.getAttribute(Page.MODEL);
		Person local_person =  null;
		BindingResult errors = null;
		
		if(RequestContextUtils.getInputFlashMap(request) != null) {
			local_person = (Person) RequestContextUtils.getInputFlashMap(request).get(Person.MODEL);
			errors = (BindingResult) RequestContextUtils.getInputFlashMap(request).get(ERRORS_MODEL_KEY);
		}else 
			local_person = (Person) session.getAttribute(Person.MODEL);
		
		String urlCurrntlyDisplayed = request.getRequestURL().toString();
		
		if(page == null || urlCurrntlyDisplayed.contains("/welcome")) {
			page = pageMap.get(0);
			local_person = new Person();
			session.setAttribute(Page.MODEL, page);
			session.setAttribute(Person.MODEL, local_person);
		}
		
		if(errors != null)
			model.addAttribute(ERRORS_MODEL_KEY, errors);
		
		model.addAttribute(Person.MODEL, local_person);
		model.addAttribute("controller", page.getUrl());
		log.info("loadPage() setting url to " + page.getUrl());
		log.info("loadPage() forwarding to " + page.getTileName());
		return page.getTileName();
	}
	
}
