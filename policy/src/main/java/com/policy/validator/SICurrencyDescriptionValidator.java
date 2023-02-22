package com.policy.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.policy.annotation.BranchDescriptionAnnotation;
import com.policy.annotation.PremCurrencyDescriptionAnnotation;
import com.policy.annotation.SICurrencyDescriptionAnnotation;
import com.policy.annotation.productCodeAnnotation;


public class SICurrencyDescriptionValidator implements ConstraintValidator<SICurrencyDescriptionAnnotation, String> {
	public boolean isValid(String field, ConstraintValidatorContext cxt) {
		if (field == null) {
			return false;
		}
		return true;
	}

	

}
