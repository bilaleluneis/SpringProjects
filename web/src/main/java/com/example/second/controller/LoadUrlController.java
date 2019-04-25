package com.example.second.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import com.example.model.Page;
import com.example.model.Person;
import com.example.servlet.SecondDispatcherServlet;

@Controller
public final class LoadUrlController extends BaseController {
	
	private LoadUrlController() {super();}
	
	@GetMapping({"/welcome", "/controllerPage*"})
	private View loadPage(Model model, HttpServletRequest request) throws Exception {
		
		Page page = (Page) session.getAttribute(Page.MODEL);
		Person local_person =  null;
		BindingResult errors = null;
		String urlCurrntlyDisplayed = request.getRequestURL().toString();
		
		if(page == null || urlCurrntlyDisplayed.contains("/welcome")) {
			log.info("loadPage() url is /welcome .. creating new person and getting page id = 0");
			page = pageMap.get(0);
			local_person = new Person();
			session.setAttribute(Page.MODEL, page);
			session.setAttribute(Person.MODEL, local_person);
		}else if(RequestContextUtils.getInputFlashMap(request) != null) {
			log.info("loadPage() Flash Attributes Found!");
			local_person = (Person) RequestContextUtils.getInputFlashMap(request).get(Person.MODEL);
			errors = (BindingResult) RequestContextUtils.getInputFlashMap(request).get(ERRORS_MODEL_KEY);
		}else{ // got here.. means someone is trying to access ContorollerPage* URL without going through front door.
			log.error("loadPage() an attempt to access ControllerPage* via url manipulation ..");
			log.info("Redirecting to front door ...");
			RedirectView rv = new RedirectView();
			rv.setContextRelative(true);
			rv.setExposeModelAttributes(false);
			rv.setUrl(SecondDispatcherServlet.ROOT_CONTEXT + "/welcome");
			return rv;
		}
		
		
		if(errors != null)
			model.addAttribute(ERRORS_MODEL_KEY, errors);
		
		model.addAttribute(Person.MODEL, local_person);
		model.addAttribute(CONTROLLER_KEY, page.getUrl());
		log.info("loadPage() setting url to /" + page.getUrl());
		log.info("loadPage() forwarding to Tile = " + page.getTileName());
		
		return viewResolver.resolveViewName(page.getTileName(), Locale.US);
		
	}

}
