package com.example.demo.validator.provider.resolver.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationProviderResolver;
import javax.validation.spi.ValidationProvider;

import com.example.demo.validator.provider.impl.CustomValidatorProvider;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */

public class CustomValidationProviderResolver implements ValidationProviderResolver{

	@Override
	public List<ValidationProvider<?>> getValidationProviders() {
		List<ValidationProvider<?>> providers = new ArrayList<ValidationProvider<?>>(1);
		providers.add(new CustomValidatorProvider());
		return providers;
	}

}
