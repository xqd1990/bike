package com.rentbikes.serviceImp.card;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rentbikes.dao.card.Card_Top_UpDao;
import com.rentbikes.model.Card;
import com.rentbikes.model.Card_Record;
import com.rentbikes.model.SYUser;
import com.rentbikes.service.card.Card_Top_Upservice;


@Service
public class Card_Top_Upserviceimp implements Card_Top_Upservice {
	//卡充值的方法
		/*
		 * 要求
		 * 1)校验充值金额，钱包账户每次充值金额为50的倍数，总金额不大于500，如果充值月票金额，每月首充不能低于50元，其他充值金额为5的倍数。
		   2)将充值信息填入卡费用表，总金额=总金额+充值金额，卡内余额=卡内余额+充值金额。
		   3)卡费用流水填写，将本次充值流水信息写入到卡费用流水中，费用类型为（1：充值）。
		 * */
	@Autowired
	private Card_Top_UpDao ctud;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public String card_Top_Up(Card card, SYUser user, Card_Record cr) {
		double monthmoeny=cr.getChg_monthly_money();
		System.out.println(monthmoeny);
		SYUser user1=ctud.getuserid(user);
		cr.setUser_id(user1.getUser_id());
		if(monthmoeny==0){								//当前端传来的月票记录为0侧不用去检查流水表中该月是否有充值月票记录
			int flag=ctud.cardTopUpcarddao(card);		//将相关的钱包进行入库持久化
			cr.setFee_type(2);
			cr.setRemark("钱包充值");
			if(flag==1){
				int flag1=ctud.cardTopUpcardrecorddao(cr);		//写入流水表
				if(flag1==1){
					return "充值成功！";
				}else{
					return "充值失败！";
				}
			}else{
				return "充值失败！";
			}
		}else{												//当月票金额不是0的时候那么就要查看当月是否有充值月票的记录
			cr.setFee_type(1);
			cr.setRemark("月票充值");
			List<Card_Record> flagm=ctud.monthTopUponece(cr);	//查看当月的充值记录
				System.out.println(flagm);
			if(!flagm.isEmpty()){
				int flag4=ctud.cardTopUpcarddao(card);
				if(flag4==1){
					int flag5=ctud.cardTopUpcardrecorddao(cr);
					if(flag5==1){
						return "充值成功！";
					}else{
						return "充值失败！";
					}
				}else{
					return "充值失败！";
				}
			}else{
				if(monthmoeny<50){
					return "该卡充值月票失败,该月首次充值月票时金额不能小于50元！";
				}else{
					int flag6=ctud.cardTopUpcarddao(card);
					if(flag6==1){
						int flag7=ctud.cardTopUpcardrecorddao(cr);
						if(flag7==1){
							return "充值成功！";
						}else{
							return "充值失败！";
						}
					}else{
						return "充值失败！";
					}
				}
			}
		}
	
	}

	public Card_Top_UpDao getCtud() {
		return ctud;
	}

	public void setCtud(Card_Top_UpDao ctud) {
		this.ctud = ctud;
	}

	
}
