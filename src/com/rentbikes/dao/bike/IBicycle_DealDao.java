package com.rentbikes.dao.bike;

import com.rentbikes.model.Bicycle_Deal;

public interface IBicycle_DealDao {
	public void fixOut(Bicycle_Deal bicycle_deal);//填写维修调出业务流水
	public void fixIn(Bicycle_Deal bicycle_deal);//填写维修调入业务流水
	public void addFixdeal(Bicycle_Deal bicycle_deal);//填写维修业务流水
	
	int addBuyBicycle(Bicycle_Deal deal);	//新车购入的流水记录
	int addForBicycleToPile(Bicycle_Deal deal);	//新车调入的流水记录
}
