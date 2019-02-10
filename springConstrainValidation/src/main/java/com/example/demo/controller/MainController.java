package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.bean.BeanWithAnnotationValidation;

@Controller
public class MainController {

	@GetMapping("/main")
    public String loadFormPage(Model m) {
        m.addAttribute("bean", new BeanWithAnnotationValidation("validName"));
        return "inputPage";
    }
	
	@PostMapping("/main")
    public String submitForm(@Valid @ModelAttribute("bean")BeanWithAnnotationValidation bean, BindingResult errors) {
		if(errors.hasErrors()) {
			return "errorPage";
		}
		return "resultPage";
	}
}
