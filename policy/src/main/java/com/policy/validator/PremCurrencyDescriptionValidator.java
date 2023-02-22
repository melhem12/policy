package com.policy.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.policy.annotation.BranchDescriptionAnnotation;
import com.policy.annotation.PremCurrencyDescriptionAnnotation;
import com.policy.annotation.productCodeAnnotation;


public class PremCurrencyDescriptionValidator implements ConstraintValidator<PremCurrencyDescriptionAnnotation, String> {
	public boolean isValid(String field, ConstraintValidatorContext cxt) {
		if (field == null) {
			return false;
		}
		return true;
	}

	

}
