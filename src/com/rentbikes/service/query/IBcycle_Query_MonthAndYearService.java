package com.rentbikes.service.query;

import java.util.Map;

import com.rentbikes.model.Card_Record;
import com.rentbikes.model.QueryDateMsg;


//得到时间内的卡费用统计信息
public interface IBcycle_Query_MonthAndYearService {

	//得到所有出租卡的费用统计信息
	public QueryDateMsg getAllcardMsg(Card_Record cardrecord);
	//得到出租卡的费用统计信息
	public QueryDateMsg getAcardMsg(Card_Record cardrecord);
	//查询出某个卡号的统计信息
	public QueryDateMsg getOnecardMsg(Card_Record cardrecord);
}
