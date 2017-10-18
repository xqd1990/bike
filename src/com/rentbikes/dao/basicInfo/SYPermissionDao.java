package com.rentbikes.dao.basicInfo;

import java.util.List;

import com.rentbikes.model.SYPermission;

/*
 * 此类封装了数据库SYPERMISSION表格数据增删改查的操作，用于Mapper文件的映射
 */
public interface SYPermissionDao {

	int addPermission(SYPermission permission);
	int deletePermission(int roleId);
	List<SYPermission> listPermission(int roleId); 	//查询某个角色的权限
	int isExist(int phaseId);	//查询权限是否被授予
}
