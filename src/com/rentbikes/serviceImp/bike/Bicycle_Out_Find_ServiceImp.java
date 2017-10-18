package com.rentbikes.serviceImp.bike;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rentbikes.dao.basicInfo.RegisterCardDao;
import com.rentbikes.dao.bike.Bicycle_Out_Find_pointDao;
import com.rentbikes.model.Bicycle_Deal;
import com.rentbikes.model.Bicycle_Deploy;
import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Bicycle_Pile;
import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Card;
import com.rentbikes.model.Page;
import com.rentbikes.model.SYUser;
import com.rentbikes.service.bike.BikeOutService;


@Service
public class Bicycle_Out_Find_ServiceImp implements BikeOutService {

	@Autowired
	private Bicycle_Out_Find_pointDao bofp;
	@Autowired
	private RegisterCardDao RCD;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int outthisbicycle(Bicycle_Pile bp1, Bicycle_Info bcif,
			Bicycle_Deploy bcdl, Bicycle_Deal bcydl, SYUser user,Card card) {
	//通过卡code得到卡id
		Card card1=bofp.getcardid(card);
		if(card1==null){
			return 3;
		}else{
			int cardid=card1.getCard_id();
	//得到用户id
		user=RCD.getuserid(user);
		int userid=user.getUser_id();
	//向业务流水注入用户id(操作人)
		bcydl.setUser_id(userid);
	//向车辆状态类型中注入操作人
		bcif.setUser_id(userid);
	//向车桩类中注入操作人
		bp1.setUser_id(userid);
	//将卡id注入到车辆调配明细类中的from_card_id
		bcdl.setFrom_card_id(cardid);
	//将id注入到业务流水表中的业务卡id
		bcydl.setCard_id(cardid);
	//向车辆状态信息类中注入卡id
		bcif.setCard_id(cardid);
	try {
		//修改自行车状态为4
			bofp.updatebucycleinfo(bcif);
		//将所在车桩的状态改成	（2：无车）
			bofp.updatebicyclepile(bp1);
		//记录车辆调配明细，调入的内容可以不填写，调入原因填写（4：普通调出）
			bofp.insertbicycledploy(bcdl);
		//记录车辆业务流水，业务类型为（4：普通调出）关联的业务记录id填写车辆调配明细id
			bcydl.setRecord_id(bcdl.getDeploy_id());
			bofp.insertbicycledeal(bcydl);
			return 1;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return 2;
	}
		}
	}
	
	//得到所有能够调出的车点的数量
	@Transactional(propagation=Propagation.SUPPORTS)
	public int getpagenumall() {
		return bofp.getpagenumall();
	}
	
	//得到所有能够调出车的车点的信息
	@Override
	public List<Bicycle_Station> allCanOutBikePoint(Page page) {
		if(page.getCurrent()<=0){
			page.setCurrent(0);
		}
		if(page.getCurrent()>=page.getTotal()){
			page.setCurrent(page.getTotal()-1);
		}
		
		Map<String, Object> map1=new HashMap<String, Object>();
		System.err.println(page.getCurrent());
		map1.put("start1", page.getCurrent()*page.getCount()+1);
		map1.put("end",  page.getCurrent()*page.getCount()+1+page.getCount());
		return bofp.allCanOutBikePoint(map1);
	}
	
	
	//查看选中车点的有车的车桩信息
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Bicycle_Pile> lookBicyclePile(Bicycle_Pile bcp) {
		List<Bicycle_Pile> bplist=bofp.lookBicyclePile(bcp);
		return bplist;
	}
	
	
	//根据传输过来的数据查询出相关的有车的车点信息
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Bicycle_Station> findCanOutBikepoint(Bicycle_Station bs,Page page) {		//根据分页和条件查出要的车点信息
		if(page.getCurrent()<0){
			page.setCurrent(0);
		}
		if(page.getCurrent()>=page.getTotal()){
			page.setCurrent(page.getTotal()-1);
		}
		
		//将地址和号码等为空的时候转化为null
		if(bs.getAddress()==""){
			bs.setAddress(null);
		}
		if(bs.getStation_code()==""){
			bs.setStation_code(null);
		}
		if(bs.getStation_name()==""){
			bs.setStation_name(null);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		System.err.println(page.getCurrent());
		map.put("start1", page.getCurrent()*page.getCount()+1);
		map.put("end",  page.getCurrent()*page.getCount()+1+page.getCount());
		map.put("station_name", bs.getStation_name());
		map.put("address", bs.getAddress());
		map.put("station_code", bs.getStation_code());
		return bofp.findCanOutBikepoint(map);
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public int getpagenum(Bicycle_Station bs) {								//得到查询总数
		return bofp.getpagenum(bs);
	}

	//得到page中的总页数的方法
		public  int getAllPage(Page page){
			return (int)page.getSize()%page.getCount()==0?page.getSize()/page.getCount():page.getSize()/page.getCount()+1;
		}

		public Bicycle_Out_Find_pointDao getBofp() {
			return bofp;
		}

		public void setBofp(Bicycle_Out_Find_pointDao bofp) {
			this.bofp = bofp;
		}



		
		
}
