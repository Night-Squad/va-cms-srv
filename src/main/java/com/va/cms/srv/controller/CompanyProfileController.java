package com.va.cms.srv.controller;

import com.va.cms.srv.controller.dto.ApiErrorResponseDto;
import com.va.cms.srv.controller.response.ResponseMessage;
import com.va.cms.srv.domain.CompanyProfileDomain;
import com.va.cms.srv.service.CompanyProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/api/v2.4/company-profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanyProfileController {

    @Autowired
    CompanyProfileService companyProfileService;

    @Operation(summary = "Get recent login attempts")
    @ApiResponse(responseCode = "201")
    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @GetMapping("/find-all")
    public Map<String, Object> findAll() {
        try{
            System.out.println("Company Profile : find-all");

            List<CompanyProfileDomain> companyProfiles = companyProfileService.findAllCompanyProfile();

            if(!companyProfiles.isEmpty()) {
                return new ResponseMessage().success("00", 200, "success to obtain data.", companyProfiles);
            }

            return new ResponseMessage().success("99", 500, "data failed to fetch.", companyProfiles);
        } catch (Exception e) {
            System.out.println("Exception e : "+e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
