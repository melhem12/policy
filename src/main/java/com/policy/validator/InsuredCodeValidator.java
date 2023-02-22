package com.policy.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.policy.annotation.InsuredCodeAnnotation;
import com.policy.annotation.productCodeAnnotation;


public class InsuredCodeValidator implements ConstraintValidator<InsuredCodeAnnotation, String> {
	public boolean isValid(String field, ConstraintValidatorContext cxt) {
		if (field == null) {
			return false;
		}
		return true;
	}

	

}
