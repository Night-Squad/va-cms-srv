package com.va.corporate.srv.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record SignupRequestDto(
        @Schema(description = "name", example = "Rizki Sadewa")
        @NotBlank(message = "Name cannot be blank")
        String name,

        @Schema(description = "email", example = "mina@gmail.com")
        @Email(message = "Invalid email format")
        @NotBlank(message = "Email cannot be blank")
        String email,

        @Schema(description = "password", example = "123456")
        @NotBlank(message = "Password cannot be blank")
        @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
        String password
) {
}
