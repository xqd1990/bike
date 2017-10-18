package com.rentbikes.model;


/*
 * 保存分页信息
 */
public class Page {
	
	private int current;	//当前页
	private int start;		//起始条数
	private int count = 8;		//每页信息条数
	
	private int total;		//总页数
	private int size;		//总条数
	
	public Page(){
		start = current * count;
	}
	
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
}
