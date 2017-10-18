package com.rentbikes.controller.query;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Page;
import com.rentbikes.service.query.BicycleAndStationService;

/*
 * 接收车点车辆信息查询的相关请求，并给予响应
 */
@Controller
public class BicycleAndStationController {
	
	@Autowired
	private BicycleAndStationService service;

	//显示车点信息
	@RequestMapping("query/showStation.do")
	public String showStation(HttpServletRequest request, Page page){
		if(page == null) page = new Page();
		request.setAttribute("stationList", service.listStationPage(page));
		request.setAttribute("page", page);
		return "/query/BicycleAndStationMain.jsp";
	}
	
	//查询车点信息(AJAX)
	@RequestMapping("query/searchStation.do")
	public String searchStation(HttpServletRequest request, String station_name, String address, Page page){
		request.setAttribute("page", page);
		request.setAttribute("stationList", service.listSearchStation(page, station_name, address));
		return "/query/BicycleAndStationAjax.jsp";
	}
	
	//查询车桩信息(AJAX)
	@RequestMapping("query/searchPile.do")
	public String searchPile(HttpServletRequest request, int station_id){
		request.setAttribute("pileList", service.listAllByStationId(station_id));
		return "/query/BicycleAndStationPileAjax.jsp";
	}
	
	//查询车辆信息(AJAX)
	@RequestMapping(value="query/searchBicycle.do",produces="text/String;charset=utf-8")
	@ResponseBody
	public String searchBicycle(int pile_id){
		Bicycle_Info info = service.getByPileId(pile_id);
		if(info == null) return "0";
		return JSONObject.toJSONString(info);
	}
	
	public BicycleAndStationService getService() {
		return service;
	}

	public void setService(BicycleAndStationService service) {
		this.service = service;
	}
}
