package com.rentbikes.model;

public class Bicycle_Record {
	private int record_id;
	private int bicycle_id;
	private int card_id;
	private String rent_time;
	private int rent_pile_id;
	private String return_time;
	private int return_pile_id;
	private double money;
	private String remark;
	public Bicycle_Record() {
		super();
	}
	public Bicycle_Record(int record_id, int bicycle_id, int card_id,
			String rent_time, int rent_pile_id, String return_time,
			int return_pile_id, double money, String remark) {
		super();
		this.record_id = record_id;
		this.bicycle_id = bicycle_id;
		this.card_id = card_id;
		this.rent_time = rent_time;
		this.rent_pile_id = rent_pile_id;
		this.return_time = return_time;
		this.return_pile_id = return_pile_id;
		this.money = money;
		this.remark = remark;
	}
	public int getRecord_id() {
		return record_id;
	}
	public void setRecord_id(int record_id) {
		this.record_id = record_id;
	}
	public int getBicycle_id() {
		return bicycle_id;
	}
	public void setBicycle_id(int bicycle_id) {
		this.bicycle_id = bicycle_id;
	}
	public int getCard_id() {
		return card_id;
	}
	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}
	public String getRent_time() {
		return rent_time;
	}
	public void setRent_time(String rent_time) {
		this.rent_time = rent_time;
	}
	public int getRent_pile_id() {
		return rent_pile_id;
	}
	public void setRent_pile_id(int rent_pile_id) {
		this.rent_pile_id = rent_pile_id;
	}
	public String getReturn_time() {
		return return_time;
	}
	public void setReturn_time(String return_time) {
		this.return_time = return_time;
	}
	public int getReturn_pile_id() {
		return return_pile_id;
	}
	public void setReturn_pile_id(int return_pile_id) {
		this.return_pile_id = return_pile_id;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
