package com.example.demo.bean;

import com.example.demo.validator.constrain.SkipValidationCheck;
import com.example.demo.validator.impl.ConstraintsOrder.Zero;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */

@SkipValidationCheck(groups=Zero.class)

public abstract class BaseBean {
	
	public boolean skipValidationCheck() {
		return false;
	}

}
