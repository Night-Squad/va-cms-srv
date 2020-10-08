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
    private final Map<String, List<RoleMenuDTO>> userRoleMenu;

    public CustomUser(String username, String password, String userRealName,
                      Collection<? extends GrantedAuthority> authorities, long userId, int applicationId, Map<String, List<RoleMenuDTO>> userRoleMenu) {
        super(username, password, authorities);
        this.userId = userId;
        this.applicationId = applicationId;
        this.userRealName = userRealName;
        this.userRoleMenu = userRoleMenu;
  
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
	public Map<String, List<RoleMenuDTO>> getUserRoleMenu() {
		return userRoleMenu;
	}
	
}