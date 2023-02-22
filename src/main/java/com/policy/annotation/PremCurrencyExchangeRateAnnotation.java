package com.policy.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.policy.validator.PremCurrencyExchangeRateValidator;
import com.policy.validator.SubLineCodeValidator;
import com.policy.validator.SubLineDescriptionValidator;
import com.policy.validator.branchCodeValidator;
	@Target( { ElementType.FIELD, ElementType.PARAMETER })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@Constraint(validatedBy = PremCurrencyExchangeRateValidator.class)
	public @interface PremCurrencyExchangeRateAnnotation {
		public String message() default "premCurrencyExchangeRate must not be null";
	    //represents group of constraints
	    public Class<?>[] groups() default {};
	    //represents additional information about annotation
	    public Class<? extends Payload>[] payload() default {};
	}
