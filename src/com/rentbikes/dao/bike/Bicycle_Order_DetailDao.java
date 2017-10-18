package com.rentbikes.dao.bike;

import java.util.List;

import com.rentbikes.model.Bicycle_Order_Detail;

/*
 * 封装了处理数据库BICYCLE_ORDER_DETAILDAO表数据的增删改查方法
 */
public interface Bicycle_Order_DetailDao {
	
	int addDetail(Bicycle_Order_Detail detail);	//增加一条订单明细
	
	List<Bicycle_Order_Detail> listByOrderId(int order_id);		//获取指定单据的明细
	int updateBicycleId(Bicycle_Order_Detail detail);	//修改明细的供应商id
}
