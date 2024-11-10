package com.va.corporate.srv.domain;

import java.time.LocalDateTime;

public record CompanyProfileDomain(

        Integer id,

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
        Boolean isActive,
        Integer companyId
) {
}
