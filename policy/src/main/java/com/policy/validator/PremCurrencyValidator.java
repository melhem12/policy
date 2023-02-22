package com.policy.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.policy.annotation.BranchDescriptionAnnotation;
import com.policy.annotation.PremCurrencyAnnotation;
import com.policy.annotation.productCodeAnnotation;


public class PremCurrencyValidator implements ConstraintValidator<PremCurrencyAnnotation, String> {
	public boolean isValid(String field, ConstraintValidatorContext cxt) {
		if (field == null) {
			return false;
		}
		return true;
	}

	

}
