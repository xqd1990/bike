package com.rentbikes.dao.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Bicycle_Record;

public interface IAnnualQueryDao {

	//查询数据库总条数(包括模糊查询)
	public int getSize(Bicycle_Info bicycle);
	
	//查询当前时间
	public String getCurrentTime();
	
	//查询车辆创建时间
	public String getOperatorTime(int bicycle_id);
	
	//查询车辆时间
	public int bicycleAge(HashMap map);
	
	public List<Bicycle_Info> queryAllAnnualBicycle(Map map);
	
	
	//根据传过来的year，查询年度车辆数
	public Integer queryBIcycleNum(HashMap map);
	
	//根据传过来的year，查询年度车辆租借次数
	public Integer getUseFrequencyByYear(HashMap map);
	
	//根据传过来的yaer，查询年度维修次数
	public Integer getRepairByYear(HashMap map);
	
	//根据传过来的year，查询年度平均用车时间
	public String getAvgUseTimeByYear(HashMap map);
	
	//根据传过来的year，查询年度总用车时间
	public String getSumUseTimeByYear(HashMap map);
	
	//根据传过来的year，查询年度平均租车费用
	public String getAvgMoneyByYear(HashMap map);
	
	//根据传过来的year，查询年度平均修理费用
	public String getAvgRepairByYear(HashMap map);
	
	//根据传过来的map，查询年度总费用
	public String  getSumMoneyByYear(HashMap map);
	
	//根据传过来的mao，查询年度总修理费用
	public String getSumRepairByYear(HashMap map);
	
	
	//根据车辆id，查询车辆使用时间，返回使用分钟数
	public List<Bicycle_Record> queryRentTime(int bicycle_id);
	
	//根据车辆id，查询车辆租还次数，
	public Integer getBicycleUsetimes(Bicycle_Record bicycle_record);
	
	//根据车辆id，查询车辆维修次数
	public Integer   getBicycleRepairtimes(Bicycle_Record bicycle_record);
	
	//根据车辆id，查询车辆调度次数
	public Integer 	getBicycleDeploytimes(Bicycle_Record bicycle_record);
	
	//根据车辆id，查询车辆总租借费用
	public Integer getBicycleSumFee(Bicycle_Record bicycle_record);
	
	//根据车辆id，查询车辆总修理费用
	public Integer getBicycleSumRepairFee(Bicycle_Record bicycle_record);
	
	//根据车辆id，查询车辆总修理次数
	public Integer getBicycleSumRepairTimes(Bicycle_Record bicycle_record);
	
	//根据传过来的bicycle_record对象，查询出车辆的使用时间
	public Integer getUseTime(Bicycle_Record bicycle_record);
}
