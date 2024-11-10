package com.va.cms.srv.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "company_profile")
public class CompanyProfileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "main_color_pallete is required")
    @Column(name = "main_color_pallete")
    private String mainColorPallete;

    @NotBlank(message = "second_color_pallete is required")
    @Column(name = "second_color_pallete")
    private String secondColorPallete;

    @NotBlank(message = "third_color_pallete is required")
    @Column(name = "third_color_pallete")
    private String thirdColorPallete;

    @NotBlank(message = "company_logo is required")
    @Column(name = "company_logo")
    private String companyLogo;

    @Column(name = "company_fav_icon")
    private String companyFavIcon;

    @Column(name = "info_color")
    private String infoColor;

    @Column(name = "error_color")
    private String errorColor;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @NotBlank(message = "is_active is required")
    @Column(name = "is_active")
    private Boolean isActive;

    @NotBlank(message = "company_id is required")
    @Column(name = "company_id")
    private Integer companyId;

    @Override
    public String toString() {
        return "CompanyProfileModel{" +
                "id=" + id +
                ", mainColorPallete='" + mainColorPallete + '\'' +
                ", secondColorPallete='" + secondColorPallete + '\'' +
                ", thirdColorPallete='" + thirdColorPallete + '\'' +
                ", companyLogo='" + companyLogo + '\'' +
                ", companyFavIcon='" + companyFavIcon + '\'' +
                ", infoColor='" + infoColor + '\'' +
                ", errorColor='" + errorColor + '\'' +
                ", createdAt=" + createdAt +
                ", createdBy='" + createdBy + '\'' +
                ", updatedAt=" + updatedAt +
                ", updatedBy='" + updatedBy + '\'' +
                ", isActive=" + isActive +
                ", companyId=" + companyId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMainColorPallete() {
        return mainColorPallete;
    }

    public void setMainColorPallete(String mainColorPallete) {
        this.mainColorPallete = mainColorPallete;
    }

    public String getSecondColorPallete() {
        return secondColorPallete;
    }

    public void setSecondColorPallete(String secondColorPallete) {
        this.secondColorPallete = secondColorPallete;
    }

    public String getThirdColorPallete() {
        return thirdColorPallete;
    }

    public void setThirdColorPallete(String thirdColorPallete) {
        this.thirdColorPallete = thirdColorPallete;
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

    public void setActive(Boolean is_active) {
        isActive = is_active;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
