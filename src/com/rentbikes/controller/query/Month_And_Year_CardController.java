package com.rentbikes.controller.query;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONAwareSerializer;
import com.rentbikes.model.Card;
import com.rentbikes.model.Card_Record;
import com.rentbikes.model.Page;
import com.rentbikes.model.QueryDateMsg;
import com.rentbikes.service.basicInfo.RegisterCardService;
import com.rentbikes.service.query.IBcycle_Query_MonthAndYearService;




//查询出年的的方式
@Controller
public class Month_And_Year_CardController {
	
	
	@Autowired
	private IBcycle_Query_MonthAndYearService ibcycle_Query_MonthAndYear;
	@Autowired
	RegisterCardService RCService;  
	
	
	//查詢本年本月季的信息
	@RequestMapping("/Month_And_Year_Card.do")
	public String getAnswer(Card_Record cardrecord,Model model){
		QueryDateMsg allCardMsg=ibcycle_Query_MonthAndYear.getAllcardMsg(cardrecord);		//得到所有的出租卡的信息
		QueryDateMsg ACardMsg=ibcycle_Query_MonthAndYear.getAcardMsg(cardrecord);			
		model.addAttribute("QallCardMsg", allCardMsg);
		model.addAttribute("AallCardMsg", ACardMsg);
		return "showAllCard.do";
	}

	//分页查询出已经办卡的用户信息
	@RequestMapping(value="/Month_And_Year_CardOne.do",produces="text/json;charset=utf-8")
	@ResponseBody
	public String Month_And_Year_CardOne(Card_Record cardrecord){
		QueryDateMsg oneQueryMsg=ibcycle_Query_MonthAndYear.getOnecardMsg(cardrecord);
		String mesg=JSONObject.toJSONString(oneQueryMsg);
		System.out.println(mesg);
		return mesg;
	}
	
		@RequestMapping("/showAllCard.do")
		public String showCardUser(Page page,Model model){
			if(page==null){
				page = new Page();
			}
			page.setSize(RCService.getpagenum());					//总条数
			page.setTotal(RCService.getAllPage(page));				//总页数
			List<Card> cardlist=RCService.getCard(page);
			model.addAttribute("cardlist", cardlist);
			model.addAttribute("cardpage", page);
			return "query/MonthAndYearSearch.jsp";
		}
		
	//查询出某项的统计信息
		
		
	public IBcycle_Query_MonthAndYearService getIbcycle_Query_MonthAndYear() {
		return ibcycle_Query_MonthAndYear;
	}

	public void setIbcycle_Query_MonthAndYear(
			IBcycle_Query_MonthAndYearService ibcycle_Query_MonthAndYear) {
		this.ibcycle_Query_MonthAndYear = ibcycle_Query_MonthAndYear;
	}
	
	
}
