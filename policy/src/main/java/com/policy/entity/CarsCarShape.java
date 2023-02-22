package com.policy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@Entity
@Table(name = "CARS_CAR_SHAPE")
public class CarsCarShape  {

	private static final long serialVersionUID = 1L;

	private static Log logger = LogFactory.getLog(CarsCarShape.class);

	@Id
	@Column(name = "CAR_SHAPE_ID")
	private String carShapeId;
	
	@Column(name = "CAR_SHAPE_CODE")
	private String carShapeCode;
	
	@Column(name = "CAR_SHAPE_DESCRIPTION")
	private String carShapeDescription;
	
	@Column(name = "CAR_TRADEMARK_ID")
	private String carTrademarkId;
	
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


	public String getCarShapeId() {
		return carShapeId;
	}

	public void setCarShapeId(String carShapeId) {
		this.carShapeId = carShapeId;
	}

	public String getCarShapeCode() {
		return carShapeCode;
	}

	public void setCarShapeCode(String carShapeCode) {
		this.carShapeCode = carShapeCode;
	}

	public String getCarShapeDescription() {
		return carShapeDescription;
	}

	public void setCarShapeDescription(String carShapeDescription) {
		this.carShapeDescription = carShapeDescription;
	}

	public String getCarTrademarkId() {
		return carTrademarkId;
	}

	public void setCarTrademarkId(String carTrademarkId) {
		this.carTrademarkId = carTrademarkId;
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
