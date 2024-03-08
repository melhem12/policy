package com.policy.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProfileRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Supplier ID cannot be blank")
    private String supplierId;

    @NotBlank(message = "Supplier Type cannot be blank")
    private String supplierType;

    @NotBlank(message = "Supplier Family Name cannot blank")
    private String supplierFamilyName;

    @NotBlank(message = "supplier Business Town Name  cannot be blank")
    private String supplierBusinessTownName;
    @NotBlank(message = "Supplier Family Name cannot be be blank")
    private String supplierBusinessCazaName;

    @NotBlank(message = "supplier Business Mouhafaza  Name cannot be be blank")

    private String supplierBusinessMouhafazaName;

    @Pattern(regexp = "^[0-9]{8}$", message = "Invalid mobile phone format. Must be exactly 8 digits.")
    private String supplierMobilePhone;


//    @NotBlank(message = "Supplier Network Name cannot blank")
    private String supplierNetwork;



    // Optional fields
    private String supplierFirstName;

    private String supplierFatherName;

    private String supplierPrefixFamily;

    @Size(min = 1, message = "Land phone cannot be empty", groups = NotEmptyGroup.class)
    private String supplierLandPhone;

    public interface NotEmptyGroup {}


}
