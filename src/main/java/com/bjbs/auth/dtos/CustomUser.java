package com.bjbs.auth.dtos;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.io.iona.core.data.annotations.WithModelID;

/**
 * Created By Aristo Pacitra
 */

@WithModelID
public class CustomUser extends User {

    private final long userId;
    private final int applicationId;
    private final String userRealName;

    public CustomUser(String username, String password, String userRealName,
                      Collection<? extends GrantedAuthority> authorities, long userId, int applicationId) {
        super(username, password, authorities);
        this.userId = userId;
        this.applicationId = applicationId;
        this.userRealName = userRealName;
    }

	public long getUserId() {
		return userId;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public String getUserRealName() {
		return userRealName;
	}
	
}