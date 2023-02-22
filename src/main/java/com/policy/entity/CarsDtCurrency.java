package com.policy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CARS_DT_CURRENCY")
public class CarsDtCurrency {

	@Id
	@Column(name = "DT_ID")
	private String dtId;

	@Column(name = "CE_CODE")
	private String ceCode;
	
	@Column(name = "INS_CURRENCY_ABR")
	private String insCurrencyAbr;
	
	@Column(name = "INS_CURRENCY_CODE")
	private String insCurrencyCode;
	
	@Column(name = "INS_CURRENCY_DESC")
	private String insCurrencyDesc;
	
	@Column(name = "INS_ID")
	private Integer insId;

	public String getDtId() {
		return dtId;
	}

	public void setDtId(String dtId) {
		this.dtId = dtId;
	}

	public String getCeCode() {
		return ceCode;
	}

	public void setCeCode(String ceCode) {
		this.ceCode = ceCode;
	}

	public String getInsCurrencyAbr() {
		return insCurrencyAbr;
	}

	public void setInsCurrencyAbr(String insCurrencyAbr) {
		this.insCurrencyAbr = insCurrencyAbr;
	}

	public String getInsCurrencyCode() {
		return insCurrencyCode;
	}

	public void setInsCurrencyCode(String insCurrencyCode) {
		this.insCurrencyCode = insCurrencyCode;
	}

	public String getInsCurrencyDesc() {
		return insCurrencyDesc;
	}

	public void setInsCurrencyDesc(String insCurrencyDesc) {
		this.insCurrencyDesc = insCurrencyDesc;
	}

	public Integer getInsId() {
		return insId;
	}

	public void setInsId(Integer insId) {
		this.insId = insId;
	}
	
}
