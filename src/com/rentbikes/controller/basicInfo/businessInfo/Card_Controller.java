package com.rentbikes.controller.basicInfo.businessInfo;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.SysexMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.rentbikes.model.Card;
import com.rentbikes.model.Card_Info_Record;
import com.rentbikes.model.Card_Record;
import com.rentbikes.model.Page;
import com.rentbikes.model.SYUser;
import com.rentbikes.service.basicInfo.RegisterCardService;


/*该类用户对卡业务的请求，但是不包括卡充值请求
 * 
 * */
@Controller
public class Card_Controller {

	@Autowired
	RegisterCardService RCService;     
	
	
	//挂失卡的控制层方法
	@RequestMapping("/losscard.do")
	public String losscard(Card card,Model model){
		String flag=RCService.losscard(card);
		model.addAttribute("losscardmsg", flag);
		return "showCardUser.do";
	}
	
	
	
	//注销卡的控制层方法
	@RequestMapping("/logoutCard.do")
	public String logoutCard(Card card,Card_Info_Record cir,Card_Record cr,SYUser su,Model model){
		String flag=RCService.logoutcard(card, cir, su, cr);
		model.addAttribute("logoutcardmsg", flag);
		return "showCardUser.do";
	}
	
	//在充值的时候进行查询
	@RequestMapping("/cardfortopup.do")
	public String cardfortopup(Card card,Model model){
		List<Card> list =RCService.qureyCardmsg(card);
		System.out.println(list.toString());
			model.addAttribute("queryCardlistq",list);
			return "card/cardfortopup.jsp";
		
		
	}
	
	//查找对应数据
	@RequestMapping("/qureyCardmsg.do")
	public String qureyCardmsg(Card card,Model model){
		List<Card> list =RCService.qureyCardmsg(card);
		System.out.println(list.toString());
			model.addAttribute("queryCardlist",list);
			return "basicInfo/businessInfo/queryCardMsg.jsp";
		
		
	}
	//修改卡信息
	@RequestMapping(value="/updateCardUser.do",produces="text/json;charset=utf-8")
	@ResponseBody
	public String updateCard(Card card,int idcardflag,Card_Info_Record cir,SYUser user){
		String flag="";									//用来返回是否修改成功的标志
		if(idcardflag==1){
			flag=JSONObject.toJSONString(RCService.updateCard(card,cir,user));
		}else{
			Card flag1=RCService.showOneCard(card);			//当前端将身份证号码改变后要先去数据库中查看是否已经改变
			if(flag1!=null) {
				return JSONObject.toJSONString("身份证号码重复");
				}else {
					flag=JSONObject.toJSONString(RCService.updateCard(card,cir,user));
					}
		}
		return flag;
	}
	
	
	//办理卡请求的处理方法
	@RequestMapping(value="/registerCard.do",produces="text/json;charset=utf-8")
	@ResponseBody
	public String registerCard(Card card,Card_Info_Record cir,SYUser user){
		String flag="";
		if(card!=null){
			flag=JSONObject.toJSONString(RCService.registerCard(card,cir,user));
			return flag;
		}else{
			flag=JSONObject.toJSONString("传输的信息为空请重新填写");
			return flag;
		}
	}
	
	
	//分页查询出已经办卡的用户信息
	@RequestMapping("/showCardUser.do")
	public String showCardUser(Page page,Model model){
		if(page==null){
			page = new Page();
		}
		page.setSize(RCService.getpagenum());					//总条数
		page.setTotal(RCService.getAllPage(page));				//总页数
		List<Card> cardlist=RCService.getCard(page);
		model.addAttribute("cardlist", cardlist);
		model.addAttribute("cardpage", page);
		return "basicInfo/businessInfo/RegisterCard.jsp";
	}

	

	public RegisterCardService getRCService() {
		return RCService;
	}


	public void setRCService(RegisterCardService rCService) {
		RCService = rCService;
	}
	
	
	
}
