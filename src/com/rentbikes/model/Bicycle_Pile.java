package com.rentbikes.model;

public class Bicycle_Pile {
	private int pile_id;
	private int vender_id;
	private String pile_code;
	private int station_id;
	private String status;
	private String install_time;
	private String disassembly_time;
	private int user_id;
	private String operator_time;
	private int bicycle_id;
	private String remark;
	public int getPile_id() {
		return pile_id;
	}
	public void setPile_id(int pile_id) {
		this.pile_id = pile_id;
	}
	public int getVender_id() {
		return vender_id;
	}
	public void setVender_id(int vender_id) {
		this.vender_id = vender_id;
	}
	public String getPile_code() {
		return pile_code;
	}
	public void setPile_code(String pile_code) {
		this.pile_code = pile_code;
	}
	public int getStation_id() {
		return station_id;
	}
	public void setStation_id(int station_id) {
		this.station_id = station_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInstall_time() {
		return install_time;
	}
	public void setInstall_time(String install_time) {
		this.install_time = install_time;
	}
	public String getDisassembly_time() {
		return disassembly_time;
	}
	public void setDisassembly_time(String disassembly_time) {
		this.disassembly_time = disassembly_time;
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
	public int getBicycle_id() {
		return bicycle_id;
	}
	public void setBicycle_id(int bicycle_id) {
		this.bicycle_id = bicycle_id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Bicycle_Pile [pile_id=" + pile_id + ", vender_id=" + vender_id
				+ ", pile_code=" + pile_code + ", station_id=" + station_id
				+ ", status=" + status + ", install_time=" + install_time
				+ ", disassembly_time=" + disassembly_time + ", user_id="
				+ user_id + ", operator_time=" + operator_time
				+ ", bicycle_id=" + bicycle_id + ", remark=" + remark + "]";
	}
	
	
}
