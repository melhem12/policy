package com.policy.bean;

import java.io.Serializable;
import java.util.List;

public class Covers implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//private String tpaCoverType;

	private Integer coverID;

	private String coverCode;

	private String coverDesc;

	private Integer tpaCoverTypeId;

	private Integer policyCoverID;

	private String tpaCoverTypeCode;

	private String tpaCoverTypeDescription;

	private double coverLimitClaim;

	private double coverCountLimit;

	private String deductibleType;

	private float deductiblePercentage;

	private float deductibleFlatAmount;

	private float sumInsured;

	private List<SubCovers> subCovers;

//	public String getTpaCoverType() {
//		return tpaCoverType;
//	}
//
//	public void setTpaCoverType(String tpaCoverType) {
//		this.tpaCoverType = tpaCoverType;
//	}

	public Integer getCoverID() {
		return coverID;
	}

	public void setCoverID(Integer coverID) {
		this.coverID = coverID;
	}

	public String getCoverCode() {
		return coverCode;
	}

	public void setCoverCode(String coverCode) {
		this.coverCode = coverCode;
	}

	public String getCoverDesc() {
		return coverDesc;
	}

	public void setCoverDesc(String coverDesc) {
		this.coverDesc = coverDesc;
	}

	public Integer getTpaCoverTypeId() {
		return tpaCoverTypeId;
	}

	public void setTpaCoverTypeId(Integer tpaCoverTypeId) {
		this.tpaCoverTypeId = tpaCoverTypeId;
	}

	public Integer getPolicyCoverID() {
		return policyCoverID;
	}

	public void setPolicyCoverID(Integer policyCoverID) {
		this.policyCoverID = policyCoverID;
	}

	public String getTpaCoverTypeCode() {
		return tpaCoverTypeCode;
	}

	public void setTpaCoverTypeCode(String tpaCoverTypeCode) {
		this.tpaCoverTypeCode = tpaCoverTypeCode;
	}

	public String getTpaCoverTypeDescription() {
		return tpaCoverTypeDescription;
	}

	public void setTpaCoverTypeDescription(String tpaCoverTypeDescription) {
		this.tpaCoverTypeDescription = tpaCoverTypeDescription;
	}



	public double getCoverLimitClaim() {
		return coverLimitClaim;
	}

	public void setCoverLimitClaim(double coverLimitClaim) {
		this.coverLimitClaim = coverLimitClaim;
	}

	public double getCoverCountLimit() {
		return coverCountLimit;
	}

	public void setCoverCountLimit(double coverCountLimit) {
		this.coverCountLimit = coverCountLimit;
	}

	public String getDeductibleType() {
		return deductibleType;
	}

	public void setDeductibleType(String deductibleType) {
		this.deductibleType = deductibleType;
	}

	public float getDeductiblePercentage() {
		return deductiblePercentage;
	}

	public void setDeductiblePercentage(float deductiblePercentage) {
		this.deductiblePercentage = deductiblePercentage;
	}

	public float getDeductibleFlatAmount() {
		return deductibleFlatAmount;
	}

	public void setDeductibleFlatAmount(float deductibleFlatAmount) {
		this.deductibleFlatAmount = deductibleFlatAmount;
	}

	public float getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(float sumInsured) {
		this.sumInsured = sumInsured;
	}

	public List<SubCovers> getSubCovers() {
		return subCovers;
	}

	public void setSubCovers(List<SubCovers> subCovers) {
		this.subCovers = subCovers;
	}

	
}
