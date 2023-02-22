package com.policy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CARS_INSURANCE")
public class CarsInsurance {
    @Id
    @Column(name = "INSURANCE_ID", nullable = false)
    private String insuranceId;
    @Column(name = "INSURANCE_DESC", nullable = false)
    private String insuranceDesc;

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsuranceDesc() {
        return insuranceDesc;
    }

    public void setInsuranceDesc(String insuranceDesc) {
        this.insuranceDesc = insuranceDesc;
    }
}
