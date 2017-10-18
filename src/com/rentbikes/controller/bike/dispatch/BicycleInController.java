package com.rentbikes.controller.bike.dispatch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rentbikes.model.Bicycle_Deal;
import com.rentbikes.model.Bicycle_Deploy;
import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Bicycle_Pile;
import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Card;
import com.rentbikes.model.Page;
import com.rentbikes.model.SYUser;
import com.rentbikes.service.bike.Bicyle_InService;
import com.rentbikes.service.bike.BikeOutService;

/*
 * 大体功能描述:对已经实现普通调出的车辆进行调入
 * */
@Controller
public class BicycleInController {

	@Autowired
	Bicyle_InService bicycle_inService;
	@Autowired
	BikeOutService bikeOutService;
	
	
	//将车调入车桩的方法
	@RequestMapping("/inThisBicycle.do")
	public String inThisBicycle(Bicycle_Pile bp1,Bicycle_Info bcif,Bicycle_Deploy bcdl,Bicycle_Deal bcydl,SYUser user,Model model,Card card,Bicycle_Station bs){
		System.out.println("dfadsf电饭锅"+bp1.getBicycle_id());
		int flag=bicycle_inService.inThisBicycle(bp1, bcif, bcdl, bcydl, user, card);
		if(flag==2){
			model.addAttribute("inbicyclemsgok", "调入成功！");
		}else if(flag==4){
			model.addAttribute("inbicyclemsgno", "该车辆已经不是普通调出状态");
		}else if(flag==3){
			model.addAttribute("inbicyclemsgno","调入失败原因：该卡号不是员工卡号!");
		}else if(flag==5){
			model.addAttribute("inbicyclemsgno", "该车桩有车或者故障！");
		}
		return "FindCanInBicyclePoint.do?bicycle_id="+bcif.getBicycle_id()+"&bicycle_code="+bcif.getBicycle_code()+"&current=0";

	}
	
	//查询可调入的车桩和车点
	@RequestMapping("/FindCanInBicyclePoint.do")
	public String FindCanInBicyclePoint(Bicycle_Info bicycle_Info,Page page,Model m1){
		int bicycle_InfoId=bicycle_Info.getBicycle_id();			//得到自行车编号
		String bicycle_InfoCode=bicycle_Info.getBicycle_code();		//得到自行车号
		if(bicycle_InfoCode==""){							//如果未接收到自行车号将其转化为null
			bicycle_Info.setBicycle_code(null);
		}
		if(page==null){
			page = new Page();
		}
		page.setSize(bicycle_inService.getpagenumallpoint());			//得到能够调入车辆的车点数目
		page.setTotal(bikeOutService.getAllPage(page));					//计算出总共多少页
		List<Bicycle_Station> CanInBicyclePointList =bicycle_inService.getCanInbicyclePoint(page);	//得到可以入车的车点
		System.out.println(CanInBicyclePointList.toString());
		if(bicycle_InfoCode==null){							//如果未接收到自行车号将其转化为null
			bicycle_Info.setBicycle_code("");
		}
		m1.addAttribute("willInPilebicycle",bicycle_Info);
		if(CanInBicyclePointList.isEmpty()){
			m1.addAttribute("noInbikepoint","没有可以调入的车点");
		}else{
			m1.addAttribute("CanInBicyclePointList1", CanInBicyclePointList);
			m1.addAttribute("CanInBicyclePointpage",page);
		}
		
		return "bike/dispatch/CanIntoBicyclepoint.jsp";
		
	}
	
	//查询出可以调入的车辆信息
	@RequestMapping("/BicycleCanInList.do")
	public String BicycleCanInList(Bicycle_Info bcif,Model model,Page page){
		int bicycle_InfoId=bcif.getBicycle_id();			//得到自行车编号
		String bicycle_InfoCode=bcif.getBicycle_code();		//得到自行车号
		if(bicycle_InfoCode==""){							//如果未接收到自行车号将其转化为null
			bcif.setBicycle_code(null);
		}
		
		if(page==null){
			page = new Page();
		}
		page.setSize(bicycle_inService.getpagenumall(bcif));
		page.setTotal(bikeOutService.getAllPage(page));
		List<Bicycle_Info> bicycle_InfoList=bicycle_inService.FindBicycleOfin(bcif,page);
		if(bicycle_InfoList.isEmpty()){									//如果查询出来的list集合中为空那么代表没有可调入的车辆
			model.addAttribute("noBicycleCanInPile","没有可调入的车辆");
		}else{															//如果list集合中部位空那么代表有可调入的车辆将集合反馈回去
			model.addAttribute("BicycleCanInPileList",bicycle_InfoList);
			model.addAttribute("BicycleCanInPilepage",page);
		}
		return "bike/dispatch/Bicyclein.jsp?qbicycle_id="+bcif.getBicycle_id()+"&qbicycle_code="+bcif.getBicycle_code();
	}

	
	
	public Bicyle_InService getBicycle_inService() {
		return bicycle_inService;
	}

	public void setBicycle_inService(Bicyle_InService bicycle_inService) {
		this.bicycle_inService = bicycle_inService;
	}



	public BikeOutService getBikeOutService() {
		return bikeOutService;
	}



	public void setBikeOutService(BikeOutService bikeOutService) {
		this.bikeOutService = bikeOutService;
	}
	
	
	
}
