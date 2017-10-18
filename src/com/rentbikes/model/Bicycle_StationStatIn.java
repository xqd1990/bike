package com.rentbikes.model;


//车点统计信息StatIn ==Statistical infomation
public class Bicycle_StationStatIn {
	private int station_id;
	private String station_name;
	private String address;
	private String station_code;
	private int bicycle_pile_num;
	private String build_time;
	private int runday;//天数
	private double pin;//普通调入
	private double pout;//普通调出
	private double rentin;//还入
	private double rentout;//还出
	public int getStation_id() {
		return station_id;
	}
	public void setStation_id(int station_id) {
		this.station_id = station_id;
	}
	public String getStation_name() {
		return station_name;
	}
	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStation_code() {
		return station_code;
	}
	public void setStation_code(String station_code) {
		this.station_code = station_code;
	}
	public int getBicycle_pile_num() {
		return bicycle_pile_num;
	}
	public void setBicycle_pile_num(int bicycle_pile_num) {
		this.bicycle_pile_num = bicycle_pile_num;
	}
	public String getBuild_time() {
		return build_time;
	}
	public void setBuild_time(String build_time) {
		this.build_time = build_time;
	}
	public int getRunday() {
		return runday;
	}
	public void setRunday(int runday) {
		this.runday = runday;
	}
	public double getPin() {
		return pin;
	}
	public void setPin(double pin) {
		this.pin = pin;
	}
	public double getPout() {
		return pout;
	}
	public void setPout(double pout) {
		this.pout = pout;
	}
	public double getRentin() {
		return rentin;
	}
	public void setRentin(double rentin) {
		this.rentin = rentin;
	}
	public double getRentout() {
		return rentout;
	}
	public void setRentout(double rentout) {
		this.rentout = rentout;
	}
	
}
