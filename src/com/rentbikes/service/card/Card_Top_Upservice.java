package com.rentbikes.service.card;

import com.rentbikes.model.Card;
import com.rentbikes.model.Card_Record;
import com.rentbikes.model.SYUser;

public interface Card_Top_Upservice {
		public String card_Top_Up(Card card,SYUser user,Card_Record cr);  //充值卡的方法
		
}
