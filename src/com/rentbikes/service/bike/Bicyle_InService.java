package com.rentbikes.service.bike;

import java.util.List;

import com.rentbikes.model.Bicycle_Deal;
import com.rentbikes.model.Bicycle_Deploy;
import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Bicycle_Pile;
import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Card;
import com.rentbikes.model.Page;
import com.rentbikes.model.SYUser;



//该接口主要实现了调入车功能的方法
public interface Bicyle_InService {
		public List<Bicycle_Info> FindBicycleOfin(Bicycle_Info bcif,Page page);			//找到相关的可以调入的车辆的信息
		public int getpagenumall(Bicycle_Info bicycle_info);					//查找出所有处于普通调出调出状态的车子(包括调价查询)
		public int getpagenumallpoint();									//得到所有的可调入自行车的这点
		public int getCanInbicycleList();									//得到可以调入车的列表
		public List<Bicycle_Station> getCanInbicyclePoint(Page page);		//得到能够入库的车点信息
		public int inThisBicycle(Bicycle_Pile bp1,Bicycle_Info bcif,Bicycle_Deploy bcdl,Bicycle_Deal bcydl,SYUser user,Card card);											//将车调入该车桩
		
}
