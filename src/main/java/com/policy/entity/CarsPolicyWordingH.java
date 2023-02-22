package com.policy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Entity
@Table(name = "CARS_POLICY_WORDING_H")
public class CarsPolicyWordingH {

	private static final long serialVersionUID = 1L;

	private static Log logger = LogFactory.getLog(CarsPolicyWordingH.class);
	@Id
	@Column(name = "POLICY_WORDING_H_ID")
	private java.lang.String policyWordingHId;
	
	@Column(name = "POLICY_WORDING_H_BRANCH")
	private java.lang.Integer policyWordingHBranch;
	
	@Column(name = "POLICY_ID")
	private java.lang.String policyId;
	
	@Column(name = "WORDING_ID")
	private java.lang.String wordingId;
	
	@Column(name = "SYS_VERSION_NUMBER")
	private java.lang.Integer sysVersionNumber;

	@Column(name = "SYS_CREATED_DATE")
	private java.sql.Timestamp sysCreatedDate;

	@Column(name = "SYS_UPDATED_DATE")
	private java.sql.Timestamp sysUpdatedDate;

	@Column(name = "SYS_CREATED_BY")
	private java.lang.String sysCreatedBy;

	@Column(name = "SYS_UPDATED_BY")
	private java.lang.String sysUpdatedBy;


	public String getPolicyWordingHId() {
		return policyWordingHId;
	}

	public void setPolicyWordingHId(String policyWordingHId) {
		this.policyWordingHId = policyWordingHId;
	}

	public java.lang.String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(java.lang.String policyId) {
		this.policyId = policyId;
	}

	public java.lang.String getWordingId() {
		return wordingId;
	}

	public void setWordingId(java.lang.String wordingId) {
		this.wordingId = wordingId;
	}

	public java.lang.Integer getSysVersionNumber() {
		return sysVersionNumber;
	}

	public void setSysVersionNumber(java.lang.Integer sysVersionNumber) {
		this.sysVersionNumber = sysVersionNumber;
	}

	public java.sql.Timestamp getSysCreatedDate() {
		return sysCreatedDate;
	}

	public void setSysCreatedDate(java.sql.Timestamp sysCreatedDate) {
		this.sysCreatedDate = sysCreatedDate;
	}

	public java.sql.Timestamp getSysUpdatedDate() {
		return sysUpdatedDate;
	}

	public void setSysUpdatedDate(java.sql.Timestamp sysUpdatedDate) {
		this.sysUpdatedDate = sysUpdatedDate;
	}

	public java.lang.String getSysCreatedBy() {
		return sysCreatedBy;
	}

	public void setSysCreatedBy(java.lang.String sysCreatedBy) {
		this.sysCreatedBy = sysCreatedBy;
	}

	public java.lang.String getSysUpdatedBy() {
		return sysUpdatedBy;
	}

	public void setSysUpdatedBy(java.lang.String sysUpdatedBy) {
		this.sysUpdatedBy = sysUpdatedBy;
	}

	public java.lang.Integer getPolicyWordingHBranch() {
		return policyWordingHBranch;
	}

	public void setPolicyWordingHBranch(java.lang.Integer policyWordingHBranch) {
		this.policyWordingHBranch = policyWordingHBranch;
	}

}
