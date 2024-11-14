package com.va.corporate.srv.controller;

import com.va.corporate.srv.controller.dto.ApiErrorResponseDto;
import com.va.corporate.srv.controller.dto.PaginatedResponseDto;
import com.va.corporate.srv.controller.response.ResponseMessage;
import com.va.corporate.srv.models.vacms.CompanyProfileModel;
import com.va.corporate.srv.service.CompanyProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(path="/api/v2.4/company-profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanyProfileController {

    @Autowired
    CompanyProfileService companyProfileService;

    @Operation(summary = "Add data of company_profiles")
    @ApiResponse(responseCode = "201")
    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @PostMapping("/add")
    public Map<String, Object> addData(@Valid @RequestBody CompanyProfileModel companyProfile) {
        try{
            System.out.println("Company Profile : add");
            System.out.println("Request body: "+companyProfile.toString());

            companyProfileService.addCompanyProfile(companyProfile);

            return new ResponseMessage().success("00", "success", 200, "Success added.", companyProfile);
        } catch (Exception e) {
            System.out.println("Exception e : "+e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Operation(summary = "Geta all data of company_profiles")
    @ApiResponse(responseCode = "201")
    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @GetMapping("/find-all")
    public Map<String, Object> findAll(@RequestParam(defaultValue =  "1") int page, @RequestParam(defaultValue = "10") int size) {
        try{
            System.out.println("Company Profile : find-all");

            PaginatedResponseDto<CompanyProfileModel> companyProfiles = companyProfileService.getPaginatedCompanyProfile(page, size);

            if(companyProfiles.getRows().isEmpty()) {
                return new ResponseMessage().success("00", "success", 200, "success to obtain data, content is empty", companyProfiles);
            }

            return new ResponseMessage().success("00", "success", 200, "data fetch successfully", companyProfiles);
        } catch (Exception e) {
            System.out.println("Exception e : "+e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
