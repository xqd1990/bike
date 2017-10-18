package com.rentbikes.service.bike;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.rentbikes.model.*;

public interface IRentService {
	
	//分页查询车点名称
	public List<Bicycle_Station> getPage(Page page)throws Exception;
	
	//执行租车之前的所有校验
	public void checkRentCondition(RentTemp renttemp)throws Exception;
	
	//执行租车动作
	public void doRent(RentTemp renttmep)throws Exception;
	
	//执行还车之前的所有校验
	public void checkReturnCondition(RentTemp renttemp)throws Exception;
	
	
	//执行还车动作
	public void  doReturn(RentTemp renttemp)throws Exception;
		
	//查询租车时间
	public String 	getCreateTime(RentTemp renttemp );
	

	
	//查询租车信息
	public List<RentTemp> getrentInfo(String station_id)throws Exception;
	

	
	//查询还车车点详细信息
	public  List<RentTemp>  getreturnInfo(String station_id)throws Exception;

	
	
	
	
	//查询用户user_id
	public Card_Info_Record checkCard_Info_Record(int card_id);

	
}
