package com.va.cms.srv.domain;

import java.time.LocalDateTime;

public record CompanyProfileDomain(
        String mainColorPallete,
        String secondColorPallete,
        String thirdColorPallete,
        String companyLogo,
        String companyFavIcon,
        String infoColor,
        String errorColor,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime updatedAt,
        String updatedBy,
        boolean isActive,
        Integer companyId
) {
}
