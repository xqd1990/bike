package com.rentbikes.controller.bike.dispatch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.rentbikes.model.Bicycle_Deal;
import com.rentbikes.model.Bicycle_Deploy;
import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Bicycle_Pile;
import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Card;
import com.rentbikes.model.Page;
import com.rentbikes.model.SYUser;
import com.rentbikes.service.bike.BikeOutService;



//对车辆密集区域的车辆进行调出，以准备调入其他车辆稀缺区域
@Controller
public class BikeOut {
	
	
	@Autowired
	BikeOutService BOService;
	
	
	
	//将该车调出
	@RequestMapping("/outthisbicycle.do")
	public String outthisbicycle(Bicycle_Pile bp1,Bicycle_Info bcif,Bicycle_Deploy bcdl,Bicycle_Deal bcydl,SYUser user,Model model,Card card,Bicycle_Station bs){
		
		int flag=BOService.outthisbicycle(bp1, bcif, bcdl, bcydl,user,card);
		if(flag==1){
			model.addAttribute("outbicyclemsgok", "调出成功！");
		}else if(flag==2){
			model.addAttribute("outbicyclemsgno", "调出失败！请重新操作或者联系网络部办公室！");
		}else{
			model.addAttribute("outbicyclemsguser","请重新调出原因：该卡号不是员工卡号!");
		}
		return "lookbicylepile.do?station_id="+bs.getStation_id()+"&station_name="+bs.getStation_name();
	}
	
	//查看将要调度的车点的车桩信息
	@RequestMapping(value="/lookbicylepile.do")
	public String lookbicylepile(Bicycle_Pile bcp,Model model,Bicycle_Station bys){
		int stationid=bcp.getStation_id();			//车点编号
		List<Bicycle_Pile> bicyclepilelist=BOService.lookBicyclePile(bcp);
		String bsname=bys.getStation_name();		//车点名称
		model.addAttribute("bicyclepointname", bsname);
		if(bicyclepilelist.isEmpty()){
			model.addAttribute("nobicyclepilelist", bsname+"车点已经没车可以调出了！");
		}else{
			model.addAttribute("bicyclepilelist", bicyclepilelist);
		}
		return "bike/dispatch/CanOutBicylePlie.jsp";
	}
	
	
	//所有可调出的车点列表
	@RequestMapping("/allbikeoutfind.do")
	public String allbikeoutfind(Page page,Model model){
		if(page==null){
			page = new Page();
		}
		page.setSize(BOService.getpagenumall());
		page.setTotal(BOService.getAllPage(page));
		List<Bicycle_Station> cobikepoint=BOService.allCanOutBikePoint(page);
		if(cobikepoint.isEmpty()){
			model.addAttribute("canoutbikemsg","没有对应的车点信息！");
		}else{
			model.addAttribute("canoutbikelist",cobikepoint);
			model.addAttribute("canoutbikelistpage",page);
		}
		return "bike/dispatch/BikeOut.jsp";
	}
	
	
	//查找能够进行调出的车点
	@RequestMapping(value="/bikeoutfind.do")
	public String bikeoutfind(Bicycle_Station bs,Page page,Model model){
		model.addAttribute("canoutbikemsgadd",bs.getAddress());
		model.addAttribute("canoutbikemsgcode",bs.getStation_code());
		model.addAttribute("canoutbikename",bs.getStation_name());
		if(page==null){
			page = new Page();
		}
		page.setSize(BOService.getpagenum(bs));

		page.setTotal(BOService.getAllPage(page));
	
		List<Bicycle_Station> bikepoint=BOService.findCanOutBikepoint(bs, page);
		System.out.println(bikepoint.toString());
		if(bikepoint.isEmpty()){
			model.addAttribute("canoutbikemsg","没有对应的车点信息！");
		}else{
			model.addAttribute("canoutbikelist",bikepoint);
			model.addAttribute("canoutbikelistpage",page);
		}
		return "bike/dispatch/BikeOut.jsp";
		
	}

	public BikeOutService getBOService() {
		return BOService;
	}

	public void setBOService(BikeOutService bOService) {
		BOService = bOService;
	}
	
	
}
