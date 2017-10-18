package com.rentbikes.controller.bike.rent;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rentbikes.exception.RentException;
import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Bicycle_Pile;
import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Card;
import com.rentbikes.model.Page;
import com.rentbikes.model.RentTemp;
import com.rentbikes.service.bike.IRentService;

@Controller
public class Returncontroller {
	@Autowired
	private IRentService returnService;

	// 点击车辆归还，查询出相关的车点信息
	@RequestMapping("bike/rent/getStationNameReturn.do")
	public String getStationNameReturn(HttpServletRequest request,Page page) {
		List<Bicycle_Station> list = null;
		if(page==null){
			page=new Page();
		}
		try{
			list=returnService.getPage(page);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("error", "查询车点失败!");
		}
		if(list!=null){
			
			request.setAttribute("page", page);
			request.setAttribute("stationnameList", list);
			return "bikereturn.jsp";
		}else{
			request.setAttribute("error", "车点信息为空!");
		}
		return "bikereturn.jsp";
	}
	
	// 点击车点名称，查询出相关的车点、车桩、车辆信息
	@RequestMapping("bike/rent/getallPileReturn.do")
	public String getallPileReturn(String station_id, HttpServletRequest request) {
		List<RentTemp> list;
		try {
			list = returnService.getreturnInfo(station_id);
			request.setAttribute("returnList", list);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "查询失败");
		}
		return "bikereturn.jsp";
	}
	
	
	// 校验数据库，完成还车
	@RequestMapping("bike/rent/returnBicycle.do")
	public String returnBicycle(RentTemp renttemp, HttpServletRequest request) {
		
		try {
			//1.执行还车之前的所有校验
			returnService.checkReturnCondition(renttemp);
			//2.执行还车
			returnService.doReturn(renttemp);
			request.setAttribute("error","还车成功");
		} 
		catch(RentException e){
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			return "bikereturn.jsp";	
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "还车异常！");
			return "bikereturn.jsp";	
		}
		return "bikereturn.jsp";
	}
}
