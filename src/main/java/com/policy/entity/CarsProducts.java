package com.policy.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Entity
@Table(name = "CARS_PRODUCTS")
public class CarsProducts  {

	private static final long serialVersionUID = 1L;

	private static Log logger = LogFactory.getLog(CarsProducts.class);

	@Id
	@Column(name = "PRODUCTS_ID")
	private java.lang.String productsId;
	
	@Column(name = "PRODUCTS_CODE")
	private java.lang.String productsCode;
	
	@Column(name = "PRODUCTS_DESCRIPTION")
	private java.lang.String productsDescription;
	
	@Column(name = "PRODUCTS_LOB")
	private java.lang.String productsLob;
	
	@Column(name = "PRODUCTS_TARIF")
	private java.lang.String productsTarif;
	
	@Column(name = "PRODUCTS_INSURANCE_ID")
	private java.lang.String productsInsuranceId;
	
	@Column(name = "PRODUCTS_TYPE")
	private java.lang.String productsType;
	
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

	@Column(name = "PRODUCTS_REFERENCE")
	private java.lang.String productsReference;
	
	public java.lang.String getProductsId() {
		return productsId;
	}

	public void setProductsId(java.lang.String productsId) {
		this.productsId = productsId;
	}

	public java.lang.String getProductsCode() {
		return productsCode;
	}

	public void setProductsCode(java.lang.String productsCode) {
		this.productsCode = productsCode;
	}

	public java.lang.String getProductsDescription() {
		return productsDescription;
	}

	public void setProductsDescription(java.lang.String productsDescription) {
		this.productsDescription = productsDescription;
	}

	public java.lang.String getProductsLob() {
		return productsLob;
	}

	public void setProductsLob(java.lang.String productsLob) {
		this.productsLob = productsLob;
	}

	public java.lang.String getProductsTarif() {
		return productsTarif;
	}

	public void setProductsTarif(java.lang.String productsTarif) {
		this.productsTarif = productsTarif;
	}

	public java.lang.String getProductsInsuranceId() {
		return productsInsuranceId;
	}

	public void setProductsInsuranceId(java.lang.String productsInsuranceId) {
		this.productsInsuranceId = productsInsuranceId;
	}

	public java.lang.String getProductsType() {
		return productsType;
	}

	public void setProductsType(java.lang.String productsType) {
		this.productsType = productsType;
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

	public java.lang.String getProductsReference() {
		return productsReference;
	}

	public void setProductsReference(java.lang.String productsReference) {
		this.productsReference = productsReference;
	}

}
