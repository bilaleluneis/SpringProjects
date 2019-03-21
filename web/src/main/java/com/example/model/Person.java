package com.example.model;

import org.hibernate.validator.constraints.NotBlank;

import com.example.validator.ConstraintsOrder.One;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since March 2019
 */

public class Person {
	
	@NotBlank(message="{error.name.empty}", groups=One.class)
	private String name;
	private Integer age;
	
	public Person() {
		this.name = "";
		this.age = Integer.valueOf(0);
	}
	
	public Person(String name, int age) {
		this.name = new String(name);
		this.age = new Integer(age);
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
