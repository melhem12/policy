package com.policy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CARS_COVER")
public class CarsCover {

	@Id
	@Column(name = "COVER_ID")
	private String coverID;
	
	@Column(name = "COVER_CODE")
	private String coverCode;
	
	@Column(name = "COVER_DESCRIPTION")
	private String coverDescription;
	
	@Column(name = "COVER_INSURANCE")
	private Integer coverInsurance;
	
	
	

	@Column(name = "COVER_INSURANCE_ID")
	private String coverInsuranceId;

	@Column(name = "COVER_STATE")
	private String coverState;
	
	@Column(name = "COVER_TYPE")
	private String coverType;
		
	@Column(name = "SYS_CREATED_BY")
	private String sysCreatedBy;
	
	@Column(name = "SYS_CREATED_DATE")
	private java.sql.Timestamp sysCreatedDate;
	
	@Column(name = "SYS_UPDATED_BY")
	private String sysUpdatedBy;
	
	@Column(name = "SYS_UPDATED_DATE")
	private java.sql.Timestamp sysUpdatedDate;
	
	@Column(name = "SYS_VERSION_NUMBER")
	private Integer sysVersionNumber;

	@Column(name = "COVER_REFERENCE")
	private String coverReference;
	
	public String getCoverID() {
		return coverID;
	}

	public void setCoverID(String coverID) {
		this.coverID = coverID;
	}

	public String getCoverCode() {
		return coverCode;
	}

	public void setCoverCode(String coverCode) {
		this.coverCode = coverCode;
	}

	public String getCoverDescription() {
		return coverDescription;
	}

	public void setCoverDescription(String coverDescription) {
		this.coverDescription = coverDescription;
	}

	public Integer getCoverInsurance() {
		return coverInsurance;
	}

	public void setCoverInsurance(Integer coverInsurance) {
		this.coverInsurance = coverInsurance;
	}

	public String getCoverInsuranceId() {
		return coverInsuranceId;
	}

	public void setCoverInsuranceId(String coverInsuranceId) {
		this.coverInsuranceId = coverInsuranceId;
	}

	public String getCoverState() {
		return coverState;
	}

	public void setCoverState(String coverState) {
		this.coverState = coverState;
	}

	public String getCoverType() {
		return coverType;
	}

	public void setCoverType(String coverType) {
		this.coverType = coverType;
	}

	public String getSysCreatedBy() {
		return sysCreatedBy;
	}

	public void setSysCreatedBy(String sysCreatedBy) {
		this.sysCreatedBy = sysCreatedBy;
	}

	public java.sql.Timestamp getSysCreatedDate() {
		return sysCreatedDate;
	}

	public void setSysCreatedDate(java.sql.Timestamp sysCreatedDate) {
		this.sysCreatedDate = sysCreatedDate;
	}

	public String getSysUpdatedBy() {
		return sysUpdatedBy;
	}

	public void setSysUpdatedBy(String sysUpdatedBy) {
		this.sysUpdatedBy = sysUpdatedBy;
	}

	public java.sql.Timestamp getSysUpdatedDate() {
		return sysUpdatedDate;
	}

	public void setSysUpdatedDate(java.sql.Timestamp sysUpdatedDate) {
		this.sysUpdatedDate = sysUpdatedDate;
	}

	public Integer getSysVersionNumber() {
		return sysVersionNumber;
	}

	public void setSysVersionNumber(Integer sysVersionNumber) {
		this.sysVersionNumber = sysVersionNumber;
	}

	public String getCoverReference() {
		return coverReference;
	}

	public void setCoverReference(String coverReference) {
		this.coverReference = coverReference;
	}
	

}
