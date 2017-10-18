package com.rentbikes.dao.basicInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rentbikes.model.Card;
import com.rentbikes.model.Card_Info_Record;
import com.rentbikes.model.Card_Record;
import com.rentbikes.model.Page;
import com.rentbikes.model.SYUser;


//此类实现的是查询所有已经办理过卡的用户信息
public interface RegisterCardDao {
		public Card showOneCard(Card card);  //查询出具体的用户并将该卡用户信息返回
		public int registerCard(Card card); //进行办卡永久化（写入数据库中） 
		public  List<Card> allCard(Map map); //查询已经办过卡的用户的信息（分页）
		public int getpagenum();	//得到该表中总共的信息条数
		public int updateCard(Card card);   //修改卡信息
		public List<Card> qureyCardmsg(Card card);  //查询相关数据 
		public int insertCIR(Card_Info_Record cir);				//插入信息进入卡信息表中
		public int insertCIRofup(Card_Info_Record cir);				//插入信息进入卡信息表中
		public SYUser getuserid(SYUser user);						//得到用户id
		public int insertCIRlgoc(Card_Info_Record cir);   			//当注销的时候向卡信息表中添加信息
		public int insertCRlgoc(Card_Record cr);					//当注销的时候将该卡流水写入流水表中
		public int upcardlgoc(Card card);							//处理注销卡后的数据修改
		public int losscard(Card card);								//挂失卡的dao层
}
