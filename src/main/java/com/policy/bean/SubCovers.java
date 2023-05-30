package com.policy.bean;

import java.io.Serializable;

public class SubCovers implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer subCoverID;

	private String subCoverCode;
	
	private String subCoverDesc;
	
	private Integer tpaSubCoverTypeID;

	private String tpaSubCoverTypeCode;

	private String tpaSubCoverTypeDescription;

	private double subCoverLimitClaim;

	private double subCoverCountLimit;

	private float subCoverSumInsured;

	private String subCoverDeductibleType;

	private float subCoverDeductiblePercentage;

	private float subCoverDeductibleFlatAmount;
	private Long subCoverOrder;


	public Long getSubCoverOrder() {
		return subCoverOrder;
	}

	public void setSubCoverOrder(Long subCoverOrder) {
		this.subCoverOrder = subCoverOrder;
	}

	public Integer getSubCoverID() {
		return subCoverID;
	}

	public void setSubCoverID(Integer subCoverID) {
		this.subCoverID = subCoverID;
	}

	public String getSubCoverCode() {
		return subCoverCode;
	}

	public void setSubCoverCode(String subCoverCode) {
		this.subCoverCode = subCoverCode;
	}

	public String getSubCoverDesc() {
		return subCoverDesc;
	}

	public void setSubCoverDesc(String subCoverDesc) {
		this.subCoverDesc = subCoverDesc;
	}

	public Integer getTpaSubCoverTypeID() {
		return tpaSubCoverTypeID;
	}

	public void setTpaSubCoverTypeID(Integer tpaSubCoverTypeID) {
		this.tpaSubCoverTypeID = tpaSubCoverTypeID;
	}

	public String getTpaSubCoverTypeCode() {
		return tpaSubCoverTypeCode;
	}

	public void setTpaSubCoverTypeCode(String tpaSubCoverTypeCode) {
		this.tpaSubCoverTypeCode = tpaSubCoverTypeCode;
	}

	public String getTpaSubCoverTypeDescription() {
		return tpaSubCoverTypeDescription;
	}

	public void setTpaSubCoverTypeDescription(String tpaSubCoverTypeDescription) {
		this.tpaSubCoverTypeDescription = tpaSubCoverTypeDescription;
	}



	public double getSubCoverLimitClaim() {
		return subCoverLimitClaim;
	}

	public void setSubCoverLimitClaim(double subCoverLimitClaim) {
		this.subCoverLimitClaim = subCoverLimitClaim;
	}

	public double getSubCoverCountLimit() {
		return subCoverCountLimit;
	}

	public void setSubCoverCountLimit(double subCoverCountLimit) {
		this.subCoverCountLimit = subCoverCountLimit;
	}

	public float getSubCoverSumInsured() {
		return subCoverSumInsured;
	}

	public void setSubCoverSumInsured(float subCoverSumInsured) {
		this.subCoverSumInsured = subCoverSumInsured;
	}

	public String getSubCoverDeductibleType() {
		return subCoverDeductibleType;
	}

	public void setSubCoverDeductibleType(String subCoverDeductibleType) {
		this.subCoverDeductibleType = subCoverDeductibleType;
	}

	public float getSubCoverDeductiblePercentage() {
		return subCoverDeductiblePercentage;
	}

	public void setSubCoverDeductiblePercentage(float subCoverDeductiblePercentage) {
		this.subCoverDeductiblePercentage = subCoverDeductiblePercentage;
	}

	public float getSubCoverDeductibleFlatAmount() {
		return subCoverDeductibleFlatAmount;
	}

	public void setSubCoverDeductibleFlatAmount(float subCoverDeductibleFlatAmount) {
		this.subCoverDeductibleFlatAmount = subCoverDeductibleFlatAmount;
	}
	
	
}