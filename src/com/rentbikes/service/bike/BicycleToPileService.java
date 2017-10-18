package com.rentbikes.service.bike;

import java.util.List;

import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Page;

/*
 * 此类是抽象业务层
 */
public interface BicycleToPileService {

	List<Bicycle_Info> listBicycle(Page page);
	String searchPiles(int station_id);
	List<Bicycle_Station> listStation();
	
	boolean toPile(Bicycle_Info info);
}
