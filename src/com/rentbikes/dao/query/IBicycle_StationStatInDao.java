package com.rentbikes.dao.query;

import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Bicycle_StationStatIn;

public interface IBicycle_StationStatInDao {
	public Bicycle_StationStatIn getStationStatIn(Bicycle_Station bs);
}
