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

public interface BikeOutService {
		public List<Bicycle_Station> findCanOutBikepoint(Bicycle_Station bs,Page page);      //找到可以调出的车点
		int getpagenum(Bicycle_Station bs);							//得到总共多少信息
		public  int getAllPage(Page page);							//得到页
		public List<Bicycle_Pile> lookBicyclePile(Bicycle_Pile bcp);		//查看相关的车点的有车车桩
		public int getpagenumall();										//查询所有能够调出车辆的车点个数
		public List<Bicycle_Station> allCanOutBikePoint(Page page);		//得到出所有的能够调出车的车点信息
		public int outthisbicycle(Bicycle_Pile bp1,Bicycle_Info bcif,Bicycle_Deploy bcdl,Bicycle_Deal bcydl,SYUser user,Card card);   //调出车辆
}
