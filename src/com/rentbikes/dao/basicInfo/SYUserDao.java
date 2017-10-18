package com.rentbikes.dao.basicInfo;

import java.util.List;
import java.util.Map;

import com.rentbikes.model.SYUser;

/*
 * 封装了对SYUSER表格数据的增删改查方法
 */
public interface SYUserDao {
	
	List<SYUser> listUsers(Map map);	//获取某页的用户信息
	int countAll();		//用户总数
	List<SYUser> listSearch(Map map);	//获取指定条件的用户信息
	int countSearch(Map map);	//指定条件用户数目
	
	int addUser(SYUser user);	//增加用户
	int updateUser(SYUser user);	//修改用户
	int removeUser(int user_id); 	//注销用户
	
	int isExist(String login_name);	//校验账号是否重复
	int haveRole(int role_id);	//校验该职业下是否有用户
	
	List<SYUser> listAdminAndBuyer();	//查询所有的管理员和购入管理员
	SYUser getForLogin(SYUser user);	//根据登录名和密码查询用户
}
