package com.rentbikes.dao.basicInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rentbikes.model.*;






public interface IBicycleDao {
	
	public List<Bicycle_Station> queryStation(HashMap map);
	public List<Bicycle_Station> queryAllPile(int id);
	


	
	
	//查询所有的权限（分页）
	public 	List<Bicycle_Station> getPage(Map map);	
	
	//查询总共的数据条数
	public int getSize();
	
	//新增车点，在数据库中完成新增
	public void registStation(Bicycle_Station station);
	
	//为新增的车点添加车桩
	public void registPile(Bicycle_Pile pile);
	
	//修改车点信息
	public void bicycle_stationModify(Bicycle_Station station);
	
	//修改车桩信息
	public void bicycle_pileModify(Bicycle_Pile pile);
	
	//注销之前，确认是不是车点上没有车辆
	public Integer bicycle_stationIsEmpty(int station_id);
	
	//注销车桩
	public void bicycle_stationLogout(int station_id);
	
	//查询所有车点的信息
	public List<Bicycle_Station> getStationName();
	
}
