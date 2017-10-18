package com.rentbikes.serviceImp.basicInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rentbikes.dao.basicInfo.RegisterCardDao;
import com.rentbikes.model.Card;
import com.rentbikes.model.Card_Info_Record;
import com.rentbikes.model.Card_Record;
import com.rentbikes.model.Page;
import com.rentbikes.model.SYUser;


/*办卡实现service*/
@Service
public class RegisterCardServiceImp implements
		com.rentbikes.service.basicInfo.RegisterCardService {
	
	@Autowired
	private RegisterCardDao sacd;

	//挂失卡的方法
	@Transactional(propagation=Propagation.SUPPORTS)
	public String losscard(Card card) {
		int flag = sacd.losscard(card);
		if(flag==1){
			return "挂失成功！";
		}else{			
			return "挂失失败！,请与管理员联系！";
		}
	}
	
	//注销卡用户的逻辑层方法
	@Transactional(propagation=Propagation.REQUIRED)
	public String logoutcard(Card card, Card_Info_Record cir, SYUser user,
			Card_Record cr) {
		SYUser syu=sacd.getuserid(user);					//根据用户名得到用户id
		cir.setUser_id(syu.getUser_id());					//为卡信息记录表添加入创建者id
		cr.setUser_id(syu.getUser_id());					//为卡流水类添加创建者id
		cir.setCard_id(card.getCard_id());					//为卡信息记录类中添加卡号
		cr.setCard_id(card.getCard_id());					//为卡流水类添加卡号
		cr.setChg_monthly_money(-cr.getChg_monthly_money());
		cr.setChg_wallet_money(-cr.getChg_wallet_money());
		cr.setFrozen_money(-cr.getFrozen_money());
		int flag1=sacd.upcardlgoc(card);					//在卡信息表中更改数据
		if(flag1==1){
			int flag2=sacd.insertCRlgoc(cr);
			if(flag2==1){
				int flag3 = sacd.insertCIRlgoc(cir);
				if(flag3==1){
					return "注销成功";
				}else{
					return "注销失败";
				}
			}else{
				return "注销失败";
			}
		}else {
			return "注销失败";
		}
	}
	//查询卡信息
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Card> qureyCardmsg(Card card) {
		String name=card.getName();
		String idcard=card.getIdcard();
		String cardcaode=card.getCard_code();
		if(cardcaode.equals("")){
			card.setCard_code(null);
		}
		if(name.equals("")){
			card.setName(null);
		}
		if(idcard.equals("")){
			card.setIdcard(null);
		}
		System.out.println(card.toString());
		return sacd.qureyCardmsg(card);
	}
	
	
	//修改卡信息
	@Transactional(propagation=Propagation.REQUIRED)
	public String updateCard(Card card,Card_Info_Record cir,SYUser user) {
			cir.setCard_id(card.getCard_id());					//设置卡号进入卡信息记录表中
			SYUser syu=sacd.getuserid(user);					//根据用户名得到用户id
			cir.setUser_id(syu.getUser_id());					//赋值计入卡信息类中
			int flag=sacd.updateCard(card);       //对数据进行修改 如果成功则返回1，否则0
			if(flag==1){
				int flag1=sacd.insertCIRofup(cir);
				if(flag1==1){
					return "修改成功";
					}else{
						return "修改失败";
					}
				}
			else{ return "修改失败";}
	}
	
	//办卡信息处理
	@Transactional(propagation=Propagation.REQUIRED)
	public String registerCard(Card card,Card_Info_Record cir,SYUser user) {
		String idcard=card.getIdcard();
		Card flag1 = showOneCard(card);     //查看身份证号是否重复也就是说该用户是否存在
		if(flag1!=null){
			return "身份证号重复";
		}else {
			
			SYUser syu=sacd.getuserid(user);					//根据用户名得到用户id
			cir.setUser_id(syu.getUser_id());					//赋值计入卡信息类中
			int flag=sacd.registerCard(card);
			if(flag==1){
				cir.setCard_id(card.getCard_id());					//设置卡号进入卡信息记录表中
				int flag2=sacd.insertCIR(cir);
				if(flag2==1){
					return "卡办理成功";
				}else {
					return "办理失败";
				}
			}else {
				return "办理失败";
			}
			
		}
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public Card showOneCard(Card card){
		return sacd.showOneCard(card);
	}
	
	
	//得到相关的card信息
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Card> getCard(Page page) {
		if(page.getCurrent()<=0){
			page.setCurrent(0);
		}
		if(page.getCurrent()>=page.getTotal()){
			page.setCurrent(page.getTotal()-1);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		System.err.println(page.getCurrent());
		map.put("start1", page.getCurrent()*page.getCount()+1);
		map.put("end",  page.getCurrent()*page.getCount()+1+page.getCount());
		return sacd.allCard(map);
	}
	
	//得到有多少条card信息
	public int getpagenum(){
		return sacd.getpagenum();
	}
	
	//得到page中的总页数的方法
	public  int getAllPage(Page page){
		return page.getSize()%page.getCount()==0?page.getSize()/page.getCount():page.getSize()/page.getCount()+1;
	}

	public RegisterCardDao getSacd() {
		return sacd;
	}

	public void setSacd(RegisterCardDao sacd) {
		this.sacd = sacd;
	}







	
	
	
	

}
