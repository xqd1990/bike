package com.rentbikes.controller.basicInfo.orgInfo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.rentbikes.model.MSPhase;
import com.rentbikes.model.Page;
import com.rentbikes.model.SYPermission;
import com.rentbikes.model.SYRole;
import com.rentbikes.service.basicInfo.MSPhaseService;
import com.rentbikes.service.basicInfo.SYPermissionService;
import com.rentbikes.service.basicInfo.SYRoleService;

/*
 * 处理客户端角色相关的请求，并给予响应
 */
@Controller
public class SYRoleController {
	
	@Autowired
	private SYRoleService roleService;
	@Autowired
	private MSPhaseService phaseService;
	@Autowired
	private SYPermissionService permissionService;
	
	//查看所有角色（分页）
	@RequestMapping("basicInfo/orgInfo/showRoles.do")
	public String showRoles(HttpServletRequest request, Page page){
		if(page == null) page = new Page();
		List<SYRole> roleList = roleService.getPage(page);
		if(roleList != null){
			List<MSPhase> allPhase = phaseService.getAll();
			request.setAttribute("allPhase", allPhase);
			request.setAttribute("page", page);
			request.setAttribute("roleList", roleList);
			return "/basicInfo/orgInfo/roleMain.jsp";
		}
		return "error.jsp";
	}

	//添加一个角色
	@RequestMapping("basicInfo/orgInfo/addRole.do")
	public String addRole(HttpServletRequest request, SYRole role){
		if(roleService.addRole(role)) request.setAttribute("prompt", "添加成功");
		else request.setAttribute("prompt", "添加失败");
		return "/basicInfo/orgInfo/showRoles.do";
	}
	
	//修改一个角色
	@RequestMapping("basicInfo/orgInfo/updateRole.do")
	public String updateRole(HttpServletRequest request, SYRole role) {
		if (roleService.updateRole(role)) request.setAttribute("prompt", "修改成功");
		else request.setAttribute("prompt", "修改失败");
		return "/basicInfo/orgInfo/showRoles.do";
		
	}
	
	//删除一个角色
	@RequestMapping("basicInfo/orgInfo/deleteRole.do")
	public String deleteRole(HttpServletRequest request, int role_id){
		request.setAttribute("prompt", roleService.deleteRole(role_id));
		return "/basicInfo/orgInfo/showRoles.do";
	}
	
	//模糊查询(AJAX)
	@RequestMapping("basicInfo/orgInfo/searchRole.do")
	public String searchRole(HttpServletRequest request,Page page,String role_name){
		List<SYRole> roleList = roleService.getSearch(page, role_name);
		if(roleList != null){
			request.setAttribute("page", page);
			request.setAttribute("roleList", roleList);
			return "/basicInfo/orgInfo/roleSearchAjax.jsp";
		}
		return "";
	}
	
	//查询指定角色拥有的权限(AJAX)
	@RequestMapping(value="basicInfo/orgInfo/listPermission.do"/*,produces="text/json;charset=utf-8"*/)
	@ResponseBody
	public String listPhases(int roleId){
		List<SYPermission> permissionList = permissionService.listPermission(roleId);
		if(permissionList != null){
			System.out.println(JSONObject.toJSONString(permissionList));
			return JSONObject.toJSONString(permissionList);
		}
		return "";
	}
	
	public SYRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(SYRoleService roleService) {
		this.roleService = roleService;
	}

	public MSPhaseService getPhaseService() {
		return phaseService;
	}

	public void setPhaseService(MSPhaseService phaseService) {
		this.phaseService = phaseService;
	}
}
