package com.rentbikes.model;
//卡费用流水
public class Card_Record {
	
	private int record_id;
	private int card_id;
	private int fee_type;
	private double chg_monthly_money;
	private double chg_wallet_money;
	private double frozen_money;
	private String create_time;
	private int user_id;
	private String remark;
	private int zxbj;
	public int getRecord_id() {
		return record_id;
	}
	public void setRecord_id(int record_id) {
		this.record_id = record_id;
	}
	public int getCard_id() {
		return card_id;
	}
	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}
	public int getFee_type() {
		return fee_type;
	}
	public void setFee_type(int fee_type) {
		this.fee_type = fee_type;
	}
	public double getChg_monthly_money() {
		return chg_monthly_money;
	}
	public void setChg_monthly_money(double chg_monthly_money) {
		this.chg_monthly_money = chg_monthly_money;
	}
	public double getChg_wallet_money() {
		return chg_wallet_money;
	}
	public void setChg_wallet_money(double chg_wallet_money) {
		this.chg_wallet_money = chg_wallet_money;
	}
	public double getFrozen_money() {
		return frozen_money;
	}
	public void setFrozen_money(double frozen_money) {
		this.frozen_money = frozen_money;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getZxbj() {
		return zxbj;
	}
	public void setZxbj(int zxbj) {
		this.zxbj = zxbj;
	}
	
	
	
}
