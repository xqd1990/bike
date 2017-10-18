package com.rentbikes.model;

public class Repair_Record {
	private int record_id;
	private int bicycle_id;
	private String repair_type;
	private String repair_part;
	private String repair_result;
	private double fee;
	private String repairer;
	private String repair_date;
	private int user_id;
	private String operator_time;
	private String remark;
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
	public String getRepair_type() {
		return repair_type;
	}
	public void setRepair_type(String repair_type) {
		this.repair_type = repair_type;
	}
	public String getRepair_part() {
		return repair_part;
	}
	public void setRepair_part(String repair_part) {
		this.repair_part = repair_part;
	}
	public String getRepair_result() {
		return repair_result;
	}
	public void setRepair_result(String repair_result) {
		this.repair_result = repair_result;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public String getRepairer() {
		return repairer;
	}
	public void setRepairer(String repairer) {
		this.repairer = repairer;
	}
	public String getRepair_date() {
		return repair_date;
	}
	public void setRepair_date(String repair_date) {
		this.repair_date = repair_date;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Repair_Record [record_id=" + record_id + ", bicycle_id="
				+ bicycle_id + ", repair_type=" + repair_type
				+ ", repair_part=" + repair_part + ", repair_result="
				+ repair_result + ", fee=" + fee + ", repairer=" + repairer
				+ ", repair_date=" + repair_date + ", user_id=" + user_id
				+ ", operator_time=" + operator_time + ", remark=" + remark
				+ "]";
	}
	
	
	
	
}
