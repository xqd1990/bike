package com.rentbikes.dao.query;

import com.rentbikes.model.Card_Record;
import com.rentbikes.model.QueryDateMsg;

//查询出租卡的分用统计持久层(租车卡代表租车卡和社会保险卡)
public interface IBcycle_Query_MonthAndYearDao {
	public String getAllDeposit(Card_Record cardrecord);				//得到这期间所有出租卡充值的金额总数
	public String getAllUseFee(Card_Record cardrecord);					//得到该期间内消费金额
	public String getRecordnum(Card_Record cardrecord);					//得到这月总共租的次数
	public String getRecordTime(Card_Record cardrecord);				//得到这期间总共有租车时间
	public String getACardDeposit(Card_Record cardrecord);				//得到值期间出租卡的充值金额总数
	public String getAllACardUseFee(Card_Record cardrecord);			//得到该期间内租车卡的消费金额
	public String getACardRecordnum(Card_Record cardrecord);			//得到该器件内租车卡的总共的租车次数
	public String getACardRecordTime(Card_Record cardrecord);			//得到这期间租车卡的总共租车时间
	public String getOneCardDeposit(Card_Record cardrecord);			//查询出某项的的充值的金额总数
	public String getOneCardUseFee(Card_Record cardrecord);				//得到该期间内该卡的消费金额
	public String getOneRecordnum(Card_Record cardrecord);				//得到该用户这月总共租的次数
	public String getOneRecordTime(Card_Record cardrecord);				//得到这期间该卡的总共有租车时间
}
