package com.policy.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.policy.annotation.SubLineDescriptionAnnotation;
import com.policy.repository.DB;

public class SubLineDescriptionValidator implements ConstraintValidator<SubLineDescriptionAnnotation, String> {
	public DB db;

	public boolean isValid(String productField, ConstraintValidatorContext cxt) {
		
		if (productField.isEmpty()) {
			
			
			return false;
		}
		
	return true;
		}


	


}
