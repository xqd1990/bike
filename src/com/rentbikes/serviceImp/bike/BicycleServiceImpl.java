package com.rentbikes.serviceImp.bike;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rentbikes.dao.basicInfo.IBicycleDao;
import com.rentbikes.model.Bicycle_Pile;
import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Page;
import com.rentbikes.service.bikeInfo.IBicycleService;

@Service
public class BicycleServiceImpl implements IBicycleService {
	@Autowired
	private IBicycleDao bicycleDao;

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Bicycle_Station> queryStation(Bicycle_Station station, Page page) {

		if (page == null) {
			page = new Page();
		}
		HashMap map = new HashMap();

		if (!"-1".equals(station.getStation_code())
				&& station.getStation_code() != null
				&& station.getStation_code().length() > 0)
			map.put("station_code", station.getStation_code());
		if (!"-1".equals(station.getStation_name())
				&& station.getStation_name() != null
				&& station.getStation_name().length() > 0)
			map.put("station_name", station.getStation_name());
		if (!"-1".equals(station.getPerson_in_charge())
				&& station.getPerson_in_charge() != null
				&& station.getPerson_in_charge().length() > 0)
			map.put("person_in_charge", station.getPerson_in_charge());
		if (!"-1".equals(station.getAddress()) && station.getAddress() != null
				&& station.getAddress().length() > 0)
			map.put("address", station.getAddress());
		map.put("start", page.getCurrent() * page.getCount() + 1);
		map.put("end", page.getCurrent() * page.getCount() + page.getCount());
		List<Bicycle_Station> list = bicycleDao.queryStation(map);
		return list;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Bicycle_Station> queryAllPile(int id) {
		List<Bicycle_Station> list = bicycleDao.queryAllPile(id);

		return list;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void registStation(Bicycle_Station station) {
		bicycleDao.registStation(station);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void registPile(Bicycle_Pile pile) {
		bicycleDao.registPile(pile);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void bicycle_stationModify(Bicycle_Station station) {
		bicycleDao.bicycle_stationModify(station);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void bicycle_pileModify(Bicycle_Pile pile) {
		bicycleDao.bicycle_pileModify(pile);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean bicycle_stationLogout(int station_id) {
		// 1.确认是不是都没有车
		Integer flag = null;
		flag = bicycleDao.bicycle_stationIsEmpty(station_id);
		if (flag != null) {
			// 2.满足条件：注销车点
			bicycleDao.bicycle_stationLogout(station_id);
			return true;

		} else {
			// 3.不满足，返回错误信息
			return false;
		}

	}

	@Override
	public int getSize() {
		int size = bicycleDao.getSize();
		return size;
	}

}
