package com.va.corporate.srv.controller;


import com.va.corporate.srv.controller.dto.ApiErrorResponseDto;
import com.va.corporate.srv.controller.dto.PaginatedResponseDto;
import com.va.corporate.srv.controller.response.ResponseMessage;
import com.va.corporate.srv.models.vav2.MasterCorporateModel;
import com.va.corporate.srv.service.MasterCorporateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping(path="/api/v2.4/korporat", produces = MediaType.APPLICATION_JSON_VALUE)
public class MasterCorporateController {

    @Autowired
    MasterCorporateService corporateService;

    @Operation(summary = "Geta all data of company_profiles")
    @ApiResponse(responseCode = "201")
    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @GetMapping("/find-all")
    public Map<String, Object> findAll(@RequestParam(defaultValue =  "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "", required = false) String corporateName) {
        try{
            System.out.println("Corporate : find-all");
            System.out.println("Time : " + LocalDateTime.now());

            PaginatedResponseDto<MasterCorporateModel> corporates = corporateService.getPaginatedMasterCorporate(page, size, corporateName);

            if(corporates.getRows() == null) {
                return new ResponseMessage().success("00", "success", 200, "success to obtain data, content is empty", corporates);
            }

            return new ResponseMessage().success("00", "success", 200, "data fetch successfully", corporates);
        } catch (Exception e) {
            System.out.println("Exception e : "+e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "Add data of master_corporation")
    @ApiResponse(responseCode = "201")
    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @PostMapping("/add")
    public Map<String, Object> addData(@Valid @RequestBody MasterCorporateModel masterCorporateModel) {
        try{

            System.out.println("Master Corporation : add");
            System.out.println("Time : " + LocalDateTime.now());
            System.out.println("Request body: "+masterCorporateModel.toString());

            corporateService.addMasterCorporation(masterCorporateModel);

            return new ResponseMessage().success("00", "success", 200, "Success added.", masterCorporateModel);
        } catch (Exception e) {
            System.out.println("Exception e : "+e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
