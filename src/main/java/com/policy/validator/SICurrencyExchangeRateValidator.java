package com.policy.validator;


import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.policy.annotation.BranchDescriptionAnnotation;
import com.policy.annotation.PremCurrencyExchangeRateAnnotation;
import com.policy.annotation.SICurrencyExchangeRateAnnotation;
import com.policy.annotation.productCodeAnnotation;


public class SICurrencyExchangeRateValidator implements ConstraintValidator<SICurrencyExchangeRateAnnotation, BigDecimal> {
	public boolean isValid(BigDecimal field, ConstraintValidatorContext cxt) {
		if (field == null) {
			return false;
		}
		return true;
	}

	

}
