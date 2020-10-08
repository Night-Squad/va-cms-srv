package com.bjbs.auth.dtos;

import com.bjbs.auth.models.RoleMenu;

public class RoleMenuDTO {
	private long roleMenuId;
	private long menuId;
	private String menuName;
	private String menuDescription;
	private String menuCategory;
	private long roleId;
	private String roleName;
	private String roleDescription;
	

	public long getRoleMenuId() {
		return roleMenuId;
	}
	public void setRoleMenuId(long roleMenuId) {
		this.roleMenuId = roleMenuId;
	}
	public long getMenuId() {
		return menuId;
	}
	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuDescription() {
		return menuDescription;
	}
	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}
	public String getMenuCategory() {
		return menuCategory;
	}
	public void setMenuCategory(String menuCategory) {
		this.menuCategory = menuCategory;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	
}
