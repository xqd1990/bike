package com.rentbikes.dao.basicInfo;

import java.util.List;
import java.util.Map;

import com.rentbikes.model.Bicycle_Catagory;
import com.rentbikes.model.Bicycle_Order_Detail;

public interface IBicycle_CatagoryDao {
	public List<Bicycle_Catagory> getBCPage();//获得所有的车辆类型信息
	public void addBC(Bicycle_Catagory bc);//新增车辆类型信息
	public void deleteBC(Bicycle_Catagory bc);//删除车辆类型
	public Bicycle_Order_Detail queryBC(Bicycle_Catagory bc);//删除前查询是否有车辆引用该车辆类型
	public void updateBC(Bicycle_Catagory bc);//修改车辆类型
	
	List<Bicycle_Catagory> getAll();	//获得所有的车辆类型信息
}
