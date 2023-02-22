package com.policy.entity;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@Entity
@Table(name = "CARS_POLICY_CAR")
public class CarsPolicyCar  {

	private static final long serialVersionUID = 1L;

	private static Log logger = LogFactory.getLog(CarsPolicyCar.class);
	@Id
	@Column(name = "CAR_ID")
	private java.lang.String carId;

	@Column(name = "CAR_OBJECT_NUMBER")
	private java.lang.Integer carObjectNumber;

	@Column(name = "CAR_YEAR")
	private java.lang.Integer carYear;

	@Column(name = "CAR_CHASSIS")
	private java.lang.String carChassis;

	@Column(name = "CAR_BRAND_CODE")
	private java.lang.String carBrandCode;

	@Column(name = "CAR_TRADEMARK_CODE")
	private java.lang.String carTrademarkCode;

	@Column(name = "CAR_SHAPE_CODE")
	private java.lang.String carShapeCode;

	@Column(name = "CAR_TYPE")
	private java.lang.String carType;

	@Column(name = "CAR_USAGE_CODE")
	private java.lang.String carUsageCode;

	@Column(name = "CAR_PLATE")
	private java.lang.String carPlate;

	
	@Column(name = "CAR_COLOR")
	private java.lang.String carColor;

	@Column(name = "CAR_DEPRECIATION")
	private java.lang.String carDepreciation;

	@Column(name = "CAR_TP_DEPR")
	private java.lang.String carTpDepreciation;

	@Column(name = "CAR_BRAND_DESC")
	private java.lang.String carBrandDesc;

	@Column(name = "CAR_TRADEMARK_DESC")
	private java.lang.String carTrademarkDesc;

	@Column(name = "CAR_USAGE")
	private java.lang.String carUsage;

	@Column(name = "CAR_ENGINE")
	private java.lang.String carEngine;

	@Column(name = "CAR_DEDUCTIBLE")
	private java.lang.String carDeductible;

	@Column(name = "CAR_THEFT_DEDUCTIBLE")
	private java.lang.String carTheetDeductible;

	@Column(name = "CAR_HOLDUP_DEDUCTIBLE")
	private java.lang.String carHoldupDeductible;

	@Column(name = "CAR_PRODUCT_DESCRIPTION")
	private java.lang.String carProductDeductible;

	@Column(name = "CAR_VIP")
	private java.lang.String carVip;

	@Column(name = "POLICY_ID")
	private java.lang.String policyId;

	@Column(name = "SHAPE_ID")
	private java.lang.String shapeId;

	@Column(name = "CAR_BENEFICIARY")
	private java.lang.String carBeneficiary;

	@Column(name = "CAR_BENEFICIARY_DESC")
	private java.lang.String carBeneficiaryDesc;

	@Column(name = "BRAND_ID")
	private java.lang.String brandId;

	@Column(name = "TRADEMARK_ID")
	private java.lang.String trademarkId;

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

	@Column(name = "CAR_DEALER_WORKSHOP_NAME")
	private java.lang.String policyDealerWorkshopName;
	
	@Column(name = "CAR_VALUE")
	private BigDecimal carInsuranceValue;

	@Column(name = "CAR_INSURANCE")
	private Integer carInsurance;
	
	@Column(name = "CAR_LOB")
	private String carLob;


	@Column(name = "CAR_MODEL_TO_PRINT")
	private  String carModelToPrint ;

	@Column(name = "CAR_BLACKLISTING")
	private  String carBlackListing ;

	@Column(name = "CAR_CERTIF_ID")
	private java.lang.String carCertIfID;




	@Column(name = "CAR_CERTIF_BLACKLISTED")
	private java.lang.String carCertifdBlackListed;


	@Column(name = "CAR_CERTIF_BLACKLIST_SET_ON")
	private java.lang.String carCertifdBlackListedSetOn;

	@Column(name = "CAR_CERTIF_BLACKLIST_SET_BY")
	private java.lang.String carCertifdBlackListedSetBy;


	@Column(name = "CAR_CERTIF_BLACKLIST_REASON")
	private java.lang.String carCertifdBlackListedReason;


	@Column(name = "CAR_CERTIF_BLACKLIST_NOTE")
	private java.lang.String carCertifdBlackListedNote;


	public static Log getLogger() {
		return logger;
	}

	public static void setLogger(Log logger) {
		CarsPolicyCar.logger = logger;
	}

	public String getCarCertifdBlackListed() {
		return carCertifdBlackListed;
	}

	public void setCarCertifdBlackListed(String carCertifdBlackListed) {
		this.carCertifdBlackListed = carCertifdBlackListed;
	}

	public String getCarCertifdBlackListedSetOn() {
		return carCertifdBlackListedSetOn;
	}

	public void setCarCertifdBlackListedSetOn(String carCertifdBlackListedSetOn) {
		this.carCertifdBlackListedSetOn = carCertifdBlackListedSetOn;
	}

	public String getCarCertifdBlackListedSetBy() {
		return carCertifdBlackListedSetBy;
	}

	public void setCarCertifdBlackListedSetBy(String carCertifdBlackListedSetBy) {
		this.carCertifdBlackListedSetBy = carCertifdBlackListedSetBy;
	}

	public String getCarCertifdBlackListedReason() {
		return carCertifdBlackListedReason;
	}

	public void setCarCertifdBlackListedReason(String carCertifdBlackListedReason) {
		this.carCertifdBlackListedReason = carCertifdBlackListedReason;
	}

	public String getCarCertifdBlackListedNote() {
		return carCertifdBlackListedNote;
	}

	public void setCarCertifdBlackListedNote(String carCertifdBlackListedNote) {
		this.carCertifdBlackListedNote = carCertifdBlackListedNote;
	}

	public String getCarCertIfID() {
		return carCertIfID;
	}

	public void setCarCertIfID(String carCertIfID) {
		this.carCertIfID = carCertIfID;
	}

	public String getCarModelToPrint() {

		return carModelToPrint;
	}

	public void setCarModelToPrint(String carModelToPrint) {
		this.carModelToPrint = carModelToPrint;
	}

	public String getCarBlackListing() {
		return carBlackListing;
	}

	public void setCarBlackListing(String carBlackListing) {
		this.carBlackListing = carBlackListing;
	}




	public java.lang.String getCarId() {
		return carId;
	}

	public void setCarId(java.lang.String carId) {
		this.carId = carId;
	}

	public java.lang.Integer getCarObjectNumber() {
		return carObjectNumber;
	}

	public void setCarObjectNumber(java.lang.Integer carObjectNumber) {
		this.carObjectNumber = carObjectNumber;
	}

	public java.lang.Integer getCarYear() {
		return carYear;
	}

	public void setCarYear(java.lang.Integer carYear) {
		this.carYear = carYear;
	}

	public java.lang.String getCarChassis() {
		return carChassis;
	}

	public void setCarChassis(java.lang.String carChassis) {
		this.carChassis = carChassis;
	}

	public java.lang.String getCarBrandCode() {
		return carBrandCode;
	}

	public void setCarBrandCode(java.lang.String carBrandCode) {
		this.carBrandCode = carBrandCode;
	}

	public java.lang.String getCarTrademarkCode() {
		return carTrademarkCode;
	}

	public void setCarTrademarkCode(java.lang.String carTrademarkCode) {
		this.carTrademarkCode = carTrademarkCode;
	}

	public java.lang.String getCarShapeCode() {
		return carShapeCode;
	}

	public void setCarShapeCode(java.lang.String carShapeCode) {
		this.carShapeCode = carShapeCode;
	}

	public java.lang.String getCarType() {
		return carType;
	}

	public void setCarType(java.lang.String carType) {
		this.carType = carType;
	}

	public java.lang.String getCarUsageCode() {
		return carUsageCode;
	}

	public void setCarUsageCode(java.lang.String carUsageCode) {
		this.carUsageCode = carUsageCode;
	}

	public java.lang.String getCarPlate() {
		return carPlate;
	}

	public void setCarPlate(java.lang.String carPlate) {
		this.carPlate = carPlate;
	}

	public java.lang.String getCarColor() {
		return carColor;
	}

	public void setCarColor(java.lang.String carColor) {
		this.carColor = carColor;
	}

	public java.lang.String getCarDepreciation() {
		return carDepreciation;
	}

	public void setCarDepreciation(java.lang.String carDepreciation) {
		this.carDepreciation = carDepreciation;
	}

	public java.lang.String getCarTpDepreciation() {
		return carTpDepreciation;
	}

	public void setCarTpDepreciation(java.lang.String carTpDepreciation) {
		this.carTpDepreciation = carTpDepreciation;
	}

	public java.lang.String getCarBrandDesc() {
		return carBrandDesc;
	}

	public void setCarBrandDesc(java.lang.String carBrandDesc) {
		this.carBrandDesc = carBrandDesc;
	}

	public java.lang.String getCarTrademarkDesc() {
		return carTrademarkDesc;
	}

	public void setCarTrademarkDesc(java.lang.String carTrademarkDesc) {
		this.carTrademarkDesc = carTrademarkDesc;
	}

	public java.lang.String getCarUsage() {
		return carUsage;
	}

	public void setCarUsage(java.lang.String carUsage) {
		this.carUsage = carUsage;
	}

	public java.lang.String getCarEngine() {
		return carEngine;
	}

	public void setCarEngine(java.lang.String carEngine) {
		this.carEngine = carEngine;
	}

	public java.lang.String getCarDeductible() {
		return carDeductible;
	}

	public void setCarDeductible(java.lang.String carDeductible) {
		this.carDeductible = carDeductible;
	}

	public java.lang.String getCarTheetDeductible() {
		return carTheetDeductible;
	}

	public void setCarTheetDeductible(java.lang.String carTheetDeductible) {
		this.carTheetDeductible = carTheetDeductible;
	}

	public java.lang.String getCarHoldupDeductible() {
		return carHoldupDeductible;
	}

	public void setCarHoldupDeductible(java.lang.String carHoldupDeductible) {
		this.carHoldupDeductible = carHoldupDeductible;
	}

	public java.lang.String getCarProductDeductible() {
		return carProductDeductible;
	}

	public void setCarProductDeductible(java.lang.String carProductDeductible) {
		this.carProductDeductible = carProductDeductible;
	}

	public java.lang.String getCarVip() {
		return carVip;
	}

	public void setCarVip(java.lang.String carVip) {
		this.carVip = carVip;
	}

	public java.lang.String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(java.lang.String policyId) {
		this.policyId = policyId;
	}

	public java.lang.String getShapeId() {
		return shapeId;
	}

	public void setShapeId(java.lang.String shapeId) {
		this.shapeId = shapeId;
	}

	
	public java.lang.String getCarBeneficiary() {
		return carBeneficiary;
	}

	public void setCarBeneficiary(java.lang.String carBeneficiary) {
		this.carBeneficiary = carBeneficiary;
	}

	public java.lang.String getCarBeneficiaryDesc() {
		return carBeneficiaryDesc;
	}

	public void setCarBeneficiaryDesc(java.lang.String carBeneficiaryDesc) {
		this.carBeneficiaryDesc = carBeneficiaryDesc;
	}

	public java.lang.String getBrandId() {
		return brandId;
	}

	public void setBrandId(java.lang.String brandId) {
		this.brandId = brandId;
	}

	public java.lang.String getTrademarkId() {
		return trademarkId;
	}

	public void setTrademarkId(java.lang.String trademarkId) {
		this.trademarkId = trademarkId;
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

	public java.lang.String getPolicyDealerWorkshopName() {
		return policyDealerWorkshopName;
	}

	public void setPolicyDealerWorkshopName(java.lang.String policyDealerWorkshopName) {
		this.policyDealerWorkshopName = policyDealerWorkshopName;
	}

	public BigDecimal getCarInsuranceValue() {
		return carInsuranceValue;
	}

	public void setCarInsuranceValue(BigDecimal carInsuranceValue) {
		this.carInsuranceValue = carInsuranceValue;
	}

	public Integer getCarInsurance() {
		return carInsurance;
	}

	public void setCarInsurance(Integer carInsurance) {
		this.carInsurance = carInsurance;
	}

	public String getCarLob() {
		return carLob;
	}

	public void setCarLob(String carLob) {
		this.carLob = carLob;
	}

	



	

}
