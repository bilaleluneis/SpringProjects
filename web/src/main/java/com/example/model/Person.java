package com.example.model;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotBlank;

import com.example.validator.ConstraintsOrder.One;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since March 2019
 */

public class Person {
	
	private static final Logger log = Logger.getLogger(Person.class);
	
	@NotBlank(message="{error.name.empty}", groups=One.class)
	private String name;
	private Integer age;
	
	public Person() {
		log.debug("Person() constructor!");
		this.name = "";
		this.age = Integer.valueOf(0);
	}
	
	public Person(String name, int age) {
		log.debug("Person(name, age) constructor!");
		this.name = new String(name);
		this.age = new Integer(age);
	}
	
	@PostConstruct
	public void init() {
		log.debug("Person @PostConstruct init()!");
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}

}
