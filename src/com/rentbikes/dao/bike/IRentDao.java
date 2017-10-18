package com.rentbikes.dao.bike;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Bicycle_Pile;
import com.rentbikes.model.Bicycle_Record;
import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Card;
import com.rentbikes.model.Card_Info_Record;
import com.rentbikes.model.RentTemp;

public interface IRentDao {

	
	//分页查询车点信息页面
	public List<Bicycle_Station> getStationName(Map map);
	
	public List<RentTemp> getrentInfo(String station_id);
	public Card checkCard(String card_code);
	public 	Bicycle_Info checkBicycle(String card_id);
	public int checkCardRent(HashMap hashmap);
	
	
	//查询数据库总条数
	public int getSize();	
	
	
	//修改车辆状态
	public int modifyBicycle_Info(RentTemp renttemp);
	
	//修改车桩信息
	public int modifyBicycle_Pile(RentTemp renttemp);
	
	//查询冻结金额
	public Card selectFrozen_Money(RentTemp renttemp);
	
	//划拨冻结资金
	public  int modifyTransfer_Money(HashMap hashmap);
	
	//记录冻结资金变动信息到卡费用流水
	public int insertCard_Record(HashMap hashmap);
	
	//记录信息到车辆租还记录
	public int insertBicycle_Record(HashMap hashmap);
	
	//记录信息到业务流水里
	public int insertBicycle_Deal(HashMap hsahmap);
	
	//查询bicycle_record的record_id
	public List<RentTemp> selectBicycle_Record(HashMap hashmap);
	
	
	//查询换车点信息
	public List<RentTemp> getreturnInfo(String station_id);
	
	//查询卡Id
	public Card checkCardID(String card_code);
	
	//查询是否已经租车
	public Bicycle_Info isRent(int card_id);
	
	//查询车桩状态
	public Bicycle_Pile checkPile(String pile_id);
	
	//查询当前时间
	public String getCurrentTime();
	
	//查询开始租车时间
	public String getCreateTime(RentTemp  renttemp);
	
	//查询本次租车时间
	public int getRentTime(HashMap hashmap);
	
	//校验卡余额
	public double getWalletMoney(int card_id);
	
	//修改车辆租还表
	public int modifyBicycle_Record(RentTemp renttemp);
	
	//查询车辆信息
	public Bicycle_Info selectBicycle_Info(RentTemp renttemp);
	
	//查询user_id
	public Card_Info_Record  checkCard_Info_Record(int card_id);
	
	//修改车桩信息
	public int modifyBicycle_PileReturn(RentTemp renttemp);
	
	//增加信息到业务流水表
	public int insertBicycle_DealReturn(RentTemp renttemp);
	
	//查询本次record_id
	public Bicycle_Record selectRecord_id(RentTemp renttemp);
	
	//修改卡内信息，执行还车之后扣费。
	public int modifyCard(RentTemp renttemp);
	
	//清空车辆信息中的租车卡id。
	public int  deleteInfo(RentTemp renttemp);
	
	//将费用计入卡费用流水中，费用类型为2：充值，收支类型为支出，此时将费用计为负数。
	public int insertCard_RecordReturn(RentTemp renttemp);
	

	
	
	public String getCreateTimes(RentTemp renttemp);
	
	
}
