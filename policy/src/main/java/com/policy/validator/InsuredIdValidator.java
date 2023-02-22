package com.policy.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.policy.annotation.InsuredIdAnnotation;
import com.policy.repository.DB;

public class InsuredIdValidator implements ConstraintValidator<InsuredIdAnnotation, Integer> {
	public DB db;

	public boolean isValid(Integer productField, ConstraintValidatorContext cxt) {
		
		if (productField == 0) {
			
			
			return false;
		}
		
	return true;
		}

	

	

}
