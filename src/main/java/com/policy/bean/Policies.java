package com.policy.bean;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Policies implements Serializable{
	
	private static final long serialVersionUID = 1L;

	//@JsonProperty("Policies")
	@Valid
	private List<Policy> policies ;

	public List<Policy> getPolicies() {
		return policies;
	}

	public void setPolicies(List<Policy> policies) {
		this.policies = policies;
	}
}
