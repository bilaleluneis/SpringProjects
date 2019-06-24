package org.hibernate.validator.internal.engine;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import javax.validation.ConstraintValidatorFactory;
import javax.validation.Validator;
import javax.validation.spi.ConfigurationState;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorManager;
import org.hibernate.validator.internal.engine.groups.ValidationOrderGenerator;
import org.hibernate.validator.internal.engine.valueextraction.ValueExtractorManager;
import org.hibernate.validator.internal.metadata.BeanMetaDataManager;
import org.hibernate.validator.internal.metadata.core.ConstraintHelper;
import org.hibernate.validator.internal.metadata.provider.MetaDataProvider;
import org.hibernate.validator.internal.util.ExecutableHelper;
import org.hibernate.validator.internal.util.ExecutableParameterNameProvider;
import org.hibernate.validator.internal.util.TypeResolutionHelper;
import org.hibernate.validator.internal.util.stereotypes.ThreadSafe;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */

public class CustomValidatorFactoryImpl extends ValidatorFactoryImpl {

	private ConstraintHelper constraintHelper;
	private ExecutableHelper executableHelper;
	private TypeResolutionHelper typeResolutionHelper;
	private ValidationOrderGenerator validationOrderGenerator;
	private ConstraintValidatorManager constraintValidatorManager;
	
	
	@ThreadSafe
	private ConcurrentMap<BeanMetaDataManagerKey, BeanMetaDataManager> beanMetaDataManagers;
	
	public CustomValidatorFactoryImpl(ConfigurationState configurationState) {
		super(configurationState);
	}

	
	
	
	@SuppressWarnings({ "unchecked" })
	@Override
	Validator createValidator(	ConstraintValidatorFactory constraintValidatorFactory,
								ValueExtractorManager valueExtractorManager,
								ValidatorFactoryScopedContext validatorFactoryScopedContext,
								MethodValidationConfiguration methodValidationConfiguration	) {
		
		try {

			beanMetaDataManagers = (ConcurrentMap<BeanMetaDataManagerKey, BeanMetaDataManager>) getPrivateField("beanMetaDataManagers");
			constraintHelper = (ConstraintHelper) getPrivateField("constraintHelper");
			executableHelper = (ExecutableHelper) getPrivateField("executableHelper");
			typeResolutionHelper = (TypeResolutionHelper) getPrivateField("typeResolutionHelper");
			validationOrderGenerator = (ValidationOrderGenerator) getPrivateField("validationOrderGenerator");
			constraintValidatorManager = (ConstraintValidatorManager) getPrivateField("constraintValidatorManager");

			List<MetaDataProvider> dataProviderList = getMetaDataProviderList(); // did this to avoid having to catch exception inside lambda

			BeanMetaDataManager beanMetaDataManager = beanMetaDataManagers.computeIfAbsent(
							
							new BeanMetaDataManagerKey(	validatorFactoryScopedContext.getParameterNameProvider(),
														valueExtractorManager, 
														methodValidationConfiguration),
							
							key -> new BeanMetaDataManager(	constraintHelper, 
															executableHelper, 
															typeResolutionHelper,
															validatorFactoryScopedContext.getParameterNameProvider(), 
															valueExtractorManager,
															validationOrderGenerator, 
															dataProviderList, 
															methodValidationConfiguration )	);
			
			return new CustomValidator( constraintValidatorFactory,
										beanMetaDataManager,
										valueExtractorManager,
										constraintValidatorManager,
										validationOrderGenerator,
										validatorFactoryScopedContext );
			
		} catch (Exception exception) {
			// TODO: log the error and return instance of default validator
			return super.createValidator(	constraintValidatorFactory, 
											valueExtractorManager, 
											validatorFactoryScopedContext, 
											methodValidationConfiguration	);
		}
		
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
	private List<MetaDataProvider> getMetaDataProviderList() throws 	NoSuchMethodException, 
																		SecurityException, 
																		IllegalAccessException, 
																		IllegalArgumentException, 
																		InvocationTargetException{
		
		Method buildDataProviders = getClass().getSuperclass().getDeclaredMethod("buildDataProviders");
		buildDataProviders.setAccessible(true);
		return (List<MetaDataProvider>)buildDataProviders.invoke(this);
		
	}
	
	//copied from super class as I couldn't get it using reflection yet
	//TODO: figure how to get this out of the base class using reflection api
	private static class BeanMetaDataManagerKey {
		private final ExecutableParameterNameProvider parameterNameProvider;
		private final ValueExtractorManager valueExtractorManager;
		private final MethodValidationConfiguration methodValidationConfiguration;
		private final int hashCode;

		public BeanMetaDataManagerKey(ExecutableParameterNameProvider parameterNameProvider, ValueExtractorManager valueExtractorManager, MethodValidationConfiguration methodValidationConfiguration) {
			this.parameterNameProvider = parameterNameProvider;
			this.valueExtractorManager = valueExtractorManager;
			this.methodValidationConfiguration = methodValidationConfiguration;
			this.hashCode = buildHashCode( parameterNameProvider, valueExtractorManager, methodValidationConfiguration );
		}

		private static int buildHashCode(ExecutableParameterNameProvider parameterNameProvider, ValueExtractorManager valueExtractorManager, MethodValidationConfiguration methodValidationConfiguration) {
			final int prime = 31;
			int result = 1;
			result = prime * result + ( ( methodValidationConfiguration == null ) ? 0 : methodValidationConfiguration.hashCode() );
			result = prime * result + ( ( parameterNameProvider == null ) ? 0 : parameterNameProvider.hashCode() );
			result = prime * result + ( ( valueExtractorManager == null ) ? 0 : valueExtractorManager.hashCode() );
			return result;
		}

		@Override
		public int hashCode() {
			return hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if ( this == obj ) {
				return true;
			}
			if ( obj == null ) {
				return false;
			}
			if ( getClass() != obj.getClass() ) {
				return false;
			}
			BeanMetaDataManagerKey other = (BeanMetaDataManagerKey) obj;

			return methodValidationConfiguration.equals( other.methodValidationConfiguration ) &&
					parameterNameProvider.equals( other.parameterNameProvider ) &&
					valueExtractorManager.equals( other.valueExtractorManager );
		}

		@Override
		public String toString() {
			return "BeanMetaDataManagerKey [parameterNameProvider=" + parameterNameProvider + ", valueExtractorManager=" + valueExtractorManager
					+ ", methodValidationConfiguration=" + methodValidationConfiguration + "]";
		}
	}
	//
		
	
	
}
