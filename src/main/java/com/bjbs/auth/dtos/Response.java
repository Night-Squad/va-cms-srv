package com.bjbs.auth.dtos;

import lombok.Data;

@Data
public class Response {
	
	private String RC;
	private String message;
	
	public Response(String RC, String message) {
		this.RC = RC;
		this.message = message;
	}

}
