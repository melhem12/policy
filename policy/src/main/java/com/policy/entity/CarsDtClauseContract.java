package com.policy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//to ask about clauseID ,description,content ,order

@Entity
@Table(name = "CARS_DT_CLAUSE_CONTRACT")
public class CarsDtClauseContract {
	
	@Id
	@Column(name = "DT_ID")
	private String dtId;
	
	@Column(name = "INS_ID")
	private String insID;
	
	@Column(name = "POLICY_ID")
	private String policyID;
	
	@Column(name = "CODE")
	private String code;

	
	@Column(name = "SHORT_DESC")
	private String shortDesc;

	@Column(name = "ClAUSE_TEXT")
	private String clauseText;
	
	@Column(name = "LINE")// 
	private String order;

	public String getDtId() {
		return dtId;
	}

	public void setDtId(String dtId) {
		this.dtId = dtId;
	}

	public String getInsID() {
		return insID;
	}

	public void setInsID(String insID) {
		this.insID = insID;
	}

	public String getPolicyID() {
		return policyID;
	}

	public void setPolicyID(String policyID) {
		this.policyID = policyID;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getClauseText() {
		return clauseText;
	}

	public void setClauseText(String clauseText) {
		this.clauseText = clauseText;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}


}
