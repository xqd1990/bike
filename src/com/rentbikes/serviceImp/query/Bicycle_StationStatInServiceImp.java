package com.rentbikes.serviceImp.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rentbikes.dao.basicInfo.IBicycle_StationDao;
import com.rentbikes.dao.query.IBicycle_StationStatInDao;
import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Bicycle_StationStatIn;
import com.rentbikes.model.Page;
import com.rentbikes.service.query.IBicycle_StationStatInService;

@Service
public class Bicycle_StationStatInServiceImp implements IBicycle_StationStatInService{
	@Autowired
	private IBicycle_StationStatInDao bssidao;
	@Autowired
	private IBicycle_StationDao stationdao;
	//获得车点的统计信息
	@Transactional(propagation = Propagation.SUPPORTS)
	public Bicycle_StationStatIn getStationStatIn(Bicycle_Station bs) throws Exception{
		return bssidao.getStationStatIn(bs);
	}

	// 获得所有模糊查询的车点分页信息
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Bicycle_Station> getBSSearch(Page page, Bicycle_Station bs) {
		int size = stationdao.getFixBSSearchSize(bs);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("station_name", bs.getStation_name());
		map.put("address", bs.getAddress());
		init(page, size, map);
		return stationdao.getFixBSSearch(map);

	}

	// 获得车点信息分页
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Bicycle_Station> getBSPage(Page page) {
		int size = stationdao.getFixBSSize();
		Map<String, Object> map = new HashMap<String, Object>();
		init(page, size, map);
		return stationdao.getFixBSPage(map);
	}

	public IBicycle_StationStatInDao getBssidao() {
		return bssidao;
	}

	public void setBssidao(IBicycle_StationStatInDao bssidao) {
		this.bssidao = bssidao;
	}

	public IBicycle_StationDao getStationdao() {
		return stationdao;
	}

	public void setStationdao(IBicycle_StationDao stationdao) {
		this.stationdao = stationdao;
	}
	// 初始化page和map
	private void init(Page page, int size, Map<String, Object> map) {
		page.setSize(size);
		if (size % page.getCount() == 0)
			page.setTotal(size / page.getCount());
		else
			page.setTotal(size / page.getCount() + 1);
		map.put("start", page.getCurrent() * page.getCount() + 1);
		map.put("end", page.getCurrent() * page.getCount() + page.getCount());
	}
}


