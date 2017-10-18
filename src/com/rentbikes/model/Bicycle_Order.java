package com.rentbikes.model;

import java.util.List;

/*
 * 此类用于存储数据库BICYCLE_ORDER表的信息
 */
public class Bicycle_Order {

	private Integer order_id;	//单据id
	private String order_code;	//单据编号
	private Integer vender_id;	//供应商id
	private String buy_date;	//购入日期
	private Integer buy_num;	//购入数量
	private Double buy_price;	//购入总金额
	private String person_in_charge;	//购入负责人
	private String invoice_no;	//发票号码
	private Integer user_id;	//创建人
	private String operator_time;	//创建日期
	private String remark;	//备注
	private Integer status;	//单据状态
	
	private List<Bicycle_Order_Detail> details;	//明细单列表
	
	public Bicycle_Order(){}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public String getOrder_code() {
		return order_code;
	}

	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}

	public Integer getVender_id() {
		return vender_id;
	}

	public void setVender_id(Integer vender_id) {
		this.vender_id = vender_id;
	}

	public String getBuy_date() {
		return buy_date;
	}

	public void setBuy_date(String buy_date) {
		this.buy_date = buy_date;
	}

	public Integer getBuy_num() {
		return buy_num;
	}

	public void setBuy_num(Integer buy_num) {
		this.buy_num = buy_num;
	}

	public Double getBuy_price() {
		return buy_price;
	}

	public void setBuy_price(Double buy_price) {
		this.buy_price = buy_price;
	}

	public String getPerson_in_charge() {
		return person_in_charge;
	}

	public void setPerson_in_charge(String person_in_charge) {
		this.person_in_charge = person_in_charge;
	}

	public String getInvoice_no() {
		return invoice_no;
	}

	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Bicycle_Order_Detail> getDetails() {
		return details;
	}

	public void setDetails(List<Bicycle_Order_Detail> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "Bicycle_Order [order_id=" + order_id + ", order_code="
				+ order_code + ", vender_id=" + vender_id + ", buy_date="
				+ buy_date + ", buy_num=" + buy_num + ", buy_price="
				+ buy_price + ", person_in_charge=" + person_in_charge
				+ ", invoice_no=" + invoice_no + ", user_id=" + user_id
				+ ", operator_time=" + operator_time + ", remark=" + remark
				+ ", status=" + status + ", details=" + details + "]";
	}

	
}
