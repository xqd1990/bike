package com.rentbikes.serviceImp.bike;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rentbikes.dao.basicInfo.RegisterCardDao;
import com.rentbikes.dao.bike.Bicycle_InDao;
import com.rentbikes.dao.bike.Bicycle_Out_Find_pointDao;
import com.rentbikes.model.Bicycle_Deal;
import com.rentbikes.model.Bicycle_Deploy;
import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Bicycle_Pile;
import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Card;
import com.rentbikes.model.Page;
import com.rentbikes.model.SYUser;
import com.rentbikes.service.bike.Bicyle_InService;


@Service
public class Bicycle_InServiceImp implements Bicyle_InService {

	
	
	@Autowired
	private Bicycle_InDao bicycle_InDao;
	@Autowired
	private Bicycle_Out_Find_pointDao bofp;
	@Autowired
	private RegisterCardDao RCD;
	
	
	
	//将该车调入车桩的方法3:不是员工，4车子状态已经改变,5该车桩不是无车状态不能调入
		@Transactional(propagation=Propagation.REQUIRED)
		public int inThisBicycle(Bicycle_Pile bp1, Bicycle_Info bcif,
				Bicycle_Deploy bcdl, Bicycle_Deal bcydl,SYUser user, Card card) {
			//通过卡code得到卡id
					Card card1=bofp.getcardid(card);			//如果是员工卡的话可以继续执行
					if(card1==null){					
						return 3;
					}else{		
						int cardid=card1.getCard_id();
						Bicycle_Info flag1=bicycle_InDao.getBicyclestatus(bcif);
						if(flag1==null){					//如果是员工卡那么检查该车状态是否还是普通调出
							return 4;						//不是的话返回4
						}else{								
							bcif=flag1;					//是的话那么就将该对象赋值为bcif并检查要调入的车桩是否还是空闲状态
							Bicycle_Pile flag2=bicycle_InDao.getBicycle_PileStatus(bp1);
							if(flag2==null){								//如果返回的类型为空那么返回5表示该车桩不是无车状态
								return 5;
							}else{
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
											bcdl.setTo_card_id(cardid);
										//将id注入到业务流水表中的业务卡id
											bcydl.setCard_id(cardid);
										//向车辆状态信息类中注入卡id
											bcif.setCard_id(cardid);	
											bicycle_InDao.updateBicycleStatus(bcif);		//修改车辆状态
											//修改车桩属性
											bicycle_InDao.updateBicycleplie(bp1);
											//填写车辆调配明细
											int deploy_id=bicycle_InDao.getIdforBicycleDeploy(bcdl);
											bicycle_InDao.updateBicycleDeployMsg(bcdl);
											//得到业务流水表中的id
											 bcydl.setRecord_id(deploy_id);
											 //添加业务表
											 bicycle_InDao.insertBicycleDeal(bcydl);
											 return 2;
							}
						}
					}
		}				
	
	
	//查询出可以入车的点的信息
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Bicycle_Station> getCanInbicyclePoint(Page page) {
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
		return bicycle_InDao.getCanInbicyclePoint(map1);
	}
	
	
	
	
	
	//主要用来查询出能够调入的车辆
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Bicycle_Info> FindBicycleOfin(Bicycle_Info bcif,Page page) {
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
		map1.put("bicycle_id", bcif.getBicycle_id());
		map1.put("bicycle_code",bcif.getBicycle_code());
		return bicycle_InDao.FindBicycleOfin(map1);
	}


	
	//将所有的状态为正常调出的车辆数目统计出来
	@Transactional(propagation=Propagation.SUPPORTS)
	public int getpagenumall(Bicycle_Info bicycle_info) {
		return bicycle_InDao.getpagenumall(bicycle_info);
	}



	@Transactional(propagation=Propagation.SUPPORTS)
	public int getpagenumallpoint() {
		
		return bicycle_InDao.getpagenumallpoint();
	}





	@Override
	public int getCanInbicycleList() {
		// TODO Auto-generated method stub
		return 0;
	}




	





	public Bicycle_InDao getBicycle_InDao() {
		return bicycle_InDao;
	}





	public void setBicycle_InDao(Bicycle_InDao bicycle_InDao) {
		this.bicycle_InDao = bicycle_InDao;
	}





	public Bicycle_Out_Find_pointDao getBofp() {
		return bofp;
	}





	public void setBofp(Bicycle_Out_Find_pointDao bofp) {
		this.bofp = bofp;
	}






	
}
