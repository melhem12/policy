package com.policy.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.policy.annotation.ProductIdAnnotation;
import com.policy.annotation.SubLineCodeAnnotation;
import com.policy.repository.DB;

public class SubLineCodeValidator implements ConstraintValidator<SubLineCodeAnnotation, String> {
	public DB db;

	public boolean isValid( String productField, ConstraintValidatorContext cxt) {

		if (productField == null) {

			return false;
		}

		return true;
	}

}
