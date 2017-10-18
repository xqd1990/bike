package com.rentbikes.serviceImp.basicInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rentbikes.dao.basicInfo.MSPhaseDao;
import com.rentbikes.model.MSPhase;
import com.rentbikes.model.Page;
import com.rentbikes.service.basicInfo.MSPhaseService;

/*
 * 业务层，处理具体的请求操作
 */
@Service
public class MSPhaseServiceImp implements MSPhaseService{
	
	@Autowired
	private MSPhaseDao phDao;
	
	// 查询所有权限(分页）
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<MSPhase> getPage(Page page) {
		int size = phDao.getSize();
		Map<String,Object> map = new HashMap<String,Object>();
		init(page,size,map);
		return phDao.getPage(map);
	}
	
	//获取所有权限
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<MSPhase> getAll(){
		return phDao.getAll();
	}
		
	//添加一个权限
	@Transactional(propagation = Propagation.REQUIRED)
	public int addPhase(MSPhase phase){
		return phDao.addPhase(phase);
	}
	
	//删除一个权限
	@Transactional(propagation = Propagation.REQUIRED)
	public int deletePhase(int phaseId){
		return phDao.deletePhase(phaseId);
	}
	
	//修改一个权限
	@Transactional(propagation = Propagation.REQUIRED)
	public int updatePhase(MSPhase phase){
		return phDao.updatePhase(phase);
	}
	
	//模糊查询
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<MSPhase> getSearch(Page page, MSPhase phase) {
		int size = phDao.getSearchSize(phase);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("description", phase.getDescription());
		init(page,size,map);
		return phDao.getSearch(map);
	}
	
	
	
	
	
	//初始化page和map
	private void init(Page page,int size,Map<String, Object> map){
		page.setSize(size);
		if(size % page.getCount() == 0) page.setTotal(size / page.getCount());
		else page.setTotal(size / page.getCount() + 1);
		map.put("start", page.getCurrent() * page.getCount() + 1);
		map.put("end", page.getCurrent() * page.getCount() + page.getCount());
	}
	
	public MSPhaseDao getPhDao() {
		return phDao;
	}

	public void setPhDao(MSPhaseDao phDao) {
		this.phDao = phDao;
	}
}
