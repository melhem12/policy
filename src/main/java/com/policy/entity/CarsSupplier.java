package com.policy.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "CARS_SUPPLIER")

public class CarsSupplier  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "SUPPLIER_COUNTRY")
	private String supplierCountry;
	
	
	@Column(name = "SUPPLIER_COMPANY")
	private Integer supplierCompany;
	
	
	@Column(name = "SUPPLIER_INTERM")
	private String supplierInterm;
	
	
	@Column(name = "SUPPLIER_NUM")
	private Integer supplierNum;
	
	
	@Column(name = "SUPPLIER_TITRE")
	private String supplierTitre;
	
	
	@Column(name = "SUPPLIER_PREFIX_FAM")
	private String supplierPrefixFam;
	
	
	@Column(name = "SUPPLIER_FAMILY_NAME")
	private String supplierFamilyName;
	
	
	@Column(name = "SUPPLIER_FIRST_NAME")
	private String supplierFirstName;
	
	
	@Column(name = "SUPPLIER_FATHER_NAME")
	private String supplierFatherName;
	
	
	@Column(name = "SUPPLIER_HOME_BLDG")
	private String supplierHomeBldg;
	
	
	@Column(name = "SUPPLIER_HOME_STREET")
	private String supplierHomeStreet;
	
	
	@Column(name = "SUPPLIER_HOME_COUNTRY")
	private String supplierHomeCountry;
	
	
	@Column(name = "SUPPLIER_HOME_DISTRICT")
	private String supplierHomeDistrict;
	
	
	@Column(name = "SUPPLIER_HOME_SECTOR")
	private String supplierHomeSector;
	
	
	@Column(name = "SUPPLIER_HOME_CITY")
	private String supplierHomeCity;
	
	
	@Column(name = "SUPPLIER_HOME_ZIP_1")
	private String supplierHomeZip1;
	
	
	@Column(name = "SUPPLIER_HOME_PHONE")
	private String supplierHomePhone;
	
	
	@Column(name = "SUPPLIER_BUS_STREET")
	private String supplierBusStreet;
	
	
	@Column(name = "SUPPLIER_BUS_COUNTRY")
	private String supplierBusCountry;
	
	
	@Column(name = "SUPPLIER_BUS_DISTRICT")
	private String supplierBusDistrict;
	
	
	@Column(name = "SUPPLIER_BUS_SECTOR")
	private String supplierBusSector;
	
	
	@Column(name = "SUPPLIER_BUS_CITY")
	private String supplierBusCity;
	
	
	@Column(name = "SUPPLIER_BUS_ZIP_1")
	private String supplierBusZip1;
	
	
	@Column(name = "SUPPLIER_BUS_PHONE")
	private String supplierBusPhone;
	
	
	@Column(name = "SUPPLIER_MOBILE_PHONE")
	private String supplierMobilePhone;
	
	
	@Column(name = "SUPPLIER_FDATEJ")
	private Integer supplierFdatej;
	
	
	@Column(name = "SUPPLIER_TAXE")
	private String supplierTaxe;
	
	
	@Column(name = "SUPPLIER_INACTDJ")
	private Integer supplierInactdj;
	
	
	@Column(name = "SUPPLIER_SRC")
	private String supplierSrc;
	
	
	@Column(name = "SUPPLIER_NCONTACT")
	private String supplierNcontact;
	
	
	@Column(name = "SUPPLIER_FAX")
	private String supplierFax;
	
	
	@Column(name = "SUPPLIER_MECHANIC_LABOR")
	private Integer supplierMechanicLabor;
	
	
	@Column(name = "SUPPLIER_PAINT_LABOR")
	private Integer supplierPaintLabor;

	@Column(name = "SUPPLIER_ARABIC_NAME")
	private String supplierArabicName;
	
	
	@Column(name = "SUPPLIER_SMS")
	private String supplierSms;
	
	
	@Column(name = "SUPPLIER_EMAIL")
	private String supplierEmail;
	
	
	@Column(name = "SUPPLIER_INITIAL_COUNT")
	private Integer supplierInitialCount;
	
	
	@Column(name = "SUPPLIER_REG_NUM")
	private String supplierRegNum;
	
	
	@Column(name = "SUPPLIER_TVA_NUM")
	private String supplierTvaNum;
	
	
	@Id
	@Column(name = "SUPPLIER_ID")
	private String supplierId;
	
	
	

	
	@Column(name = "SUPPLIER_GRADE_ID")
	private String supplierGradeId;
	
	
	@Column(name = "SUPPLIER_SHOW_IN_LIST")
	private String supplierShowInList;
	
	
	@Column(name = "SUPPLIER_NAME")
	private String supplierName;
	
	
	@Column(name = "SUPPLIER_OUT_NETWORK")
	private String supplierOutNetwork;
	
	
	@Column(name = "SUPPLIER_FDATE")
	private LocalDateTime supplierFdate;
	
	
	@Column(name = "SUPPLIER_INACTD")
	private LocalDateTime supplierInactd;
	
	@Column(name = "SUPPLIER_TYPE")
	private String supplierType;
	
	
	@Column(name = "SUPPLIER_INCLUDE_APP")
	private String supplierIncludeApp;
	
	
	@Column(name = "SUPPLIER_CORE_USER_ID")
	private String supplierCoreUserId;
	
	
	@Column(name = "SUPPLIER_LATITUDE")
	private String supplierLatitude;
	
	
//	@Column(name = "SUPPLIER_LONGITUDE")
//	private String supplierLongitude;
//
	public CarsSupplier() {
		this.supplierId = UUID.randomUUID().toString();
	}


	public String getSupplierCountry() {
		return supplierCountry;
	}

	public void setSupplierCountry(String supplierCountry) {
		this.supplierCountry = supplierCountry;
	}

	public Integer getSupplierCompany() {
		return supplierCompany;
	}

	public void setSupplierCompany(Integer supplierCompany) {
		this.supplierCompany = supplierCompany;
	}

	public String getSupplierInterm() {
		return supplierInterm;
	}

	public void setSupplierInterm(String supplierInterm) {
		this.supplierInterm = supplierInterm;
	}

	public Integer getSupplierNum() {
		return supplierNum;
	}

	public void setSupplierNum(Integer supplierNum) {
		this.supplierNum = supplierNum;
	}

	public String getSupplierTitre() {
		return supplierTitre;
	}

	public void setSupplierTitre(String supplierTitre) {
		this.supplierTitre = supplierTitre;
	}

	public String getSupplierPrefixFam() {
		return supplierPrefixFam;
	}

	public void setSupplierPrefixFam(String supplierPrefixFam) {
		this.supplierPrefixFam = supplierPrefixFam;
	}

	public String getSupplierFamilyName() {
		return supplierFamilyName;
	}

	public void setSupplierFamilyName(String supplierFamilyName) {
		this.supplierFamilyName = supplierFamilyName;
	}

	public String getSupplierFirstName() {
		return supplierFirstName;
	}

	public void setSupplierFirstName(String supplierFirstName) {
		this.supplierFirstName = supplierFirstName;
	}

	public String getSupplierFatherName() {
		return supplierFatherName;
	}

	public void setSupplierFatherName(String supplierFatherName) {
		this.supplierFatherName = supplierFatherName;
	}

	public String getSupplierHomeBldg() {
		return supplierHomeBldg;
	}

	public void setSupplierHomeBldg(String supplierHomeBldg) {
		this.supplierHomeBldg = supplierHomeBldg;
	}

	public String getSupplierHomeStreet() {
		return supplierHomeStreet;
	}

	public void setSupplierHomeStreet(String supplierHomeStreet) {
		this.supplierHomeStreet = supplierHomeStreet;
	}

	public String getSupplierHomeCountry() {
		return supplierHomeCountry;
	}

	public void setSupplierHomeCountry(String supplierHomeCountry) {
		this.supplierHomeCountry = supplierHomeCountry;
	}

	public String getSupplierHomeDistrict() {
		return supplierHomeDistrict;
	}

	public void setSupplierHomeDistrict(String supplierHomeDistrict) {
		this.supplierHomeDistrict = supplierHomeDistrict;
	}

	public String getSupplierHomeSector() {
		return supplierHomeSector;
	}

	public void setSupplierHomeSector(String supplierHomeSector) {
		this.supplierHomeSector = supplierHomeSector;
	}

	public String getSupplierHomeCity() {
		return supplierHomeCity;
	}

	public void setSupplierHomeCity(String supplierHomeCity) {
		this.supplierHomeCity = supplierHomeCity;
	}

	public String getSupplierHomeZip1() {
		return supplierHomeZip1;
	}

	public void setSupplierHomeZip1(String supplierHomeZip1) {
		this.supplierHomeZip1 = supplierHomeZip1;
	}

	public String getSupplierHomePhone() {
		return supplierHomePhone;
	}

	public void setSupplierHomePhone(String supplierHomePhone) {
		this.supplierHomePhone = supplierHomePhone;
	}

	public String getSupplierBusStreet() {
		return supplierBusStreet;
	}

	public void setSupplierBusStreet(String supplierBusStreet) {
		this.supplierBusStreet = supplierBusStreet;
	}

	public String getSupplierBusCountry() {
		return supplierBusCountry;
	}

	public void setSupplierBusCountry(String supplierBusCountry) {
		this.supplierBusCountry = supplierBusCountry;
	}

	public String getSupplierBusDistrict() {
		return supplierBusDistrict;
	}

	public void setSupplierBusDistrict(String supplierBusDistrict) {
		this.supplierBusDistrict = supplierBusDistrict;
	}

	public String getSupplierBusSector() {
		return supplierBusSector;
	}

	public void setSupplierBusSector(String supplierBusSector) {
		this.supplierBusSector = supplierBusSector;
	}

	public String getSupplierBusCity() {
		return supplierBusCity;
	}

	public void setSupplierBusCity(String supplierBusCity) {
		this.supplierBusCity = supplierBusCity;
	}

	public String getSupplierBusZip1() {
		return supplierBusZip1;
	}

	public void setSupplierBusZip1(String supplierBusZip1) {
		this.supplierBusZip1 = supplierBusZip1;
	}

	public String getSupplierBusPhone() {
		return supplierBusPhone;
	}

	public void setSupplierBusPhone(String supplierBusPhone) {
		this.supplierBusPhone = supplierBusPhone;
	}

	public String getSupplierMobilePhone() {
		return supplierMobilePhone;
	}

	public void setSupplierMobilePhone(String supplierMobilePhone) {
		this.supplierMobilePhone = supplierMobilePhone;
	}

	public Integer getSupplierFdatej() {
		return supplierFdatej;
	}

	public void setSupplierFdatej(Integer supplierFdatej) {
		this.supplierFdatej = supplierFdatej;
	}

	public String getSupplierTaxe() {
		return supplierTaxe;
	}

	public void setSupplierTaxe(String supplierTaxe) {
		this.supplierTaxe = supplierTaxe;
	}

	public Integer getSupplierInactdj() {
		return supplierInactdj;
	}

	public void setSupplierInactdj(Integer supplierInactdj) {
		this.supplierInactdj = supplierInactdj;
	}

	public String getSupplierSrc() {
		return supplierSrc;
	}

	public void setSupplierSrc(String supplierSrc) {
		this.supplierSrc = supplierSrc;
	}

	public String getSupplierNcontact() {
		return supplierNcontact;
	}

	public void setSupplierNcontact(String supplierNcontact) {
		this.supplierNcontact = supplierNcontact;
	}

	public String getSupplierFax() {
		return supplierFax;
	}

	public void setSupplierFax(String supplierFax) {
		this.supplierFax = supplierFax;
	}

	public Integer getSupplierMechanicLabor() {
		return supplierMechanicLabor;
	}

	public void setSupplierMechanicLabor(Integer supplierMechanicLabor) {
		this.supplierMechanicLabor = supplierMechanicLabor;
	}

	public Integer getSupplierPaintLabor() {
		return supplierPaintLabor;
	}

	public void setSupplierPaintLabor(Integer supplierPaintLabor) {
		this.supplierPaintLabor = supplierPaintLabor;
	}

	public String getSupplierArabicName() {
		return supplierArabicName;
	}

	public void setSupplierArabicName(String supplierArabicName) {
		this.supplierArabicName = supplierArabicName;
	}

	public String getSupplierSms() {
		return supplierSms;
	}

	public void setSupplierSms(String supplierSms) {
		this.supplierSms = supplierSms;
	}

	public String getSupplierEmail() {
		return supplierEmail;
	}

	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}

	public Integer getSupplierInitialCount() {
		return supplierInitialCount;
	}

	public void setSupplierInitialCount(Integer supplierInitialCount) {
		this.supplierInitialCount = supplierInitialCount;
	}

	public String getSupplierRegNum() {
		return supplierRegNum;
	}

	public void setSupplierRegNum(String supplierRegNum) {
		this.supplierRegNum = supplierRegNum;
	}

	public String getSupplierTvaNum() {
		return supplierTvaNum;
	}

	public void setSupplierTvaNum(String supplierTvaNum) {
		this.supplierTvaNum = supplierTvaNum;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierGradeId() {
		return supplierGradeId;
	}

	public void setSupplierGradeId(String supplierGradeId) {
		this.supplierGradeId = supplierGradeId;
	}

	public String getSupplierShowInList() {
		return supplierShowInList;
	}

	public void setSupplierShowInList(String supplierShowInList) {
		this.supplierShowInList = supplierShowInList;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierOutNetwork() {
		return supplierOutNetwork;
	}

	public void setSupplierOutNetwork(String supplierOutNetwork) {
		this.supplierOutNetwork = supplierOutNetwork;
	}

	public LocalDateTime getSupplierFdate() {
		return supplierFdate;
	}

	public void setSupplierFdate(LocalDateTime supplierFdate) {
		this.supplierFdate = supplierFdate;
	}

	public LocalDateTime getSupplierInactd() {
		return supplierInactd;
	}

	public void setSupplierInactd(LocalDateTime supplierInactd) {
		this.supplierInactd = supplierInactd;
	}

	public String getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

	public String getSupplierIncludeApp() {
		return supplierIncludeApp;
	}

	public void setSupplierIncludeApp(String supplierIncludeApp) {
		this.supplierIncludeApp = supplierIncludeApp;
	}

	public String getSupplierCoreUserId() {
		return supplierCoreUserId;
	}

	public void setSupplierCoreUserId(String supplierCoreUserId) {
		this.supplierCoreUserId = supplierCoreUserId;
	}

	public String getSupplierLatitude() {
		return supplierLatitude;
	}

	public void setSupplierLatitude(String supplierLatitude) {
		this.supplierLatitude = supplierLatitude;
	}

//	public String getSupplierLongitude() {
//		return supplierLongitude;
//	}
//
//	public void setSupplierLongitude(String supplierLongitude) {
//		this.supplierLongitude = supplierLongitude;
//	}
}