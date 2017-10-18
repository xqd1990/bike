package com.rentbikes.model;

import java.util.Arrays;
import java.util.List;

/*
 * 此类用于封装数据库中SYROLE角色表的数据
 */
public class SYRole {
	
	private int role_id;	//角色标识
	private String role_name;	//角色名称
	private String role_describe;	//角色描述
	private int[] phaseIds;		//存放浏览器提交的phaseId
	private List<Integer> idList;	//存放角色拥有的权限
	
	public SYRole(){ }
	
	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getRole_describe() {
		return role_describe;
	}

	public void setRole_describe(String role_describe) {
		this.role_describe = role_describe;
	}

	public int[] getPhaseIds() {
		return phaseIds;
	}

	public void setPhaseIds(int[] phaseIds) {
		this.phaseIds = phaseIds;
	}

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

	@Override
	public String toString() {
		return "SYRole [role_id=" + role_id + ", role_name=" + role_name
				+ ", role_describe=" + role_describe + ", phaseIds="
				+ Arrays.toString(phaseIds) + ", idList=" + idList + "]";
	}

	
}
