package com.bjbs.auth.dtos;

import lombok.Data;

@Data
public class ChangePasswordDTO {
	
	private String oldPassword;
	private String newPassword;
	
}
