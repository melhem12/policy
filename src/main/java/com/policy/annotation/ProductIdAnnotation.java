package com.policy.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.policy.validator.productIdValidator;
	@Target( { ElementType.FIELD, ElementType.PARAMETER })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@Constraint(validatedBy = productIdValidator.class)
public @interface ProductIdAnnotation {


	    //error message
	    public String message() default "ProductId must not be null";
	    //represents group of constraints
	    public Class<?>[] groups() default {};
	    //represents additional information about annotation
	    public Class<? extends Payload>[] payload() default {};
}
