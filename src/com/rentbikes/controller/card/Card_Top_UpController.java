package com.rentbikes.controller.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.rentbikes.model.Card;
import com.rentbikes.model.Card_Record;
import com.rentbikes.model.SYUser;
import com.rentbikes.service.card.Card_Top_Upservice;


//卡充值的控制层
@Controller
public class Card_Top_UpController {
	
	
	@Autowired
	Card_Top_Upservice cdtus;
	
	//卡充值的方法
	/*
	 * 要求
	 * 1)校验充值金额，钱包账户每次充值金额为50的倍数，总金额不大于500，如果充值月票金额，每月首充不能低于50元，其他充值金额为5的倍数。
	   2)将充值信息填入卡费用表，总金额=总金额+充值金额，卡内余额=卡内余额+充值金额。
	   3)卡费用流水填写，将本次充值流水信息写入到卡费用流水中，费用类型为（1：充值）。
	 * */
	@RequestMapping(value="/topupcard.do",produces="text/json;charset=utf-8")
	@ResponseBody
	public String topupcard(Card card,SYUser user,Card_Record cr){
		System.out.println(card.getCard_id()+"   "+card.getMonthly_money()+" "+card.getWallet_money()+" "+user.getUsername()+" "+cr.getCreate_time());
		String flag=JSONObject.toJSONString(cdtus.card_Top_Up(card, user, cr));
		return flag;
	}
	
	
}
