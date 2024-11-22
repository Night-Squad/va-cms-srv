package com.va.corporate.srv.controller;


import com.va.corporate.srv.controller.dto.ApiErrorResponseDto;
import com.va.corporate.srv.controller.dto.PaginatedResponseDto;
import com.va.corporate.srv.controller.response.ResponseMessage;
import com.va.corporate.srv.domain.ReportInstansiDomain;
import com.va.corporate.srv.service.ReportInstansiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path="/api/v2.4/general-report")
public class GeneralReportController {

    @Autowired
    ReportInstansiService reportInstansiService;


    @Operation(summary = "Geta all data of General Report Instansi")
    @ApiResponse(responseCode = "201")
    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @GetMapping("/report-instansi")
    public Map<String, Object> findAllReportInstansi(@RequestParam(defaultValue =  "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam Map<String, String> allParams) {
        try {

            System.out.println("Report Instansi : find-all");
            System.out.println("Search Parameters : " + allParams);

            // Extract search parameters
            Map<String, String> searchParams = new HashMap<>(allParams);

            PaginatedResponseDto<ReportInstansiDomain> reportInansi = reportInstansiService.getPaginationReportInstansi(page, size, searchParams);


            if(reportInansi.getRows() == null) {
                return new ResponseMessage().success("00", "success", 200, "success to obtain data, content is empty", reportInansi);
            }

            return new ResponseMessage().success("00", "success", 200, "data fetch successfully", reportInansi);


        } catch (Exception e) {
            System.out.println("Exception e : "+e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }


}
