package com.example.validator;

import javax.validation.groups.Default;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */

public final class ConstraintsOrder { // cannot inherit
	
	private ConstraintsOrder() {} // cannot create instance
	
	public static Class<?>[] getDefaultConstraints(){
		return new Class<?>[] {
			One.class,
			Two.class,
			Three.class,
			Four.class,
			Five.class,
			Default.class
		};
	}
	
	public interface One		{}
	public interface Two		{}
	public interface Three		{}
	public interface Four		{}
	public interface Five		{}

}
