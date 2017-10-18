package com.rentbikes.serviceImp.basicInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rentbikes.dao.basicInfo.SYRoleDao;
import com.rentbikes.dao.basicInfo.SYUserDao;
import com.rentbikes.model.Page;
import com.rentbikes.model.SYUser;
import com.rentbikes.service.basicInfo.SYUserService;

/*
 * 此类是具体的业务层，处理具体的用户相关的客户端请求
 */
@Service
public class SYUserServiceImp implements SYUserService {
	
	@Autowired
	private SYUserDao userDao;
	@Autowired
	private SYRoleDao roleDao;
	
	//获取所有的管理员和购入管理员
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<SYUser> listAdminAndBuyer(){
		return userDao.listAdminAndBuyer();
	}
	
	//获取指定页面的用户列表
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<SYUser> listUsers(Page page){
		int size = userDao.countAll();
		Map<String,Object> map = new HashMap<String,Object>();
		init(page,size,map);
		return userDao.listUsers(map);
	}
	
	//添加用户
	@Transactional(propagation=Propagation.REQUIRED)
	public int addUser(SYUser user){
		return userDao.addUser(user);
	}
	
	//注销用户
	@Transactional(propagation=Propagation.REQUIRED)
	public int removeUser(int user_id){
		return userDao.removeUser(user_id);
	}
	
	//修改用户
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateUser(SYUser user){
		return userDao.updateUser(user);
	}
	
	//校验账号是否重复(AJAX)
	@Transactional(propagation=Propagation.SUPPORTS)
	public boolean checkAccount(String login_name){
		return userDao.isExist(login_name) >= 1;
	}
	
	//联合查询(AJAX)
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<SYUser> listSearch(SYUser user, String role_name, Page page){
		if(!(role_name == null || role_name == ""))  user.setRole_id(roleDao.getRole_id(role_name));
		System.out.println(user);
		Map<String, Object> map = new HashMap<String, Object>();
		if(user.getLogin_name().length() > 0) map.put("login_name",user.getLogin_name());
		if(user.getUsername().length() > 0) map.put("username", user.getUsername());
		map.put("role_id", user.getRole_id());
		int size = userDao.countSearch(map);
		init(page,size,map);
		List<SYUser> list = userDao.listSearch(map);
		return list;
	}
	
	//初始化page和map
	private void init(Page page, int size, Map<String, Object> map) {
		page.setSize(size);
		if (size % page.getCount() == 0) page.setTotal(size / page.getCount());
		else page.setTotal(size / page.getCount() + 1);
		map.put("start", page.getCurrent() * page.getCount() + 1);
		map.put("end", page.getCurrent() * page.getCount() + page.getCount());
	}
	public SYUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(SYUserDao userDao) {
		this.userDao = userDao;
	}

	public SYRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(SYRoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
}
