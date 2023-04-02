package com.policy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Entity
@Table(name = "CARS_POLICY_WORDING_D")
public class CarsPolicyWordingD  {

	private static final long serialVersionUID = 1L;

	private static Log logger = LogFactory.getLog(CarsPolicyWordingD.class);
	@Id
	@Column(name = "POLICY_WORDING_D_ID")
	private java.lang.String policyWordingDId;
	
	@Column(name = "POLICY_WORDING_D_CODE")
	private java.lang.String policyWordingDCode;
	
	@Column(name = "POLICY_WORDING_D_LIGNE")
	private java.lang.Integer policyWordingDLine;
	
	@Column(name = "POLICY_WORDING_D_FREE_TEXT")
	private java.lang.String policyWordingDFreeText;
	
	@Column(name = "WORDING_H_ID")
	private java.lang.String policyWordingHId;

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
	
	@Column(name = "POLICY_WORDING_D_HTML")
	private java.lang.String policyWordingDHtml;


	
	public String getPolicyWordingDId() {
		return policyWordingDId;
	}

	public void setPolicyWordingDId(String policyWordingDId) {
		this.policyWordingDId = policyWordingDId;
	}

	public String getPolicyWordingDCode() {
		return policyWordingDCode;
	}

	public void setPolicyWordingDCode(String policyWordingDCode) {
		this.policyWordingDCode = policyWordingDCode;
	}

	public java.lang.Integer getPolicyWordingDLine() {
		return policyWordingDLine;
	}

	public void setPolicyWordingDLine(java.lang.Integer policyWordingDLine) {
		this.policyWordingDLine = policyWordingDLine;
	}

	public String getPolicyWordingDFreeText() {
		return policyWordingDFreeText;
	}

	public void setPolicyWordingDFreeText(String policyWordingDFreeText) {
		this.policyWordingDFreeText = policyWordingDFreeText;
	}

	public String getPolicyWordingHId() {
		return policyWordingHId;
	}

	public void setPolicyWordingHId(String policyWordingHId) {
		this.policyWordingHId = policyWordingHId;
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

	public java.lang.String getPolicyWordingDHtml() {
		return policyWordingDHtml;
	}

	public void setPolicyWordingDHtml(java.lang.String policyWordingDHtml) {
		this.policyWordingDHtml = policyWordingDHtml;
	}


}
