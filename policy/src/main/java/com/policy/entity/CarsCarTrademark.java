package com.policy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Entity
@Table(name = "CARS_CAR_TRADEMARK")
public class CarsCarTrademark  {

	private static final long serialVersionUID = 1L;

	private static Log logger = LogFactory.getLog(CarsCarTrademark.class);
	@Id
	@Column(name = "CAR_TRADEMARK_ID")
	private String carTrademarkId;
	
	@Column(name = "CAR_TRADEMARK_CODE")
	private String carTrademarkCode;
	
	@Column(name = "CAR_TRADEMARK_DESCRIPTION")
	private String carTrademarkDescription;
	
	@Column(name = "CAR_TRADEMARK_QUERY_ONLY")
	private String carTrademarkQueryOnly;
	
	@Column(name = "CAR_BRAND_ID")
	private String carBrandId;	
	
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

	

	

	public String getCarTrademarkId() {
		return carTrademarkId;
	}

	public void setCarTrademarkId(String carTrademarkId) {
		this.carTrademarkId = carTrademarkId;
	}

	public String getCarTrademarkCode() {
		return carTrademarkCode;
	}

	public void setCarTrademarkCode(String carTrademarkCode) {
		this.carTrademarkCode = carTrademarkCode;
	}

	public String getCarTrademarkDescription() {
		return carTrademarkDescription;
	}

	public void setCarTrademarkDescription(String carTrademarkDescription) {
		this.carTrademarkDescription = carTrademarkDescription;
	}

	public String getCarTrademarkQueryOnly() {
		return carTrademarkQueryOnly;
	}

	public void setCarTrademarkQueryOnly(String carTrademarkQueryOnly) {
		this.carTrademarkQueryOnly = carTrademarkQueryOnly;
	}

	public String getCarBrandId() {
		return carBrandId;
	}

	public void setCarBrandId(String carBrandId) {
		this.carBrandId = carBrandId;
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
