package com.policy.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Entity
@Table(name = "CARS_SUBLINE")
public class CarsSubline  {

	private static final long serialVersionUID = 1L;

	private static Log logger = LogFactory.getLog(CarsSubline.class);


	@Id
	@Column(name = "SUBLINE_ID")
	private java.lang.String sublineId;

	@Column(name = "SUBLINE_CODE")
	private java.lang.String sublineCode;

	@Column(name = "SUBLINE_DESC")
	private java.lang.String sublineDes;

	@Column(name = "SUBLINE_NOTES")
	private java.lang.String sublineNote;

	@Column(name = "SUBLINE_INSURANCE_ID")
	private java.lang.String sublineInsuranceId;

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

	public java.lang.String getSublineId() {
		return sublineId;
	}

	public void setSublineId(java.lang.String sublineId) {
		this.sublineId = sublineId;
	}

	public java.lang.String getSublineCode() {
		return sublineCode;
	}

	public void setSublineCode(java.lang.String sublineCode) {
		this.sublineCode = sublineCode;
	}

	public java.lang.String getSublineDes() {
		return sublineDes;
	}

	public void setSublineDes(java.lang.String sublineDes) {
		this.sublineDes = sublineDes;
	}

	public java.lang.String getSublineNote() {
		return sublineNote;
	}

	public void setSublineNote(java.lang.String sublineNote) {
		this.sublineNote = sublineNote;
	}

	public java.lang.String getSublineInsuranceId() {
		return sublineInsuranceId;
	}

	public void setSublineInsuranceId(java.lang.String sublineInsuranceId) {
		this.sublineInsuranceId = sublineInsuranceId;
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
