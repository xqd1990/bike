package com.rentbikes.serviceImp.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rentbikes.dao.basicInfo.IBicycle_InfoDao;
import com.rentbikes.dao.basicInfo.IBicycle_PileDao;
import com.rentbikes.dao.basicInfo.IBicycle_StationDao;
import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Bicycle_Pile;
import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Page;
import com.rentbikes.service.query.BicycleAndStationService;

/*
 * 业务层，封装了处理具体的客户端请求的方法
 */
@Service
public class BicycleAndStationServiceImp implements BicycleAndStationService {

	@Autowired
	private IBicycle_StationDao stationDao;
	@Autowired
	private IBicycle_PileDao pileDao;
	@Autowired
	private IBicycle_InfoDao infoDao;

	//查询车点
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Bicycle_Station> listStationPage(Page page){
		int size = stationDao.countAll();
		Map<String, Object> map = new HashMap<String, Object>();
		init(page, size, map);
		return stationDao.listStationPage(map);
	}
	
	//联合查询车点(AJAX)
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Bicycle_Station> listSearchStation(Page page, String station_name, String address){
		Map<String, Object> map = new HashMap<String, Object>();
		if(station_name.length() > 0) map.put("station_name", station_name);
		if(address.length() > 0) map.put("address", address);
		int size = stationDao.countSearch(map);
		init(page, size, map);
		return stationDao.listSearchPage(map);
	}
	
	//获取指定车点的所有车桩(AJAX)
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Bicycle_Pile> listAllByStationId(int station_id){
		return pileDao.listAllByStationId(station_id);
	}
	
	//获取停在指定车桩的车辆
	@Transactional(propagation=Propagation.SUPPORTS)
	public Bicycle_Info getByPileId(int pile_id){
		return infoDao.getByPileId(pile_id);
	}
	
	//初始化page和map
	private void init(Page page, int size, Map<String, Object> map){
		page.setSize(size);
		if(size % page.getCount() == 0) page.setTotal(size / page.getCount());
		else page.setTotal(size / page.getCount() + 1);
		map.put("begin",page.getCurrent()*page.getCount() + 1);
		map.put("end",page.getCurrent()*page.getCount() + page.getCount());
	}
	
	
	public IBicycle_StationDao getStationDao() {
		return stationDao;
	}

	public void setStationDao(IBicycle_StationDao stationDao) {
		this.stationDao = stationDao;
	}

	public IBicycle_PileDao getPileDao() {
		return pileDao;
	}

	public void setPileDao(IBicycle_PileDao pileDao) {
		this.pileDao = pileDao;
	}

	public IBicycle_InfoDao getInfoDao() {
		return infoDao;
	}

	public void setInfoDao(IBicycle_InfoDao infoDao) {
		this.infoDao = infoDao;
	}
	
}
