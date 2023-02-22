package com.policy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CARS_BROKER")
public class CarsBroker {

	@Id
	@Column(name = "BROKER_ID")
	private java.lang.String brokerId;
	
	@Column(name = "BROKER_NUM")
	private java.lang.String brokerNum;
	
	@Column(name = "BROKER_DESC")
	private java.lang.String brokerDesc;
	
	@Column(name = "BROKER_CONTACT_INFO")
	private java.lang.String brokerContactInfo;
	
	@Column(name = "BROKER_TELEPHONE")
	private java.lang.String brokerTelephone;
	
	@Column(name = "BROKER_DEPR")
	private java.lang.Integer brokerDepr;
	
	@Column(name = "BROKER_BRANCH")
	private java.lang.String brokerBranch;
	
	@Column(name = "BROKER_INSURANCE_ID")
	private java.lang.String brokerInsuranceId;
	
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

	@Column(name = "BROKER_REFERENCE")
	private java.lang.String brokerReference;
	
	public java.lang.String getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(java.lang.String brokerId) {
		this.brokerId = brokerId;
	}

	public java.lang.String getBrokerNum() {
		return brokerNum;
	}

	public void setBrokerNum(java.lang.String brokerNum) {
		this.brokerNum = brokerNum;
	}

	public java.lang.String getBrokerDesc() {
		return brokerDesc;
	}

	public void setBrokerDesc(java.lang.String brokerDesc) {
		this.brokerDesc = brokerDesc;
	}

	public java.lang.String getBrokerContactInfo() {
		return brokerContactInfo;
	}

	public void setBrokerContactInfo(java.lang.String brokerContactInfo) {
		this.brokerContactInfo = brokerContactInfo;
	}

	public java.lang.String getBrokerTelephone() {
		return brokerTelephone;
	}

	public void setBrokerTelephone(java.lang.String brokerTelephone) {
		this.brokerTelephone = brokerTelephone;
	}

	public java.lang.Integer getBrokerDepr() {
		return brokerDepr;
	}

	public void setBrokerDepr(java.lang.Integer brokerDepr) {
		this.brokerDepr = brokerDepr;
	}

	public java.lang.String getBrokerBranch() {
		return brokerBranch;
	}

	public void setBrokerBranch(java.lang.String brokerBranch) {
		this.brokerBranch = brokerBranch;
	}

	public java.lang.String getBrokerInsuranceId() {
		return brokerInsuranceId;
	}

	public void setBrokerInsuranceId(java.lang.String brokerInsuranceId) {
		this.brokerInsuranceId = brokerInsuranceId;
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

	public java.lang.String getBrokerReference() {
		return brokerReference;
	}

	public void setBrokerReference(java.lang.String brokerReference) {
		this.brokerReference = brokerReference;
	}
	

}
