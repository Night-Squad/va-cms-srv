package com.bjbs.auth.dtos;

public class CreateUserBranchParentDTO {

	private String message;
	
	private CreateUserBranchDTO data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CreateUserBranchDTO getData() {
		return data;
	}

	public void setData(CreateUserBranchDTO data) {
		this.data = data;
	}

	
}
