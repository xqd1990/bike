package com.rentbikes.controller.bike.dispatch;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Page;
import com.rentbikes.service.bike.BicycleToPileService;

/*
 * 处理新车入桩的相关请求，并给予响应
 */
@Controller
public class BicycleToPileController {

	@Autowired
	private BicycleToPileService toPileService;

	//显示所有待入桩的新车
	@RequestMapping("bike/dispatch/showBicycleToPile.do")
	public String showBicycle(HttpServletRequest request, Page page){
		if(page == null) page = new Page();
		request.setAttribute("bicycleList", toPileService.listBicycle(page));
		request.setAttribute("page", page);
		request.setAttribute("stationList", toPileService.listStation());
		return "/bike/dispatch/toPileMain.jsp";
	}
	
	//根据车点查询所有车桩(AJAX)
	@RequestMapping(value="bike/dispatch/searchPiles.do", produces="text/String;charset=utf-8")
	@ResponseBody
	public String searchPiles(int station_id){
		return toPileService.searchPiles(station_id);
	}
	
	//新车入桩
	@RequestMapping("bike/dispatch/toPile.do")
	public String toPile(HttpServletRequest request, Bicycle_Info info, int station_id){
		if(toPileService.toPile(info)) request.setAttribute("prompt", "新车已入桩");
		else request.setAttribute("prompt", "新车入桩失败");
		return "/bike/dispatch/showBicycleToPile.do";
	}

	public BicycleToPileService getToPileService() {
		return toPileService;
	}

	public void setToPileService(BicycleToPileService toPileService) {
		this.toPileService = toPileService;
	}
}
