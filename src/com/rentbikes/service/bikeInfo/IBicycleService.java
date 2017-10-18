package com.rentbikes.service.bikeInfo;

import java.util.HashMap;
import java.util.List;

import com.rentbikes.model.*;

public interface IBicycleService {
	
	//查询数据库总数据条数
	public int getSize();
	
	//分页查询车点信息
	public List<Bicycle_Station> queryStation(Bicycle_Station station,Page page);
	
	//查询车点下的所有车桩
	public List<Bicycle_Station> queryAllPile(int id) throws Exception;
	
	//新增车点
	public void registStation(Bicycle_Station station)throws Exception;
	
	//为车点新增车桩
	public void registPile(Bicycle_Pile pile)throws Exception;
	
	//车点信息修改
	public void bicycle_stationModify(Bicycle_Station station) throws Exception;
	
	//车桩信息修改
	public void bicycle_pileModify(Bicycle_Pile pile) throws Exception;
	
	//注销车点
	public boolean bicycle_stationLogout(int station_id) throws Exception;
	
	
	
}
