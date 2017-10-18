package com.rentbikes.serviceImp.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentbikes.dao.query.IAnnualQueryDao;
import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Bicycle_Record;
import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Page;
import com.rentbikes.service.query.IAnnualBicycleCostService;
@Service
public class AnnualBicycleCostServiceImpl implements IAnnualBicycleCostService {
	
	@Autowired
	private IAnnualQueryDao queryDao;
	
	
	//查询年度/月度所有车辆
	@Override
	public List<Bicycle_Info> queryAllAnnualBicycle(Bicycle_Info bicycle,Page page) {
		//查询数据库总的数据条数(包括模糊查询)
		int size=queryDao.getSize(bicycle);
		Map<String,Object> map=new HashMap<String, Object>();
		if(bicycle.getBicycle_code()!=null){
			map.put("bicycle_code", bicycle.getBicycle_code());
		}
		init(page,size,map);
					System.out.println("start="+map.get("start"));
					System.out.println("end="+map.get("end"));
		List<Bicycle_Info> list=queryDao.queryAllAnnualBicycle(map);
		
		return list;
		
	}
	//初始化page和map
	public void init(Page page,int size,Map<String, Object> map){
				page.setSize(size);
				if(size % page.getCount() == 0) page.setTotal(size / page.getCount());
				else page.setTotal(size / page.getCount() + 1);
				map.put("start", page.getCurrent() * page.getCount() + 1);
				map.put("end", page.getCurrent() * page.getCount() + page.getCount());
				System.out.println("start="+map.get("start")+"end="+map.get("end"));
			}
	
	
	

	//查询单个车辆使用时间
	@Override
	public HashMap queryBicycleDetail(int bicycle_id,HttpServletRequest request) {
		HashMap map=new HashMap();
		/*双击单个车辆，显示车辆的使用时间，借还次数，修理次数，调度次数，总借
		还时间，平均借还时间，总借车收入费用，平均借车费用，平均修理费用，总修理
		费用等信息，并且显示其车辆。*/
		
	
		
		List<Bicycle_Record> list;
		list=queryDao.queryRentTime(bicycle_id);
			if(list.size()==0){
				request.setAttribute("error", "车辆信息为空");
				return map;
			}
		//1.1获得借还次数
		int getBicycleUsetimes=queryDao.getBicycleUsetimes(list.get(0));
		map.put("BicycleUsetimes", getBicycleUsetimes);
		//1.2获得修理次数
		int getBicycleRepairtimes=queryDao.getBicycleRepairtimes(list.get(0));
		map.put("BicycleRepairtimes", getBicycleRepairtimes);
		//1.3.获得调度次数
		int getBicycleDeploytimes=queryDao.getBicycleDeploytimes(list.get(0));
		map.put("BicycleDeploytimes", getBicycleDeploytimes);
		//1.4获得总租还车时间
		
		int sumTime=0;
		for(int i=0;i<list.size();i++){
			sumTime+=queryDao.getUseTime(list.get(i));
			
		}
		map.put("sumTime", sumTime);
		//1.5获得平均借还时间
		double BicycleAvgUseTime= sumTime/getBicycleUsetimes;
		map.put("BicycleAvgUseTime", BicycleAvgUseTime);
		//1.6获得总借车收入费用
		int getBicycleSumFee=queryDao.getBicycleSumFee(list.get(0));
		map.put("BicycleSumFee", getBicycleSumFee);
		//1.7平均借车费用
		double BicycleAvgFee=getBicycleSumFee/getBicycleUsetimes;
		map.put("BicycleAvgFee", BicycleAvgFee);
		
		//1.8总修理费用  
		int BicycleSumRepairFee=queryDao.getBicycleSumRepairFee(list.get(0));
		map.put("BicycleSumRepairFee", BicycleSumRepairFee);
		//1.9平均修理费用
		double BicycleAvgRepairFee;
		int BicycleSumRepairTimes=queryDao.getBicycleSumRepairTimes(list.get(0));
	
		if(BicycleSumRepairFee==0|BicycleSumRepairTimes==0){
			 BicycleAvgRepairFee=0.0;
		}else{
			 BicycleAvgRepairFee=BicycleSumRepairFee/BicycleSumRepairTimes;
		}
	
		map.put("BicycleAvgRepairFee", BicycleAvgRepairFee);
		//1.10车辆使用时间
		
		String currentTime=queryDao.getCurrentTime();
		String operatorTime=queryDao.getOperatorTime(bicycle_id);
		map.put("currenttime", currentTime);
		map.put("operatortime", operatorTime);
		int age=queryDao.bicycleAge(map)/60;
		map.put("BicycleAge", age);
		
		return map;
	}

	public IAnnualQueryDao getQueryDao() {
		return queryDao;
	}

	public void setQueryDao(IAnnualQueryDao queryDao) {
		this.queryDao = queryDao;
	}

	//查询年度/月度车辆数
	@Override
	public Integer queryBicycleNum(HashMap   map) {
		Integer bicycleNum=queryDao.queryBIcycleNum(map);
		if(bicycleNum==0){
			return null;
		}
		return bicycleNum;
	}
	
	//查询年度/月度车辆租借次数
	@Override
	public Integer getUseFrequencyByYear(HashMap map) {
		int frequencyByYear=queryDao.getUseFrequencyByYear(map);
		if(frequencyByYear==0){
			return null;
		}
		return frequencyByYear;
	}

	//查询年度/月度车辆修理次数
	@Override
	public Integer getRepairByYear(HashMap map) {
		try{
			int getRepairByYear=queryDao.getRepairByYear(map);
			return getRepairByYear;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

	//查询年度/月度平均借车时间
	@Override
	public String getAvgUseTimeByYear(HashMap map) {
		try{
			
			String avgUseTimeByYear=queryDao.getAvgUseTimeByYear(map);
			return avgUseTimeByYear;
		}
		catch(Exception e){
			return null;
		}
		
		
		
	}

	//查询年度/月度总用车时间
	@Override
	public String getSumUseTimeByYear(HashMap	map) {
		
		try{
			String sumUseTimeByYear= queryDao.getSumUseTimeByYear(map);
			return sumUseTimeByYear;
		}catch(Exception e){
			return null;
		}
		
		
		
	}
	//查询年度/月度平均租车费用
	@Override
	public String getAvgMoneyByYear(HashMap map) {
		try{
		String avgMoneyByYear=queryDao.getAvgMoneyByYear(map);
		return avgMoneyByYear;
		}

		catch(Exception e){
			return null;
		}
		
	}
	//查询年度/月度平均修理费用
	@Override
	public String getAvgRepairByYear(HashMap map) {
		
		try{
			String avgRepairByYear=queryDao.getAvgRepairByYear(map);
			return avgRepairByYear;
		}
		catch(Exception e){
			return null;
		}

		
	}

	//查询年度/月度总借车收入费用
	@Override
	public String getSumMoneyByYear(HashMap map) {
		try{
		String sumMoneyByYear=queryDao.getSumMoneyByYear(map);
		return sumMoneyByYear;
		}
		
		catch(Exception e){
			return null;
		}
		
	}

	//查询年度/月度总修理费用
	@Override
	public String getSumRepairByYear(HashMap map) {
		try{
			String sumRepairByYear=queryDao.getSumRepairByYear(map);
			return sumRepairByYear;
		}
		
		catch(Exception e){
			return null;
		}
		
	}



}
