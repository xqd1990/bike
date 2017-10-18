package com.rentbikes.controller.basicInfo.bikeInfo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rentbikes.model.*;
import com.rentbikes.service.bikeInfo.IBicycleService;


@Controller
// 维护车点、车桩信息的controller层
public class Bicyclecontroller {

	@Autowired
	private IBicycleService bicycleService;

	// 车点查询：根据用户输入的信息，通过mybatis的动态sql语句，完成条件匹配和模糊查询。
	@RequestMapping("basicInfo/bikeInfo/queryStation.do")
	public String queryStation(Bicycle_Station station,
			HttpServletRequest request, Page page) {
		int size = bicycleService.getSize();
		page.setSize(size);
		if (size % page.getCount() == 0)
			page.setTotal(size / page.getCount());
		else
			page.setTotal(size / page.getCount() + 1);
		List<Bicycle_Station> list = bicycleService.queryStation(station, page);
		if (list != null) {
			request.setAttribute("station", station);
			request.setAttribute("bicycle_stationList", list);
			request.setAttribute("page", page);
			return "main.jsp";
		}
		return "error.jsp";
	}

	// 车桩查询：根据用户输入的信息，通过mybatis的动态sql语句，完成条件匹配和模糊查询
	@RequestMapping("basicInfo/bikeInfo/queryPiles.do")
	public String queryPiles(Bicycle_Station station,
			HttpServletRequest request, Page page) {
		int size = bicycleService.getSize();
		page.setSize(size);
		if (size % page.getCount() == 0)
			page.setTotal(size / page.getCount());
		else
			page.setTotal(size / page.getCount() + 1);
		List<Bicycle_Station> list = bicycleService.queryStation(station, page);
		if (list != null) {
			request.setAttribute("station", station);
			request.setAttribute("bicycle_stationList", list);
			request.setAttribute("page", page);
			return "bicycle_pile_info.jsp";
		}
		request.setAttribute("error", "查询失败");
		return "bicycle_pile_info.jsp";
	}

	// 点击查看车桩，查询出该车点的所有车桩的信息
	@RequestMapping("basicInfo/bikeInfo/queryPile.do")
	public String queryPile(int id, HttpServletRequest request) {
		List<Bicycle_Station> list;
		try {
			list = bicycleService.queryAllPile(id);
			if (list.size() != 0) {
				request.setAttribute("pileList", list);
			} else {
				request.setAttribute("error", "查询失败或者数据为空");
			}
			return "bicycle_pile.jsp";
		} catch (Exception e) {
			request.setAttribute("error", "查询失败或者数据为空");
			e.printStackTrace();
		}
		return "bicycle_pile.jsp";
	}

	// 新增车点
	@RequestMapping("basicInfo/bikeInfo/bicycle_stationAdd.do")
	public String bicycle_stationAdd(Bicycle_Station station,
			HttpServletRequest request) {
		try {
			bicycleService.registStation(station);
			request.setAttribute("error", "新增成功!");
		} catch (Exception e) {
			request.setAttribute("error", "新增失败!");
		}
		return "/basicInfo/bikeInfo/queryStation.do";
	}

	// 为车点新增车桩
	@RequestMapping("basicInfo/bikeInfo/bicycle_pileRegist.do")
	public String bicycle_pileRegist(Bicycle_Pile pile,
			HttpServletRequest request) {
		try {
			bicycleService.registPile(pile);
			request.setAttribute("error", "新增成功!");
		} catch (Exception e) {
			request.setAttribute("error", "新增失败!");
		}
		return "/basicInfo/bikeInfo/queryPiles.do";
	}

	// 修改车点信息
	@RequestMapping("basicInfo/bikeInfo/bicycle_stationModify.do")
	public String bicycle_stationModify(Bicycle_Station station,
			HttpServletRequest request) {
		try {
			bicycleService.bicycle_stationModify(station);
			request.setAttribute("error", "修改成功!");
		} catch (Exception e) {
			request.setAttribute("error", "修改失败!");
		}
		return "/basicInfo/bikeInfo/queryStation.do";
	}

	// 修改车桩信息
	@RequestMapping("basicInfo/bikeInfo/bicycle_pileModify.do")
	public String bicycle_pileModify(Bicycle_Pile pile,
			HttpServletRequest request) {
		try {
			bicycleService.bicycle_pileModify(pile);
			request.setAttribute("error", "修改成功!");
		} catch (Exception e) {
			request.setAttribute("error", "修改失败!");
		}

		return "/basicInfo/bikeInfo/queryPiles.do";
	}

	// 注销车点
	@RequestMapping("basicInfo/bikeInfo/logoutStation.do")
	public String logoutStation(int station_id, HttpServletRequest request) {
		try {
			if (bicycleService.bicycle_stationLogout(station_id)) {
				request.setAttribute("error", "注销成功!");
			} else {
				request.setAttribute("error", "注销失败,请先清空所有车桩的车辆!");
			}
		} catch (Exception e) {
			request.setAttribute("error", "注销异常");
			e.printStackTrace();
		}
		return "/basicInfo/bikeInfo/queryStation.do";
	}

}
