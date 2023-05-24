package com.policy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.persistence.Id;

@Entity
@Table(name = "CARS_CLIENT")
public class CarsClient {

	private static final long serialVersionUID = 1L;

	private static Log logger = LogFactory.getLog(CarsClient.class);
	@Id
	@Column(name = "CLIENT_ID")
	private java.lang.String clientId;

	@Column(name = "CLIENT_NUM_1")
	private java.lang.String clientNum1;

	@Column(name = "CLIENT_NUM_2")
	private java.lang.Long clientNum2;

	@Column(name = "CLIENT_FAMILY_NAME")
	private java.lang.String clientFamilyName;

	@Column(name = "CLIENT_PREFIX_FAMILY")
	private java.lang.String clientPrefixName;

	@Column(name = "CLIENT_FIRST_NAME")
	private java.lang.String clientFirstName;

	@Column(name = "CLIENT_FATHER_NAME")
	private java.lang.String clientFatherName;

	@Column(name = "CLIENT_BUS_PHONE")
	private java.lang.String clientBusinessPhone;




	@Column(name = "CLIENT_REFERENCE")
	private java.lang.String clientReference;

	@Column(name = "CLIENT_MOBILE_PHONE")
	private java.lang.String clientMobilePhone;

	@Column(name = "CLIENT_TITRE")
	private java.lang.String clientTitre;

	@Column(name = "CLIENT_GENDER")
	private java.lang.String clientGender;

	@Column(name = "CLIENT_BROKER")
	private java.lang.Integer clientBroker;

	@Column(name = "CLIENT_INDIC1")
	private java.lang.Integer clientIndic1;

	@Column(name = "CLIENT_INDIC2")
	private java.lang.Integer clientIndic2;

	@Column(name = "CLIENT_VIP")
	private java.lang.String clientVip;

	@Column(name = "CLIENT_DESCRIPTION")
	private java.lang.String clientDescription;

	@Column(name = "CLIENT_INSURANCE_ID")
	private java.lang.String clientInsuranceId;

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

	public java.lang.String getClientId() {
		return clientId;
	}

	public void setClientId(java.lang.String clientId) {
		this.clientId = clientId;
	}

	
	public java.lang.String getClientNum1() {
		return clientNum1;
	}

	public void setClientNum1(java.lang.String clientNum1) {
		this.clientNum1 = clientNum1;
	}

	public java.lang.Long getClientNum2() {
		return clientNum2;
	}

	public void setClientNum2(java.lang.Long clientNum2) {
		this.clientNum2 = clientNum2;
	}

	public java.lang.String getClientFamilyName() {
		return clientFamilyName;
	}

	public void setClientFamilyName(java.lang.String clientFamilyName) {
		this.clientFamilyName = clientFamilyName;
	}

	public java.lang.String getClientPrefixName() {
		return clientPrefixName;
	}

	public void setClientPrefixName(java.lang.String clientPrefixName) {
		this.clientPrefixName = clientPrefixName;
	}

	public java.lang.String getClientFirstName() {
		return clientFirstName;
	}

	public void setClientFirstName(java.lang.String clientFirstName) {
		this.clientFirstName = clientFirstName;
	}

	public java.lang.String getClientFatherName() {
		return clientFatherName;
	}

	public void setClientFatherName(java.lang.String clientFatherName) {
		this.clientFatherName = clientFatherName;
	}

	public java.lang.String getClientBusinessPhone() {
		return clientBusinessPhone;
	}

	public void setClientBusinessPhone(java.lang.String clientBusinessPhone) {
		this.clientBusinessPhone = clientBusinessPhone;
	}

	public java.lang.String getClientMobilePhone() {
		return clientMobilePhone;
	}

	public void setClientMobilePhone(java.lang.String clientMobilePhone) {
		this.clientMobilePhone = clientMobilePhone;
	}

	public java.lang.String getClientTitre() {
		return clientTitre;
	}

	public void setClientTitre(java.lang.String clientTitre) {
		this.clientTitre = clientTitre;
	}

	public java.lang.String getClientGender() {
		return clientGender;
	}

	public void setClientGender(java.lang.String clientGender) {
		this.clientGender = clientGender;
	}

	public java.lang.Integer getClientBroker() {
		return clientBroker;
	}

	public void setClientBroker(java.lang.Integer clientBroker) {
		this.clientBroker = clientBroker;
	}

	public java.lang.Integer getClientIndic1() {
		return clientIndic1;
	}

	public void setClientIndic1(java.lang.Integer clientIndic1) {
		this.clientIndic1 = clientIndic1;
	}

	public java.lang.Integer getClientIndic2() {
		return clientIndic2;
	}

	public void setClientIndic2(java.lang.Integer clientIndic2) {
		this.clientIndic2 = clientIndic2;
	}

	public java.lang.String getClientVip() {
		return clientVip;
	}

	public void setClientVip(java.lang.String clientVip) {
		this.clientVip = clientVip;
	}

	public java.lang.String getClientDescription() {
		return clientDescription;
	}

	public void setClientDescription(java.lang.String clientDescription) {
		this.clientDescription = clientDescription;
	}

	public java.lang.String getClientInsuranceId() {
		return clientInsuranceId;
	}

	public void setClientInsuranceId(java.lang.String clientInsuranceId) {
		this.clientInsuranceId = clientInsuranceId;
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

	public static Log getLogger() {
		return logger;
	}

	public static void setLogger(Log logger) {
		CarsClient.logger = logger;
	}

	public String getClientReference() {
		return clientReference;
	}

	public void setClientReference(String clientReference) {
		this.clientReference = clientReference;
	}
}
