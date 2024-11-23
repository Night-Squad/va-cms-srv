package com.va.corporate.srv.controller;

import com.va.corporate.srv.dto.ApiErrorResponseDto;
import com.va.corporate.srv.controller.response.ResponseMessage;
import com.va.corporate.srv.domain.LoginAttempDomain;
import com.va.corporate.srv.service.LoginService;
import com.va.corporate.srv.dto.LoginAttemptResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    LoginService loginService;

    @Operation(summary = "Get recent login attempts")
    @ApiResponse(responseCode = "201")
    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @GetMapping("/loginAttempts")
    public Map<String, Object> loginAttempts(@RequestHeader("Authorization") String token) {
        try {
            System.out.println("loginAttempts..");

//            List<LoginAttempDomain> loginAttempts = loginService.findRecentLoginAttempts("hello");
//            String messageResponse = "success to load the data..";
//
//            if(loginAttempts.isEmpty()) {
//                messageResponse = "data is empty";
//            }

//            return new ResponseMessage().success("00", "success", 200, messageResponse, loginAttempts);
            return new ResponseMessage().success("00", "success", 200, "success", "");


        } catch (Exception e) {
            System.out.println("Exception e");
            throw new RuntimeException(e);
        }
    }

    private List<LoginAttemptResponseDto> convertToDTOs(List<LoginAttempDomain> loginAttempts) {
        return loginAttempts.stream()
                .map(LoginAttemptResponseDto::convertDto)
                .collect(Collectors.toList());
    }



}
