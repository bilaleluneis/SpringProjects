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
	
	static final String ERRORS_MODEL_KEY = BindingResult.MODEL_KEY_PREFIX + Person.MODEL;
	static final String CONTROLLER_KEY = "controller";
	Logger log;
	
	BaseController(){
		log = Logger.getLogger(this.getClass());
		log.info("Instance of " + this.getClass().getSimpleName() + " Created!");
	}
	
	@Autowired
	@Qualifier("secondValidator")
	Validator validator;
	
	@Autowired
	@Qualifier("pageMap")
	Map<Integer, Page> pageMap;
	
	@Autowired
	HttpSession session;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@ModelAttribute(Person.MODEL)
	private Person getPerson(Model model) {
		log.info("inside getPerson()");
		Person person = (Person)model.asMap().get(Person.MODEL);
		
		if(person == null) {
			log.info("person Obtained from Model is empty.. attempt to get from session");
			person = (Person)session.getAttribute(Person.MODEL);
		}else
			log.info("person Sucessfully Obtained from Model with name property = " + person.getName());
		
		if(person ==  null) {
			log.info("person Obtained from session is empty.. creating new Person()");
			person = new Person();
		}else
			log.info("person Sucessfully Obtained from session with name property = " + person.getName());
		
		model.addAttribute(Person.MODEL, person);
		session.setAttribute(Person.MODEL, person);
		log.info("exiting getPerson()");
		return person;
	}
	
	Page getCurrentPage() {
		Page currentPage = (Page)session.getAttribute(Page.MODEL);
		if(currentPage == null)
			currentPage = pageMap.get(0);
		return currentPage;
	}
	
	@GetMapping
	private String loadPage(Model model, HttpServletRequest request) {
		Page page = (Page) session.getAttribute(Page.MODEL);
		Person local_person =  null;
		BindingResult errors = null;
		
		if(RequestContextUtils.getInputFlashMap(request) != null) {
			log.info("loadPage() Flash Attributes Found!");
			local_person = (Person) RequestContextUtils.getInputFlashMap(request).get(Person.MODEL);
			errors = (BindingResult) RequestContextUtils.getInputFlashMap(request).get(ERRORS_MODEL_KEY);
		}else{
			log.info("loadPage() Flash Attributes not found , pulling from Session!");
			local_person = (Person) session.getAttribute(Person.MODEL);
		}
		
		String urlCurrntlyDisplayed = request.getRequestURL().toString();
		
		if(page == null || urlCurrntlyDisplayed.contains("/welcome")) {
			log.info("loadPage() url is /welcome .. creating new person and getting page id = 0");
			page = pageMap.get(0);
			local_person = new Person();
			session.setAttribute(Page.MODEL, page);
			session.setAttribute(Person.MODEL, local_person);
		}
		
		if(errors != null)
			model.addAttribute(ERRORS_MODEL_KEY, errors);
		
		model.addAttribute(Person.MODEL, local_person);
		model.addAttribute(CONTROLLER_KEY, page.getUrl());
		log.info("loadPage() setting url to /" + page.getUrl());
		log.info("loadPage() forwarding to Tile = " + page.getTileName());
		return page.getTileName();
	}
	
}
