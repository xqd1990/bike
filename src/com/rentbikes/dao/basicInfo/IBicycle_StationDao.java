package com.rentbikes.dao.basicInfo;

import java.util.List;
import java.util.Map;

import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Page;


public interface IBicycle_StationDao {
	public List<Bicycle_Station> getFixBSPage(Map map);//查询车点信息
	public int getFixBSSize();//查询所有车点总数
	public List<Bicycle_Station> getFixBSSearch(Map map);//模糊查询车点
	public int getFixBSSearchSize(Bicycle_Station bs);//模糊结果总数
	
	List<Bicycle_Station> getAll();	//查询所有的车点
	List<Bicycle_Station> listStationPage(Map map);	//查询分页的车点
	int countAll();	//查询车点总数
	List<Bicycle_Station> listSearchPage(Map map);	//查询符合联合条件的分页的车点
	int countSearch(Map map);	//查询符合模糊联合条件的车点数量
}
