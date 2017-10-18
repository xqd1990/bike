package com.rentbikes.serviceImp.bike;

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
import com.rentbikes.dao.bike.IRepair_RecordDao;
import com.rentbikes.model.Bicycle_Deal;
import com.rentbikes.model.Bicycle_Deploy;
import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Bicycle_Pile;
import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Page;
import com.rentbikes.model.Repair_Record;
import com.rentbikes.service.bike.IRepairService;

@Service
public class RepairServiceImp implements IRepairService{
	@Autowired
	private IRepair_RecordDao recorddao;
	@Autowired
	private IBicycle_DealDao dealdao;
	@Autowired
	private IBicycle_DeployDao deploydao;
	@Autowired
	private IBicycle_InfoDao infodao;
	@Autowired
	private IBicycle_PileDao piledao;
	@Autowired
	private IBicycle_StationDao stationdao;
	//维修调出
	@Transactional(propagation=Propagation.REQUIRED)
	public void fixOut(Bicycle_Info info) throws Exception {
		Bicycle_Deploy deploy=new Bicycle_Deploy();
		Bicycle_Deal deal=new Bicycle_Deal();
		piledao.fixOut(info);
		infodao.fixOut(info);
		deploy.setBicycle_id(info.getBicycle_id());
		deploy.setFrom_pile_id(info.getPile_id());
		deploy.setFrom_card_id(info.getUser_id());
		deploydao.fixOut(deploy);
		deal.setBicycle_id(info.getBicycle_id());
		deal.setPile_id(info.getPile_id());
		deal.setUser_id(info.getUser_id());
		deal.setRecord_id(deploy.getDeploy_id());
		dealdao.fixOut(deal);
		
	}
	//维修调入
	@Transactional(propagation=Propagation.REQUIRED)
	public void fixIn(Bicycle_Info info) throws Exception {
		piledao.fixIn(info);
		infodao.fixIn(info);
		Bicycle_Deploy deploy=new Bicycle_Deploy();
		Bicycle_Deal deal=new Bicycle_Deal();
		deploy.setBicycle_id(info.getBicycle_id());
		deploy.setTo_pile_id(info.getPile_id());
		deploy.setTo_card_id(info.getUser_id());
		deploydao.fixIn(deploy);
		deal.setBicycle_id(info.getBicycle_id());
		deal.setPile_id(info.getPile_id());
		deal.setUser_id(info.getUser_id());
		deal.setRecord_id(deploy.getDeploy_id());
		dealdao.fixIn(deal);
		
	}
	//填写维修单
	@Transactional(propagation=Propagation.REQUIRED)
	public void addFixrecord(Repair_Record record) throws Exception {
		Bicycle_Deal deal=new Bicycle_Deal();
		deal.setBicycle_id(record.getBicycle_id());
		deal.setCreate_time(record.getRepair_date());
		deal.setChg_money(record.getFee());
		deal.setUser_id(record.getUser_id());
		recorddao.addRecord(record);
		deal.setRecord_id(record.getRecord_id());
		dealdao.addFixdeal(deal);
		infodao.recom(record);
		
	}
	//获得维修车辆信息(分页）
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Bicycle_Info> getFixPage(Page page){
		int size = infodao.getFixSize();
		Map<String,Object> map = new HashMap<String,Object>();
		init(page,size,map);
		return infodao.getFixPage(map);
		
	}
	
	//获得需要报废的车辆信息(分页）
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Bicycle_Info> getScrapPage(Page page){
		int size = infodao.getScrapSize();
		Map<String,Object> map = new HashMap<String,Object>();
		init(page,size,map);
		return infodao.getScrapPage(map);
	}
	
	//获得已经维修成功需要维修调入的车辆信息（分页）
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Bicycle_Info> getFixssPage(Page page){
		int size = infodao.getFixssSize();
		Map<String,Object> map = new HashMap<String,Object>();
		init(page,size,map);
		return infodao.getFixssPage(map);
	}
	//报废
	@Transactional(propagation = Propagation.SUPPORTS)
	public void scrap(Bicycle_Info info)throws Exception{
		infodao.scrap(info);
	}
	//获得所有模糊查询的车点分页信息
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Bicycle_Station> getFixBSSearch(Page page,Bicycle_Station bs) {
		int size=stationdao.getFixBSSearchSize(bs);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("station_name",bs.getStation_name() );
		map.put("address", bs.getAddress());
		init(page,size,map);
		return stationdao.getFixBSSearch(map);
		
	}
	//获得车点信息分页
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Bicycle_Station> getFixBSPage(Page page) {
		int size =stationdao.getFixBSSize();
		Map<String,Object> map = new HashMap<String,Object>();
		init(page,size,map);
		return stationdao.getFixBSPage(map);
	}
	//获得某车点所有车桩信息
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Bicycle_Pile> getFixBIPage(Page page,Bicycle_Station bs) {
		int size=bs.getBicycle_pile_num();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("station_id", bs.getStation_id());
		init(page,size,map);
		return piledao.getFixBIPage(map);
	}
	
	public IRepair_RecordDao getRecorddao() {
		return recorddao;
	}

	public void setRecorddao(IRepair_RecordDao recorddao) {
		this.recorddao = recorddao;
	}

	public IBicycle_DealDao getDealdao() {
		return dealdao;
	}

	public void setDealdao(IBicycle_DealDao dealdao) {
		this.dealdao = dealdao;
	}

	public IBicycle_DeployDao getDeploydao() {
		return deploydao;
	}

	public void setDeploydao(IBicycle_DeployDao deploydao) {
		this.deploydao = deploydao;
	}

	public IBicycle_InfoDao getInfodao() {
		return infodao;
	}

	public void setInfodao(IBicycle_InfoDao infodao) {
		this.infodao = infodao;
	}

	public IBicycle_PileDao getPiledao() {
		return piledao;
	}

	public void setPiledao(IBicycle_PileDao piledao) {
		this.piledao = piledao;
	}
	public IBicycle_StationDao getStationdao() {
		return stationdao;
	}
	public void setStationdao(IBicycle_StationDao stationdao) {
		this.stationdao = stationdao;
	}
		//初始化page和map
		private void init(Page page,int size,Map<String, Object> map){
			page.setSize(size);
			if(size % page.getCount() == 0) page.setTotal(size / page.getCount());
			else page.setTotal(size / page.getCount() + 1);
			map.put("start", page.getCurrent() * page.getCount() + 1);
			map.put("end", page.getCurrent() * page.getCount() + page.getCount());
		}
	

	
	
}
