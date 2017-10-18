package com.rentbikes.dao.bike;

import java.util.List;
import java.util.Map;


import com.rentbikes.model.Bicycle_Deal;
import com.rentbikes.model.Bicycle_Deploy;
import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Bicycle_Pile;
import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Card;





//根据条件查询出相关的能够调出的车点
public interface Bicycle_Out_Find_pointDao {
		public int getpagenum(Bicycle_Station bs);				//查找条数
		public List<Bicycle_Station> findCanOutBikepoint(Map map);			//查找到有车的车点
		public List<Bicycle_Pile> lookBicyclePile(Bicycle_Pile bcp);		//查看相关的车点的有车车桩
		public int getpagenumall();											//得到所有的能够调出车的车点的数量
		public List<Bicycle_Station> allCanOutBikePoint(Map map1);					//得到所有可以调出的车的车点的信息
		public Card getcardid(Card card);									//得到卡号以及验证该卡是否是员工卡
		public int updatebucycleinfo(Bicycle_Info bcif);					//修改车辆状态将被选中的车辆的车辆状态改成（4：普通调出）
		public int updatebicyclepile(Bicycle_Pile bp1);						//将所在车桩的状态改成（2：无车）
		public int insertbicycledploy(Bicycle_Deploy bcdl);									//记录车辆调配明细，调入的内容可以不填写，调入原因填写（4：普通调出）
		public int insertbicycledeal(Bicycle_Deal bcydl);						//记录车辆业务流水，业务类型为（4：普通调出）关联的业务记录id填写车辆调配明细id
}
