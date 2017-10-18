package com.rentbikes.serviceImp.query;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rentbikes.dao.query.IBcycle_Query_MonthAndYearDao;
import com.rentbikes.model.Card_Record;
import com.rentbikes.model.QueryDateMsg;
import com.rentbikes.service.query.IBcycle_Query_MonthAndYearService;
@Service
public class Query_MonthAndYearServiceImp implements
		IBcycle_Query_MonthAndYearService {

	@Autowired
	IBcycle_Query_MonthAndYearDao ibcycleQueryMonthAndYeardao;	
	
	
	//返回i所有租車卡的統計信息
	@Transactional(propagation=Propagation.SUPPORTS)
	public QueryDateMsg getAllcardMsg(Card_Record cardrecord) {
		String time1=cardrecord.getCreate_time();
		int num=getMonthNum(time1);
		QueryDateMsg qdm=new QueryDateMsg();
		String deop=ibcycleQueryMonthAndYeardao.getAllDeposit(cardrecord);//总充值金额
		if(deop==null){
			qdm.setInFee("0");	
		}else{
			qdm.setInFee(deop);
		}
		String usefee=ibcycleQueryMonthAndYeardao.getAllUseFee(cardrecord);//总消费金额
		if(usefee==null){
			qdm.setPoFerr("0");	
		}else{
			qdm.setPoFerr(usefee);
		}
		int recordnum=Integer.parseInt(ibcycleQueryMonthAndYeardao.getRecordnum(cardrecord));//总租车次数
		qdm.setNumRecord(ibcycleQueryMonthAndYeardao.getRecordnum(cardrecord));
		if((recordnum/num)<=0){
			qdm.setAvgRecordNum(recordnum);
		}else{
			qdm.setAvgRecordNum(recordnum/num);
		}
		String recoderTime=ibcycleQueryMonthAndYeardao.getRecordTime(cardrecord);	//这期间共租车多长时间论小时算
		if(recoderTime==null){
			recoderTime="0";
		}
		qdm.setRecordTimenum(recoderTime);											
		int avgRecordTime=Integer.parseInt(recoderTime)/num;						//算出该月内平均一天的租车数
		if(avgRecordTime<=0){
			qdm.setAvgRecordTime(Integer.parseInt(recoderTime));
		}else{
			qdm.setAvgRecordTime(avgRecordTime);
		}

		return qdm;
	}
	
	//根据月份不同返回不同的天数
	private int getMonthNum(String time1){
		String[] num=time1.split("-");
		int year=Integer.parseInt(num[0]);
		int month=Integer.parseInt(num[1]);
		if((year%4==0&&year%100!=0)||year%400==0){
			if(month==2){
				return 29;
			}else if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
				return 31;
			}else{
				return 30;
			}
		}else{
			if(month==2){
				return 28;
			}else if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
				return 31;
			}else{
				return 30;
			}
		}
	}
	
	//只得出租车的费用信息
	@Transactional(propagation=Propagation.SUPPORTS)
	public QueryDateMsg getAcardMsg(Card_Record cardrecord) {
		String time1=cardrecord.getCreate_time();
		int num=getMonthNum(time1);
		QueryDateMsg qdm1=new QueryDateMsg();
		String infee=ibcycleQueryMonthAndYeardao.getACardDeposit(cardrecord);//租车卡总充值金额
		if(infee==null){
			qdm1.setInFee("0");
		}else{			
			qdm1.setInFee(infee);	
		}
		String poferr=ibcycleQueryMonthAndYeardao.getAllACardUseFee(cardrecord);//租车卡总消费金额
		if(poferr==null){
			qdm1.setPoFerr("0");	
		}else{
			qdm1.setPoFerr(poferr);
		}
		int recordnum=Integer.parseInt(ibcycleQueryMonthAndYeardao.getACardRecordnum(cardrecord));//租车卡的总租车次数
		qdm1.setNumRecord(ibcycleQueryMonthAndYeardao.getACardRecordnum(cardrecord));
		if((recordnum/num)<=0){
			qdm1.setAvgRecordNum(recordnum);
		}else{
			qdm1.setAvgRecordNum(recordnum/num);
		}
		String recoderTime=ibcycleQueryMonthAndYeardao.getACardRecordTime(cardrecord);	//这期间租车卡的共租车多长时间论小时算
		if(recoderTime==null){
			recoderTime="0";
		}
		qdm1.setRecordTimenum(recoderTime);											
		int avgRecordTime=Integer.parseInt(recoderTime)/num;						//算出该月内平均一天的租车数
		if(avgRecordTime<=0){
			qdm1.setAvgRecordTime(Integer.parseInt(recoderTime));
		}else{
			qdm1.setAvgRecordTime(avgRecordTime);
		}
		System.out.println("youzhemeduo"+qdm1.getPoFerr());
		return qdm1;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public QueryDateMsg getOnecardMsg(Card_Record cardrecord) {
		String time1=cardrecord.getCreate_time();
		int num=getMonthNum(time1);
		QueryDateMsg qdm1=new QueryDateMsg();
		String deposit=ibcycleQueryMonthAndYeardao.getOneCardDeposit(cardrecord);
		if(deposit==null){
			qdm1.setInFee("0");
		}else{
		qdm1.setInFee(deposit);	//租车卡总充值金额
		}
		String poferr=ibcycleQueryMonthAndYeardao.getOneCardUseFee(cardrecord);
		if(poferr==null){
			qdm1.setPoFerr("0");
		}else{
			qdm1.setPoFerr(poferr);	//租车卡总消费金额
		}
		int recordnum=Integer.parseInt(ibcycleQueryMonthAndYeardao.getOneRecordnum(cardrecord));//租车卡的总租车次数
		qdm1.setNumRecord(ibcycleQueryMonthAndYeardao.getOneRecordnum(cardrecord));
		if((recordnum/num)<=0){
			qdm1.setAvgRecordNum(recordnum);
		}else{
			qdm1.setAvgRecordNum(recordnum/num);
		}
		String recoderTime=ibcycleQueryMonthAndYeardao.getOneRecordTime(cardrecord);	//这期间租车卡的共租车多长时间论小时算
		if(recoderTime==null){
			recoderTime="0";
		}
		qdm1.setRecordTimenum(recoderTime);											
		int avgRecordTime=Integer.parseInt(recoderTime)/num;						//算出该月内平均一天的租车数
		if(avgRecordTime<=0){
			qdm1.setAvgRecordTime(Integer.parseInt(recoderTime));
		}else{
			qdm1.setAvgRecordTime(avgRecordTime);
		}
		return qdm1;
		
	}
	public IBcycle_Query_MonthAndYearDao getIbcycleQueryMonthAndYeardao() {
		return ibcycleQueryMonthAndYeardao;
	}

	public void setIbcycleQueryMonthAndYeardao(
			IBcycle_Query_MonthAndYearDao ibcycleQueryMonthAndYeardao) {
		this.ibcycleQueryMonthAndYeardao = ibcycleQueryMonthAndYeardao;
	}


}
