package com.rentbikes.dao.bike;

import java.util.List;
import java.util.Map;

import com.rentbikes.model.Bicycle_Deal;
import com.rentbikes.model.Bicycle_Deploy;
import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Bicycle_Pile;
import com.rentbikes.model.Bicycle_Station;



//所有有关调入车的数据库操作都在这里
public interface Bicycle_InDao {
	public List<Bicycle_Info> FindBicycleOfin(Map map);			//用来查询出所有的能够调入的车辆也可以进行条件查询
	public int getpagenumall(Bicycle_Info bicycle_info);		//将所有状态为普通调出的状态的车辆数目进行统计									//查询出所有处于正常调出状态的车子数量
	public int getpagenumallpoint();							//得到所有的可以调入车辆的车点数目		
	public List<Bicycle_Station> getCanInbicyclePoint(Map map);	//得到所有可以调入车辆的车点
	public Bicycle_Info getBicyclestatus(Bicycle_Info bicycle_info);	//检查当前车的状态是否是普通调出
	public Bicycle_Pile getBicycle_PileStatus(Bicycle_Pile bicycle_Pile);	//检查当前车桩是否是无车状态
	public int updateBicycleStatus(Bicycle_Info bicycle_Info2);					//修改车辆状态
	public int updateBicycleDep(Bicycle_Deploy bcdl2);						//修改调配明细
	public int updateBicycleplie(Bicycle_Pile bicycle_pile1);				//修改车桩状态
	public int updateBicycleDeployMsg(Bicycle_Deploy Bicycle_Deploy1);		//修改车辆调配明细表
	public int getIdforBicycleDeploy(Bicycle_Deploy Bicycle_Deploy2);		//得到明细流水
	public int insertBicycleDeal(Bicycle_Deal bicycle_deal2);											//添加业务记录
	
}
