package com.rentbikes.serviceImp.basicInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rentbikes.dao.basicInfo.IVenderDao;
import com.rentbikes.model.Page;
import com.rentbikes.model.Vender;
import com.rentbikes.service.basicInfo.IVenderService;

@Service
public class VenderServiceImpl implements IVenderService {
	
	@Autowired
	private IVenderDao venderDao;
	
	
	public IVenderDao getVenderDao() {
		return venderDao;
	}
	public void setVenderDao(IVenderDao venderDao) {
		this.venderDao = venderDao;
	}

	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void registVender(Vender vender) throws Exception {
		venderDao.registVender(vender);
		
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public Vender getVender(int id) throws Exception {
		Vender vender=new Vender();
		vender=venderDao.getVender(id);
		return vender;
	}
	
	//执行修改供应商
	@Transactional(propagation=Propagation.REQUIRED)
	public void modifyVender(Vender vender) throws Exception {
		venderDao.modifyVender(vender);
		
	}
	
	//执行注销供应商
	@Transactional(propagation=Propagation.REQUIRED)
	public void logoutVender(int id) throws Exception {
		venderDao.logoutVender(id);
		
		
	}
	
	//获得所有的供应商信息
		@Transactional(propagation=Propagation.SUPPORTS)
		public List<Vender> getAll(){
			return venderDao.getAll();
		}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Vender> getPage(Page page) throws Exception {
		//查询出总共的数据条数
		int size=venderDao.getSize();
		Map<String,Object> map = new HashMap<String,Object>();
		init(page,size,map);
		List<Vender> list = venderDao.getPage(map);

		return list;
	}

	

	
	
	//初始化page和map
	private void init(Page page,int size,Map<String, Object> map){
			page.setSize(size);
			if(size % page.getCount() == 0) page.setTotal(size / page.getCount());
			else page.setTotal(size / page.getCount() + 1);
			map.put("start", page.getCurrent() * page.getCount() + 1);
			map.put("end", page.getCurrent() * page.getCount() + page.getCount());
			System.out.println("start="+map.get("start")+"end="+map.get("end"));
		}

	

	
	
	
	





	



	


}
