package com.example.demo.bean;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.example.demo.validator.constrain.NotNullProperty;
import com.example.demo.validator.impl.ConstraintsOrder.One;
import com.example.demo.validator.impl.ConstraintsOrder.Two;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */

public class BeanWithAnnotationValidation extends BaseBean{
	
	@Override
	public boolean skipValidationCheck() {
		return true;
	}

	@NotNullProperty(message="name connot be null", groups=One.class)
	@Size(min=2, max=15, message="length should be larger than 0 and smaller or equal to 10 characters.", groups=Two.class)
	private String name;
	
	@NotNullProperty(message="age cannot be empty", groups=One.class)
	@Size(min=2, max=3, message="age cannot be less than 2 or more than 3 digits", groups=Two.class)
	private String age;
	
	@NotEmpty(message="height is empty", groups=One.class)
	@Size(min=2, message="hight should be 2 to 3 digits long", groups=Two.class)
	private String height;
	
	private String weight;
	
	public BeanWithAnnotationValidation() {
		this.name = "";
		this.age = "";
		this.height = "";
		this.weight = "";
	}
	
	public BeanWithAnnotationValidation(String name, String age, String height, String weight) {
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

}
