package com.va.corporate.srv.controller;

import com.va.corporate.srv.dto.ApiErrorResponseDto;
import com.va.corporate.srv.dto.PaginatedResponseDto;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
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
            System.out.println("Timestamp : " + LocalDateTime.now());

            try {
                companyProfileService.addCompanyProfile(companyProfile);
            } catch (Exception e) {
                return new ResponseMessage().success("99", "Error", 200, e.getMessage(), null);
            }

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
    public Map<String, Object> findAll(@RequestParam(defaultValue =  "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam Map<String, String> allParams) {
        try{
            System.out.println("Company Profile : find-all");
            System.out.println("params: " + allParams.toString());
            System.out.println("Timestamp : " + LocalDateTime.now());

            // Extract search parameters
            Map<String, String> searchParams = new HashMap<>(allParams);

            PaginatedResponseDto<CompanyProfileModel> companyProfiles = companyProfileService.getPaginatedCompanyProfile(page, size, searchParams);

            if(companyProfiles.getRows().isEmpty()) {
                return new ResponseMessage().success("00", "success", 200, "success to obtain data, content is empty", companyProfiles);
            }

            return new ResponseMessage().success("00", "success", 200, "data fetch successfully", companyProfiles);
        } catch (Exception e) {
            System.out.println("Exception e : "+e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "Edit data of company_profiles")
    @ApiResponse(responseCode = "201")
    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @PutMapping("/edit")
    public Map<String, Object> editData(@Valid @RequestBody CompanyProfileModel companyProfile) {
        try{
            System.out.println("Company Profile : edit");
            System.out.println("Request body: "+companyProfile.toString());
            System.out.println("Timestamp : " + LocalDateTime.now());

            companyProfileService.updateCompanyProfile(companyProfile);

            return new ResponseMessage().success("00", "success", 200, "Success edited.", companyProfile);
        } catch (Exception e) {
            System.out.println("Exception e : "+e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "Delete data of company_profiles")
    @ApiResponse(responseCode = "201")
    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @DeleteMapping("/delete")
    @ResponseBody
    public Map<String, Object> deleteData(@Valid @RequestBody CompanyProfileModel companyProfile) {
        try{
            System.out.println("Company Profile : delete");
            System.out.println("Request body: "+companyProfile.toString());
            System.out.println("Timestamp : " + LocalDateTime.now());

            companyProfileService.deleteCompanyProfile(companyProfile);

            return new ResponseMessage().success("00", "success", 200, "Success deleted.", companyProfile);
        } catch (Exception e) {
            System.out.println("Exception e : "+e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "Upload company logo")
    @ApiResponse(responseCode = "201")
    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @PostMapping("/upload-logo")
    public Map<String, Object> uploadLogo(@RequestParam("file") MultipartFile file, @RequestParam("id") String id) {
        try {
            System.out.println("Company Profile : upload logo");
            System.out.println("Request: "+file.getOriginalFilename()+" id : "+id);
            System.out.println("Timestamp : " + LocalDateTime.now());
            companyProfileService.uploadLogo(file, id);
            return new ResponseMessage().success("00", "success", 200, "Success uploaded.", null);
        } catch (Exception e) {
            System.out.println("Exception e : "+e.getMessage());
            return new ResponseMessage().success("99", "error", 500, "Failed to upload image.", null);
        }
    }
}
