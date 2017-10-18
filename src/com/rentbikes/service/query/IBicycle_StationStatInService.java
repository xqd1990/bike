package com.rentbikes.service.query;

import java.util.List;

import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Bicycle_StationStatIn;
import com.rentbikes.model.Page;



public interface IBicycle_StationStatInService {
	public Bicycle_StationStatIn getStationStatIn(Bicycle_Station bs) throws Exception;//根据车点获得车点信息
	public List<Bicycle_Station> getBSPage(Page page);//查询车点信息
	public List<Bicycle_Station> getBSSearch(Page page,Bicycle_Station bs);//模糊查询车点
}
