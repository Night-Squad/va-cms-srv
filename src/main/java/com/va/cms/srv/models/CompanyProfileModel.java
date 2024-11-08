package com.va.cms.srv.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "company_profile")
public class CompanyProfileModel {

    @Id
    private Long id;

    @Column(name = "main_color_pallete")
    private String mainColorPalete;

    @Column(name = "second_color_pallete")
    private String secondColorPallete;

    @Column(name = "third_color_pallete")
    private String thirdColorPalete;

    @Column(name = "company_logo")
    private String companyLogo;

    @Column(name = "company_fav_icon")
    private String companyFavIcon;

    @Column(name = "info_color")
    private String infoColor;

    @Column(name = "error_color")
    private String errorColor;

    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private java.time.LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "company_id")
    private Integer companyId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMainColorPalete() {
        return mainColorPalete;
    }

    public void setMainColorPalete(String mainColorPalete) {
        this.mainColorPalete = mainColorPalete;
    }

    public String getSecondColorPallete() {
        return secondColorPallete;
    }

    public void setSecondColorPallete(String secondColorPallete) {
        this.secondColorPallete = secondColorPallete;
    }

    public String getThirdColorPalete() {
        return thirdColorPalete;
    }

    public void setThirdColorPalete(String thirdColorPalete) {
        this.thirdColorPalete = thirdColorPalete;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanyFavIcon() {
        return companyFavIcon;
    }

    public void setCompanyFavIcon(String companyFavIcon) {
        this.companyFavIcon = companyFavIcon;
    }

    public String getInfoColor() {
        return infoColor;
    }

    public void setInfoColor(String infoColor) {
        this.infoColor = infoColor;
    }

    public String getErrorColor() {
        return errorColor;
    }

    public void setErrorColor(String errorColor) {
        this.errorColor = errorColor;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
