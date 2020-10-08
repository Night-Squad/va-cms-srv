package com.bjbs.auth.olo;

import com.io.iona.core.data.annotations.OptionListKey;

public class UsersOLO {
	@OptionListKey
	private long userId;
	private String userName;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
