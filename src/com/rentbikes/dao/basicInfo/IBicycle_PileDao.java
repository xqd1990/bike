package com.rentbikes.dao.basicInfo;

import java.util.List;
import java.util.Map;

import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Bicycle_Pile;

public interface IBicycle_PileDao {
	public void fixOut(Bicycle_Info bicycle_info);//维修调出
	public void fixIn(Bicycle_Info bicycle_info);//维修调入
	public List<Bicycle_Pile> getFixBIPage(Map map);//获得车桩信息
	
	List<Bicycle_Pile> listByStationId(int station_id);	//查询指定车点的所有车桩,无车的车桩
	int updateToFull(Bicycle_Pile pile);	//状态改为有车
	List<Bicycle_Pile> listAllByStationId(int station_id);	//查询车点的所有车桩
}
