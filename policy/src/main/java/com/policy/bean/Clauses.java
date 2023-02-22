package com.policy.bean;

import java.io.Serializable;

import javax.validation.constraints.Min;

public class Clauses implements Serializable{
	
	private static final long serialVersionUID = 1L;

//	@Min(value=1, message="age: positive , min 18 is required")
	private Integer clauseID;
	
	private String code;
	
	private String description;
	
	private String content;
	
    private Integer  order;

	public Integer getClauseID() {
		return clauseID;
	}

	public void setClauseID(Integer clauseID) {
		this.clauseID = clauseID;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	

	

	


}
