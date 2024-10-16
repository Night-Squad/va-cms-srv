package com.server.auth.dtos;

import lombok.Data;

@Data
public class UpdateUserDTO {
	
	private String userName;
	private String userRealName;
	private String userPhoneNumber;
    private short userGender;
    private long branchId;
    private long roleId;
    private String userEmail;
    private long userBranchId;
    private long userRoleId;

}
