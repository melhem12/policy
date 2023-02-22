package com.policy.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.policy.annotation.ProductDescriptionAnnotation;
import com.policy.annotation.productCodeAnnotation;


public class productDescriptionValidator implements ConstraintValidator<ProductDescriptionAnnotation, String> {
	public boolean isValid(String field, ConstraintValidatorContext cxt) {
		if (field == null) {
			return false;
		}
		return true;
	}

	

}
