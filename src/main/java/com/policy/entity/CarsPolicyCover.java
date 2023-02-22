package com.policy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CARS_POLICY_COVER")
public class CarsPolicyCover {

	@Id
	@Column(name = "POLICY_COVERS_ID")
	private java.lang.String policyCoversId;

	@Column(name = "POLICY_COVERS_COVER")
	private java.lang.String policyCoversCover;
	
	@Column(name = "POLICY_COVERS_ORDER")
	private java.lang.Integer policyCoversOrder;
	
	@Column(name = "POLICY_COVERS_SUM_INSURED")
	private java.lang.Long policyCoversSumInsured;
	
	@Column(name = "POLICY_COVERS_DEDUCTIBLE_100")
	private java.lang.Double policyCoversDeductible100;
	
	@Column(name = "POLICY_COVERS_DEDUCTIBLE")
	private java.lang.Double policyCoversDeductible;
	
	@Column(name = "POLICY_COVERS_LIMIT_CLAIM")
	private double policyCoversLimitClaim;
	
	@Column(name = "POLICY_COVERS_COUNT_LIMIT")
	private double policyCoversCountLimit;
	
	
	@Column(name = "POLICY_COVERS_ACTION")
	private java.lang.String policyCoversAction;
	
	@Column(name = "POLICY_COVERS_CAR_ID")
	private java.lang.String policyCoversCarId;
	
	@Column(name = "POLICY_COVERS_COVER_ID")
	private java.lang.String policyCoversCoverId;
	
	@Column(name = "POLICY_COVERS_PERCENTAGE")
	private java.lang.String policyCoversPercentage;
	
	
	@Column(name = "POLICY_COVERS_POLCOVER_ID")
	private java.lang.String policyCoversPolcoverId;
	
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
	
	

	public java.lang.String getPolicyCoversId() {
		return policyCoversId;
	}

	public void setPolicyCoversId(java.lang.String policyCoversId) {
		this.policyCoversId = policyCoversId;
	}

	public java.lang.String getPolicyCoversCover() {
		return policyCoversCover;
	}

	public void setPolicyCoversCover(java.lang.String policyCoversCover) {
		this.policyCoversCover = policyCoversCover;
	}

	public java.lang.Integer getPolicyCoversOrder() {
		return policyCoversOrder;
	}

	public void setPolicyCoversOrder(java.lang.Integer policyCoversOrder) {
		this.policyCoversOrder = policyCoversOrder;
	}

	public java.lang.Long getPolicyCoversSumInsured() {
		return policyCoversSumInsured;
	}

	public void setPolicyCoversSumInsured(java.lang.Long policyCoversSumInsured) {
		this.policyCoversSumInsured = policyCoversSumInsured;
	}

	public java.lang.Double getPolicyCoversDeductible100() {
		return policyCoversDeductible100;
	}

	public void setPolicyCoversDeductible100(java.lang.Double policyCoversDeductible100) {
		this.policyCoversDeductible100 = policyCoversDeductible100;
	}

	public java.lang.Double getPolicyCoversDeductible() {
		return policyCoversDeductible;
	}

	public void setPolicyCoversDeductible(java.lang.Double policyCoversDeductible) {
		this.policyCoversDeductible = policyCoversDeductible;
	}

	public java.lang.String getPolicyCoversAction() {
		return policyCoversAction;
	}

	public void setPolicyCoversAction(java.lang.String policyCoversAction) {
		this.policyCoversAction = policyCoversAction;
	}

	public java.lang.String getPolicyCoversCarId() {
		return policyCoversCarId;
	}

	public void setPolicyCoversCarId(java.lang.String policyCoversCarId) {
		this.policyCoversCarId = policyCoversCarId;
	}

	public java.lang.String getPolicyCoversCoverId() {
		return policyCoversCoverId;
	}

	public void setPolicyCoversCoverId(java.lang.String policyCoversCoverId) {
		this.policyCoversCoverId = policyCoversCoverId;
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

	public java.lang.String getPolicyCoversPolcoverId() {
		return policyCoversPolcoverId;
	}

	public void setPolicyCoversPolcoverId(java.lang.String policyCoversPolcoverId) {
		this.policyCoversPolcoverId = policyCoversPolcoverId;
	}

	public java.lang.String getPolicyCoversPercentage() {
		return policyCoversPercentage;
	}

	public void setPolicyCoversPercentage(java.lang.String policyCoversPercentage) {
		this.policyCoversPercentage = policyCoversPercentage;
	}

	public double getPolicyCoversLimitClaim() {
		return policyCoversLimitClaim;
	}

	public void setPolicyCoversLimitClaim(double policyCoversLimitClaim) {
		this.policyCoversLimitClaim = policyCoversLimitClaim;
	}

	public double getPolicyCoversCountLimit() {
		return policyCoversCountLimit;
	}

	public void setPolicyCoversCountLimit(double policyCoversCountLimit) {
		this.policyCoversCountLimit = policyCoversCountLimit;
	}


}
