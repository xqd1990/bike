package com.rentbikes.dao.basicInfo;

import java.util.List;
import java.util.Map;

import com.rentbikes.model.MSPhase;

/*
 * 此类封装了一些对MSPHASE表格（权限表）做增删改查的方法
 */
public interface MSPhaseDao {
	
	int addPhase(MSPhase phase);		//增加一个权限
	int deletePhase(int phaseId);	//删除一个权限
	int updatePhase(MSPhase phase);	//修改一个权限
	List<MSPhase> getPage(Map map);	//查询所有的权限（分页）
	int getSize();		//查询权限总数
	List<MSPhase> getSearch(Map map);//模糊查询
	int getSearchSize(MSPhase phase);	//模糊结果总数
	List<MSPhase> getAll();	//获取所有权限
	
	List<MSPhase> listAllByRoleId(int roleId);	//根据角色获取所有权限 
}
