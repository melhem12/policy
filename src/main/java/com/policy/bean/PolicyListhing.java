package com.policy.bean;

import io.swagger.annotations.ApiModelProperty;

public class PolicyListhing {
    @ApiModelProperty(position = 1)

    private String insuranceId ;
    @ApiModelProperty(position = 2)

    private String profileTypeId;
    @ApiModelProperty(position = 3)
    private String profileId;

    @ApiModelProperty(position = 4)
    private String profileFirstName ;
    @ApiModelProperty(position = 5)
    private String profileFatherName ;
    @ApiModelProperty(position = 6)
    private String profileLastName;
    @ApiModelProperty(position = 7)
    private Boolean blacklisted;
    @ApiModelProperty(position = 8)
    private String note;
    @ApiModelProperty(position = 9)
    private String reason;
    @ApiModelProperty(position = 10)
    private String setOn;
    @ApiModelProperty(position = 11)
    private String setBy;



    public String getProfileTypeId() {
        return profileTypeId;
    }

    public void setProfileTypeId(String profileTypeId) {
        this.profileTypeId = profileTypeId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public Boolean getBlacklisted() {
        return blacklisted;
    }

    public void setBlacklisted(Boolean blacklisted) {
        this.blacklisted = blacklisted;
    }

    public String getSetOn() {
        return setOn;
    }

    public void setSetOn(String setOn) {
        this.setOn = setOn;
    }

    public String getSetBy() {
        return setBy;
    }

    public void setSetBy(String setBy) {
        this.setBy = setBy;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getProfileFirstName() {
        return profileFirstName;
    }

    public void setProfileFirstName(String profileFirstName) {
        this.profileFirstName = profileFirstName;
    }

    public String getProfileFatherName() {
        return profileFatherName;
    }

    public void setProfileFatherName(String profileFatherName) {
        this.profileFatherName = profileFatherName;
    }

    public String getProfileLastName() {
        return profileLastName;
    }

    public void setProfileLastName(String profileLastName) {
        this.profileLastName = profileLastName;
    }
}
