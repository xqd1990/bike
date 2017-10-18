package com.rentbikes.dao.basicInfo;

import java.util.List;
import java.util.Map;

import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Repair_Record;

public interface IBicycle_InfoDao {
	public void fixOut(Bicycle_Info bicycle_info);//维修调出
	public void fixIn(Bicycle_Info bicycle_info);//维修调入
	public void recom(Repair_Record record);//维修完成（repair complete=recom)
	public void scrap(Bicycle_Info bicycle_info);//报废
	public List<Bicycle_Info> getFixPage(Map map);//查询维修调出的车辆信息
	public int getFixSize();//查询维修调出的车辆总数
	public List<Bicycle_Info> getFixssPage(Map map);//查询维修完成且需还入的车辆信息
	public int getFixssSize();//查询维修完成且需还入的车辆总数
	public List<Bicycle_Info> getScrapPage(Map map);//查询需要报废的车辆信息
	public int getScrapSize();//查询需要报废的车辆总数
	
	int addBicycle(Bicycle_Info info);	//新增车辆
	List<Bicycle_Info> listToPile(Map map);	//查询待入桩的新车
	int countToPile();	//查询待入桩车辆的总数
	int updateByPile(Bicycle_Info info);	//修改车辆的车桩，改状态为 3：入桩
	Bicycle_Info getByPileId(int pile_id);	//根据车桩查询车辆
}
