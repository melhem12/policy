package com.policy.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


	@Entity
	@Table(name = "CARS_DT_POLICY")
public class CarsDtPolicy {

		@Id
		@Column(name = "DT_ID")
		private String dtId;

		@Column(name = "INS_ID")
		private String insID;
		
		@Column(name = "SCRIPT_NAME")
		private String scriptName;
		
		@Column(name = "POL_POLICY_ID")
		private String polPolicyID;
		
		@Column(name = "POL_POLICY_ROOT_ID")
		private String polPolicyRootId;
		
		@Column(name = "POL_SUB_LINE_CODE")
		private String polSublineCode;
		
		@Column(name = "POL_SUBLINE")
		private String polSublineDescription;

				
		@Column(name = "POL_PRODUCT_ID")
		private String polProductID;
		
		@Column(name = "POL_PRODUCT")
		private String polProductCode;
		
		@Column(name = "POL_PRODUCT_DESCRIPTION")
		private String polProductDescription;
		
		@Column(name = "POL_INSURED_ID")
		private String polInsuredId;
		
		@Column(name = "POL_CLIENT_CODE")
		private String polInsuredCode;
		
		@Column(name = "POL_PRINT_NAME")
		private String polPrintName;
		
		@Column(name = "POL_FIRST_NAME")
		private String polFirstName;
		
		@Column(name = "POL_FATHER_NAME")
		private String polFatherName;
		
		@Column(name = "POL_FAMILY_NAME")
		private String polFamilyName;
		
		@Column(name = "POL_CLIENT_PHONE")
		private String insuredPhoneNumber;
		
		@Column(name = "POL_INS_BLACK_LISTED")
		private Boolean insuranceBlacklisted;
		
		@Column(name = "POL_INS_BLACK_SET_ON")
		private String insuranceBlackSetOn;
		
		@Column(name = "POL_INS_BLACK_SET_BY")
		private String insuranceBlackSetBy;
		
		@Column(name = "POL_INS_BLACK_REASON")
		private String insuranceBlackReason;
		
		@Column(name = "POL_INS_BLACK_NOTE")
		private String insuranceBlackNote;
		
		@Column(name = "POL_POLICY_NUM")
		private String policyNo ;
		
		@Column(name = "POL_BRANCH")
		private String branchCode  ;
		
		@Column(name = "POL_BRANCH_DESCRIPTION")
		private String branchDescription   ;
		
		@Column(name = "POL_POLICY_AMEND")
		private String endorsementNo   ;
		
		@Column(name = "POL_POLICY_TYPE")
		private String endorsementTypeCode   ;
		

		@Column(name = "POL_ENDORSEMENT_TYPE_DES")
		private String endorsementTypeDescription    ;
		
		@Column(name = "POL_ENDORSEMENT_SUB_TYPE_CODE")
		private String endorsementSubTypeCode;
		
		@Column(name = "POL_ENDORSEMENT_SUB_TYPE_DESC")
		private String endorsementSubTypeDescription;
		
		@Column(name = "POL_ISSUE_DATE")
		private java.sql.Timestamp dateIssue;
		
		@Column(name = "POL_END_AT_NOON")
		private String endAtNoon;
		
		@Column(name = "POL_CURRENCY_ID")
		private String premCurrency;
		
		@Column(name = "POL_PREM_CURRENCY_DESCRIPTION")
		private String premCurrencyDescription;
		
		@Column(name = "POL_PREM_EXCHANGE_RATE")
		private BigDecimal PremExchangeRate;
		
		@Column(name = "POL_SI_CURRENCY")
		private String siCurrency;
		
		@Column(name = "POL_SI_CURRENCY_DESCRIPTION")
		private String siCurrencyDescription;
		
		@Column(name = "POL_SI_EXCHANGE_RATE")
		private BigDecimal siExchangeRate;
		
		@Column(name = "POL_BROKER_ID")
		private String brokerId;
		
		@Column(name = "POL_BROKER_CODE")
		private String brokerCode;
		
		@Column(name = "POL_BROKER_NAME")
		private String brokerName;
		
		@Column(name = "POL_BROKER_PHONE_NUMBER")
		private String brokerPhoneNumber;
		
		@Column(name = "POL_IS_DIRECT_BROKER")
		private String isDirectBroker;
		
		@Column(name = "POL_BROKER_MAIL")
		private String brokerMail;
		
		@Column(name = "POL_BROKER_WEB_ORIGIN")
		private String brokerWebOrigin;
		
		@Column(name = "POL_IS_WEB_POLICY")
		private String IsWebPolicy;
		
		@Column(name = "POL_BROKER_BLACK_LISTED")
		private String brokerBlacklisted;
		
		@Column(name = "POL_BROKER_BLACK_SET_ON")
		private String brokerBlackSetOn;
		
		@Column(name = "POL_BROKER_BLACK_SET_BY")
		private String brokerBlackSetBy;
		
		@Column(name = "POL_BROKER_BLACK_REASON")
		private String BrokerBlackReason;
		
		@Column(name = "POL_BROKER_BLACK_NOTE")
		private String brokerBlackNote;
		
		@Column(name = "POL_ISSUING_USER")
		private String issuingUser;
		
		@Column(name = "POL_MODIFIED_BY_USER")
		private String modifiedByUser;
		
		@Column(name = "POL_BLACK_LISTED")
		private String blacklisted;
		
		@Column(name = "POL_SET_ON")
		private String setOn;
		
		@Column(name = "POL_SET_BY")
		private String setBy;
		
		@Column(name = "POL_REASON")
		private String reason;
		
		@Column(name = "POL_CLAUSES")
		private String note;
		
		@Column(name = "POL_POLICY_CERTIFICATE")
		private String certificateNo;
		
		@Column(name = "POL_CAR_DATE_EFFECTIVE")
		private java.sql.Timestamp dateEffective;
		
		@Column(name = "POL_CAR_DATE_EXPIRY")
		private java.sql.Timestamp dateExpiry;
		
		@Column(name = "POL_CAR_INSURED_CODE")
		private String carInsuredCode;
		
		@Column(name = "POL_CAR_INSURED_NAME")
		private String carInsuredName;
		
		@Column(name = "POL_CAR_INSURED_ID")
		private String carInsuredID;
		
		@Column(name = "POL_CAR_MAKE")
		private String carMake;
		
		@Column(name = "POL_CAR_MAKE_ID")
		private String carMakeID;
		
		@Column(name = "POL_CAR_MAKE_CODE")
		private String carMakeCode;
		
		@Column(name = "POL_CAR_MODEL_CODE")
		private String carModelCode;
		
		@Column(name = "POL_CAR_MODEL_ID")
		private String carModelID;
		
		@Column(name = "POL_CAR_MODEL")
		private String carModel;
		
		@Column(name = "POL_CAR_MODEL_TO_PRINT")
		private String modelToPrint;
		
		@Column(name = "POL_AGENCY_REPAIR")
		private String dealerRepair;

		@Column(name = "POL_CAR_DEALER_WORKSHOP_NAME")
		private String dealerWorkshopName;
		
		@Column(name = "POL_CAR_YEAR")
		private String carYear;
		
		@Column(name = "POL_CAR_PLATE_SIGN")
		private String carPlatePrefix;
		
		@Column(name = "POL_CAR_USAGE")
		private String carPlateNumber;
		
		@Column(name = "POL_CAR_CHASSIS")
		private String carChassis;
		
		@Column(name = "POL_CAR_ENGINE")
		private String carEngine;
		
		@Column(name = "POL_CAR_PLATE")
		private String carUsage;
		
		@Column(name = "POL_CAR_INS_VAL")
		private String carInsVal;
		
	//	@Column(name = "")
		//private String CarStatus;
		
		@Column(name = "POL_CAR_MOP_NOTE")
		private String polCarMOPNote;
		
		@Column(name = "POL_CAR_BLACK_LISTED")
		private String polCarBlacklisted;
		
		@Column(name = "POL_CAR_SET_ON")
		private String polCarSetOn;
		
		@Column(name = "POL_CAR_SET_BY")
		private String polCarSetBy;
		
		@Column(name = "POL_CAR_REASON")
		private String polCarReason;
		
		@Column(name = "POL_CAR_NOTE")
		private String polCarNote;


		@Column(name = "POL_ENDORS1")
		private BigDecimal test;
		
		
		
		
		


	
		
		@Column(name = "SYS_CREATED_BY")
		private java.lang.String sysCreatedBy;

		@Column(name = "SYS_CREATED_DATE")
		private java.sql.Timestamp sysCreatedDate;

		@Column(name = "SYS_UPDATED_BY")
		private java.lang.String sysUpdatedBy;

		@Column(name = "SYS_UPDATED_DATE")
		private java.sql.Timestamp sysUpdatedDate;
		
		@Column(name="SYS_VERSION_NUMBER")
		private java.lang.Integer sysVersionNumber;



		public BigDecimal getTest() {
			return test;
		}

		public void setTest(BigDecimal test) {
			this.test = test;
		}

		public String getPolPolicyID() {
			return polPolicyID;
		}

		public void setPolPolicyID(String polPolicyID) {
			this.polPolicyID = polPolicyID;
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

		public String getScriptName() {
			return scriptName;
		}

		public void setScriptName(String scriptName) {
			this.scriptName = scriptName;
		}

	

		public String getPolSublineDescription() {
			return polSublineDescription;
		}

		public void setPolSublineDescription(String polSublineDescription) {
			this.polSublineDescription = polSublineDescription;
		}

		public String getPolProductID() {
			return polProductID;
		}

		public void setPolProductID(String polProductID) {
			this.polProductID = polProductID;
		}

		public String getPolProductCode() {
			return polProductCode;
		}

		public void setPolProductCode(String polProductCode) {
			this.polProductCode = polProductCode;
		}

		public String getPolClientCode() {
			return polInsuredCode;
		}

		public void setPolClientCode(String polClientCode) {
			this.polInsuredCode = polClientCode;
		}

		public String getPolFirstName() {
			return polFirstName;
		}

		public void setPolFirstName(String polFirstName) {
			this.polFirstName = polFirstName;
		}

		public String getPolFatherName() {
			return polFatherName;
		}

		public void setPolFatherName(String polFatherName) {
			this.polFatherName = polFatherName;
		}

		public String getPolFamilyName() {
			return polFamilyName;
		}

		public void setPolFamilyName(String polFamilyName) {
			this.polFamilyName = polFamilyName;
		}

		public java.lang.String getSysCreatedBy() {
			return sysCreatedBy;
		}

		public void setSysCreatedBy(java.lang.String sysCreatedBy) {
			this.sysCreatedBy = sysCreatedBy;
		}

		public java.sql.Timestamp getSysCreatedDate() {
			return sysCreatedDate;
		}

		public void setSysCreatedDate(java.sql.Timestamp sysCreatedDate) {
			this.sysCreatedDate = sysCreatedDate;
		}

		public java.lang.String getSysUpdatedBy() {
			return sysUpdatedBy;
		}

		public void setSysUpdatedBy(java.lang.String sysUpdatedBy) {
			this.sysUpdatedBy = sysUpdatedBy;
		}

		public java.sql.Timestamp getSysUpdatedDate() {
			return sysUpdatedDate;
		}

		public void setSysUpdatedDate(java.sql.Timestamp sysUpdatedDate) {
			this.sysUpdatedDate = sysUpdatedDate;
		}

		public java.lang.Integer getSysVersionNumber() {
			return sysVersionNumber;
		}

		public void setSysVersionNumber(java.lang.Integer sysVersionNumber) {
			this.sysVersionNumber = sysVersionNumber;
		}

	

		public String getPolPolicyRootId() {
			return polPolicyRootId;
		}

		public void setPolPolicyRootId(String polPolicyRootId) {
			this.polPolicyRootId = polPolicyRootId;
		}

		public String getPolSublineCode() {
			return polSublineCode;
		}

		public void setPolSublineCode(String polSublineCode) {
			this.polSublineCode = polSublineCode;
		}

		public String getPolProductDescription() {
			return polProductDescription;
		}

		public void setPolProductDescription(String polProductDescription) {
			this.polProductDescription = polProductDescription;
		}

		public String getPolInsuredId() {
			return polInsuredId;
		}

		public void setPolInsuredId(String polInsuredId) {
			this.polInsuredId = polInsuredId;
		}

		public String getPolInsuredCode() {
			return polInsuredCode;
		}

		public void setPolInsuredCode(String polInsuredCode) {
			this.polInsuredCode = polInsuredCode;
		}

		public String getPolPrintName() {
			return polPrintName;
		}

		public void setPolPrintName(String polPrintName) {
			this.polPrintName = polPrintName;
		}

		public String getInsuredPhoneNumber() {
			return insuredPhoneNumber;
		}

		public void setInsuredPhoneNumber(String insuredPhoneNumber) {
			this.insuredPhoneNumber = insuredPhoneNumber;
		}

		public Boolean getInsuranceBlacklisted() {
			return insuranceBlacklisted;
		}

		public void setInsuranceBlacklisted(Boolean insuranceBlacklisted) {
			this.insuranceBlacklisted = insuranceBlacklisted;
		}

		public String getInsuranceBlackSetOn() {
			return insuranceBlackSetOn;
		}

		public void setInsuranceBlackSetOn(String insuranceBlackSetOn) {
			this.insuranceBlackSetOn = insuranceBlackSetOn;
		}

		public String getInsuranceBlackSetBy() {
			return insuranceBlackSetBy;
		}

		public void setInsuranceBlackSetBy(String insuranceBlackSetBY) {
			this.insuranceBlackSetBy = insuranceBlackSetBY;
		}

		public String getInsuranceBlackReason() {
			return insuranceBlackReason;
		}

		public void setInsuranceBlackReason(String insuranceBlackReason) {
			this.insuranceBlackReason = insuranceBlackReason;
		}

		public String getInsuranceBlackNote() {
			return insuranceBlackNote;
		}

		public void setInsuranceBlackNote(String insuranceBlackNote) {
			this.insuranceBlackNote = insuranceBlackNote;
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

		public java.sql.Timestamp getDateIssue() {
			return dateIssue;
		}

		public void setDateIssue(java.sql.Timestamp dateIssue) {
			this.dateIssue = dateIssue;
		}

		public String getEndAtNoon() {
			return endAtNoon;
		}

		public void setEndAtNoon(String endAtNoon) {
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

		public BigDecimal getPremExchangeRate() {
			return PremExchangeRate;
		}

		public void setPremExchangeRate(BigDecimal premExchangeRate) {
			PremExchangeRate = premExchangeRate;
		}

		public String getSiCurrency() {
			return siCurrency;
		}

		public void setSiCurrency(String siCurrency) {
			this.siCurrency = siCurrency;
		}

		public String getSiCurrencyDescription() {
			return siCurrencyDescription;
		}

		public void setSiCurrencyDescription(String siCurrencyDescription) {
			this.siCurrencyDescription = siCurrencyDescription;
		}

		public BigDecimal getSiExchangeRate() {
			return siExchangeRate;
		}

		public void setSiExchangeRate(BigDecimal siExchangeRate) {
			this.siExchangeRate = siExchangeRate;
		}

		public String getBrokerId() {
			return brokerId;
		}

		public void setBrokerId(String brokerId) {
			this.brokerId = brokerId;
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

		public String getIsDirectBroker() {
			return isDirectBroker;
		}

		public void setIsDirectBroker(String isDirectBroker) {
			this.isDirectBroker = isDirectBroker;
		}

		public String getBrokerMail() {
			return brokerMail;
		}

		public void setBrokerMail(String brokerMail) {
			this.brokerMail = brokerMail;
		}

		public String getBrokerWebOrigin() {
			return brokerWebOrigin;
		}

		public void setBrokerWebOrigin(String brokerWebOrigin) {
			this.brokerWebOrigin = brokerWebOrigin;
		}

		public String getIsWebPolicy() {
			return IsWebPolicy;
		}

		public void setIsWebPolicy(String isWebPolicy) {
			IsWebPolicy = isWebPolicy;
		}

		public String getBrokerBlacklisted() {
			return brokerBlacklisted;
		}

		public void setBrokerBlacklisted(String brokerBlacklisted) {
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
			return BrokerBlackReason;
		}

		public void setBrokerBlackReason(String brokerBlackReason) {
			BrokerBlackReason = brokerBlackReason;
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

		public String getBlacklisted() {
			return blacklisted;
		}

		public void setBlacklisted(String blacklisted) {
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

		public String getCertificateNo() {
			return certificateNo;
		}

		public void setCertificateNo(String certificateNo) {
			this.certificateNo = certificateNo;
		}

		public java.sql.Timestamp getDateEffective() {
			return dateEffective;
		}

		public void setDateEffective(java.sql.Timestamp dateEffective) {
			this.dateEffective = dateEffective;
		}

		public java.sql.Timestamp getDateExpiry() {
			return dateExpiry;
		}

		public void setDateExpiry(java.sql.Timestamp dateExpiry) {
			this.dateExpiry = dateExpiry;
		}

		public String getCarInsuredCode() {
			return carInsuredCode;
		}

		public void setCarInsuredCode(String carInsuredCode) {
			this.carInsuredCode = carInsuredCode;
		}

		public String getCarInsuredName() {
			return carInsuredName;
		}

		public void setCarInsuredName(String carInsuredName) {
			this.carInsuredName = carInsuredName;
		}

		public String getCarInsuredID() {
			return carInsuredID;
		}

		public void setCarInsuredID(String carInsuredID) {
			this.carInsuredID = carInsuredID;
		}

		public String getCarMake() {
			return carMake;
		}

		public void setCarMake(String carMake) {
			this.carMake = carMake;
		}

		public String getCarMakeID() {
			return carMakeID;
		}

		public void setCarMakeID(String carMakeID) {
			this.carMakeID = carMakeID;
		}

		public String getCarMakeCode() {
			return carMakeCode;
		}

		public void setCarMakeCode(String carMakeCode) {
			this.carMakeCode = carMakeCode;
		}

		public String getCarModelCode() {
			return carModelCode;
		}

		public void setCarModelCode(String carModelCode) {
			this.carModelCode = carModelCode;
		}

		public String getCarModelID() {
			return carModelID;
		}

		public void setCarModelID(String carModelID) {
			this.carModelID = carModelID;
		}

		public String getCarModel() {
			return carModel;
		}

		public void setCarModel(String carModel) {
			this.carModel = carModel;
		}

		public String getModelToPrint() {
			return modelToPrint;
		}

		public void setModelToPrint(String modelToPrint) {
			this.modelToPrint = modelToPrint;
		}

		public String getDealerRepair() {
			return dealerRepair;
		}

		public void setDealerRepair(String dealerRepair) {
			this.dealerRepair = dealerRepair;
		}

		public String getDealerWorkshopName() {
			return dealerWorkshopName;
		}

		public void setDealerWorkshopName(String dealerWorkshopName) {
			this.dealerWorkshopName = dealerWorkshopName;
		}

		public String getCarYear() {
			return carYear;
		}

		public void setCarYear(String carYear) {
			this.carYear = carYear;
		}

		public String getCarPlatePrefix() {
			return carPlatePrefix;
		}

		public void setCarPlatePrefix(String carPlatePrefix) {
			this.carPlatePrefix = carPlatePrefix;
		}

		public String getCarPlateNumber() {
			return carPlateNumber;
		}

		public void setCarPlateNumber(String carPlateNumber) {
			this.carPlateNumber = carPlateNumber;
		}

		public String getCarChassis() {
			return carChassis;
		}

		public void setCarChassis(String carChassis) {
			this.carChassis = carChassis;
		}

		public String getCarEngine() {
			return carEngine;
		}

		public void setCarEngine(String carEngine) {
			this.carEngine = carEngine;
		}

		public String getCarUsage() {
			return carUsage;
		}

		public void setCarUsage(String carUsage) {
			this.carUsage = carUsage;
		}

		public String getCarInsVal() {
			return carInsVal;
		}

		public void setCarInsVal(String carInsVal) {
			this.carInsVal = carInsVal;
		}

		public String getPolCarMOPNote() {
			return polCarMOPNote;
		}

		public void setPolCarMOPNote(String polCarMOPNote) {
			this.polCarMOPNote = polCarMOPNote;
		}

		public String getPolCarBlacklisted() {
			return polCarBlacklisted;
		}

		public void setPolCarBlacklisted(String polCarBlacklisted) {
			this.polCarBlacklisted = polCarBlacklisted;
		}

		public String getPolCarSetOn() {
			return polCarSetOn;
		}

		public void setPolCarSetOn(String polCarSetOn) {
			this.polCarSetOn = polCarSetOn;
		}

		public String getPolCarSetBy() {
			return polCarSetBy;
		}

		public void setPolCarSetBy(String polCarSetBy) {
			this.polCarSetBy = polCarSetBy;
		}

		public String getPolCarReason() {
			return polCarReason;
		}

		public void setPolCarReason(String polCarReason) {
			this.polCarReason = polCarReason;
		}

		public String getPolCarNote() {
			return polCarNote;
		}

		public void setPolCarNote(String polCarNote) {
			this.polCarNote = polCarNote;
		}


		

}
