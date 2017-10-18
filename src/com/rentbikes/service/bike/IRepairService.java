package com.rentbikes.service.bike;

import java.util.List;

import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Bicycle_Pile;
import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Page;
import com.rentbikes.model.Repair_Record;

public interface IRepairService {
	public void fixOut(Bicycle_Info info)throws Exception;
	public void fixIn(Bicycle_Info info)throws Exception;
	public void addFixrecord(Repair_Record record)throws Exception;
	public List<Bicycle_Info> getFixPage(Page page);
	public List<Bicycle_Info> getFixssPage(Page page);
	public List<Bicycle_Info> getScrapPage(Page page);
	public void scrap(Bicycle_Info info) throws Exception;
	public List<Bicycle_Station> getFixBSPage(Page page);//查询车点信息
	public List<Bicycle_Station> getFixBSSearch(Page page,Bicycle_Station bs);//模糊查询车点
	public List<Bicycle_Pile> getFixBIPage(Page page,Bicycle_Station bs);//获得车点信息
}
