package com.rentbikes.serviceImp.bike;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.rentbikes.dao.bike.IBicycle_DealDao;
import com.rentbikes.dao.bike.IBicycle_DeployDao;
import com.rentbikes.model.Bicycle_Deal;
import com.rentbikes.model.Bicycle_Deploy;
import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Bicycle_Pile;
import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Page;
import com.rentbikes.service.bike.BicycleToPileService;

/*
 * 封装了具体的业务处理方法
 */
@Service
public class BicycleToPileServiceImp implements BicycleToPileService {
	
	@Autowired
	private IBicycle_InfoDao infoDao;
	@Autowired
	private IBicycle_PileDao pileDao;
	@Autowired
	private IBicycle_StationDao stationDao;
	@Autowired
	private IBicycle_DeployDao deployDao;
	@Autowired
	private IBicycle_DealDao dealDao;
	
	//获取需要入桩的新车
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Bicycle_Info> listBicycle(Page page){
		int size = infoDao.countToPile();
		Map<String, Object> map = new HashMap<String, Object>();
		init(size, page, map);
		return infoDao.listToPile(map);
	}
	
	//获取车点的所有车桩
	@Transactional(propagation=Propagation.SUPPORTS)
	public String searchPiles(int station_id){
		List<Bicycle_Pile> pileList = pileDao.listByStationId(station_id);
		StringBuffer sb = new StringBuffer("<option>选择车桩</option>");
		for(Bicycle_Pile pile : pileList){
			sb.append("<option value=" + pile.getPile_id() + " >" + pile.getPile_code() + "</option>");
		}
		return sb.toString();
	}
	
	//查询所有车点
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Bicycle_Station> listStation(){
		return stationDao.getAll();
	}
	
	//新车入桩
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean toPile(Bicycle_Info info){
		//修改车辆的车桩信息及状态
		if(infoDao.updateByPile(info) != 1) return false;
		Bicycle_Pile pile = new Bicycle_Pile();
		pile.setBicycle_id(info.getBicycle_id());
		pile.setPile_id(info.getPile_id());
		//修改车桩的状态为：有车,并添加车辆id
		if(pileDao.updateToFull(pile) != 1) return false;
		Bicycle_Deploy deploy = new Bicycle_Deploy();
		deploy.setBicycle_id(info.getBicycle_id());
		deploy.setTo_pile_id(info.getPile_id());
		deploy.setTo_time(info.getOperator_time());
		//添加一条车辆调配记录
		if(deployDao.addForBicycleToPile(deploy) != 1) return false;
		Bicycle_Deal deal = new Bicycle_Deal();
		deal.setCreate_time(info.getOperator_time());
		deal.setRecord_id(deploy.getDeploy_id());
		deal.setBicycle_id(info.getBicycle_id());
		deal.setPile_id(info.getPile_id());
		deal.setUser_id(info.getUser_id());
		//添加一条车辆流水记录
		if(dealDao.addForBicycleToPile(deal) != 1) return false;
		return true;
	}
	
	//初始化page和map
	private void init(int size, Page page, Map<String, Object> map){
		page.setSize(size);
		if(size % page.getCount() == 0) page.setTotal(size / page.getCount());
		else page.setTotal(size / page.getCount() + 1);
		map.put("begin",page.getCurrent()*page.getCount() + 1);
		map.put("end", page.getCurrent()*page.getCount() + page.getCount());
	}
	/*//获取系统时间
	private String getTime(){
		long t = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format.format(new Date(t));
	}*/
	
	public IBicycle_InfoDao getInfoDao() {
		return infoDao;
	}

	public void setInfoDao(IBicycle_InfoDao infoDao) {
		this.infoDao = infoDao;
	}

	public IBicycle_PileDao getPileDao() {
		return pileDao;
	}

	public void setPileDao(IBicycle_PileDao pileDao) {
		this.pileDao = pileDao;
	}

	public IBicycle_StationDao getStationDao() {
		return stationDao;
	}

	public void setStationDao(IBicycle_StationDao stationDao) {
		this.stationDao = stationDao;
	}

	public IBicycle_DeployDao getDeployDao() {
		return deployDao;
	}

	public void setDeployDao(IBicycle_DeployDao deployDao) {
		this.deployDao = deployDao;
	}

	public IBicycle_DealDao getDealDao() {
		return dealDao;
	}

	public void setDealDao(IBicycle_DealDao dealDao) {
		this.dealDao = dealDao;
	}
	
}
