package com.rentbikes.controller.basicInfo.businessInfo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.rentbikes.model.Page;
import com.rentbikes.model.Vender;
import com.rentbikes.service.basicInfo.IVenderService;

@Controller
public class Vendercontroller {

	@Autowired
	private IVenderService venderService;
	
	
	public IVenderService getVenderService() {
		return venderService;
	}
	public void setVenderService(IVenderService venderService) {
		this.venderService = venderService;
	}
	
	
	// 查询出所有供应商的信息,分页
	@RequestMapping("basicInfo/businessInfo/getAllVender.do")
	public String getPage(HttpServletRequest request, Page page) {
		if (page == null) {
			page = new Page();
		}
		List<Vender> list = new ArrayList<Vender>();
		try {
			list = venderService.getPage(page);
		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("error", "查询供应商失败，请检查");
		}
		if (list != null) {
			request.setAttribute("page", page);
			request.setAttribute("venderList", list);
			return "main.jsp";
		}
		return "redirect:/error.jsp";
	}

	// 执行增加供应商操作
	@RequestMapping("basicInfo/businessInfo/venderAdd.do")
	public String registVender(Vender vender,HttpServletRequest request) {
		try {
			venderService.registVender(vender);
			request.setAttribute("error", "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "增加供应商失败，请检查");
		}
		return "/basicInfo/businessInfo/getAllVender.do";
	}
	

	// 执行修改供应商操作之前的查询
	@RequestMapping(value = "basicInfo/businessInfo/getVender.do", produces = "text/json;charset=utf-8")
	@ResponseBody
	public String getVender(int id,HttpServletRequest request) {
		Vender vender = new Vender();
		try {
			vender = venderService.getVender(id);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "查询供应商失败，请检查");
		}
		String json = JSONObject.toJSONString(vender);
		return json;
	}

	// 执行修改供应商操作
	@RequestMapping("basicInfo/businessInfo/venderModify.do")
	public String modifyVender(Vender vender,HttpServletRequest request) {
		try {
			venderService.modifyVender(vender);
			request.setAttribute("error", "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "修改供应商失败，请检查");
		}
		return "/basicInfo/businessInfo/getAllVender.do";
	}
	// 执行注销供应商
	@RequestMapping("basicInfo/businessInfo/logoutVender.do")
	public String logoutVender(int id,HttpServletRequest request) {
		try {
			venderService.logoutVender(id);
			request.setAttribute("error", "注销供应商成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "注销供应商失败，请检查");
			
		}
		return "/basicInfo/businessInfo/getAllVender.do";
	}

}
