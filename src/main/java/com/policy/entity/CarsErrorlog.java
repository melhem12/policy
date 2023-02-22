package com.policy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CARS_ERRORLOG")
public class CarsErrorlog {

	@Id
	@Column(name = "ERRORLOG_ID")
	private java.lang.String errorlogId;
	
	@Column(name = "ERRORLOG_INSURANCE_ID")
	private java.lang.String errorlogInsuranceId;
	
	@Column(name = "ERRORLOG_ERROR_MSG")
	private java.lang.String errorlogErrorMessage;
	
	@Column(name = "ERRORLOG_KEY_VALUE")
	private java.lang.String errorlogKeyValue;
	
	@Column(name = "ERRORLOG_PROG_NAME")
	private java.lang.String errorlogProgName;
	
	@Column(name = "ERRORLOG_TABLE_NAME")
	private java.lang.String errorlogTableName;
	
	@Column(name = "ERRORLOG_ERROR_CODE")
	private java.lang.String errorlogErrorCode;
	
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

	public java.lang.String getErrorlogId() {
		return errorlogId;
	}

	public void setErrorlogId(java.lang.String errorlogId) {
		this.errorlogId = errorlogId;
	}

	public java.lang.String getErrorlogInsuranceId() {
		return errorlogInsuranceId;
	}

	public void setErrorlogInsuranceId(java.lang.String errorlogInsuranceId) {
		this.errorlogInsuranceId = errorlogInsuranceId;
	}

	public java.lang.String getErrorlogErrorMessage() {
		return errorlogErrorMessage;
	}

	public void setErrorlogErrorMessage(java.lang.String errorlogErrorMessage) {
		this.errorlogErrorMessage = errorlogErrorMessage;
	}

	public java.lang.String getErrorlogKeyValue() {
		return errorlogKeyValue;
	}

	public void setErrorlogKeyValue(java.lang.String errorlogKeyValue) {
		this.errorlogKeyValue = errorlogKeyValue;
	}

	public java.lang.String getErrorlogProgName() {
		return errorlogProgName;
	}

	public void setErrorlogProgName(java.lang.String errorlogProgName) {
		this.errorlogProgName = errorlogProgName;
	}

	public java.lang.String getErrorlogTableName() {
		return errorlogTableName;
	}

	public void setErrorlogTableName(java.lang.String errorlogTableName) {
		this.errorlogTableName = errorlogTableName;
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

	public java.lang.String getErrorlogErrorCode() {
		return errorlogErrorCode;
	}

	public void setErrorlogErrorCode(java.lang.String errorlogErrorCode) {
		this.errorlogErrorCode = errorlogErrorCode;
	}
	
	

}
