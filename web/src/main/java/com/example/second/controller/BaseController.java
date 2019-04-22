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

import com.example.model.Page;
import com.example.model.Person;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since April 2019
 */

public abstract class BaseController {
	
	static final Logger log = Logger.getLogger(BaseController.class);
	static final String ERRORS_MODEL_KEY = BindingResult.MODEL_KEY_PREFIX + Person.MODEL;
	
	@Autowired
	@Qualifier("secondValidator")
	Validator validator;
	
	@Autowired
	@Qualifier("pageMap")
	Map<Integer, Page> pageMap;
	
	@Autowired
	HttpSession session;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@ModelAttribute(Person.MODEL)
	public Person getPerson(Model model) {
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
	
	@GetMapping
	public String loadPage(@ModelAttribute(Person.MODEL) Person person, Model model, HttpServletRequest request) {
		Page page = (Page) session.getAttribute(Page.MODEL);
		Person local_person = person;
		String urlCurrntlyDisplayed = request.getRequestURL().toString();
		if(page == null || urlCurrntlyDisplayed.contains("/welcome")) {
			page = pageMap.get(0);
			local_person = new Person();
			session.setAttribute(Page.MODEL, page);
			session.setAttribute(Person.MODEL, local_person);
		}
		if(local_person == null || !model.containsAttribute(Person.MODEL)) 
			local_person = (Person)session.getAttribute(Person.MODEL);
		if(session.getAttribute(ERRORS_MODEL_KEY) != null) {
			BindingResult errors = (BindingResult) session.getAttribute(ERRORS_MODEL_KEY);
			model.addAttribute(ERRORS_MODEL_KEY, errors);
			session.removeAttribute(ERRORS_MODEL_KEY);
		}
		model.addAttribute(Person.MODEL, local_person);
		model.addAttribute("controller", page.getUrl());
		log.info("loadPage() setting url to " + page.getUrl());
		log.info("loadPage() forwarding to " + page.getTileName());
		return page.getTileName();
	}
	
}
