package com.rentbikes.serviceImp.basicInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rentbikes.dao.basicInfo.SYPermissionDao;
import com.rentbikes.dao.basicInfo.SYRoleDao;
import com.rentbikes.dao.basicInfo.SYUserDao;
import com.rentbikes.model.Page;
import com.rentbikes.model.SYPermission;
import com.rentbikes.model.SYRole;
import com.rentbikes.model.SYUser;
import com.rentbikes.service.basicInfo.SYRoleService;

/*
 * 业务层，处理具体的角色相关的请求
 */
@Service
public class SYRoleServiceImp implements SYRoleService {
	
	@Autowired
	private SYRoleDao roleDao;
	@Autowired
	private SYPermissionDao permissionDao;
	@Autowired
	private SYUserDao userDao;

	//查看所有角色
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<SYRole> getPage(Page page){
		int size = roleDao.getSize();
		Map<String, Object> map = new HashMap<String, Object>();
		init(page,size,map);
		return roleDao.getPage(map);
	}
	
	//添加一个角色，在授权表添加相应的记录
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean addRole(SYRole role){
		roleDao.addRole(role);
		SYPermission permission = new SYPermission();
		permission.setRoleId(role.getRole_id());
		if(role.getPhaseIds() != null){
			for(int i = 0;i < role.getPhaseIds().length;i++){
				permission.setPhaseId(role.getPhaseIds()[i]);
				if(permissionDao.addPermission(permission) != 1) return false;
			}
		}
		return true;
	}
	
	//修改一个角色，同时修改权限表的记录
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateRole(SYRole role){
		if(roleDao.updateRole(role) == 0) return false;
		permissionDao.deletePermission(role.getRole_id());
		SYPermission permission = new SYPermission();
		permission.setRoleId(role.getRole_id());
		for(int phaseId : role.getPhaseIds()){
			permission.setPhaseId(phaseId);
			if(permissionDao.addPermission(permission) == 0) return false;
		}
		return true;
	}
	
	//删除一个角色，删除权限表中相应的记录
	@Transactional(propagation=Propagation.REQUIRED)
	public String deleteRole(int role_id){
		if(userDao.haveRole(role_id)<1){
			int i1 = permissionDao.deletePermission(role_id);
			int i2 = roleDao.deleteRole(role_id);
			if(i1 >= 0 && i2 == 1) return "删除成功";
			return "删除失败";
		}
		else
			return "角色有用户使用，删除失败";
		
	}
	
	//模糊查询
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<SYRole> getSearch(Page page, String role_name){
		int size = roleDao.getSearchSize(role_name);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("role_name", role_name);
		init(page,size,map);
		return roleDao.getSearch(map);
	}
	
	//获取所有的角色
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<SYRole> getAll(){
		return roleDao.getAll();
	}
	
	//初始化page和map
	private void init(Page page, int size, Map<String, Object> map){
		page.setSize(size);
		if(size % page.getCount() == 0) page.setTotal(size / page.getCount());
		else page.setTotal(size / page.getCount() + 1);
		map.put("start", page.getCurrent() * page.getCount() + 1);
		map.put("end", page.getCurrent() * page.getCount() + page.getCount());
	}
	
	
	public SYRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(SYRoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	public SYPermissionDao getPermissionDao() {
		return permissionDao;
	}

	public void setPermissionDao(SYPermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}

	public SYUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(SYUserDao userDao) {
		this.userDao = userDao;
	}

}
