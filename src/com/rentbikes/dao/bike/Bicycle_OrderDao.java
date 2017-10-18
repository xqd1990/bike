package com.rentbikes.dao.bike;

import java.util.List;

import com.rentbikes.model.Bicycle_Order;

/*
 * 此类封装了对BICYCLE_ORDER表数据增删改查的方法
 */
public interface Bicycle_OrderDao {

	int addOrder(Bicycle_Order order);	//添加订单
	List<Bicycle_Order> listUnSure(); 	//获取状态为录入的订单
	
	int updateStatus(Bicycle_Order order);		//修改单据状态为确认
}
