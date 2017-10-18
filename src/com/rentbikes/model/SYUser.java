package com.rentbikes.model;

/*
 * 此类用于存放数据库中SYUSER表的数据
 */
public class SYUser {
	
	private Integer user_id;	
	private Integer role_id;
	private String login_name;
	private String username;
	private String password;
	private int active_flag;	//激活标志
	private String zxbj;	//注销标志
	private String office_phone;
	private String mobile_phone;
	private String email;
	
	public SYUser(){}
	
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getActive_flag() {
		return active_flag;
	}
	public void setActive_flag(int active_flag) {
		this.active_flag = active_flag;
	}
	public String getZxbj() {
		return zxbj;
	}
	public void setZxbj(String zxbj) {
		this.zxbj = zxbj;
	}
	public String getOffice_phone() {
		return office_phone;
	}
	public void setOffice_phone(String office_phone) {
		this.office_phone = office_phone;
	}
	public String getMobile_phone() {
		return mobile_phone;
	}
	public void setMobile_phone(String mobile_phone) {
		this.mobile_phone = mobile_phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "SYUser [user_id=" + user_id + ", role_id=" + role_id
				+ ", login_name=" + login_name + ", username=" + username
				+ ", password=" + password + ", active_flag=" + active_flag
				+ ", zxbj=" + zxbj + ", office_phone=" + office_phone
				+ ", mobile_phone=" + mobile_phone + ", email=" + email + "]";
	}
	
	

}
