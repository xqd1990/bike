package com.rentbikes.model;

import java.util.List;

public class Vender {
	private int vender_id;
	private String vender_code;
	private char vender_type;
	private String vender_name;
	private String address;
	private String telphone;
	private String contacts;
	private String product_license;
	private String bussiness_registration_no;
	private long registered_capital;
	private int user_id;
	private String create_time;
	private char zxbj;
	private String remark;
	/*private List<Vender> venderList;*/
	
	

	@Override
	public String toString() {
		return "Vender [vender_id=" + vender_id + ", vender_code="
				+ vender_code + ", vender_type=" + vender_type
				+ ", vender_name=" + vender_name + ", address=" + address
				+ ", telephone=" + telphone + ", contacts=" + contacts
				+ ", product_license=" + product_license
				+ ", bussiness_registration_no=" + bussiness_registration_no
				+ ", registered_capital=" + registered_capital + ", user_id="
				+ user_id + ", create_time=" + create_time + ", zxbj=" + zxbj
				+ ", remark=" + remark + "]";
	}

	public Vender() {
		super();
	}

	public int getVender_id() {
		return vender_id;
	}

	public void setVender_id(int vender_id) {
		this.vender_id = vender_id;
	}

	public String getVender_code() {
		return vender_code;
	}

	public void setVender_code(String vender_code) {
		this.vender_code = vender_code;
	}

	public char getVender_type() {
		return vender_type;
	}

	public void setVender_type(char vender_type) {
		this.vender_type = vender_type;
	}

	public String getVender_name() {
		return vender_name;
	}

	public void setVender_name(String vender_name) {
		this.vender_name = vender_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getProduct_license() {
		return product_license;
	}

	public void setProduct_license(String product_license) {
		this.product_license = product_license;
	}

	public String getBussiness_registration_no() {
		return bussiness_registration_no;
	}

	public void setBussiness_registration_no(String bussiness_registration_no) {
		this.bussiness_registration_no = bussiness_registration_no;
	}

	public long getRegistered_capital() {
		return registered_capital;
	}

	public void setRegistered_capital(long registered_capital) {
		this.registered_capital = registered_capital;
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

	public char getZxbj() {
		return zxbj;
	}

	public void setZxbj(char zxbj) {
		this.zxbj = zxbj;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
}
