package com.rentbikes.service.basicInfo;

import java.util.List;

import com.rentbikes.model.Card;
import com.rentbikes.model.Card_Info_Record;
import com.rentbikes.model.Card_Record;
import com.rentbikes.model.Page;
import com.rentbikes.model.SYUser;

public interface RegisterCardService {
	String registerCard(Card card,Card_Info_Record cir,SYUser user);				//办卡方法
	List<Card> getCard(Page page);				//得到所有卡的信息
	int getpagenum();							//得到总共多少信息
	int getAllPage(Page page);					//计算出有多少页
	String updateCard(Card card,Card_Info_Record cir,SYUser user);               //修改卡信息
	Card showOneCard(Card card);				//用来查找重复的身份证号
	List<Card> qureyCardmsg(Card card);				//查询相关的信息
	String logoutcard(Card card,Card_Info_Record cir,SYUser user,Card_Record cr);  //注销用户达方法
	String losscard(Card card);			//挂失卡的方法
}
