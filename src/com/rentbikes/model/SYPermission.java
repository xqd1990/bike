package com.rentbikes.model;

/*
 * 此类对象用于存放数据库中SYPERMISSION表的数据
 */
public class SYPermission {
	
	private int permissionId;	//授权编号
	private int roleId;		//角色编号
	private int phaseId;	//权限编号
	
	public SYPermission(){}

	public int getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(int phaseId) {
		this.phaseId = phaseId;
	}
	
}
