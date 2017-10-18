package com.rentbikes.service.query;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Bicycle_Record;
import com.rentbikes.model.Page;

//年度、月度车辆费用统计

public interface IAnnualBicycleCostService {
	//查询和模糊查询所有车辆信息
	public List<Bicycle_Info> queryAllAnnualBicycle(Bicycle_Info bicycle,Page page);
	
	//查询年度车辆总数量
	public Integer queryBicycleNum(HashMap map);
	
	//查询年度车辆租借次数
	public Integer getUseFrequencyByYear(HashMap map);
	
	//查询年度维修次数
	public Integer  getRepairByYear(HashMap map);
	
	//查询年度用车时间
	public String getAvgUseTimeByYear(HashMap map);
	
	//查询年度总用车时间
	public String getSumUseTimeByYear(HashMap map);
	
	//查询年度均租车费用
	public String getAvgMoneyByYear(HashMap map);
	
	//查询年度总借车收入费用
	public String getSumMoneyByYear(HashMap map);
	
	//查询年度平均修理费用
	public String getAvgRepairByYear(HashMap map);
	
	//查询年度总修理费用
	public String getSumRepairByYear(HashMap map);
	
	
	//查询单个车辆信息
	public HashMap queryBicycleDetail(int bicycle_id,HttpServletRequest request);
	
	
	
}
