package com.rentbikes.serviceImp.bike;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rentbikes.dao.basicInfo.IBicycle_InfoDao;
import com.rentbikes.dao.bike.Bicycle_OrderDao;
import com.rentbikes.dao.bike.Bicycle_Order_DetailDao;
import com.rentbikes.dao.bike.IBicycle_DealDao;
import com.rentbikes.model.Bicycle_Deal;
import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Bicycle_Order;
import com.rentbikes.model.Bicycle_Order_Detail;
import com.rentbikes.service.bike.Bicycle_OrderService;

/*
 * 具体的业务层，封装客户端请求的具体操作
 */
@Service
public class Bicycle_OrderServiceImp implements Bicycle_OrderService {
	
	@Autowired
	private Bicycle_OrderDao orderDao;
	@Autowired
	private Bicycle_Order_DetailDao detailDao;
	@Autowired
	private IBicycle_InfoDao infoDao;
	@Autowired
	private IBicycle_DealDao dealDao;
	
	//添加一条主订单，并添加相应的明细单
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean addOrder (Bicycle_Order order){
		//添加至购入主信息表
		if(orderDao.addOrder(order) != 1) return false;
		//添加至业务流水
		Bicycle_Deal deal = new Bicycle_Deal();
		deal.setCreate_time(order.getOperator_time());
		deal.setRecord_id(order.getOrder_id());
		deal.setChg_money(order.getBuy_price());
		deal.setUser_id(order.getUser_id());
		if(dealDao.addBuyBicycle(deal) != 1) return false; 
		
		Bicycle_Info info = new Bicycle_Info();
		for(Bicycle_Order_Detail detail : order.getDetails()){
			/*//添加至车辆信息表
			info.setUser_id(order.getOrder_id());
			info.setOperator_time(order.getOperator_time());
			if(infoDao.addBicycle(info) != 1) return false;*/
			/*detail.setBicycle_id(info.getBicycle_id());*/
			
			//添加至明细表
			detail.setOrder_id(order.getOrder_id());
			if(detailDao.addDetail(detail) != 1) return false;
		}
		return true;
	}
	
	//获取状态为录入的所有单据
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Bicycle_Order> listUnSure(){
		return orderDao.listUnSure();
	}
	
	//确认录入车辆:修改单据状态，修改明细表的车辆id，添加车辆信息
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean comfirmOrder(Bicycle_Order order){
		//修改单据状态为    2：确认
		if(orderDao.updateStatus(order) != 1) return false;
		List<Bicycle_Order_Detail> details = detailDao.listByOrderId(order.getOrder_id());
		Bicycle_Info info = new Bicycle_Info();
		info.setUser_id(order.getUser_id());
		info.setOperator_time(order.getOperator_time());
		for(Bicycle_Order_Detail detail : details){
			//添加车辆信息
			if(infoDao.addBicycle(info) != 1) return false;
			//修改车辆id
			detail.setBicycle_id(info.getBicycle_id());
			if(detailDao.updateBicycleId(detail) != 1) return false;
		}
		return true;
	}

	
	public Bicycle_OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(Bicycle_OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public Bicycle_Order_DetailDao getDetailDao() {
		return detailDao;
	}

	public void setDetailDao(Bicycle_Order_DetailDao detailDao) {
		this.detailDao = detailDao;
	}

	public IBicycle_InfoDao getInfoDao() {
		return infoDao;
	}

	public void setInfoDao(IBicycle_InfoDao infoDao) {
		this.infoDao = infoDao;
	}

	public IBicycle_DealDao getDealDao() {
		return dealDao;
	}

	public void setDealDao(IBicycle_DealDao dealDao) {
		this.dealDao = dealDao;
	}
	
}
