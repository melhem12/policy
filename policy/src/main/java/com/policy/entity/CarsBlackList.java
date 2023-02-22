package com.policy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CARS_BLACK_LIST")
public class CarsBlackList {

	@Id
	@Column(name = "BL_ID")
	private String blId;

	@Column(name = "BL_INSURANCE_ID")
	private String blInsuranceId;

	@Column(name = "CLIENT_NUM")
	private String clientNum;

	@Column(name = "BL_NOTIFICATION")
	private String blNotification;

	@Column(name = "BL_FIRST_NAME")
	private String blFirstName;

	@Column(name = "BL_FATHER_NAME")
	private String blFatherName;

	@Column(name = "BL_FAMILY_NAME")
	private String blFamilyName;

	@Column(name = "BL_DRIVING_LICENSE")
	private String blDrivingLicense;

	@Column(name = "BL_PLATE_NUMBER")
	private String blPlateNumber;

	@Column(name = "BL_CAR")
	private String blCar;

	@Column(name = "BL_DRIVER")
	private String blDriver;

	@Column(name = "BL_AMOUNT")
	private String blAmount;

	@Column(name = "BL_DATE")
	private String blDate;

	@Column(name = "BL_STATUS")
	private String blStatus;

	@Column(name = "BL_NOTE")
	private String blNote;

	@Column(name = "BL_BROKER_ID")
	private String blBrokerId;

	@Column(name = "BL_POLICY_BRANCH")
	private String blPolicyBranch;

	@Column(name = "BL_POLICY_NUM")
	private String blPolicyNum;

	@Column(name = "BL_CAR_OBJECT_NUMBER")
	private String blCarObjectNumber;

	@Column(name = "SYS_VERSION_NUMBER")
	private Integer sysVersionNumber;

	@Column(name = "SYS_CREATED_DATE")
	private java.sql.Timestamp sysCreatedDate;

	@Column(name = "SYS_CREATED_BY")
	private String sysCreatedBy;

	@Column(name = "SYS_UPDATED_DATE")
	private java.sql.Timestamp sysUpdatedDate;

	public String getBlId() {
		return blId;
	}

	public void setBlId(String blId) {
		this.blId = blId;
	}

	public String getBlInsuranceId() {
		return blInsuranceId;
	}

	public void setBlInsuranceId(String blInsuranceId) {
		this.blInsuranceId = blInsuranceId;
	}

	public String getClientNum() {
		return clientNum;
	}

	public void setClientNum(String clientNum) {
		this.clientNum = clientNum;
	}

	public String getBlNotification() {
		return blNotification;
	}

	public void setBlNotification(String blNotification) {
		this.blNotification = blNotification;
	}

	public String getBlFirstName() {
		return blFirstName;
	}

	public void setBlFirstName(String blFirstName) {
		this.blFirstName = blFirstName;
	}

	public String getBlFatherName() {
		return blFatherName;
	}

	public void setBlFatherName(String blFatherName) {
		this.blFatherName = blFatherName;
	}

	public String getBlFamilyName() {
		return blFamilyName;
	}

	public void setBlFamilyName(String blFamilyName) {
		this.blFamilyName = blFamilyName;
	}

	public String getBlDrivingLicense() {
		return blDrivingLicense;
	}

	public void setBlDrivingLicense(String blDrivingLicense) {
		this.blDrivingLicense = blDrivingLicense;
	}

	public String getBlPlateNumber() {
		return blPlateNumber;
	}

	public void setBlPlateNumber(String blPlateNumber) {
		this.blPlateNumber = blPlateNumber;
	}

	public String getBlCar() {
		return blCar;
	}

	public void setBlCar(String blCar) {
		this.blCar = blCar;
	}

	public String getBlDriver() {
		return blDriver;
	}

	public void setBlDriver(String blDriver) {
		this.blDriver = blDriver;
	}

	public String getBlAmount() {
		return blAmount;
	}

	public void setBlAmount(String blAmount) {
		this.blAmount = blAmount;
	}

	public String getBlDate() {
		return blDate;
	}

	public void setBlDate(String blDate) {
		this.blDate = blDate;
	}

	public String getBlStatus() {
		return blStatus;
	}

	public void setBlStatus(String blStatus) {
		this.blStatus = blStatus;
	}

	public String getBlNote() {
		return blNote;
	}

	public void setBlNote(String blNote) {
		this.blNote = blNote;
	}

	public String getBlBrokerId() {
		return blBrokerId;
	}

	public void setBlBrokerId(String blBrokerId) {
		this.blBrokerId = blBrokerId;
	}

	public String getBlPolicyBranch() {
		return blPolicyBranch;
	}

	public void setBlPolicyBranch(String blPolicyBranch) {
		this.blPolicyBranch = blPolicyBranch;
	}

	public String getBlPolicyNum() {
		return blPolicyNum;
	}

	public void setBlPolicyNum(String blPolicyNum) {
		this.blPolicyNum = blPolicyNum;
	}

	public String getBlCarObjectNumber() {
		return blCarObjectNumber;
	}

	public void setBlCarObjectNumber(String blCarObjectNumber) {
		this.blCarObjectNumber = blCarObjectNumber;
	}

	public Integer getSysVersionNumber() {
		return sysVersionNumber;
	}

	public void setSysVersionNumber(Integer sysVersionNumber) {
		this.sysVersionNumber = sysVersionNumber;
	}

	public java.sql.Timestamp getSysCreatedDate() {
		return sysCreatedDate;
	}

	public void setSysCreatedDate(java.sql.Timestamp sysCreatedDate) {
		this.sysCreatedDate = sysCreatedDate;
	}

	public String getSysCreatedBy() {
		return sysCreatedBy;
	}

	public void setSysCreatedBy(String sysCreatedBy) {
		this.sysCreatedBy = sysCreatedBy;
	}

	public java.sql.Timestamp getSysUpdatedDate() {
		return sysUpdatedDate;
	}

	public void setSysUpdatedDate(java.sql.Timestamp sysUpdatedDate) {
		this.sysUpdatedDate = sysUpdatedDate;
	}

	public String getSysUpdatedBy() {
		return sysUpdatedBy;
	}

	public void setSysUpdatedBy(String sysUpdatedBy) {
		this.sysUpdatedBy = sysUpdatedBy;
	}

	@Column(name = "SYS_UPDATED_BY")
	private String sysUpdatedBy;

}
