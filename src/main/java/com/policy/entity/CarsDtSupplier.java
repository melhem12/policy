package com.policy.entity;



import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "CARS_DT_SUPPLIER")
public class CarsDtSupplier  implements Serializable {
    private static final long serialVersionUID = 2912313135903388401L;
    @Column(name = "CE_CODE", length = 502)
    private String ceCode;
    @Id
    @Column(name = "DT_ID", nullable = false, length = 36)
    private String dtId;
    @Column(name = "INS_ID")
    private String insId;
    @Column(name = "INS_SUPPLIER_CODE", length = 1000)
    private String insSupplierCode;
    @Column(name = "INS_SUPPLIER_DESC", length = 1000)
    private String insSupplierDesc;
    @Column(name = "INS_SUPPLIER_NAME", length = 1000)
    private String insSupplierName;
    @Column(name = "INS_SUPPLIER_NUM1", length = 1000)
    private String insSupplierNum1;
    @Column(name = "INS_SUPPLIER_NUM2", length = 1000)
    private String insSupplierNum2;
    @Column(name = "INS_SUPPLIER_NUM3", length = 1000)
    private String insSupplierNum3;
    @Column(name = "INS_SUPPLIER_NUM4", length = 1000)
    private String insSupplierNum4;
    @Column(name = "INS_SUPPLIER_NUM5", length = 1000)
    private String insSupplierNum5;
    @Column(name = "INS_SUPPLIER_OWNER", length = 1000)
    private String insSupplierOwner;
    @Column(name = "SUPPLIER_TYPE", length = 1)
    private String supplierType;

    
    @Transient
    private String supplierCeDesc;
    
    @Transient
    private String supplierDtType;



    public CarsDtSupplier() {
        dtId = UUID.randomUUID().toString();
    }


    public String getCeCode() {
        return ceCode;
    }

    public void setCeCode(String ceCode) {
        this.ceCode = ceCode;
    }

    public String getDtId() {
        return dtId;
    }

    public void setDtId(String dtId) {
        this.dtId = dtId;
    }

    public String getInsId() {
        return insId;
    }

    public void setInsId(String insId) {
        this.insId = insId;
    }

    public String getInsSupplierCode() {
        return insSupplierCode;
    }

    public void setInsSupplierCode(String insSupplierCode) {
        this.insSupplierCode = insSupplierCode;
    }

    public String getInsSupplierDesc() {
        return insSupplierDesc;
    }

    public void setInsSupplierDesc(String insSupplierDesc) {
        this.insSupplierDesc = insSupplierDesc;
    }

    public String getInsSupplierName() {
        return insSupplierName;
    }

    public void setInsSupplierName(String insSupplierName) {
        this.insSupplierName = insSupplierName;
    }

    public String getInsSupplierNum1() {
        return insSupplierNum1;
    }

    public void setInsSupplierNum1(String insSupplierNum1) {
        this.insSupplierNum1 = insSupplierNum1;
    }

    public String getInsSupplierNum2() {
        return insSupplierNum2;
    }

    public void setInsSupplierNum2(String insSupplierNum2) {
        this.insSupplierNum2 = insSupplierNum2;
    }

    public String getInsSupplierNum3() {
        return insSupplierNum3;
    }

    public void setInsSupplierNum3(String insSupplierNum3) {
        this.insSupplierNum3 = insSupplierNum3;
    }

    public String getInsSupplierNum4() {
        return insSupplierNum4;
    }

    public void setInsSupplierNum4(String insSupplierNum4) {
        this.insSupplierNum4 = insSupplierNum4;
    }

    public String getInsSupplierNum5() {
        return insSupplierNum5;
    }

    public void setInsSupplierNum5(String insSupplierNum5) {
        this.insSupplierNum5 = insSupplierNum5;
    }

    public String getInsSupplierOwner() {
        return insSupplierOwner;
    }

    public void setInsSupplierOwner(String insSupplierOwner) {
        this.insSupplierOwner = insSupplierOwner;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public String getSupplierCeDesc() {
        return supplierCeDesc;
    }

    public void setSupplierCeDesc(String supplierCeDesc) {
        this.supplierCeDesc = supplierCeDesc;
    }

    public String getSupplierDtType() {
        return supplierDtType;
    }

    public void setSupplierDtType(String supplierDtType) {
        this.supplierDtType = supplierDtType;
    }
}
