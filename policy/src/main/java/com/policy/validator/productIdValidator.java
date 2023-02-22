package com.policy.validator;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.policy.annotation.ProductIdAnnotation;
import com.policy.entity.CarsProducts;
import com.policy.repository.DB;

public class productIdValidator implements ConstraintValidator<ProductIdAnnotation, Integer> {
	public DB db;

	public boolean isValid(Integer productField, ConstraintValidatorContext cxt) {
		
		if (productField == 0) {
			
			
			return false;
		}
		
	return true;
		}

	

	@Override
	public void initialize(ProductIdAnnotation constraintAnnotation) {
		// TODO Auto-generated method stub
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

}
