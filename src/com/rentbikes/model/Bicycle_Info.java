package com.rentbikes.model;

public class Bicycle_Info {
	private int bicycle_id;
	private String bicycle_code;
	private String status;
	private int pile_id;
	private String destory_date;
	private int user_id;
	private String operator_time;
	private int card_id;
	private String remark;
	private String catagory_name;	//车辆类型名称
	public int getBicycle_id() {
		return bicycle_id;
	}
	public void setBicycle_id(int bicycle_id) {
		this.bicycle_id = bicycle_id;
	}
	public String getBicycle_code() {
		return bicycle_code;
	}
	public void setBicycle_code(String bicycle_code) {
		this.bicycle_code = bicycle_code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getPile_id() {
		return pile_id;
	}
	public void setPile_id(int pile_id) {
		this.pile_id = pile_id;
	}
	public String getDestory_date() {
		return destory_date;
	}
	public void setDestory_date(String destory_date) {
		this.destory_date = destory_date;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getOperator_time() {
		return operator_time;
	}
	public void setOperator_time(String operator_time) {
		this.operator_time = operator_time;
	}
	public int getCard_id() {
		return card_id;
	}
	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCatagory_name() {
		return catagory_name;
	}
	public void setCatagory_name(String catagory_name) {
		this.catagory_name = catagory_name;
	}
	@Override
	public String toString() {
		return "Bicycle_Info [bicycle_id=" + bicycle_id + ", bicycle_code="
				+ bicycle_code + ", status=" + status + ", pile_id=" + pile_id
				+ ", destory_date=" + destory_date + ", user_id=" + user_id
				+ ", operator_time=" + operator_time + ", card_id=" + card_id
				+ ", remark=" + remark + "]";
	}
	
}
