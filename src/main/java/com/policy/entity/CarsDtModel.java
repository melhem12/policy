package com.policy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CARS_DT_MODEL")
public class CarsDtModel {
	@Id
	@Column(name = "DT_ID")
	private java.lang.String id;

	
	@Column(name = "INS_ID")
	private java.lang.String insuranceId;

	@Column(name = "INS_MODEL_CODE")
	private java.lang.String insuranceModelCode;

	@Column(name = "MODEL_NAME")
	private java.lang.String modelName;

	@Column(name = "INS_MAKE_CODE")
	private java.lang.String insuranceMakeCode;

	@Column(name = "BRAND_ID")
	private java.lang.String brandId;

	@Column(name = "TRADEMARK_ID")
	private java.lang.String trademarkId;



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

	public java.lang.String getInsuranceModelCode() {
		return insuranceModelCode;
	}

	public void setInsuranceModelCode(java.lang.String insuranceModelCode) {
		this.insuranceModelCode = insuranceModelCode;
	}

	public java.lang.String getModelName() {
		return modelName;
	}

	public void setModelName(java.lang.String modelName) {
		this.modelName = modelName;
	}

	public java.lang.String getInsuranceMakeCode() {
		return insuranceMakeCode;
	}

	public void setInsuranceMakeCode(java.lang.String insuranceMakeCode) {
		this.insuranceMakeCode = insuranceMakeCode;
	}

	public java.lang.String getBrandId() {
		return brandId;
	}

	public void setBrandId(java.lang.String brandId) {
		this.brandId = brandId;
	}

	public java.lang.String getTrademarkId() {
		return trademarkId;
	}

	public void setTrademarkId(java.lang.String trademarkId) {
		this.trademarkId = trademarkId;
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
