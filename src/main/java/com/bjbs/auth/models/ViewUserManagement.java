package com.bjbs.auth.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "view_user_management", schema = "public")
@Data
@NoArgsConstructor
public class ViewUserManagement {
	
	@Id
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_real_name")
	private String userRealName;
	
	@Column(name = "user_email")
	private String userEmail;
	
	@Column(name = "user_gender")
	private short userGender;
	
	@Column(name = "user_phone_number")
	private String userPhoneNumber;
	
	@Column(name = "is_active")
    private short isActive;
	
	@Column(name = "created_by")
    private String createdBy;
	
	@Column(name = "created_date", columnDefinition = "DATE")
    private Date createdDate;
	
	@Column(name = "updated_by")
    private String updatedBy;
	
	@Column(name = "updated_date", columnDefinition = "DATE")
    private Date updatedDate;
	
	@Column(name = "user_branch_id")
	private long userBranchId;
	
	@Column(name = "branch_id")
    private long branchId;
	
	@Column(name = "branch_code")
    private String branchCode;
	
	@Column(name = "branch_name")
    private String branchName;
	
	@Column(name = "branch_address")
    private String branchAddress;
	
	@Column(name = "user_branch_code")
    private String userBranchCode;
	
	@Column(name = "user_role_id")
    private long userRoleId;
	
	@Column(name = "role_id")
    private long roleId;
	
	@Column(name = "role_name")
    private String roleName;

	@Column(name = "city_code")
	private String cityCode;

	@Column(name = "city_name")
	private String cityName;

	@Column(name = "province_code")
	private String provinceCode;

	@Column(name = "province_name")
	private String provinceName;

}
