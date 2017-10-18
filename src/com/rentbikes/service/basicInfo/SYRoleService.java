package com.rentbikes.service.basicInfo;

import java.util.List;

import com.rentbikes.model.Page;
import com.rentbikes.model.SYRole;

/*
 * 此类是抽象业务层，用于被具体业务层实现
 */
public interface SYRoleService {
	
	List<SYRole> getPage(Page page);
	boolean addRole(SYRole role);
	boolean updateRole(SYRole role);
	String deleteRole(int role_id);
	List<SYRole> getSearch(Page page, String role_describe);
	
	List<SYRole> getAll();
}
