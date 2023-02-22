package com.policy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Constraint;

@Entity
@Table(name = "CARS_BRANCH")
public class CarsBranch {

	@Id
	@Column(name = "BRANCH_ID")
	private String branchId;

	
	@Column(name = "BRANCH_CODE")
	
	private String branchCode;

	@Column(name = "BRANCH_DES")
	private String branchDes;

	@Column(name = "BRANCH_ADR_1")
	private String branchAdr1;

	@Column(name = "BRANCH_ADR_2")
	private String branchAdr2;

	@Column(name = "BRANCH_DES_ARA")
	private String branchDescAra;

	@Column(name = "BRANCH_INSURANCE_ID")
	private String branchInsuranceId;

	@Column(name = "SYS_VERSION_NUMBER")
	private java.lang.Integer sysVersionNumber;

	@Column(name = "SYS_CREATED_DATE")
	private java.sql.Timestamp sysCreatedDate;

	@Column(name = "SYS_UPDATED_DATE")
	private java.sql.Timestamp sysUpdatedDate;

	@Column(name = "SYS_CREATED_BY")
	private String sysCreatedBy;

	@Column(name = "SYS_UPDATED_BY")
	private String sysUpdatedBy;

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchDes() {
		return branchDes;
	}

	public void setBranchDes(String branchDes) {
		this.branchDes = branchDes;
	}

	public String getBranchAdr1() {
		return branchAdr1;
	}

	public void setBranchAdr1(String branchAdr1) {
		this.branchAdr1 = branchAdr1;
	}

	public String getBranchAdr2() {
		return branchAdr2;
	}

	public void setBranchAdr2(String branchAdr2) {
		this.branchAdr2 = branchAdr2;
	}

	public String getBranchDescAra() {
		return branchDescAra;
	}

	public void setBranchDescAra(String branchDescAra) {
		this.branchDescAra = branchDescAra;
	}

	public String getBranchInsuranceId() {
		return branchInsuranceId;
	}

	public void setBranchInsuranceId(String branchInsuranceId) {
		this.branchInsuranceId = branchInsuranceId;
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

	public String getSysCreatedBy() {
		return sysCreatedBy;
	}

	public void setSysCreatedBy(String sysCreatedBy) {
		this.sysCreatedBy = sysCreatedBy;
	}

	public String getSysUpdatedBy() {
		return sysUpdatedBy;
	}

	public void setSysUpdatedBy(String sysUpdatedBy) {
		this.sysUpdatedBy = sysUpdatedBy;
	}

}
