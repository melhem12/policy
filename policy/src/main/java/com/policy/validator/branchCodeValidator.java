package com.policy.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.policy.annotation.BranchCodeAnnotation;
import com.policy.annotation.productCodeAnnotation;


public class branchCodeValidator implements ConstraintValidator<BranchCodeAnnotation, String> {
	public boolean isValid(String field, ConstraintValidatorContext cxt) {
		if (field == null) {
			return false;
		}
		return true;
	}

	

}
