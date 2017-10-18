package com.rentbikes.model;

import java.util.List;

public class Bicycle_Station {
	private int station_id;
	private String station_code;
	private String station_name;
	private double longitude;
	private double latitude;
	private int bicycle_pile_num;
	private String address;
	private String person_in_charge;
	private String build_time;
	private String run_time;
	private int user_id;
	private char zxbj;
	public char getZxbj() {
		return zxbj;
	}
	public void setZxbj(char zxbj) {
		this.zxbj = zxbj;
	}
	private String create_time;
	private String remark;
	private List<Bicycle_Pile> bicycle_pile;
	public int getStation_id() {
		return station_id;
	}
	public void setStation_id(int station_id) {
		this.station_id = station_id;
	}
	public String getStation_code() {
		return station_code;
	}
	public void setStation_code(String station_code) {
		this.station_code = station_code;
	}
	public String getStation_name() {
		return station_name;
	}
	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public int getBicycle_pile_num() {
		return bicycle_pile_num;
	}
	public void setBicycle_pile_num(int bicycle_pile_num) {
		this.bicycle_pile_num = bicycle_pile_num;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPerson_in_charge() {
		return person_in_charge;
	}
	public void setPerson_in_charge(String person_in_charge) {
		this.person_in_charge = person_in_charge;
	}
	public String getBuild_time() {
		return build_time;
	}
	public void setBuild_time(String build_time) {
		this.build_time = build_time;
	}
	public String getRun_time() {
		return run_time;
	}
	public void setRun_time(String run_time) {
		this.run_time = run_time;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public List<Bicycle_Pile> getBicycle_pile() {
		return bicycle_pile;
	}
	public void setBicycle_pile(List<Bicycle_Pile> bicycle_pile) {
		this.bicycle_pile = bicycle_pile;
	}
	@Override
	public String toString() {
		return "Bicycle_Station [station_id=" + station_id + ", station_code="
				+ station_code + ", station_name=" + station_name
				+ ", longitude=" + longitude + ", latitude=" + latitude
				+ ", bicycle_pile_num=" + bicycle_pile_num + ", address="
				+ address + ", person_in_charge=" + person_in_charge
				+ ", build_time=" + build_time + ", run_time=" + run_time
				+ ", user_id=" + user_id + ", create_time=" + create_time
				+ ", remark=" + remark + "]";
	}
	
	
}
