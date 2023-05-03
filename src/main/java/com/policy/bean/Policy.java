package com.policy.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;

import javax.validation.Constraint;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.policy.annotation.BranchCodeAnnotation;
import com.policy.annotation.BranchDescriptionAnnotation;
import com.policy.annotation.InsuredCodeAnnotation;
import com.policy.annotation.InsuredIdAnnotation;
import com.policy.annotation.PolicyAnnotation;
import com.policy.annotation.PremCurrencyAnnotation;
import com.policy.annotation.PremCurrencyDescriptionAnnotation;
import com.policy.annotation.PremCurrencyExchangeRateAnnotation;
import com.policy.annotation.ProductDescriptionAnnotation;
import com.policy.annotation.ProductIdAnnotation;
import com.policy.annotation.SICurrencyAnnotation;
import com.policy.annotation.SICurrencyDescriptionAnnotation;
import com.policy.annotation.SICurrencyExchangeRateAnnotation;
import com.policy.annotation.SubLineCodeAnnotation;
import com.policy.annotation.SubLineDescriptionAnnotation;
import com.policy.annotation.productCodeAnnotation;


//@PolicyAnnotation
public class Policy implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer	sysID ;
	private BigInteger policyID;

	public Integer getSysID() {
		return sysID;
	}

	public void setSysID(Integer sysID) {
		this.sysID = sysID;
	}

	public Boolean getDirectBroker() {
		return isDirectBroker;
	}

	public void setDirectBroker(Boolean directBroker) {
		isDirectBroker = directBroker;
	}

	public Boolean getWebPolicy() {
		return isWebPolicy;
	}

	public void setWebPolicy(Boolean webPolicy) {
		isWebPolicy = webPolicy;
	}

	private Integer policyRootID;












	private String dateEffective;

	private String dateExpiry;











	//@SubLineCodeAnnotation
	private String subLineCode;

//	@SubLineDescriptionAnnotation
	private String sublineDescription;

//    @ProductIdAnnotation
	private Integer productID;

//	@productCodeAnnotation
	private String productCode;
	
	//@ProductDescriptionAnnotation
	private String productDescription;

//	@InsuredIdAnnotation
	private Integer insuredID;

//	@InsuredCodeAnnotation
	private String insuredCode;
	// not unique

	private String printName;

	private String firstInsuredName;

	private String fatherInsuredName;

	private String lastInsuredName;

	private String insuredPhoneNumber;

	private Boolean insBlacklisted;

	private String insBlackSetOn;

	private String insBlackSetBy;

	private String insBlackReason;

	private String insBlackNote;

	private String policyNo;

	//@BranchCodeAnnotation
	private String branchCode;
	
	//@BranchDescriptionAnnotation
	private String branchDescription;

	private String endorsementNo;

	private String endorsementTypeCode;

	private String endorsementTypeDescription;

	private String endorsementSubTypeCode;

	private String endorsementSubTypeDescription;

	private String dateIssue;

	private Boolean endAtNoon;

//	@PremCurrencyAnnotation
	private String premCurrency;

	//@PremCurrencyDescriptionAnnotation
	private String premCurrencyDescription;

//	@PremCurrencyExchangeRateAnnotation
	private BigDecimal premExchangeRate;

//	@SICurrencyAnnotation
	private String sICurrency;

	//@SICurrencyDescriptionAnnotation
	private String sICurrencyDescription;

	//@SICurrencyExchangeRateAnnotation
	private BigDecimal sIExchangeRate;

	private Integer brokerID;

	private String brokerCode;

	private String brokerName;

	private String brokerPhoneNumber;

	private Boolean isDirectBroker;

	private String brokerMail;

	private Boolean brokerWebOrigin;

	private Boolean isWebPolicy;

	private Boolean brokerBlacklisted;

	private String brokerBlackSetOn;

	private String brokerBlackSetBy;

	private String brokerBlackReason;

	private String brokerBlackNote;

	private String issuingUser;

	private String modifiedByUser;

	private Boolean blacklisted;

	private String setOn;

	private String setBy;

	private String reason;

	private String note;
	
	private String mopNote;
	@Valid
	private List<Vehicles> vehicles;

	public BigDecimal getPremExchangeRate() {
		return premExchangeRate;
	}

	public void setPremExchangeRate(BigDecimal premExchangeRate) {
		this.premExchangeRate = premExchangeRate;
	}

	public BigInteger getPolicyID() {
		return policyID;
	}

	public void setPolicyID(BigInteger policyID) {
		this.policyID = policyID;
	}

	public Integer getPolicyRootID() {
		return policyRootID;
	}

	public void setPolicyRootID(Integer policyRootID) {
		this.policyRootID = policyRootID;
	}

	public String getSubLineCode() {
		return subLineCode;
	}

	public void setSubLineCode(String subLineCode) {
		this.subLineCode = subLineCode;
	}

	public String getSublineDescription() {
		return sublineDescription;
	}

	public void setSublineDescription(String sublineDescription) {
		this.sublineDescription = sublineDescription;
	}

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Integer getInsuredID() {
		return insuredID;
	}

	public void setInsuredID(Integer insuredID) {
		this.insuredID = insuredID;
	}

	public String getInsuredCode() {
		return insuredCode;
	}

	public void setInsuredCode(String insuredCode) {
		this.insuredCode = insuredCode;
	}

	public String getPrintName() {
		return printName;
	}

	public void setPrintName(String printName) {
		this.printName = printName;
	}



	public String getFirstInsuredName() {
		return firstInsuredName;
	}

	public void setFirstInsuredName(String firstInsuredName) {
		this.firstInsuredName = firstInsuredName;
	}

	public String getFatherInsuredName() {
		return fatherInsuredName;
	}

	public void setFatherInsuredName(String fatherInsuredName) {
		this.fatherInsuredName = fatherInsuredName;
	}

	public String getLastInsuredName() {
		return lastInsuredName;
	}

	public void setLastInsuredName(String lastInsuredName) {
		this.lastInsuredName = lastInsuredName;
	}

	public String getInsuredPhoneNumber() {
		return insuredPhoneNumber;
	}

	public void setInsuredPhoneNumber(String insuredPhoneNumber) {
		this.insuredPhoneNumber = insuredPhoneNumber;
	}

	public Boolean getInsBlacklisted() {
		return insBlacklisted;
	}

	public void setInsBlacklisted(Boolean insBlacklisted) {
		this.insBlacklisted = insBlacklisted;
	}

	public String getInsBlackSetOn() {
		return insBlackSetOn;
	}

	public void setInsBlackSetOn(String insBlackSetOn) {
		this.insBlackSetOn = insBlackSetOn;
	}

	public String getInsBlackSetBy() {
		return insBlackSetBy;
	}

	public void setInsBlackSetBy(String insBlackSetBy) {
		this.insBlackSetBy = insBlackSetBy;
	}

	public String getInsBlackReason() {
		return insBlackReason;
	}

	public void setInsBlackReason(String insBlackReason) {
		this.insBlackReason = insBlackReason;
	}

	public String getInsBlackNote() {
		return insBlackNote;
	}

	public void setInsBlackNote(String insBlackNote) {
		this.insBlackNote = insBlackNote;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchDescription() {
		return branchDescription;
	}

	public void setBranchDescription(String branchDescription) {
		this.branchDescription = branchDescription;
	}

	public String getEndorsementNo() {
		return endorsementNo;
	}

	public void setEndorsementNo(String endorsementNo) {
		this.endorsementNo = endorsementNo;
	}

	public String getEndorsementTypeCode() {
		return endorsementTypeCode;
	}

	public void setEndorsementTypeCode(String endorsementTypeCode) {
		this.endorsementTypeCode = endorsementTypeCode;
	}

	public String getEndorsementTypeDescription() {
		return endorsementTypeDescription;
	}

	public void setEndorsementTypeDescription(String endorsementTypeDescription) {
		this.endorsementTypeDescription = endorsementTypeDescription;
	}

	public String getEndorsementSubTypeCode() {
		return endorsementSubTypeCode;
	}

	public void setEndorsementSubTypeCode(String endorsementSubTypeCode) {
		this.endorsementSubTypeCode = endorsementSubTypeCode;
	}

	public String getEndorsementSubTypeDescription() {
		return endorsementSubTypeDescription;
	}

	public void setEndorsementSubTypeDescription(String endorsementSubTypeDescription) {
		this.endorsementSubTypeDescription = endorsementSubTypeDescription;
	}

	public String getDateIssue() {
		return dateIssue;
	}

	public void setDateIssue(String dateIssue) {
		this.dateIssue = dateIssue;
	}

	public Boolean getEndAtNoon() {
		return endAtNoon;
	}

	public void setEndAtNoon(Boolean endAtNoon) {
		this.endAtNoon = endAtNoon;
	}

	public String getPremCurrency() {
		return premCurrency;
	}

	public void setPremCurrency(String premCurrency) {
		this.premCurrency = premCurrency;
	}

	public String getPremCurrencyDescription() {
		return premCurrencyDescription;
	}

	public void setPremCurrencyDescription(String premCurrencyDescription) {
		this.premCurrencyDescription = premCurrencyDescription;
	}



	public String getsICurrency() {
		return sICurrency;
	}

	public void setsICurrency(String sICurrency) {
		this.sICurrency = sICurrency;
	}

	public String getsICurrencyDescription() {
		return sICurrencyDescription;
	}

	public void setsICurrencyDescription(String sICurrencyDescription) {
		this.sICurrencyDescription = sICurrencyDescription;
	}

	public BigDecimal getsIExchangeRate() {
		return sIExchangeRate;
	}

	public void setsIExchangeRate(BigDecimal sIExchangeRate) {
		this.sIExchangeRate = sIExchangeRate;
	}

	public Integer getBrokerID() {
		return brokerID;
	}

	public void setBrokerID(Integer brokerID) {
		this.brokerID = brokerID;
	}

	public String getBrokerCode() {
		return brokerCode;
	}

	public void setBrokerCode(String brokerCode) {
		this.brokerCode = brokerCode;
	}

	public String getBrokerName() {
		return brokerName;
	}

	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}

	public String getBrokerPhoneNumber() {
		return brokerPhoneNumber;
	}

	public void setBrokerPhoneNumber(String brokerPhoneNumber) {
		this.brokerPhoneNumber = brokerPhoneNumber;
	}

	public Boolean getIsDirectBroker() {
		return isDirectBroker;
	}

	public void setIsDirectBroker(Boolean isDirectBroker) {
		this.isDirectBroker = isDirectBroker;
	}

	public String getBrokerMail() {
		return brokerMail;
	}

	public void setBrokerMail(String brokerMail) {
		this.brokerMail = brokerMail;
	}

	public Boolean getBrokerWebOrigin() {
		return brokerWebOrigin;
	}

	public void setBrokerWebOrigin(Boolean brokerWebOrigin) {
		this.brokerWebOrigin = brokerWebOrigin;
	}

	public Boolean getIsWebPolicy() {
		return isWebPolicy;
	}

	public void setIsWebPolicy(Boolean isWebPolicy) {
		this.isWebPolicy = isWebPolicy;
	}

	public Boolean getBrokerBlacklisted() {
		return brokerBlacklisted;
	}

	public void setBrokerBlacklisted(Boolean brokerBlacklisted) {
		this.brokerBlacklisted = brokerBlacklisted;
	}

	public String getBrokerBlackSetOn() {
		return brokerBlackSetOn;
	}

	public void setBrokerBlackSetOn(String brokerBlackSetOn) {
		this.brokerBlackSetOn = brokerBlackSetOn;
	}

	public String getBrokerBlackSetBy() {
		return brokerBlackSetBy;
	}

	public void setBrokerBlackSetBy(String brokerBlackSetBy) {
		this.brokerBlackSetBy = brokerBlackSetBy;
	}

	public String getBrokerBlackReason() {
		return brokerBlackReason;
	}

	public void setBrokerBlackReason(String brokerBlackReason) {
		this.brokerBlackReason = brokerBlackReason;
	}

	public String getBrokerBlackNote() {
		return brokerBlackNote;
	}

	public void setBrokerBlackNote(String brokerBlackNote) {
		this.brokerBlackNote = brokerBlackNote;
	}

	public String getIssuingUser() {
		return issuingUser;
	}

	public void setIssuingUser(String issuingUser) {
		this.issuingUser = issuingUser;
	}

	public String getModifiedByUser() {
		return modifiedByUser;
	}

	public void setModifiedByUser(String modifiedByUser) {
		this.modifiedByUser = modifiedByUser;
	}

	public Boolean getBlacklisted() {
		return blacklisted;
	}

	public void setBlacklisted(Boolean blacklisted) {
		this.blacklisted = blacklisted;
	}

	public String getSetOn() {
		return setOn;
	}

	public void setSetOn(String setOn) {
		this.setOn = setOn;
	}

	public String getSetBy() {
		return setBy;
	}

	public void setSetBy(String setBy) {
		this.setBy = setBy;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<Vehicles> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicles> vehicles) {
		this.vehicles = vehicles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMopNote() {
		return mopNote;
	}

	public void setMopNote(String mopNote) {
		this.mopNote = mopNote;
	}

	public String getDateEffective() {
		return dateEffective;
	}

	public void setDateEffective(String dateEffective) {
		this.dateEffective = dateEffective;
	}

	public String getDateExpiry() {
		return dateExpiry;
	}

	public void setDateExpiry(String dateExpiry) {
		this.dateExpiry = dateExpiry;
	}
}