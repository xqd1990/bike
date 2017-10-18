package com.rentbikes.exception;

public class RentException  extends Exception{
	/*private String msg;*/
		


	/*	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}*/

		//指定消息
		public RentException(String msg){
			super(msg);
		}

		public RentException( ){
			super("系统异常");
		}
}
