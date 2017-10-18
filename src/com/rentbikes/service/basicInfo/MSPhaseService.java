package com.rentbikes.service.basicInfo;

import java.util.List;

import com.rentbikes.model.MSPhase;
import com.rentbikes.model.Page;

/*
 * 此类是抽象业务层，用于被具体业务层实现
 */
public interface MSPhaseService {
	
	int addPhase(MSPhase phase);	
	int deletePhase(int phaseId);
	int updatePhase(MSPhase phase);
	List<MSPhase> getPage(Page page);
	List<MSPhase> getSearch(Page page, MSPhase phase);
	List<MSPhase> getAll();
}
