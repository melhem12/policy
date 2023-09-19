package com.policy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CARS_DT_POLICY_TRANSFER_LOG")
public class 	CarsDtPolicyTransferLog {

	@Id
	@Column(name = "DT_ID")
	private java.lang.String dtId;
	
	@Column(name = "INS_ID")
	private java.lang.String insId;
	
	@Column(name = "SCRIPT_NAME ")
	private java.lang.String scriptName;
	
	@Column(name = "POL_POLICY_NUM")
	private java.lang.String polPolicyNum;
	
	@Column(name = "POL_POLICY_ID")
	private java.lang.String polPolicyId;
	
	@Column(name = "POL_TRANSFER_STATUS")
	private java.lang.String polTransferStatus;
	
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

	public java.lang.String getDtId() {
		return dtId;
	}

	public void setDtId(java.lang.String dtId) {
		this.dtId = dtId;
	}

	public java.lang.String getInsId() {
		return insId;
	}

	public void setInsId(java.lang.String insId) {
		this.insId = insId;
	}

	public java.lang.String getScriptName() {
		return scriptName;
	}

	public void setScriptName(java.lang.String scriptName) {
		this.scriptName = scriptName;
	}

	public java.lang.String getPolPolicyNum() {
		return polPolicyNum;
	}

	public void setPolPolicyNum(java.lang.String polPolicyNum) {
		this.polPolicyNum = polPolicyNum;
	}

	public java.lang.String getPolPolicyId() {
		return polPolicyId;
	}

	public void setPolPolicyId(java.lang.String polPolicyId) {
		this.polPolicyId = polPolicyId;
	}

	public java.lang.String getPolTransferStatus() {
		return polTransferStatus;
	}

	public void setPolTransferStatus(java.lang.String polTransferStatus) {
		this.polTransferStatus = polTransferStatus;
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

	
	
	

}
