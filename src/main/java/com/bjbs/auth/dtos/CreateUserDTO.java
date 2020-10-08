package com.bjbs.auth.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class CreateUserDTO {

    @NotEmpty
    private String userName;

    @NotEmpty
    private String userRealName;

    @NotEmpty
    private String userPhoneNumber;

    @NotEmpty
    private short userGender;
    
    @NotEmpty
    private long branchId;
    
    @NotEmpty
    private long roleId;

    @Email
    @NotEmpty
    private String userEmail;

    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }
    
    public short getUserGender() {
        return userGender;
    }

    public void setUserGender(short userGender) {
        this.userGender = userGender;
    }
    
    public long getBranchId() {
        return branchId;
    }

    public void setBranchId(long branchId) {
        this.branchId = branchId;
    }
    
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}