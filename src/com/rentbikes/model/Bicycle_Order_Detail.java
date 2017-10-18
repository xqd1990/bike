package com.rentbikes.model;

/*
 * 此类用于存储数据库中BICYCEL_ORDER_DETAIL表中的数据
 */
public class Bicycle_Order_Detail {

	private Integer detail_id;	//明细id
	private Integer catagory_id;	//类型编号
	private Integer order_id;	//主单据id
	private Integer bicycle_id;	//车辆id
	private String create_date;	//出厂日期
	private String batch_no;	//批次号
	private Double price;	//价格
	private String remark;	//备注
	
	public Bicycle_Order_Detail(){}

	public Integer getDetail_id() {
		return detail_id;
	}

	public void setDetail_id(Integer detail_id) {
		this.detail_id = detail_id;
	}

	public Integer getCatagory_id() {
		return catagory_id;
	}

	public void setCatagory_id(Integer catagory_id) {
		this.catagory_id = catagory_id;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getBicycle_id() {
		return bicycle_id;
	}

	public void setBicycle_id(Integer bicycle_id) {
		this.bicycle_id = bicycle_id;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Bicycle_Order_Detail [detail_id=" + detail_id
				+ ", catagory_id=" + catagory_id + ", order_id=" + order_id
				+ ", bicycle_id=" + bicycle_id + ", create_date=" + create_date
				+ ", batch_no=" + batch_no + ", price=" + price + ", remark="
				+ remark + "]";
	}
	
}
