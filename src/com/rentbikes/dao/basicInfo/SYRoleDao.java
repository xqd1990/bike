package com.rentbikes.dao.basicInfo;

import java.util.List;
import java.util.Map;

import com.rentbikes.model.SYRole;

/*
 * 此类封装了一些对SYRole表格（角色表）做增删改查的方法
 */
public interface SYRoleDao {
	
	int addRole(SYRole role);		
	int deleteRole(int role_id);	
	int updateRole(SYRole role);	
	List<SYRole> getPage(Map map);	//查询所有角色（分页）
	int getSize();		//查询角色总数
	List<SYRole> getSearch(Map map);//模糊查询
	int getSearchSize(String role_describe);	//模糊结果总数
	
	List<SYRole> getAll();	//获得所有的角色
	int getRole_id(String role_name);	//获取角色的编号
}
