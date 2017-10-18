package com.rentbikes.serviceImp.basicInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rentbikes.dao.basicInfo.SYPermissionDao;
import com.rentbikes.model.SYPermission;
import com.rentbikes.service.basicInfo.SYPermissionService;

/*
 * 此类是具体的业务层，处理具体的SYPERMISSION表数据的增删改查
 */
@Service
public class SYPermissionServiceImp implements SYPermissionService{
	
	@Autowired
	private SYPermissionDao permissionDao;

	//获取指定角色id的权限
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<SYPermission> listPermission(int roleId){
		return permissionDao.listPermission(roleId);
	}
	
	//判断指定权限是否被使用
	@Transactional(propagation=Propagation.SUPPORTS)
	public boolean isExist(int phaseId){
		return permissionDao.isExist(phaseId) != 0;
	}
	
	public SYPermissionDao getPermissionDao() {
		return permissionDao;
	}

	public void setPermissionDao(SYPermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}
	
}
