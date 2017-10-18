package com.rentbikes.service.bike;

import java.util.List;

import com.rentbikes.model.Bicycle_Catagory;
import com.rentbikes.model.Bicycle_Order;

/*
 * 抽象业务层，封装的用于子类实现的方法
 */
public interface Bicycle_OrderService {
	
	boolean addOrder(Bicycle_Order order);
	List<Bicycle_Order> listUnSure();
	
	boolean comfirmOrder(Bicycle_Order order);
}
