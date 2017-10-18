package com.rentbikes.controller.query;



import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Page;
import com.rentbikes.service.query.IAnnualBicycleCostService;

@Controller
public class QueryAnnualcontroller {
	@Autowired
	private IAnnualBicycleCostService annualcicyclecostService;
	
	
	
	//查询所有车辆信息，分页
	@RequestMapping("query/queryAllAnnualBicycle.do")
	public String queryAllAnnualBicycle(Bicycle_Info bicycle,HttpServletRequest request,Page page){
		if(page==null){page=new Page();}
		List<Bicycle_Info> list=null;
		try{
			list=annualcicyclecostService.queryAllAnnualBicycle(bicycle,page);
			
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("error", "查询车辆失败!");
		}
		if(list.size()!=0){
			request.setAttribute("page", page);
			request.setAttribute("bicycle_infoList", list);
		}else{
			request.setAttribute("error", "没有相关车辆信息，请核实");
		}
		return"annualbicyclecost.jsp";
	}
	
	//按照年度信息查询记录
	@RequestMapping("query/queryYearInfo.do")
	public String queryYearInfo(String year,String month,HttpServletRequest request){
		
		/*：1：输入年度信息，月度信息，统计车辆数量，借还次数，修理次数，平均借还时
			间，总借还时间平均借车收入费用，总借车收入费用，平均修理费用，总修理费显
			示在界面上。*/
		boolean flag=false;
		HashMap map=new HashMap();
		if(month==""){month=null;}
		map.put("year", year);
		map.put("month", month);
		//1.年度/年度车辆数量  √√
		
		if(annualcicyclecostService.queryBicycleNum(map)!=null){
			Integer bicycleNum=annualcicyclecostService.queryBicycleNum(map);
			System.out.println(bicycleNum);
			request.setAttribute("bicycleNum", bicycleNum);
		}else{
			flag=true;
			request.setAttribute("error", "年度/月度车辆数量查询失败！");
			return"years_of_bike.jsp";
		}
		
		//2.年度/月度借还次数√√
		if(annualcicyclecostService.getUseFrequencyByYear(map)!=null){
			int frequencyByYear=annualcicyclecostService.getUseFrequencyByYear(map);
			request.setAttribute("frequencyByYear", frequencyByYear);
		}
		else{
			flag=true;
			request.setAttribute("error", "年度/月度借还次数查询失败！");
			return"years_of_bike.jsp";
		}
		
		
		//3.年度/月度修理次数√√
		int repairByYear=annualcicyclecostService.getRepairByYear(map);
		if(annualcicyclecostService.getRepairByYear(map)!=null){
			 repairByYear=annualcicyclecostService.getRepairByYear(map);
			request.setAttribute("repairByYear", repairByYear);
		}
		else{
			flag=true;
			request.setAttribute("error", "年度/月度修理次数查询失败！");
			return"/query/years_of_bike.jsp";
		}
		
		
		//4.查询年度平均借还时间——分钟数√√
		if(annualcicyclecostService.getAvgUseTimeByYear(map)!=null){
			String avgUseTimeByYear=annualcicyclecostService.getAvgUseTimeByYear(map);
			double ss=Double.parseDouble(avgUseTimeByYear);
			
		
			ss=ss*100;
			int temp=(int)ss/100;
			request.setAttribute("avgUseTimeByYear", temp);
		}else{
			flag=true;
			request.setAttribute("error", "查询年度/月度平均借还时间查询失败！");
			return"years_of_bike.jsp";
		}
		
		//5.查询总借还时间√√
		if(annualcicyclecostService.getSumUseTimeByYear(map)!=null){
		String sumUseTimeByYear=annualcicyclecostService.getSumUseTimeByYear(map);
		request.setAttribute("sumUseTimeByYear", sumUseTimeByYear);
		}else{
			flag=true;
			request.setAttribute("error", "查询总借还时间查询失败！");
			return"years_of_bike.jsp";
		}
		//6.查询平均借车收入费用√√
		if(annualcicyclecostService.getAvgMoneyByYear(map)!=null){
			String avgMoneyByYear=annualcicyclecostService.getAvgMoneyByYear(map);
			double xx=Double.parseDouble(avgMoneyByYear);
			xx=xx*100;
			int temp2=(int)xx/100;
			request.setAttribute("avgMoneyByYear", temp2);
		}else{
			flag=true;
			request.setAttribute("error", "查询平均借车收入费用查询失败！");
			return"years_of_bike.jsp";
		}
		
		
		//7.总借车收入费用√√
		if(annualcicyclecostService.getSumMoneyByYear(map)!=null){
		String sumMoneyByYear=annualcicyclecostService.getSumMoneyByYear(map);
		request.setAttribute("sumMoneyByYear", sumMoneyByYear);
		}else{
			flag=true;
			request.setAttribute("error", "总借车收入费用查询失败！");
			return"years_of_bike.jsp";
		}
		//8.平均修理费用√√
		if(annualcicyclecostService.getAvgRepairByYear(map)!=null){
			String avgRepairByYear=annualcicyclecostService.getAvgRepairByYear(map);	
			request.setAttribute("avgRepairByYear", avgRepairByYear);	
		}else{
			flag=true;
			request.setAttribute("error", "平均修理费用查询失败！");
			return"years_of_bike.jsp";
		}
		//9.总修理费用√√
		if(annualcicyclecostService.getSumRepairByYear(map)!=null){
		String sumRepairByYear=annualcicyclecostService.getSumRepairByYear(map);
		request.setAttribute("sumRepairByYear", sumRepairByYear);
		}else{
			flag=true;
			request.setAttribute("error", "总修理费用查询失败！");
			return"years_of_bike.jsp";
		}
		if(flag==false){
			request.setAttribute("error", "查询成功！");
			
		}
		return"years_of_bike.jsp";
	}
	
	@RequestMapping("query/queryBicycleDetail.do")
	public String querBicycleDetail(int bicycle_id,HttpServletRequest request){
		/*3：双击单个车辆，显示车辆的使用时间，借还次数，修理次数，调度次数，总借
		还时间，平均借还时间，总借车收入费用，平均借车费用，平均修理费用，总修理
		费用等信息，并且显示其车辆。*/
		HashMap map=annualcicyclecostService.queryBicycleDetail(bicycle_id,request);
		if(map==null){
			return "bicycle_detail.jsp";
		}else{
			request.setAttribute("BicycleInfo", map);
			return "bicycle_detail.jsp";
		
		}
	}

}
