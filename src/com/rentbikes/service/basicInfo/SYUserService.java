package com.rentbikes.service.basicInfo;

import java.util.List;

import com.rentbikes.model.Page;
import com.rentbikes.model.SYUser;

/*
 * 此类为抽象业务层，定义了一些方法用于被子类实现
 */
public interface SYUserService {
	
	List<SYUser> listUsers(Page page); 
	int addUser(SYUser user);
	int removeUser(int user_id);
	int updateUser(SYUser user);
	
	boolean checkAccount(String login_name);
	List<SYUser> listSearch(SYUser user, String role_name, Page page);
	
	List<SYUser> listAdminAndBuyer();
}
