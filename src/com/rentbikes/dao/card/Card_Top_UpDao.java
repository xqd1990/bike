package com.rentbikes.dao.card;

import java.util.List;

import com.rentbikes.model.Card;
import com.rentbikes.model.Card_Record;
import com.rentbikes.model.SYUser;

public interface Card_Top_UpDao {
	
	//将充值的内容持久化
	public int cardTopUpcarddao(Card card);								//修改卡中的费用
	public int cardTopUpcardrecorddao(Card_Record cr);					//记录卡费用流水
	public List<Card_Record> monthTopUponece(Card_Record cr);					//查看在该月是否有该卡的月票充值记录
	public SYUser getuserid(SYUser user);									//获得对应操作用户的id
}
