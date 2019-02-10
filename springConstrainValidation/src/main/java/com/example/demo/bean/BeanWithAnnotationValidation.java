package com.example.demo.bean;

import com.example.demo.validator.NoSpecialChars;
import com.example.demo.validator.NotEmpty;

public class BeanWithAnnotationValidation {
	
	@NotEmpty
	@NoSpecialChars
	private String name;
	
	public BeanWithAnnotationValidation(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
