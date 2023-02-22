package com.policy.entity;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Entity
@Table(name = "CARS_POLICY")
public class CarsPolicy {

	private static final long serialVersionUID = 1L;

	private static Log logger = LogFactory.getLog(CarsPolicy.class);


	@Id
	@Column(name = "POLICY_ID")
	private java.lang.String policyId;








	@Column(name = "POLICY_ROOT_ID")
	private java.lang.String policyRootId;

	@Column(name = "POLICY_FLEET_ID")
	private java.lang.String policyFleetId;

	@Column(name = "POLICY_CERTIF_ID")
	private java.lang.String policyCertIfID;













	public String getPolicyRootId() {
		return policyRootId;
	}

	public void setPolicyRootId(String policyRootId) {
		this.policyRootId = policyRootId;
	}

	public String getPolicyFleetId() {
		return policyFleetId;
	}

	public void setPolicyFleetId(String policyFleetId) {
		this.policyFleetId = policyFleetId;
	}

	public String getPolicyCertIfID() {
		return policyCertIfID;
	}

	public void setPolicyCertIfID(String policyCertIfID) {
		this.policyCertIfID = policyCertIfID;
	}

	@Column(name = "POLICY_BRANCH")
	private java.lang.String policyBranch;




	@Column(name = "POLICY_BLACKLIST")
	private java.lang.String policyBlackListed ;

	@Column(name = "POLICY_ENDORSMENT_TYPE_CODE")
	private java.lang.String policyEndorsementTypeCode ;

	@Column(name = "POLICY_ENDORSMENT_TYPE_DESC")
	private java.lang.String policyEndorsementTypeDesc ;

	@Column(name = "POLICY_ENDORS_SUB_TYPE_CODE")
	private java.lang.String policyEndorsSubTypeCode ;

	@Column(name = "POLICY_ENDORS_SUB_TYPE_DESC")
	private java.lang.String policyEndorsSubTypeDesc ;

	@Column(name = "POLICY_BLACKLIST_REASON")
private String  policyBlackListedReason ;

	@Column(name = "POLICY_BLACKLIST_NOTE")
	private String  policyBlackListedNote ;

	public String getPolicyBlackListedReason() {
		return policyBlackListedReason;
	}

	public void setPolicyBlackListedReason(String policyBlackListedReason) {
		this.policyBlackListedReason = policyBlackListedReason;
	}

	public String getPolicyBlackListedNote() {
		return policyBlackListedNote;
	}

	public void setPolicyBlackListedNote(String policyBlackListedNote) {
		this.policyBlackListedNote = policyBlackListedNote;
	}

	public static Log getLogger() {
		return logger;
	}

	public static void setLogger(Log logger) {
		CarsPolicy.logger = logger;
	}

	public String getPolicyBlackListed() {
		return policyBlackListed;
	}

	public void setPolicyBlackListed(String policyBlackListed) {
		this.policyBlackListed = policyBlackListed;
	}

	public String getPolicyEndorsementTypeCode() {
		return policyEndorsementTypeCode;
	}

	public void setPolicyEndorsementTypeCode(String policyEndorsementTypeCode) {
		this.policyEndorsementTypeCode = policyEndorsementTypeCode;
	}

	public String getPolicyEndorsementTypeDesc() {
		return policyEndorsementTypeDesc;
	}

	public void setPolicyEndorsementTypeDesc(String policyEndorsementTypeDesc) {
		this.policyEndorsementTypeDesc = policyEndorsementTypeDesc;
	}

	public String getPolicyEndorsSubTypeCode() {
		return policyEndorsSubTypeCode;
	}

	public void setPolicyEndorsSubTypeCode(String policyEndorsSubTypeCode) {
		this.policyEndorsSubTypeCode = policyEndorsSubTypeCode;
	}

	public String getPolicyEndorsSubTypeDesc() {
		return policyEndorsSubTypeDesc;
	}

	public void setPolicyEndorsSubTypeDesc(String policyEndorsSubTypeDesc) {
		this.policyEndorsSubTypeDesc = policyEndorsSubTypeDesc;
	}

	@Column(name = "POLICY_LOB")
	private java.lang.String policyLob;

	@Column(name = "POLICY_NUMBER")
	private java.lang.String policyNumber;
	@Column(name = "POLICY_CAR")
	private java.lang.Integer policyCar;

	@Column(name = "POLICY_AMENDMENT")
	private java.lang.Integer policyAmendment;

	@Column(name = "POLICY_PRODUCT")
	private java.lang.String policyProduct;

	@Column(name = "POLICY_CLIENT")
	private java.lang.Long policyClient;

	@Column(name = "POLICY_SECOND_CLIENT")
	private java.lang.Integer policySecondClient;

	@Column(name = "POLICY_BROKER_NUM")
	private java.lang.String policyBrokerNum;

	@Column(name = "POLICY_REMARKS")
	private java.lang.String policyRemarks;

	@Column(name = "POLICY_PRIMUM")
	private java.lang.Integer policyPrimum;

	@Column(name = "POLICY_CURRENCY")
	private java.lang.String policyCurrency;

	@Column(name = "POLICY_ACTION")
	private java.lang.String policyAction;

	@Column(name = "POLICY_UWY")
	private java.lang.Integer policyUwy;

	@Column(name = "POLICY_FLEET")
	private java.lang.String policyFleet;

	@Column(name = "POLICY_CLAUSES")
	private java.lang.String policyClauses;

	@Column(name = "POLICY_BROKER_BRANCH")
	private java.lang.String policyPolicyBranch;

	@Column(name = "POLICY_BROKER")
	private java.lang.String policyBroker;

	@Column(name = "POLICY_CAR_OWNER")
	private java.lang.String policyCarOwner;

	@Column(name = "POLICY_SECOND_CAR_OWNER")
	private java.lang.String policySecondCarOwner;

	@Column(name = "POLICY_ID_INS")
	private java.lang.String policyIdIns;

	@Column(name = "POLICY_SUBLINE_ID")
	private java.lang.String policySublineId;

	@Column(name = "POLICY_PLAN")
	private java.lang.String policyPlan;

	@Column(name = "POLICY_COVER")
	private java.lang.String policyCover;

	@Column(name = "POLICY_ENDORS1")
	private java.lang.Integer policyEndors1;

	@Column(name = "POLICY_ENDORS2")
	private java.lang.Integer policyEndors2;

	@Column(name = "POLICY_AGENCY_REPAIR")
	private java.lang.String policyAgencyRepair;

	@Column(name = "POLICY_CAR_REPLACEMENT")
	private java.lang.String policyCarReplacement;

	@Column(name = "POLICY_COMPANY")
	private java.lang.String policyCompany;

	@Column(name = "POLICY_PRODUCT_ID")
	private java.lang.String policyProductId;

	@Column(name = "POLICY_PRODUCTS_ID")
	private java.lang.String policyProductsId;

	@Column(name = "POLICY_CLIENT_ID")
	private java.lang.String policyClientId;

	@Column(name = "POLICY_TOW_FLAG")
	private java.lang.String policyTowFlag;

	@Column(name = "POLICY_SPECIAL_FLAG")
	private java.lang.String policySpecialFlag;

	@Column(name = "POLICY_FACULTATIF")
	private java.lang.String policyFacultatif;

	@Column(name = "POLICY_DEDUCTIBLE_AMT")
	private java.lang.Integer policyDeductibleAmt;
	
	@Column(name = "POLICY_DEDUCTIBLE")
	private java.lang.String policyDeductible;

	@Column(name = "POLICY_REPLACEMENT_FLAG")
	private java.lang.String policyReplacementFlag;

	@Column(name = "POLICY_REPLACEMENT_AMOUNT")
	private java.lang.Integer policyReplacementAmount;

	@Column(name = "POLICY_REPLACEMENT_CURRENCY")
	private java.lang.String policyReplacementCurrency;

	@Column(name = "POLICY_REPLACEMENT_NUMBER_DAYS")
	private java.lang.Integer policyReplacementNumberDays;

	@Column(name = "POLICY_REPLACEMENT_DED_NBRDAYS")
	private java.lang.Integer policyReplacementdecNbrdays;

	@Column(name = "POLICY_NEW_DEDUCTIBLE_DATE")
	private java.sql.Timestamp policyNewDeductibleDate;

	@Column(name = "POLICY_PRODUCT_2ND_NAMING")
	private java.lang.String policyProductSecindNaming;

	@Column(name = "POLICY_RESP")
	private java.lang.Integer policyResp;

	@Column(name = "POLICY_BRANCH_ID")
	private java.lang.String policyBranchId;

	@Column(name = "POLICY_BROKER_ID")
	private java.lang.String policyBrokerId;

	@Column(name = "POLICY_INSURANCE_ID")
	private java.lang.String policyInsuranceId;

	@Column(name = "POLICY_ISSUE_DATE")
	private java.sql.Timestamp policyIssueDate;

	@Column(name = "POLICY_EFFECTIVE_DATE")
	private java.sql.Timestamp policyEffectiveDate;

	@Column(name = "POLICY_EXPIRY_DATE")
	private java.sql.Timestamp policyExpiryDate;

	@Column(name = "POLICY_AMENDMENT_DATE")
	private java.sql.Timestamp policyAmendmentDate;

	@Column(name = "POLICY_DATE_DOCUMENT")
	private java.sql.Timestamp policyDateDocument;

	@Column(name = "POLICY_PRODUCT_DATE")
	private java.sql.Timestamp policyProductDate;

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
	
	
	@Column(name = "POLICY_BROKER_WEB_ORIGIN")
	private java.lang.String policyBrokerWebOrigin;
	
	@Column(name = "POLICY_IS_DIRECT_BROKER    ")
	private java.lang.String policyIsDirectBroker;
	
	@Column(name = "POLICY_WEB")
	private java.lang.String policyWeb;
	
	@Column(name = "POLICY_ISSUING_USER  ")
	private java.lang.String policyIssuingUser;
	
	
	@Column(name = "POLICY_MODIFIED_BY_USER")
	private java.lang.String policyModifiedUser;
	
	
	@Column(name = "POLICY_BLACKLIST_SET_ON")
	private java.lang.String policyBlacklistSetOn;
	
	@Column(name = "POLICY_BLACKLIST_SET_BY")
	private java.lang.String policyBlacklistSetBy;

	@Column(name = "POLICY_PREMIUM_CURRENCY")
	private java.lang.String policyPremiumCurrency;
	
	@Column(name = "POLICY_PREMIUM_CURRENCY_RATE")
	private BigDecimal policyPremiumCurrencyRate;
	
	@Column(name = "POLICY_SUM_INS_CURRENCY_RATE")
	private BigDecimal policySumInsCurrencyRate;
	
	
	@Column(name = "POLICY_INSURANCE_BL_SET_ON")
	private java.lang.String policyInsuranceBlacklistSetOn;
	
	@Column(name = "POLICY_INSURANCE_BL_SET_BY")
	private java.lang.String policyInsuranceBlacklistSetBy;
	
	@Column(name = "POLICY_BROKER_BL_SET_ON")
	private java.lang.String policyBrokerBlacklistSetOn;
	
	@Column(name = "POLICY_BROKER_BL_SET_BY")
	private java.lang.String policyBrokerBlacklistSetBy;
	
	@Column(name = "POLICY_PRINT_NAME")
	private java.lang.String policyPrintName;

	@Column(name = "POLICY_NUMBER_DISPLAY")
	private java.lang.String policyNumberDisplay;
	
	@Column(name = "POLICY_SPECIAL_NOTES")
	private java.lang.String policyNumberSpecialNotes;
	
	@Column(name = "POLICY_END_AT_NOON")
	private java.lang.String policyEndAtNoon;


	@Column(name = "POLICY_BATCH_NUM")

	private String policyBatchNum;



	@Column(name = "POLICY_HOLDER_ID")

	private String policyHolderId;

	@Column(name = "POLICY_HOLDER_CODE")
	private String policyHolderCode;

	@Column(name = "POLICY_HOLDER_NAME")
	private String policyHolderName;

	@Column(name = "POLICY_HOLDER_PHONE")

	private String policyHolderPhone;


	public String getPolicyHolderId() {
		return policyHolderId;
	}

	public void setPolicyHolderId(String policyHolderId) {
		this.policyHolderId = policyHolderId;
	}

	public String getPolicyHolderCode() {
		return policyHolderCode;
	}

	public void setPolicyHolderCode(String policyHolderCode) {
		this.policyHolderCode = policyHolderCode;
	}

	public String getPolicyHolderName() {
		return policyHolderName;
	}

	public void setPolicyHolderName(String policyHolderName) {
		this.policyHolderName = policyHolderName;
	}

	public String getPolicyHolderPhone() {
		return policyHolderPhone;
	}

	public void setPolicyHolderPhone(String policyHolderPhone) {
		this.policyHolderPhone = policyHolderPhone;
	}

	public String getPolicyBatchNum() {
		return policyBatchNum;
	}

	public void setPolicyBatchNum(String policyBatchNum) {
		this.policyBatchNum = policyBatchNum;
	}

	public java.lang.String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(java.lang.String policyId) {
		this.policyId = policyId;
	}

	public java.lang.String getPolicyBranch() {
		return policyBranch;
	}

	public void setPolicyBranch(java.lang.String policyBranch) {
		this.policyBranch = policyBranch;
	}

	public java.lang.String getPolicyLob() {
		return policyLob;
	}

	public void setPolicyLob(java.lang.String policyLob) {
		this.policyLob = policyLob;
	}

	public java.lang.String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(java.lang.String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public java.lang.Integer getPolicyCar() {
		return policyCar;
	}

	public void setPolicyCar(java.lang.Integer policyCar) {
		this.policyCar = policyCar;
	}

	public java.lang.Integer getPolicyAmendment() {
		return policyAmendment;
	}

	public void setPolicyAmendment(java.lang.Integer policyAmendment) {
		this.policyAmendment = policyAmendment;
	}

	public java.lang.String getPolicyProduct() {
		return policyProduct;
	}

	public void setPolicyProduct(java.lang.String policyProduct) {
		this.policyProduct = policyProduct;
	}

	public java.lang.Long getPolicyClient() {
		return policyClient;
	}

	public void setPolicyClient(java.lang.Long policyClient) {
		this.policyClient = policyClient;
	}

	public java.lang.Integer getPolicySecondClient() {
		return policySecondClient;
	}

	public void setPolicySecondClient(java.lang.Integer policySecondClient) {
		this.policySecondClient = policySecondClient;
	}

	public java.lang.String getPolicyBrokerNum() {
		return policyBrokerNum;
	}

	public void setPolicyBrokerNum(java.lang.String policyBrokerNum) {
		this.policyBrokerNum = policyBrokerNum;
	}

	public java.lang.String getPolicyRemarks() {
		return policyRemarks;
	}

	public void setPolicyRemarks(java.lang.String policyRemarks) {
		this.policyRemarks = policyRemarks;
	}

	public java.lang.Integer getPolicyPrimum() {
		return policyPrimum;
	}

	public void setPolicyPrimum(java.lang.Integer policyPrimum) {
		this.policyPrimum = policyPrimum;
	}

	public java.lang.String getPolicyCurrency() {
		return policyCurrency;
	}

	public void setPolicyCurrency(java.lang.String policyCurrency) {
		this.policyCurrency = policyCurrency;
	}

	public java.lang.String getPolicyAction() {
		return policyAction;
	}

	public void setPolicyAction(java.lang.String policyAction) {
		this.policyAction = policyAction;
	}

	public java.lang.Integer getPolicyUwy() {
		return policyUwy;
	}

	public void setPolicyUwy(java.lang.Integer policyUwy) {
		this.policyUwy = policyUwy;
	}

	public java.lang.String getPolicyFleet() {
		return policyFleet;
	}

	public void setPolicyFleet(java.lang.String policyFleet) {
		this.policyFleet = policyFleet;
	}

	public java.lang.String getPolicyClauses() {
		return policyClauses;
	}

	public void setPolicyClauses(java.lang.String policyClauses) {
		this.policyClauses = policyClauses;
	}

	public java.lang.String getPolicyPolicyBranch() {
		return policyPolicyBranch;
	}

	public void setPolicyPolicyBranch(java.lang.String policyPolicyBranch) {
		this.policyPolicyBranch = policyPolicyBranch;
	}

	public java.lang.String getPolicyBroker() {
		return policyBroker;
	}

	public void setPolicyBroker(java.lang.String policyBroker) {
		this.policyBroker = policyBroker;
	}

	public java.lang.String getPolicyCarOwner() {
		return policyCarOwner;
	}

	public void setPolicyCarOwner(java.lang.String policyCarOwner) {
		this.policyCarOwner = policyCarOwner;
	}

	public java.lang.String getPolicySecondCarOwner() {
		return policySecondCarOwner;
	}

	public void setPolicySecondCarOwner(java.lang.String policySecondCarOwner) {
		this.policySecondCarOwner = policySecondCarOwner;
	}

	public java.lang.String getPolicyIdIns() {
		return policyIdIns;
	}

	public void setPolicyIdIns(java.lang.String policyIdIns) {
		this.policyIdIns = policyIdIns;
	}


	public java.lang.String getPolicyPlan() {
		return policyPlan;
	}

	public void setPolicyPlan(java.lang.String policyPlan) {
		this.policyPlan = policyPlan;
	}

	public java.lang.String getPolicyCover() {
		return policyCover;
	}

	public void setPolicyCover(java.lang.String policyCover) {
		this.policyCover = policyCover;
	}

	public java.lang.Integer getPolicyEndors1() {
		return policyEndors1;
	}

	public void setPolicyEndors1(java.lang.Integer policyEndors1) {
		this.policyEndors1 = policyEndors1;
	}

	public java.lang.Integer getPolicyEndors2() {
		return policyEndors2;
	}

	public void setPolicyEndors2(java.lang.Integer policyEndors2) {
		this.policyEndors2 = policyEndors2;
	}

	public java.lang.String getPolicyAgencyRepair() {
		return policyAgencyRepair;
	}

	public void setPolicyAgencyRepair(java.lang.String policyAgencyRepair) {
		this.policyAgencyRepair = policyAgencyRepair;
	}

	public java.lang.String getPolicyCarReplacement() {
		return policyCarReplacement;
	}

	public void setPolicyCarReplacement(java.lang.String policyCarReplacement) {
		this.policyCarReplacement = policyCarReplacement;
	}

	public java.lang.String getPolicyCompany() {
		return policyCompany;
	}

	public void setPolicyCompany(java.lang.String policyCompany) {
		this.policyCompany = policyCompany;
	}

	public java.lang.String getPolicyProductId() {
		return policyProductId;
	}

	public void setPolicyProductId(java.lang.String policyProductId) {
		this.policyProductId = policyProductId;
	}

	public java.lang.String getPolicyProductsId() {
		return policyProductsId;
	}

	public void setPolicyProductsId(java.lang.String policyProductsId) {
		this.policyProductsId = policyProductsId;
	}

	public java.lang.String getPolicyClientId() {
		return policyClientId;
	}

	public void setPolicyClientId(java.lang.String policyClientId) {
		this.policyClientId = policyClientId;
	}

	public java.lang.String getPolicyTowFlag() {
		return policyTowFlag;
	}

	public void setPolicyTowFlag(java.lang.String policyTowFlag) {
		this.policyTowFlag = policyTowFlag;
	}

	public java.lang.String getPolicySpecialFlag() {
		return policySpecialFlag;
	}

	public void setPolicySpecialFlag(java.lang.String policySpecialFlag) {
		this.policySpecialFlag = policySpecialFlag;
	}

	public java.lang.String getPolicyFacultatif() {
		return policyFacultatif;
	}

	public void setPolicyFacultatif(java.lang.String policyFacultatif) {
		this.policyFacultatif = policyFacultatif;
	}

	public java.lang.Integer getPolicyDeductibleAmt() {
		return policyDeductibleAmt;
	}

	public void setPolicyDeductibleAmt(java.lang.Integer policyDeductibleAmt) {
		this.policyDeductibleAmt = policyDeductibleAmt;
	}

	public java.lang.String getPolicyDeductible() {
		return policyDeductible;
	}

	public void setPolicyDeductible(java.lang.String policyDeductible) {
		this.policyDeductible = policyDeductible;
	}

	public java.lang.String getPolicyReplacementFlag() {
		return policyReplacementFlag;
	}

	public void setPolicyReplacementFlag(java.lang.String policyReplacementFlag) {
		this.policyReplacementFlag = policyReplacementFlag;
	}

	public java.lang.Integer getPolicyReplacementAmount() {
		return policyReplacementAmount;
	}

	public void setPolicyReplacementAmount(java.lang.Integer policyReplacementAmount) {
		this.policyReplacementAmount = policyReplacementAmount;
	}

	public java.lang.String getPolicyReplacementCurrency() {
		return policyReplacementCurrency;
	}

	public void setPolicyReplacementCurrency(java.lang.String policyReplacementCurrency) {
		this.policyReplacementCurrency = policyReplacementCurrency;
	}

	public java.lang.Integer getPolicyReplacementNumberDays() {
		return policyReplacementNumberDays;
	}

	public void setPolicyReplacementNumberDays(java.lang.Integer policyReplacementNumberDays) {
		this.policyReplacementNumberDays = policyReplacementNumberDays;
	}

	public java.lang.Integer getPolicyReplacementdecNbrdays() {
		return policyReplacementdecNbrdays;
	}

	public void setPolicyReplacementdecNbrdays(java.lang.Integer policyReplacementdecNbrdays) {
		this.policyReplacementdecNbrdays = policyReplacementdecNbrdays;
	}

	public java.sql.Timestamp getPolicyNewDeductibleDate() {
		return policyNewDeductibleDate;
	}

	public void setPolicyNewDeductibleDate(java.sql.Timestamp policyNewDeductibleDate) {
		this.policyNewDeductibleDate = policyNewDeductibleDate;
	}

	public java.lang.String getPolicyProductSecindNaming() {
		return policyProductSecindNaming;
	}

	public void setPolicyProductSecindNaming(java.lang.String policyProductSecindNaming) {
		this.policyProductSecindNaming = policyProductSecindNaming;
	}

	public java.lang.Integer getPolicyResp() {
		return policyResp;
	}

	public void setPolicyResp(java.lang.Integer policyResp) {
		this.policyResp = policyResp;
	}

	public java.lang.String getPolicyBranchId() {
		return policyBranchId;
	}

	public void setPolicyBranchId(java.lang.String policyBranchId) {
		this.policyBranchId = policyBranchId;
	}

	public java.lang.String getPolicyBrokerId() {
		return policyBrokerId;
	}

	public void setPolicyBrokerId(java.lang.String policyBrokerId) {
		this.policyBrokerId = policyBrokerId;
	}

	public java.lang.String getPolicyInsuranceId() {
		return policyInsuranceId;
	}

	public void setPolicyInsuranceId(java.lang.String policyInsuranceId) {
		this.policyInsuranceId = policyInsuranceId;
	}

	public java.sql.Timestamp getPolicyIssueDate() {
		return policyIssueDate;
	}

	public void setPolicyIssueDate(java.sql.Timestamp policyIssueDate) {
		this.policyIssueDate = policyIssueDate;
	}

	public java.sql.Timestamp getPolicyEffectiveDate() {
		return policyEffectiveDate;
	}

	public void setPolicyEffectiveDate(java.sql.Timestamp policyEffectiveDate) {
		this.policyEffectiveDate = policyEffectiveDate;
	}

	public java.sql.Timestamp getPolicyExpiryDate() {
		return policyExpiryDate;
	}

	public void setPolicyExpiryDate(java.sql.Timestamp policyExpiryDate) {
		this.policyExpiryDate = policyExpiryDate;
	}

	public java.sql.Timestamp getPolicyAmendmentDate() {
		return policyAmendmentDate;
	}

	public void setPolicyAmendmentDate(java.sql.Timestamp policyAmendmentDate) {
		this.policyAmendmentDate = policyAmendmentDate;
	}

	public java.sql.Timestamp getPolicyDateDocument() {
		return policyDateDocument;
	}

	public void setPolicyDateDocument(java.sql.Timestamp policyDateDocument) {
		this.policyDateDocument = policyDateDocument;
	}

	public java.sql.Timestamp getPolicyProductDate() {
		return policyProductDate;
	}

	public void setPolicyProductDate(java.sql.Timestamp policyProductDate) {
		this.policyProductDate = policyProductDate;
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

	public java.lang.String getPolicyBrokerWebOrigin() {
		return policyBrokerWebOrigin;
	}

	public void setPolicyBrokerWebOrigin(java.lang.String policyBrokerWebOrigin) {
		this.policyBrokerWebOrigin = policyBrokerWebOrigin;
	}

	public java.lang.String getPolicyIsDirectBroker() {
		return policyIsDirectBroker;
	}

	public void setPolicyIsDirectBroker(java.lang.String policyIsDirectBroker) {
		this.policyIsDirectBroker = policyIsDirectBroker;
	}

	public java.lang.String getPolicyWeb() {
		return policyWeb;
	}

	public void setPolicyWeb(java.lang.String policyWeb) {
		this.policyWeb = policyWeb;
	}

	public java.lang.String getPolicyIssuingUser() {
		return policyIssuingUser;
	}

	public void setPolicyIssuingUser(java.lang.String policyIssuingUser) {
		this.policyIssuingUser = policyIssuingUser;
	}

	public java.lang.String getPolicyModifiedUser() {
		return policyModifiedUser;
	}

	public void setPolicyModifiedUser(java.lang.String policyModifiedUser) {
		this.policyModifiedUser = policyModifiedUser;
	}

	public java.lang.String getPolicyBlacklistSetOn() {
		return policyBlacklistSetOn;
	}

	public void setPolicyBlacklistSetOn(java.lang.String policyBlacklistSetOn) {
		this.policyBlacklistSetOn = policyBlacklistSetOn;
	}

	public java.lang.String getPolicyBlacklistSetBy() {
		return policyBlacklistSetBy;
	}

	public void setPolicyBlacklistSetBy(java.lang.String policyBlacklistSetBy) {
		this.policyBlacklistSetBy = policyBlacklistSetBy;
	}

	public java.lang.String getPolicyPremiumCurrency() {
		return policyPremiumCurrency;
	}

	public void setPolicyPremiumCurrency(java.lang.String policyPremiumCurrency) {
		this.policyPremiumCurrency = policyPremiumCurrency;
	}

	public BigDecimal getPolicyPremiumCurrencyRate() {
		return policyPremiumCurrencyRate;
	}

	public void setPolicyPremiumCurrencyRate(BigDecimal policyPremiumCurrencyRate) {
		this.policyPremiumCurrencyRate = policyPremiumCurrencyRate;
	}

	public BigDecimal getPolicySumInsCurrencyRate() {
		return policySumInsCurrencyRate;
	}

	public void setPolicySumInsCurrencyRate(BigDecimal policySumInsCurrencyRate) {
		this.policySumInsCurrencyRate = policySumInsCurrencyRate;
	}

	public java.lang.String getPolicyInsuranceBlacklistSetOn() {
		return policyInsuranceBlacklistSetOn;
	}

	public void setPolicyInsuranceBlacklistSetOn(java.lang.String policyInsuranceBlacklistSetOn) {
		this.policyInsuranceBlacklistSetOn = policyInsuranceBlacklistSetOn;
	}

	public java.lang.String getPolicyInsuranceBlacklistSetBy() {
		return policyInsuranceBlacklistSetBy;
	}

	public void setPolicyInsuranceBlacklistSetBy(java.lang.String policyInsuranceBlacklistSetBy) {
		this.policyInsuranceBlacklistSetBy = policyInsuranceBlacklistSetBy;
	}

	public java.lang.String getPolicyBrokerBlacklistSetOn() {
		return policyBrokerBlacklistSetOn;
	}

	public void setPolicyBrokerBlacklistSetOn(java.lang.String policyBrokerBlacklistSetOn) {
		this.policyBrokerBlacklistSetOn = policyBrokerBlacklistSetOn;
	}

	public java.lang.String getPolicyBrokerBlacklistSetBy() {
		return policyBrokerBlacklistSetBy;
	}

	public void setPolicyBrokerBlacklistSetBy(java.lang.String policyBrokerBlacklistSetBy) {
		this.policyBrokerBlacklistSetBy = policyBrokerBlacklistSetBy;
	}

	public java.lang.String getPolicyPrintName() {
		return policyPrintName;
	}

	public void setPolicyPrintName(java.lang.String policyPrintName) {
		this.policyPrintName = policyPrintName;
	}

	public java.lang.String getPolicyNumberDisplay() {
		return policyNumberDisplay;
	}

	public void setPolicyNumberDisplay(java.lang.String policyNumberDisplay) {
		this.policyNumberDisplay = policyNumberDisplay;
	}

	public java.lang.String getPolicyNumberSpecialNotes() {
		return policyNumberSpecialNotes;
	}

	public void setPolicyNumberSpecialNotes(java.lang.String policyNumberSpecialNotes) {
		this.policyNumberSpecialNotes = policyNumberSpecialNotes;
	}

	public java.lang.String getPolicySublineId() {
		return policySublineId;
	}

	public void setPolicySublineId(java.lang.String policySublineId) {
		this.policySublineId = policySublineId;
	}

	public java.lang.String getPolicyEndAtNoon() {
		return policyEndAtNoon;
	}

	public void setPolicyEndAtNoon(java.lang.String policyEndAtNoon) {
		this.policyEndAtNoon = policyEndAtNoon;
	}




	
}
