package com.rentbikes.model;

//用于临时存放信息
public class RentTemp {
	private String station_id;
	private String station_name;
	private String station_code;
	private String bicycle_id;
	private String pile_id;
	private int pile_code;
	private int bicycle_code;
	private int card_id;
	private String card_code;
	private int user_id;
	private  double chg_money;
	private int fee_type;
	private char is_fee;
	private String currenttime;
	private int record_id;
	private double rentfee;
	
	
	
	

	public RentTemp() {
		super();
	}
	
	
	
	



	public RentTemp(String station_id, String station_name,
			String station_code, String bicycle_id, String pile_id,
			int pile_code, int bicycle_code, int card_id, String card_code,
			int user_id, double chg_money, int fee_type, char is_fee,
			String currenttime, int record_id, double rentfee) {
		super();
		this.station_id = station_id;
		this.station_name = station_name;
		this.station_code = station_code;
		this.bicycle_id = bicycle_id;
		this.pile_id = pile_id;
		this.pile_code = pile_code;
		this.bicycle_code = bicycle_code;
		this.card_id = card_id;
		this.card_code = card_code;
		this.user_id = user_id;
		this.chg_money = chg_money;
		this.fee_type = fee_type;
		this.is_fee = is_fee;
		this.currenttime = currenttime;
		this.record_id = record_id;
		this.rentfee = rentfee;
	}
	







	public double getRentfee() {
		return rentfee;
	}







	public void setRentfee(double rentfee) {
		this.rentfee = rentfee;
	}







	public int getRecord_id() {
		return record_id;
	}

	public void setRecord_id(int record_id) {
		this.record_id = record_id;
	}

	public String getCurrenttime() {
		return currenttime;
	}

	public void setCurrenttime(String currenttime) {
		this.currenttime = currenttime;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public double getChg_money() {
		return chg_money;
	}

	public void setChg_money(double chg_money) {
		this.chg_money = chg_money;
	}

	public int getFee_type() {
		return fee_type;
	}

	public void setFee_type(int fee_type) {
		this.fee_type = fee_type;
	}

	public char getIs_fee() {
		return is_fee;
	}

	public void setIs_fee(char is_fee) {
		this.is_fee = is_fee;
	}


	

	

	
	public String getStation_name() {
		return station_name;
	}
	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}
	public String getStation_code() {
		return station_code;
	}
	public void setStation_code(String station_code) {
		this.station_code = station_code;
	}
	public String getCard_code() {
		return card_code;
	}
	public void setCard_code(String card_code) {
		this.card_code = card_code;
	}
	public String getStation_id() {
		return station_id;
	}
	public void setStation_id(String station_id) {
		this.station_id = station_id;
	}
	public String getBicycle_id() {
		return bicycle_id;
	}
	public void setBicycle_id(String bicycle_id) {
		this.bicycle_id = bicycle_id;
	}
	public String getPile_id() {
		return pile_id;
	}
	public void setPile_id(String pile_id) {
		this.pile_id = pile_id;
	}
	
	
	public int getCard_id() {
		return card_id;
	}
	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}
	public int getPile_code() {
		return pile_code;
	}
	public void setPile_code(int pile_code) {
		this.pile_code = pile_code;
	}
	public int getBicycle_code() {
		return bicycle_code;
	}
	public void setBicycle_code(int bicycle_code) {
		this.bicycle_code = bicycle_code;
	}
	

	

}
