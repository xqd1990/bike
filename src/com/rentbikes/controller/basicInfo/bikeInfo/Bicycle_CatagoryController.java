package com.rentbikes.controller.basicInfo.bikeInfo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rentbikes.model.Bicycle_Catagory;
import com.rentbikes.model.Bicycle_Order_Detail;
import com.rentbikes.service.basicInfo.IBicycle_CatagoryService;

@Controller
public class Bicycle_CatagoryController {
	@Autowired
	private IBicycle_CatagoryService bcservice;
	//获得所有车辆类型信息
	@RequestMapping("basicInfo/bikeInfo/showBC.do")
	public String showBCPage(HttpServletRequest req,String message){
		List<Bicycle_Catagory> catagoryList=bcservice.getBCPage();
		if(catagoryList!=null){
			req.setAttribute("catagoryList", catagoryList);
			if(message!=null){
			req.setAttribute("message", message);
			}
			return "BCMain.jsp";
		}
		return "redirect:/error.jsp";
	}

	// 增加车辆类型信息
	@RequestMapping("basicInfo/bikeInfo/addBC.do")
	public String addBCPage(HttpServletRequest req, Bicycle_Catagory bc) {
		try {
			String str = req.getParameter("weight");
			bcservice.addBC(bc);
			req.setAttribute("message", "添加成功");
			return "showBC.do";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("message", "添加失败");
		return "showBC.do";

	}

	// 删除车辆类型信息
	@RequestMapping("basicInfo/bikeInfo/deleteBC.do")
	public String deleteBCPage(HttpServletRequest req, Bicycle_Catagory bc) {
		try {
			bcservice.deleteBC(bc);
			req.setAttribute("message", "删除成功!");
			return "showBC.do";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("message", "删除失败!");
		return "showBC.do";

	}

	// 删除车辆类型信息前查询是否有车辆使用该类型
	@RequestMapping("basicInfo/bikeInfo/queryBC.do")
	public String queryBC(HttpServletRequest req, Bicycle_Catagory bc) {
		Bicycle_Order_Detail bod=bcservice.queryBC(bc);
		if(bod!=null){
			req.setAttribute("message","由于已有车辆使用该类型，删除失败！");
			return "showBC.do";
		}else{
			req.setAttribute("Bicycle_Catagory", bc);
			return "deleteBC.do";
		}
		
	}
	//修改车辆类型
	@RequestMapping("basicInfo/bikeInfo/updateBC.do")
	public String updateBC(HttpServletRequest req, Bicycle_Catagory bc) {
		try {
			bcservice.updateBC(bc);
			req.setAttribute("message", "修改成功!");
			return "showBC.do";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("message", "修改失败!");
		return "showBC.do";
		
	}

	public IBicycle_CatagoryService getBcservice() {
		return bcservice;
	}

	public void setBcservice(IBicycle_CatagoryService bcservice) {
		this.bcservice = bcservice;
	}
	
}
