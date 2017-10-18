package com.rentbikes.controller.basicInfo.orgInfo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rentbikes.model.MSPhase;
import com.rentbikes.model.Page;
import com.rentbikes.service.basicInfo.MSPhaseService;
import com.rentbikes.service.basicInfo.SYPermissionService;

/*
 * 此类用于接收客户端权限相关的请求，并给予响应
 */
@Controller
public class MSPhaseController {
	
	@Autowired
	private MSPhaseService phService;
	@Autowired
	private SYPermissionService permissionService;
	
	//查看所有权限（分页）
	@RequestMapping("basicInfo/orgInfo/showPhases.do")
	public String showPage(HttpServletRequest request, Page page){
		if(page == null) page = new Page();
		List<MSPhase> phaseList = phService.getPage(page);
		if(phaseList != null){
			request.setAttribute("page", page);
			request.setAttribute("phaseList", phaseList);
			return "/basicInfo/orgInfo/phaseMain.jsp";
		}
		return "redirect:/error.jsp";
	}
	
	//添加权限
	@RequestMapping("basicInfo/orgInfo/addPhase.do")
	public String addOne(HttpServletRequest request, MSPhase phase){
		if(phService.addPhase(phase) == 1) request.setAttribute("prompt", "添加成功");
		else request.setAttribute("prompt", "添加失败");
		return "/basicInfo/orgInfo/showPhases.do";
	}
	
	//删除权限
	@RequestMapping("basicInfo/orgInfo/deletePhase.do")
	public String deleteOne(HttpServletRequest request, int phaseId){
		if(phService.deletePhase(phaseId) == 1) request.setAttribute("prompt", "删除成功");
		else request.setAttribute("prompt", "删除失败");
		return "/basicInfo/orgInfo/showPhases.do";
	}
	
	//修改权限
	@RequestMapping("basicInfo/orgInfo/updatePhase.do")
	public String updateOne(HttpServletRequest request, MSPhase phase){
		if(phService.updatePhase(phase) == 1) request.setAttribute("prompt", "修改成功");
		else request.setAttribute("prompt", "修改失败");
		return "/basicInfo/orgInfo/showPhases.do";
	}

	//模糊查询权限(AJAX)
	@RequestMapping("basicInfo/orgInfo/searchPhase.do")
	public String searchPage(HttpServletRequest request, MSPhase phase, Page page){
		if(page == null) page = new Page();
		List<MSPhase> phaseList = phService.getSearch(page, phase);
		if(phaseList != null){
			request.setAttribute("page", page);
			request.setAttribute("phaseList", phaseList);
			return "/basicInfo/orgInfo/phaseSearchAjax.jsp";
		}
		return "/error.jsp";
	}
	
	//查询权限是否授予使用
	@RequestMapping("basicInfo/orgInfo/isUsed.do")
	@ResponseBody
	public String isUsed(int phaseId){
		if(permissionService.isExist(phaseId)) return "true";
		return "false";
	}
	
	
	public MSPhaseService getPhService() {
		return phService;
	}

	public void setPhService(MSPhaseService phService) {
		this.phService = phService;
	}

	public SYPermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(SYPermissionService permissionService) {
		this.permissionService = permissionService;
	}
}
