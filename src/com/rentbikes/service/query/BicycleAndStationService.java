package com.rentbikes.service.query;

import java.util.List;

import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Bicycle_Pile;
import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Page;

/*
 * 此类属于抽象业务层，封装的方法用于被子类实现
 */
public interface BicycleAndStationService {
	
	List<Bicycle_Station> listStationPage(Page page);
	List<Bicycle_Station> listSearchStation(Page page, String station_name, String address);
	
	List<Bicycle_Pile> listAllByStationId(int station_id);
	Bicycle_Info getByPileId(int pile_id);
}
