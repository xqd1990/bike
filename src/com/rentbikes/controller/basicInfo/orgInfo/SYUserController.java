package com.rentbikes.controller.basicInfo.orgInfo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rentbikes.model.Page;
import com.rentbikes.model.SYUser;
import com.rentbikes.service.basicInfo.SYRoleService;
import com.rentbikes.service.basicInfo.SYUserService;

/*
 * 此类用于接收客户端的用户相关的请求，并给予响应
 */
@Controller
public class SYUserController {
	
	@Autowired
	private SYUserService userService;
	@Autowired
	private SYRoleService roleService;

	//显示用户列表
	@RequestMapping("basicInfo/orgInfo/showUsers.do")
	public String listUsers(HttpServletRequest request, Page page){
		if(page == null) page = new Page();
		List<SYUser> userList = userService.listUsers(page);
		if(userList != null){
			request.setAttribute("allRoles", roleService.getAll());
			request.setAttribute("page", page);
			request.setAttribute("userList",userList);
			return "/basicInfo/orgInfo/userMain.jsp";
		}
		return "";
	}
	
	//添加用户
	@RequestMapping("basicInfo/orgInfo/addUser.do")
	public String addUser(HttpServletRequest request, SYUser user){
		if(userService.addUser(user) == 1)	request.setAttribute("prompt", "添加成功");
		else request.setAttribute("prompt","添加失败");
		return "/basicInfo/orgInfo/showUsers.do";
	}
	
	//注销用户
	@RequestMapping("basicInfo/orgInfo/removeUser.do")
	public String removeUser(HttpServletRequest request, int user_id){
		if(userService.removeUser(user_id) == 1)	request.setAttribute("prompt", "注销成功");
		else  request.setAttribute("prompt","注销失败");
		return "/basicInfo/orgInfo/showUsers.do";
	}
	
	//修改用户
	@RequestMapping("basicInfo/orgInfo/updateUser.do")
	public String updateUser(HttpServletRequest request, SYUser user){
		if(userService.updateUser(user) == 1) request.setAttribute("prompt", "修改成功");
		else request.setAttribute("prompt", "修改失败");
		return "/basicInfo/orgInfo/showUsers.do";
	}
	
	//校验账号是否重复(AJAX)
	@RequestMapping("basicInfo/orgInfo/checkAccount.do")
	@ResponseBody
	public String checkAccount(String login_name){
		if(userService.checkAccount(login_name))
			return "true";
		return "false";
	}
	
	//联合查询
	@RequestMapping("basicInfo/orgInfo/searchUser.do")
	public String getSearch(HttpServletRequest request, SYUser user, String role_name, Page page){
		List<SYUser> userList = userService.listSearch(user, role_name, page);
		if(userList != null){
			request.setAttribute("allRoles", roleService.getAll());
			request.setAttribute("page", page);
			request.setAttribute("userList", userList);
			return "/basicInfo/orgInfo/userSearchAjax.jsp";
		}
		return "/error.jsp";
	}
	
	public SYUserService getUserService() {
		return userService;
	}

	public void setUserService(SYUserService userService) {
		this.userService = userService;
	}
	
}
