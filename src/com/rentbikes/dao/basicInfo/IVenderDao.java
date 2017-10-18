package com.rentbikes.dao.basicInfo;

import java.util.List;
import java.util.Map;


import com.rentbikes.model.Vender;

public interface IVenderDao {
	public void registVender(Vender vender);
	public Vender getVender(int id);
	public void modifyVender(Vender vender);
	public void logoutVender(int id);
	public 	List<Vender> getPage(Map map);	//查询所有的权限（分页）
	public int getSize();	//查询总共的数据条数
	
	List<Vender> getAll();	//获取所有的供应商
}
