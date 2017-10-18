package com.rentbikes.serviceImp.basicInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rentbikes.dao.basicInfo.IBicycle_CatagoryDao;
import com.rentbikes.model.Bicycle_Catagory;
import com.rentbikes.model.Bicycle_Order_Detail;
import com.rentbikes.service.basicInfo.IBicycle_CatagoryService;
@Service
public class Bicycle_CatagoryServiceImp implements IBicycle_CatagoryService{
	@Autowired
	private IBicycle_CatagoryDao bsdao;
	//获得所有车辆类型的信息
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Bicycle_Catagory> getBCPage() {
		
		return bsdao.getBCPage();
	}
	//新增车辆类型信息
	@Transactional(propagation = Propagation.SUPPORTS)
	public void addBC(Bicycle_Catagory bc) throws Exception{
		bsdao.addBC(bc);
		
	}
	//删除车辆类型
	@Transactional(propagation = Propagation.SUPPORTS)
	public void deleteBC(Bicycle_Catagory bc) throws Exception{
		// TODO Auto-generated method stub
		bsdao.deleteBC(bc);
	}
	//删除前查询是否有车辆引用该车辆类型
	@Transactional(propagation = Propagation.SUPPORTS)
	public Bicycle_Order_Detail queryBC(Bicycle_Catagory bc) {
		// TODO Auto-generated method stub
		return bsdao.queryBC(bc);
	}
	//修改车辆类型
	@Transactional(propagation = Propagation.SUPPORTS)
	public void updateBC(Bicycle_Catagory bc) throws Exception{
		bsdao.updateBC(bc);
	}
	
	//获得所有的车辆类型（不分页）
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Bicycle_Catagory> getAll(){
		return bsdao.getAll();
	}
	
	
	public IBicycle_CatagoryDao getBsdao() {
		return bsdao;
	}
	public void setBsdao(IBicycle_CatagoryDao bsdao) {
		this.bsdao = bsdao;
	}
	
}
