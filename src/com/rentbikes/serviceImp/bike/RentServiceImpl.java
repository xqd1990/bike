package com.rentbikes.serviceImp.bike;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rentbikes.dao.bike.IRentDao;
import com.rentbikes.dao.basicInfo.IBicycleDao;
import com.rentbikes.exception.RentException;
import com.rentbikes.model.Bicycle_Info;
import com.rentbikes.model.Bicycle_Pile;
import com.rentbikes.model.Bicycle_Record;
import com.rentbikes.model.Bicycle_Station;
import com.rentbikes.model.Card;
import com.rentbikes.model.Card_Info_Record;
import com.rentbikes.model.Page;
import com.rentbikes.model.RentTemp;
import com.rentbikes.model.ReturnTemp;
import com.rentbikes.model.Vender;
import com.rentbikes.service.bike.IRentService;

@Service
public class RentServiceImpl implements IRentService {

	@Autowired
	private IRentDao rentDao;

	// 分页查询车点名称信息
	@Override
	public List<Bicycle_Station> getPage(Page page) throws Exception {
		// 查询数据库总的数据条数
		int size = rentDao.getSize();
		Map<String, Object> map = new HashMap<String, Object>();
		// 调用方法初始化page对象,根据传来的page对象，计算出初始的查询条件，并将start和end放入map中
		init(page, size, map);
		System.out.println("start=" + map.get("start"));
		System.out.println("end=" + map.get("end"));
		List<Bicycle_Station> list = rentDao.getStationName(map);
		return list;
	}

	// 初始化page和map
	public void init(Page page, int size, Map<String, Object> map) {
		page.setSize(size);
		if (size % page.getCount() == 0)
			page.setTotal(size / page.getCount());
		else
			page.setTotal(size / page.getCount() + 1);
		map.put("start", page.getCurrent() * page.getCount() + 1);
		map.put("end", page.getCurrent() * page.getCount() + page.getCount());
		System.out.println("start=" + map.get("start") + "end="
				+ map.get("end"));
	}

	
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<RentTemp> getrentInfo(String station_id) {
		List<RentTemp> list = rentDao.getrentInfo(station_id);
		return list;
	}

	/**
	 * 执行租车之前的所有校验
	 */
	@Override
	public void checkRentCondition(RentTemp renttemp) throws Exception{

		// 1.校验卡信息
		Card card = rentDao.checkCard(renttemp.getCard_code());
		renttemp.setCard_id(card.getCard_id());
		if (card == null) {	throw new RentException("请核对卡余额、卡号，卡状态异常");}		
		
		// 2.检验车辆信息
		Bicycle_Info bici = rentDao.checkBicycle(renttemp.getBicycle_id());
		if (bici == null) {	throw new RentException("车辆状态异常，请联系管理员");}
		// 3.校验卡租车信息
		HashMap map = new HashMap();
		map.put("card_id", card.getCard_id());
		map.put("bicycle_id", renttemp.getBicycle_id());
		int temp = rentDao.checkCardRent(map);
		if (temp != 0) {throw new RentException("该卡目前已经租车，请先还车！");}
	}

	/**
	 * 执行还车之前的所有检验
	 */
	@Override
	public void  checkReturnCondition(RentTemp renttemp)throws Exception {
		Card card = rentDao.checkCardID(renttemp.getCard_code());
		renttemp.setCard_id(card.getCard_id());
		Bicycle_Info bic = null;
		bic = rentDao.isRent(renttemp.getCard_id());
		int ff = 0;
		if (bic != null) {
			ff = bic.getBicycle_id();
		} else {
			throw new RentException("该卡未租车！");
		}
		// 2.校验归还车桩是否为（2：无车）。
		Bicycle_Pile bip = rentDao.checkPile(renttemp.getPile_id());
		if (bip == null) {
			throw new RentException("车桩状态异常，无法还车");
		}
		// 3.计算租车费用（当前时间-租车时间，1个小时之内免费，1-2个小时为1元，2-3个小时为2元，3个小时以上为每小时3元）。
			// 3.1获取当前时间：
			String currentTime = rentDao.getCurrentTime();
			renttemp.setCurrenttime(currentTime);
			// 3.2获取租车时间
			renttemp.setBicycle_id(bic.getBicycle_id() + "");
			String createTime = rentDao.getCreateTimes(renttemp);
			HashMap map = new HashMap();
			map.put("currenttime", currentTime);
			map.put("createTime", createTime);
	
			// 3.3获取用车时间
			int sumTime = rentDao.getRentTime(map);
			// 3.4.计算车费
			double rentFee = 0.0;
				if (sumTime > 0 && sumTime < 60) {rentFee = 0.0;renttemp.setIs_fee('0');};
				if (sumTime >= 60 && sumTime < 120) {rentFee = 1.0;renttemp.setIs_fee('1');};
				if (sumTime >= 120 && sumTime < 180) {rentFee = 2.0;renttemp.setIs_fee('1');};
				if (sumTime > 180) {
					sumTime = sumTime - 180;
					sumTime = sumTime / 60;
					sumTime += 1;
					rentFee = 2.0 + 3 * sumTime;
					renttemp.setIs_fee('1');
				}
			renttemp.setChg_money(rentFee);
			renttemp.setRentfee(0 - rentFee);
			// 3.5.校验卡余额
			double wallet_money = rentDao.getWalletMoney(renttemp.getCard_id());
			if (wallet_money - rentFee < 0) {
				throw new RentException("卡费用不足，无法还车");
			}

	}

	// 执行租车的方法
	@Transactional(propagation = Propagation.REQUIRED)
	public void doRent(RentTemp renttemp) throws Exception{
		Card cc = rentDao.checkCard(renttemp.getCard_code());
		renttemp.setCard_id(cc.getCard_id());
		//查询出本次租车需要的信息：
		Card card = rentDao.selectFrozen_Money(renttemp);
		double transfer_money = 200.00 - card.getFrozen_money();
		double wallet_Money = card.getWallet_money() - transfer_money;
			HashMap map1 = new HashMap();
			map1.put("transfer_money", transfer_money);
			map1.put("wallet_money", wallet_Money);
			map1.put("card_id", renttemp.getCard_id());
			map1.put("fee_type", 3);
			map1.put("chg_frozen_money", transfer_money);
			map1.put("remark", "租车冻结");
			map1.put("bicycle_id", renttemp.getBicycle_id());
			map1.put("rent_pile_id", renttemp.getPile_id());
			map1.put("chg_frozen_money", transfer_money);
			
		// 1.修改车辆状态,车辆信息中租车卡号填写租车卡编号。
		int num1 = rentDao.modifyBicycle_Info(renttemp);
		if (num1 != 1) {throw new RentException("车辆信息修改失败");}
		// 2.将车桩状态改成（2：无车）。
		int num2 = rentDao.modifyBicycle_Pile(renttemp);
		if (num2 != 1) {throw new RentException("车桩状态修改失败");}
		//3.校验卡余额
		if (card.getFrozen_money() < 200.00) {
			// 3.将冻结金额修改成为200,同时变更实际金额
			int num3 = rentDao.modifyTransfer_Money(map1);
			if (num3 != 1) {throw new RentException("冻结金额修改失败");}
		
		// 4.将变动的信息记录在卡费流水表中
		int num4 = rentDao.insertCard_Record(map1);
		if (num4 != 1)  {throw new RentException("冻结金额变动记录失败");	}
		}
		
		// 5.记录信息到租还记录表中增加一条租车记录
		int num5 = rentDao.insertBicycle_Record(map1);
		if (num5 != 1)  {throw new RentException("租还记录表增加失败");}
		
		
		// 5.1查询到record_id
		List<RentTemp> rr = rentDao.selectBicycle_Record(map1);
		map1.put("record_id", rr.get(0).getRecord_id());
		
		// 6.记录车辆业务流水，业务类型为（2：租车）关联的业务记录id为车辆租还记录id，业务名称为（租出），业务卡号为租车卡号，是否发生费用为（0：未发生）。
		int num7 = rentDao.insertBicycle_Deal(map1);
		if (num7 != 1) {throw new RentException("车辆业务流水增加失败");}
	}

	// 执行还车
	@Transactional(propagation = Propagation.REQUIRED)
	public void doReturn(RentTemp renttemp) throws Exception{
		// 1查询出该卡对应的相关信息
		renttemp.setFee_type('2');
		Bicycle_Info bic = rentDao.selectBicycle_Info(renttemp);
		renttemp.setBicycle_id(bic.getBicycle_id() + "");	
		Card_Info_Record card_info = rentDao.checkCard_Info_Record(renttemp.getCard_id());
		renttemp.setUser_id(card_info.getUser_id());
		Card card = rentDao.checkCardID(renttemp.getCard_code());
		renttemp.setCard_id(card.getCard_id());		
		// 1.1修改车辆租还记录（补充信息）
			int num1 = rentDao.modifyBicycle_Record(renttemp);
				if (num1 != 1) {throw new RentException("修改车辆租还失败");}
		// 2.将还车车桩的状态改成（1：有车）
		int num2 = rentDao.modifyBicycle_PileReturn(renttemp);
		if (num2 != 1) {throw new RentException("修改车桩信息失败");}
		// 3.记录信息到流水表
			// 3.1查询本次的record_id
			Bicycle_Record bicc = rentDao.selectRecord_id(renttemp);
			renttemp.setRecord_id(bicc.getRecord_id());
			// 3.2记录信息到流水表
			int num3 = rentDao.insertBicycle_DealReturn(renttemp);
			if (num3 != 1) {throw new RentException("添加记录信息失败");}
		// 4.修改卡内余额。将（可用余额-租车费用）
		int num4 = rentDao.modifyCard(renttemp);
		if (num4 != 1) {throw new RentException("修改卡内余额失败");}
		// 5.清空车辆信息中的租车卡id。
		int num5 = rentDao.deleteInfo(renttemp);
		if (num5 != 1) {throw new RentException("清空车辆信息失败");}

		// 6.将费用计入卡费用流水中，费用类型为2：充值，收支类型为支出，此时将费用计为负数。
		int num6 = rentDao.insertCard_RecordReturn(renttemp);
		if (num6 != 1)  {throw new RentException("添加卡费用流水失败");}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<RentTemp> getreturnInfo(String station_id) {
		List<RentTemp> list = rentDao.getreturnInfo(station_id);
		return list;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public Card checkCardReturn(String card_code) {
		Card card = rentDao.checkCard(card_code);

		return card;
	}

	// 查询开始租车的时间
	@Transactional(propagation = Propagation.SUPPORTS)
	public String getCreateTime(RentTemp renttemp) {
		String createtime = rentDao.getCreateTime(renttemp);
		return createtime;
	}

	// 查询user_id
	@Transactional(propagation = Propagation.SUPPORTS)
	public Card_Info_Record checkCard_Info_Record(int card_id) {
		Card_Info_Record card = rentDao.checkCard_Info_Record(card_id);
		return card;
	}


}
