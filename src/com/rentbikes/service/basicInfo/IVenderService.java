package com.rentbikes.service.basicInfo;

import java.util.List;

import com.rentbikes.model.Page;
import com.rentbikes.model.Vender;

public interface IVenderService {
	
	//新增供应商
	public void registVender(Vender vender)throws Exception;
	
	// 执行修改供应商操作之前的查询
	public Vender getVender(int id)throws Exception;
	
	//修改供应商
	public void modifyVender(Vender vender)throws Exception;
	
	//注销供应商
	public void logoutVender(int id)throws Exception;
	
	//分页查询供应商
	public List<Vender> getPage(Page page)throws Exception;
	
	//查询所有供应商
	List<Vender> getAll();

}
