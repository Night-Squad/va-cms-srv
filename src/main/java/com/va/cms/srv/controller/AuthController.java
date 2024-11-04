package com.va.cms.srv.controller;

import com.va.cms.srv.config.SignatureConvertor;
import com.va.cms.srv.controller.dto.*;
import com.va.cms.srv.domain.LoginAttempDomain;
import com.va.cms.srv.helper.JwtHelper;
import com.va.cms.srv.service.LoginService;
import com.va.cms.srv.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.PublicKey;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final LoginService loginService;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, LoginService loginService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.loginService = loginService;
    }

    @Operation(summary = "Get recent login attempts")
    @ApiResponse(responseCode = "201")
    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class)))
    @GetMapping("/loginAttempts")
    public ResponseEntity<List<LoginAttemptResponseDto>> loginAttempts(@RequestHeader("Authorization") String token , @RequestHeader("publicKey") String publicKeyPEM) {
        try {
            System.out.println("loginAttempts..");
            System.out.println(token);

            // handling public key
            SignatureConvertor signatureConvertor = new SignatureConvertor();
            PublicKey publicKey = signatureConvertor.getPublicKeyFromString(publicKeyPEM);

            String email = JwtHelper.extractUsername(token.replace("Bearer ", ""), publicKey);
            List<LoginAttempDomain> loginAttempts = loginService.findRecentLoginAttempts(email);
            return ResponseEntity.ok(convertToDTOs(loginAttempts));

        } catch (BadCredentialsException e) {
            System.out.println("Message : "+e.getMessage());
            throw e;
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
