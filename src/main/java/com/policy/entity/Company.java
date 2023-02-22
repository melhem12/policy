package com.policy.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
@Entity
@Table(name="CORE_COMPANY")
@EntityListeners(AuditingEntityListener.class)
public class Company  implements Cloneable {

    private static Log logger = LogFactory.getLog(Company.class);

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID")
    private java.lang.Integer companyId;
    @Column(name="NAME")

    private java.lang.String companyName;
    @Column(name="ADDRESS_INFO")

    private java.lang.String companyAddress;

    public java.lang.Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(java.lang.Integer companyId) {
        this.companyId = companyId;
    }

    public java.lang.String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(java.lang.String companyName) {
        this.companyName = companyName;
    }

    public java.lang.String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(java.lang.String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public Company() {
        super();
    }

    public Company(java.lang.Integer companyId) {
        super();

        this.setCompanyId(companyId);
    }






}
