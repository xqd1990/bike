package com.rentbikes.controller.query;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Bicycle_StationStatIn;
import com.rentbikes.model.Page;
import com.rentbikes.service.query.IBicycle_StationStatInService;

@Controller
public class Bicycle_StationStatInController {
	@Autowired
	private IBicycle_StationStatInService bssiservice;

	// 获得所有车点（分页）
	@RequestMapping("query/showStat.do")
	public String showStat(HttpServletRequest request, Page page) {
		if (page == null)
			page = new Page();
		List<Bicycle_Station> stationList = bssiservice.getBSPage(page);
		if (stationList != null) {
			request.setAttribute("page", page);
			request.setAttribute("stationList", stationList);
			return "stationMain.jsp";
		}
		return "redirect:/error.jsp";
	}

	// 获得模糊查询维修调出所有车点（分页）
	@RequestMapping("query/showStatSearch.do")
	public String showStatSearch(HttpServletRequest request, Page page,
			Bicycle_Station bs) {
		if (page == null)
			page = new Page();
		List<Bicycle_Station> stationList = bssiservice.getBSSearch(page, bs);
		if (stationList != null) {
			request.setAttribute("page", page);
			request.setAttribute("stationList", stationList);
			return "stationSearchAjax.jsp";
		}
		return "redirect:/error.jsp";
	}
	//获得车点的统计信息
	@RequestMapping("query/showStatInfo.do")
	public String showStatInfo(HttpServletRequest request,Bicycle_Station bs){
		try {
			Bicycle_StationStatIn bssi=bssiservice.getStationStatIn(bs);
			request.setAttribute("BSSI",bssi );
			return "stationinfo.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return"redirect:/error.jsp";
		
	}

	public IBicycle_StationStatInService getBssiservice() {
		return bssiservice;
	}

	public void setBssiservice(IBicycle_StationStatInService bssiservice) {
		this.bssiservice = bssiservice;
	}
	
}
