package com.bjbs.auth.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class ViewUserManagementDTO {
	
	private long userId;
	private String userName;
	private String userRealName;
	private String userEmail;
	private short userGender;
	private String userPhoneNumber;
	private short isActive;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private long userBranchId;
	private long branchId;
	private String branchCode;
	private String branchName;
	private String branchAddress;
	private String userBranchCode;
	private long userRoleId;
	private long roleId;
	private String roleName;
	private String cityCode;
	private String cityName;
	private String provinceCode;
	private String provinceName;

}
