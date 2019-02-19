package com.example.demo.validator.factory.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintValidatorFactory;
import javax.validation.MessageInterpolator;
import javax.validation.ParameterNameProvider;
import javax.validation.TraversableResolver;
import javax.validation.Validator;
import javax.validation.spi.ConfigurationState;

import org.hibernate.validator.internal.engine.MethodValidationConfiguration;
import org.hibernate.validator.internal.engine.ValidatorFactoryImpl;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorManager;
import org.hibernate.validator.internal.metadata.BeanMetaDataManager;
import org.hibernate.validator.internal.metadata.core.ConstraintHelper;
import org.hibernate.validator.internal.metadata.provider.MetaDataProvider;
import org.hibernate.validator.internal.util.ExecutableHelper;
import org.hibernate.validator.internal.util.TypeResolutionHelper;
import org.hibernate.validator.spi.time.TimeProvider;
import org.hibernate.validator.spi.valuehandling.ValidatedValueUnwrapper;

import com.example.demo.validator.impl.CustomValidatorImpl;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */

public class CustomValidatorFactoryImpl extends ValidatorFactoryImpl {

	private ConstraintValidatorManager constrainValidatorManager;
	private TimeProvider timeProvider;
	private MethodValidationConfiguration methodValidationConfiguration;
	private ConstraintHelper constraintHelper;
	private Map<ParameterNameProvider, BeanMetaDataManager> beanMetaDataManagerMap;
	private ExecutableHelper executableHelper;
	TypeResolutionHelper typeResolutionHelper;
	
	public CustomValidatorFactoryImpl(ConfigurationState configurationState) {
		super(configurationState);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Validator getValidator() {
		
		try {
			constrainValidatorManager = (ConstraintValidatorManager)getPrivateField("constraintValidatorManager");
			timeProvider = (TimeProvider) getPrivateField("timeProvider");
			methodValidationConfiguration = (MethodValidationConfiguration) getPrivateField("methodValidationConfiguration");
			constraintHelper = (ConstraintHelper) getPrivateField("constraintHelper");
			beanMetaDataManagerMap = (Map<ParameterNameProvider, BeanMetaDataManager>) getPrivateField("beanMetaDataManagerMap");
			executableHelper = (ExecutableHelper) getPrivateField("executableHelper");
			typeResolutionHelper = (TypeResolutionHelper) getPrivateField("typeResolutionHelper");
			
			return createCustomValidator(   constrainValidatorManager.getDefaultConstraintValidatorFactory(), 
											super.getMessageInterpolator(), 
											super.getTraversableResolver(), 
											super.getParameterNameProvider(), 
											super.isFailFast(), 
											super.getValidatedValueHandlers(), 
											timeProvider, 
											methodValidationConfiguration);
		}catch(Exception e) {
			return super.getValidator();
		}
		
	
	}
	
	Validator createCustomValidator(ConstraintValidatorFactory constraintValidatorFactory,
			MessageInterpolator messageInterpolator,
			TraversableResolver traversableResolver,
			ParameterNameProvider parameterNameProvider,
			boolean failFast,
			List<ValidatedValueUnwrapper<?>> validatedValueHandlers,
			TimeProvider timeProvider,
			MethodValidationConfiguration methodValidationConfiguration) throws 	IllegalArgumentException, 
																					IllegalAccessException, 
																					NoSuchFieldException, 
																					SecurityException, 
																					NoSuchMethodException, 
																					InvocationTargetException {
		
		BeanMetaDataManager beanMetaDataManager;
		if ( !beanMetaDataManagerMap.containsKey( parameterNameProvider ) ) {
			beanMetaDataManager = new BeanMetaDataManager(
					constraintHelper,
					executableHelper,
					parameterNameProvider,
					getMetaDataProviderList( parameterNameProvider ),
					methodValidationConfiguration
			);
			beanMetaDataManagerMap.put( parameterNameProvider, beanMetaDataManager );
		}
		else {
			beanMetaDataManager = beanMetaDataManagerMap.get( parameterNameProvider );
		}

		return new CustomValidatorImpl(
				constraintValidatorFactory,
				messageInterpolator,
				traversableResolver,
				beanMetaDataManager,
				parameterNameProvider,
				timeProvider,
				typeResolutionHelper,
				validatedValueHandlers,
				constrainValidatorManager,
				failFast
		);
		
	}
	
	private Object getPrivateField(String fieldName) throws 	IllegalArgumentException, 
																IllegalAccessException, 
																NoSuchFieldException, 
																SecurityException {
		Field field = ValidatorFactoryImpl.class.getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(this);
	}
	
	
	@SuppressWarnings("unchecked")
	private List<MetaDataProvider> getMetaDataProviderList(ParameterNameProvider parameterNameProvider) throws 	NoSuchMethodException, 
																												SecurityException, 
																												IllegalAccessException, 
																												IllegalArgumentException, 
																												InvocationTargetException{
		
		Method buildDataProviders = getClass().getSuperclass().getDeclaredMethod("buildDataProviders", new Class<?>[]{ParameterNameProvider.class});
		buildDataProviders.setAccessible(true);
		return (List<MetaDataProvider>)buildDataProviders.invoke(this, new Object[] {parameterNameProvider});
		
	}
		
	
	
}
