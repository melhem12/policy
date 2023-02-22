package com.policy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//ntebih aal type
//"TPACoverType": "111",varchar
//"CoverDesc": "Cover (f): Own Damage of the insured vehicle",varchar
//"TPACoverTypeId": 0,varchar
//"TPACoverTypeCode": null,varchar
//"TPACoverTypeDescription": null,varchar
//"CoverExcessAmount": 0.0,
//"CoverExcessRate": 3.500000,
//"DeductibleType": null,varchar
//"SumInsured": 91000.00,
//SUBCOVERS
//"subCovers": []


@Entity
@Table(name = "CARS_DT_COVER_CONTRACT")
public class CarsDtCoverContract {

	@Id
	@Column(name = "DT_ID")
	private String dtId;
	
	@Column(name = "INS_ID")
	private String insID;
	
	@Column(name = "COVER_ID")
	private String coverID;
	
	@Column(name = "COVER_CODE")
	private String coverCode;
	
	@Column(name = "POLICY_ID")
	private String policyID;
	
	
	@Column(name = "POLCOVER_ID")
	private String polCoverID;
	
	@Column(name = "DEDUCTIBLE_PERC")
	private String deductiblePercentage;
	
	@Column(name = "DEDUCTIBLE_FLAT")
	private String deductibleFlatAmount;
	
	@Column(name = "TPA_COVER_TYPE")
	private String tpaCoverType;
	
	@Column(name = "TPA_COVER_TYPE_ID")
	private String tpaCoverTypeId;
	
	@Column(name = "TPA_COVER_TYPE_CODE")
	private String tpaCoverTypeCode;
	
	@Column(name = "TPA_COVER_TYPE_DESCRIPTION")
	private String tpaCoverTypeDescription;
	
	@Column(name = "DEDUCTIBLE_TYPE")
	private String deductibleType;

	@Column(name = "SUB_COVERS")//BHOT Cover or SubCover ka value
	private String subCovers;

	public String getPolicyID() {
		return policyID;
	}

	public void setPolicyID(String policyID) {
		this.policyID = policyID;
	}

	public String getDtId() {
		return dtId;
	}

	public void setDtId(String dtId) {
		this.dtId = dtId;
	}

	public String getInsID() {
		return insID;
	}

	public void setInsID(String insID) {
		this.insID = insID;
	}

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

	public String getPolCoverID() {
		return polCoverID;
	}

	public void setPolCoverID(String polCoverID) {
		this.polCoverID = polCoverID;
	}

	public String getDeductiblePercentage() {
		return deductiblePercentage;
	}

	public void setDeductiblePercentage(String deductiblePercentage) {
		this.deductiblePercentage = deductiblePercentage;
	}

	public String getDeductibleFlatAmount() {
		return deductibleFlatAmount;
	}

	public void setDeductibleFlatAmount(String deductibleFlatAmount) {
		this.deductibleFlatAmount = deductibleFlatAmount;
	}

	public String getTpaCoverType() {
		return tpaCoverType;
	}

	public void setTpaCoverType(String tpaCoverType) {
		this.tpaCoverType = tpaCoverType;
	}

	public String getTpaCoverTypeId() {
		return tpaCoverTypeId;
	}

	public void setTpaCoverTypeId(String tpaCoverTypeId) {
		this.tpaCoverTypeId = tpaCoverTypeId;
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

	public String getDeductibleType() {
		return deductibleType;
	}

	public void setDeductibleType(String deductibleType) {
		this.deductibleType = deductibleType;
	}

	public String getSubCovers() {
		return subCovers;
	}

	public void setSubCovers(String subCovers) {
		this.subCovers = subCovers;
	}
}
