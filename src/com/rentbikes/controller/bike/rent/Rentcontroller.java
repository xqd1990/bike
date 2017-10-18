package com.rentbikes.controller.bike.rent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rentbikes.exception.RentException;
import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Card;
import com.rentbikes.model.Page;
import com.rentbikes.model.RentTemp;
import com.rentbikes.model.Vender;
import com.rentbikes.service.bike.IRentService;

@Controller
public class Rentcontroller {

	@Autowired
	private IRentService rentService;

	// 点击车辆出租，查询出相关的车点信息
	@RequestMapping("bike/rent/getStationNameRent.do")
	public String getStationNameRent(HttpServletRequest request, Page page) {
		if (page == null)
			page = new Page();
		List<Bicycle_Station> list = null;
		try {
			list = rentService.getPage(page);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "查询车点失败!");
		}
		if (list != null) {
			request.setAttribute("page", page);
			request.setAttribute("stationnameList", list);
			return "bikerent.jsp";
		} else {
			request.setAttribute("error", "车点信息为空!");
		}
		return "bikerent.jsp";
	}

	// 车点名称，查询出相关的车点、车桩、车辆信息
	@RequestMapping("bike/rent/getallPileRent.do")
	public String getallPileRent(String station_id, HttpServletRequest request) {
		List<RentTemp> list;
		try {
			list = rentService.getrentInfo(station_id);
			if (list.size() != 0) {
				request.setAttribute("rentList", list);
			} else {
				request.setAttribute("error", "该车点下目前没有可租车辆，请更换车点查询");
			}
		} catch (Exception e) {
			request.setAttribute("error", "查询异常");
			e.printStackTrace();
		}
		return "bikerent.jsp";
	}

	// 租车
	@RequestMapping("bike/rent/rentBicycle.do")
	public String rentBicycle(RentTemp renttemp, HttpServletRequest request) {
		try {
			// 1.执行租车之前的所有校验(如果校验不通过，可以通过捕获自定义异常RentException获得信息)
			rentService.checkRentCondition(renttemp);			
			// 2.执行租车动作
			rentService.doRent(renttemp);
			request.setAttribute("error", "租车成功！");
			return "bikerent.jsp";
		}
				catch(RentException ee){
					ee.printStackTrace();
					request.setAttribute("error", ee.getMessage());
					return "bikerent.jsp";
				}
			    catch (Exception e) {
			    	request.setAttribute("error", "页面异常！");
					return "bikerent.jsp";
		}
	}
}
