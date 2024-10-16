package com.server.auth.dtos;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


import jakarta.persistence.Column;

/**
 * Created By Aristo Pacitra
 */

public class CustomUser extends User {
	private int id;

	private String name;

	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, int id, String name, String password1) {
		super(username, password, authorities);
		this.id = id;
		this.name = name;
		this.password = password1;
	}

	public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, int id, String name, String password1) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.id = id;
		this.name = name;
		this.password = password1;
	}
}