package com.policy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "CARS_DT_PARAMS")
public class CarsDtParams {
    @Id
    @Column(name = "DT_ID")
    private java.lang.String id;


    @Column(name = "INS_ID")
    private java.lang.String insuranceId;




    @Column(name = "PARAMS_CODE")
    private java.lang.String paramsCode;


    @Column(name = "PARAMS_CE_CODE")
    private java.lang.String paramsCeCode;





    @Column(name = "INS_MODEL_CODE")
    private java.lang.String insuranceModelCode;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getParamsCode() {
        return paramsCode;
    }

    public void setParamsCode(String paramsCode) {
        this.paramsCode = paramsCode;
    }

    public String getParamsCeCode() {
        return paramsCeCode;
    }

    public void setParamsCeCode(String paramsCeCode) {
        this.paramsCeCode = paramsCeCode;
    }

    public String getInsuranceModelCode() {
        return insuranceModelCode;
    }

    public void setInsuranceModelCode(String insuranceModelCode) {
        this.insuranceModelCode = insuranceModelCode;
    }

    public Integer getSysVersionNumber() {
        return sysVersionNumber;
    }

    public void setSysVersionNumber(Integer sysVersionNumber) {
        this.sysVersionNumber = sysVersionNumber;
    }

    public Timestamp getSysCreatedDate() {
        return sysCreatedDate;
    }

    public void setSysCreatedDate(Timestamp sysCreatedDate) {
        this.sysCreatedDate = sysCreatedDate;
    }

    public Timestamp getSysUpdatedDate() {
        return sysUpdatedDate;
    }

    public void setSysUpdatedDate(Timestamp sysUpdatedDate) {
        this.sysUpdatedDate = sysUpdatedDate;
    }

    public String getSysCreatedBy() {
        return sysCreatedBy;
    }

    public void setSysCreatedBy(String sysCreatedBy) {
        this.sysCreatedBy = sysCreatedBy;
    }

    public String getSysUpdatedBy() {
        return sysUpdatedBy;
    }

    public void setSysUpdatedBy(String sysUpdatedBy) {
        this.sysUpdatedBy = sysUpdatedBy;
    }
}
