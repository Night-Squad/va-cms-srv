package com.bjbs.auth.models;
// Generated Sep 9, 2019 4:24:42 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * RoleMenu generated by hbm2java
 */
@Entity
@Table(name = "role_menu", schema = "public")
public class RoleMenu implements java.io.Serializable {

	private long roleMenuId;
	private Menu menu;
	private Role role;
	private Date availableDate;

	public RoleMenu() {
	}

	public RoleMenu(long roleMenuId) {
		this.roleMenuId = roleMenuId;
	}

	public RoleMenu(long roleMenuId, Menu menu, Role role, Date availableDate) {
		this.roleMenuId = roleMenuId;
		this.menu = menu;
		this.role = role;
		this.availableDate = availableDate;
	}

	@Id

	@Column(name = "role_menu_id", unique = true, nullable = false)
	public long getRoleMenuId() {
		return this.roleMenuId;
	}

	public void setRoleMenuId(long roleMenuId) {
		this.roleMenuId = roleMenuId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id")
	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "available_date", length = 13)
	public Date getAvailableDate() {
		return this.availableDate;
	}

	public void setAvailableDate(Date availableDate) {
		this.availableDate = availableDate;
	}

}
