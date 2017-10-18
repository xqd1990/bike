package com.rentbikes.service.basicInfo;

import java.util.List;

import com.rentbikes.model.SYPermission;

/*
 * 抽象业务层，用于被子类实现
 */
public interface SYPermissionService {
	
	List<SYPermission> listPermission(int roleId);
	boolean isExist(int phaseId);
}
