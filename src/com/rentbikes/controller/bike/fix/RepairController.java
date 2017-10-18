package com.rentbikes.controller.bike.fix;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Bicycle_Pile;
import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Page;
import com.rentbikes.model.Repair_Record;
import com.rentbikes.service.bike.IRepairService;

@Controller
public class RepairController {
	@Autowired
	private IRepairService repairService;
	
	//查看待维修的车辆（分页）
	@RequestMapping("bike/fix/showFixbikes.do")
	public String showFixPage(HttpServletRequest request, Page page,String message){
		if(page == null) page = new Page();
		List<Bicycle_Info> fixList =repairService.getFixPage(page);
		if(fixList != null){
			request.setAttribute("page", page);
			request.setAttribute("fixList", fixList);
			if(message!=null){
				request.setAttribute("message", message);
			}
			return "fixMain.jsp";
		}
		request.setAttribute("message", "待维修车辆信息获取失败，请重新获取！");
		return "fixMain.jsp";
	}
	
	//填写表单
	@RequestMapping("bike/fix/addFix.do")
	public String addFixrecord(HttpServletRequest request,Repair_Record record){
		try {
			repairService.addFixrecord(record);
			request.setAttribute("message", "填写表单成功！");
			return "showFixbikes.do";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("message", "填写表单失败，请重新填写！");
		return  "showFixbikes.do";
	}

	// 获得已经维修成功需要维修调入的车辆信息（分页）
	@RequestMapping("bike/fix/showFixssbikes.do")
	public String showFixssPage(HttpServletRequest request, Page page,String message) {
		if (page == null){
			page = new Page();}
		List<Bicycle_Info> fixssList = repairService.getFixssPage(page);
		if (fixssList != null) {
			request.setAttribute("page", page);
			request.setAttribute("fixssList", fixssList);
			if(message!=null){
				request.setAttribute("message", message);
			}
			return "fixinMain.jsp";
		}
		request.setAttribute("message", "获取维修成功车辆信息失败，请重新获取！");
		return "fixinMain.jsp";
	}
	//获得需要报废的车辆信息（报废）分页
	@RequestMapping("bike/fix/showScrapbikes.do")
	public String showScrapPage(HttpServletRequest request, Page page,String message) {
		if (page == null)
			page = new Page();
		List<Bicycle_Info> scrapList = repairService.getScrapPage(page);
		if (scrapList != null) {
			request.setAttribute("page", page);
			request.setAttribute("scrapList", scrapList);
			if(message!=null){
				request.setAttribute("message", message);
			}
			return "scrapMain.jsp";
		}
		request.setAttribute("message", "获取报废车辆信息失败，请重新获取！");
		return "scrapMain.jsp";
	}
	//报废
	@RequestMapping("bike/fix/scrapbikes.do")
	public String scrap(HttpServletRequest request,Bicycle_Info info){
		try {
			repairService.scrap(info);
			request.setAttribute("message", "车辆报废成功！");
			return "showScrapbikes.do";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("message", "车辆报废失败，请重新尝试！");
		return "showScrapbikes.do";
		
	}
	//获得维修调入所有车点（分页）
	@RequestMapping("bike/fix/showStatIn.do")
	public String showStatIn(HttpServletRequest request,Page page,Bicycle_Info info){
		if (page == null)
			page = new Page();
		List<Bicycle_Station> stationList = repairService.getFixBSPage(page);
		if (stationList != null) {
			request.setAttribute("page", page);
			request.setAttribute("stationList", stationList);
			request.setAttribute("BInfo", info);
			return "stationInMain.jsp";
		}
		request.setAttribute("message", "维修调入车点信息获取失败，请重新尝试！");
		return "stationInMain.jsp";
	}

	// 获得维修调出所有车点（分页）
	@RequestMapping("bike/fix/showStatOut.do")
	public String showStatOut(HttpServletRequest request, Page page) {
		if (page == null)
			page = new Page();
		List<Bicycle_Station> stationList = repairService.getFixBSPage(page);
		if (stationList != null) {
			request.setAttribute("page", page);
			request.setAttribute("stationList", stationList);
			return "stationOutMain.jsp";
		}
		request.setAttribute("message", "维修调出车点信息获取失败，请重新尝试！");
		return "stationOutMain.jsp";
	}

	// 获得模糊查询维修调出所有车点（分页）
	@RequestMapping("bike/fix/showStatOutSearch.do")
	public String showStatOutSearch(HttpServletRequest request, Page page,Bicycle_Station bs) {
		if (page == null)
			page = new Page();
		List<Bicycle_Station> stationList = repairService.getFixBSSearch(page, bs);
		if (stationList != null) {
			request.setAttribute("page", page);
			request.setAttribute("stationList", stationList);
			return "stationOutSearchAjax.jsp";
		}
		request.setAttribute("message", "维修调出车点信息获取失败，请重新尝试！");
		return "error.jsp";
	}

	// 获得模糊查询维修调入所有车点（分页）
	@RequestMapping("bike/fix/showStatInSearch.do")
	public String showStatInSearch(HttpServletRequest request, Page page,
			Bicycle_Station bs) {
		if (page == null)
			page = new Page();
		List<Bicycle_Station> stationList = repairService.getFixBSSearch(page,
				bs);
		if (stationList != null) {
			request.setAttribute("page", page);
			request.setAttribute("stationList", stationList);
			return "stationInSearchAjax.jsp";
		}
		request.setAttribute("message", "维修调入车点信息获取失败，请重新尝试！");
		return "error.jsp";
	}

	//获得该车点的车桩信息（分页）（维修调入）
	@RequestMapping("bike/fix/showPileIn.do")
	public String showPileIn(HttpServletRequest request, Page page,Bicycle_Station bs,Bicycle_Info info) {
		if (page == null)
			page = new Page();
		List<Bicycle_Pile> pileList = repairService.getFixBIPage(page, bs);
		if (pileList != null) {
			request.setAttribute("page", page);
			request.setAttribute("pileList", pileList);
			request.setAttribute("BStation",bs);
			request.setAttribute("BInfo", info);
			return "pileInMain.jsp";
		}
		request.setAttribute("message", "车点车桩信息获取失败，请重新尝试！");
		return "pileInMain.jsp";
	}

	// 获得该车点的车桩信息（分页）（维修调出）
	@RequestMapping("bike/fix/showPileOut.do")
	public String showPileOut(HttpServletRequest request, Page page,
			Bicycle_Station bs) {
		if (page == null){
			page = new Page();
			}
		List<Bicycle_Pile> pileList = repairService.getFixBIPage(page, bs);
		System.out.println(pileList);
		if (pileList != null) {
			request.setAttribute("page", page);
			request.setAttribute("pileList", pileList);
			request.setAttribute("BStation",bs);
			return "pileOutMain.jsp";
		}
		request.setAttribute("message", "车点车桩信息获取失败，请重新尝试！");
		return "pileOutMain.jsp";
	}
	//维修调出
		@RequestMapping("bike/fix/FixOut.do")
		public String fixout(HttpServletRequest request,Bicycle_Info info,Bicycle_Station bs){
			try {
				repairService.fixOut(info);
				request.setAttribute("BStation",bs);
				request.setAttribute("message", "维修调出成功！");
				return "showPileOut.do";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("BStation",bs);
			request.setAttribute("message", "维修调出失败，请重新尝试！");
			return "showPileOut.do";
			
		}
	//维修调入
		@RequestMapping("bike/fix/FixIn.do")
		public String fixIn(HttpServletRequest request,Bicycle_Info info){
			try {
				repairService.fixIn(info);
				request.setAttribute("message", "维修调人成功！");
				return "showFixssbikes.do";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("message", "维修调入失败，请重新尝试！");
			return "showFixssbikes.do";
			
		}
}
