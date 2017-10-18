package com.rentbikes.model;

public class QueryDateMsg {

	
	private String inFee;		//充值金额
	private String poFerr;		//消费金额
	private String numRecord;		//租车次数
	private int avgRecordNum;	//平均租车次数
	private int avgRecordTime;	//平均租车时间
	private String recordTimenum;			//租车时间
	
	
	public int getAvgRecordNum() {
		return avgRecordNum;
	}
	public void setAvgRecordNum(int avgRecordNum) {
		this.avgRecordNum = avgRecordNum;
	}
	public String getInFee() {
		return inFee;
	}
	public void setInFee(String inFee) {
		this.inFee = inFee;
	}
	public String getPoFerr() {
		return poFerr;
	}
	public void setPoFerr(String poFerr) {
		this.poFerr = poFerr;
	}
	public String getNumRecord() {
		return numRecord;
	}
	public void setNumRecord(String numRecord) {
		this.numRecord = numRecord;
	}
	
	public int getAvgRecordTime() {
		return avgRecordTime;
	}
	public void setAvgRecordTime(int avgRecordTime) {
		this.avgRecordTime = avgRecordTime;
	}
	public String getRecordTimenum() {
		return recordTimenum;
	}
	public void setRecordTimenum(String recordTimenum) {
		this.recordTimenum = recordTimenum;
	}

	
	
}
