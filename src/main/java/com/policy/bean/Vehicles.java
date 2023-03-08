package com.policy.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.validation.Valid;

public class Vehicles implements Serializable {

	private static final long serialVersionUID = 1L;

	private BigInteger policyID;

	private String certificateNo;
	private BigInteger certifID;


	private String dateEffective;

	private String dateExpiry;

	private String carInsuredCode;
	private String  carBeneficiary;

	private String carinsuredfirstName;//change to carInsuredFirstName for lia
	
	private String carinsuredfatherName;
	
	private String carinsuredlastName ;
	
	private String carInsuredPhoneNumber;

	private Integer	carInsuredID;

	private String carMake;

	private String carMakeID;

	private String carMakeCode;

	private String carModelCode;

	private Integer carModelID;

	private String carModel;

	private String modelToPrint;

	private Boolean dealerRepair;

	private String dealerWorkshopName;

	private String carYear;

	private String carPlatePrefix;

	private String carPlateNumber;

	private String carUsage;

	private String carChassis;

	private String carEngine;

	private String carInsVal; // follow SI currency

	private String carStatus;
	@Valid
	private List<Clauses> clauses;

	private String mopNote;
//	// To send Policy MOP details as a note
	private Boolean certificateBlacklisted;
	// boolean
	private String certificateSetOn;

	private String certificateSetBy;

	private String certificateReason;

	private String certificateNote;

//	  private Boolean blacklisted ;
//	private String setOn ;
//	private String setBy ;
//
//	private String reason ;
//	private String note ;




	@Valid
	private List<Covers> covers;

	public String getCarBeneficiary() {
		return carBeneficiary;
	}

	public void setCarBeneficiary(String carBeneficiary) {
		this.carBeneficiary = carBeneficiary;
	}

	public BigInteger getPolicyID() {
		return policyID;
	}

	public void setPolicyID(BigInteger policyID) {
		this.policyID = policyID;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
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

	public String getCarInsuredCode() {
		return carInsuredCode;
	}

	public void setCarInsuredCode(String carInsuredCode) {
		this.carInsuredCode = carInsuredCode;
	}

	

	public Integer getCarInsuredID() {
		return carInsuredID;
	}

	public void setCarInsuredID(Integer carInsuredID) {
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

	public Integer getCarModelID() {
		return carModelID;
	}

	public void setCarModelID(Integer carModelID) {
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

	public Boolean getDealerRepair() {
		return dealerRepair;
	}

	public void setDealerRepair(Boolean dealerRepair) {
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

	public String getCarUsage() {
		return carUsage;
	}

	public void setCarUsage(String carUsage) {
		this.carUsage = carUsage;
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

	public String getCarInsVal() {
		return carInsVal;
	}

	public void setCarInsVal(String carInsVal) {
		this.carInsVal = carInsVal;
	}

	public String getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(String carStatus) {
		this.carStatus = carStatus;
	}

	public List<Clauses> getClauses() {
		return clauses;
	}

	public void setClauses(List<Clauses> clauses) {
		this.clauses = clauses;
	}

	public String getMopNote() {
		return mopNote;
	}

	public void setMopNote(String mopNote) {
		this.mopNote = mopNote;
	}

	public Boolean getCertificateBlacklisted() {
		return certificateBlacklisted;
	}

	public void setCertificateBlacklisted(Boolean certificateBlacklisted) {
		this.certificateBlacklisted = certificateBlacklisted;
	}

	public String getCertificateSetOn() {
		return certificateSetOn;
	}

	public void setCertificateSetOn(String certificateSetOn) {
		this.certificateSetOn = certificateSetOn;
	}

	public String getCertificateSetBy() {
		return certificateSetBy;
	}

	public void setCertificateSetBy(String certificateSetBy) {
		this.certificateSetBy = certificateSetBy;
	}

	public String getCertificateReason() {
		return certificateReason;
	}

	public void setCertificateReason(String certificateReason) {
		this.certificateReason = certificateReason;
	}

	public String getCertificateNote() {
		return certificateNote;
	}

	public void setCertificateNote(String certificateNote) {
		this.certificateNote = certificateNote;
	}


//	public Boolean getBlacklisted() {
//		return blacklisted;
//	}
//
//	public void setBlacklisted(Boolean blacklisted) {
//		this.blacklisted = blacklisted;
//	}
//
//	public String getSetOn() {
//		return setOn;
//	}
//
//	public void setSetOn(String setOn) {
//		this.setOn = setOn;
//	}
//
//	public String getSetBy() {
//		return setBy;
//	}
//
//	public void setSetBy(String setBy) {
//		this.setBy = setBy;
//	}
//
//	public String getReason() {
//		return reason;
//	}
//
//	public void setReason(String reason) {
//		this.reason = reason;
//	}
//
//	public String getNote() {
//		return note;
//	}
//
//	public void setNote(String note) {
//		this.note = note;
//	}

	public List<Covers> getCovers() {
		return covers;
	}

	public void setCovers(List<Covers> covers) {
		this.covers = covers;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCarinsuredfirstName() {
		return carinsuredfirstName;
	}

	public void setCarinsuredfirstName(String carinsuredfirstName) {
		this.carinsuredfirstName = carinsuredfirstName;
	}

	public String getCarinsuredfatherName() {
		return carinsuredfatherName;
	}

	public void setCarinsuredfatherName(String carinsuredfatherName) {
		this.carinsuredfatherName = carinsuredfatherName;
	}

	public String getCarinsuredlastName() {
		return carinsuredlastName;
	}

	public void setCarinsuredlastName(String carinsuredlastName) {
		this.carinsuredlastName = carinsuredlastName;
	}

	public String getCarInsuredPhoneNumber() {
		return carInsuredPhoneNumber;
	}

	public void setCarInsuredPhoneNumber(String carInsuredPhoneNumber) {
		this.carInsuredPhoneNumber = carInsuredPhoneNumber;
	}

	public BigInteger getCertifID() {
		return certifID;
	}

	public void setCertifID(BigInteger certifID) {
		this.certifID = certifID;
	}
}
