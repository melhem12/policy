package com.policy.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.policy.annotation.PolicyAnnotation;
import com.policy.annotation.SubLineDescriptionAnnotation;
import com.policy.bean.Policy;
import com.policy.repository.DB;

public class policyValidator implements ConstraintValidator<PolicyAnnotation, Policy> {
	public DB db;

	public boolean isValid(Policy policy, ConstraintValidatorContext cxt) {
		
		if (policy.getBranchCode() == null) {
			
			
			return false;
		}
		
	return true;
		}

	


}
