package com.policy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CARS_DT_MAKE")
public class CarsDtMake {

	@Id
	@Column(name = "DT_ID")
	private java.lang.String id;
	
	@Column(name = "INS_ID")
	private java.lang.String insuranceId;
	
	@Column(name = "INS_MAKE_CODE")
	private java.lang.String insuranceMakeCode;
	
	@Column(name = "MAKE_NAME")
	private java.lang.String makeName;
	
	@Column(name = "BRAND_ID")
	private java.lang.String brandId;
	
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

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(java.lang.String insuranceId) {
		this.insuranceId = insuranceId;
	}

	public java.lang.String getInsuranceMakeCode() {
		return insuranceMakeCode;
	}

	public void setInsuranceMakeCode(java.lang.String insuranceMakeCode) {
		this.insuranceMakeCode = insuranceMakeCode;
	}

	public java.lang.String getMakeName() {
		return makeName;
	}

	public void setMakeName(java.lang.String makeName) {
		this.makeName = makeName;
	}

	public java.lang.String getBrandId() {
		return brandId;
	}

	public void setBrandId(java.lang.String brandId) {
		this.brandId = brandId;
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
