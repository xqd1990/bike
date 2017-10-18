package com.rentbikes.controller.bike.init;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rentbikes.model.Bicycle_Order;
import com.rentbikes.service.basicInfo.IBicycle_CatagoryService;
import com.rentbikes.service.basicInfo.IVenderService;
import com.rentbikes.service.basicInfo.SYUserService;
import com.rentbikes.service.bike.Bicycle_OrderService;

/*
 * 接收客户端购买新车的请求
 */
@Controller
public class BicycleOrderController {
	
	@Autowired
	private Bicycle_OrderService orderService;
	@Autowired
	private SYUserService userService;
	@Autowired
	private IVenderService venderService;
	@Autowired
	private IBicycle_CatagoryService catagoryService;
	
	//显示添加订单的页面
	@RequestMapping("bike/init/showOrder.do")
	public String showOrder(HttpServletRequest request){
		request.setAttribute("orderList", orderService.listUnSure());
		request.setAttribute("userList", userService.listAdminAndBuyer());
		request.setAttribute("venderList", venderService.getAll());
		request.setAttribute("catagoryList", catagoryService.getAll());
		return "/bike/init/orderMain.jsp";
	}
	
	//添加主订单和明细单
	@RequestMapping("bike/init/addOrder.do")
	public String addOrderAndDetail(HttpServletRequest request, Bicycle_Order order){
		if(orderService.addOrder(order)) request.setAttribute("prompt", "创建订单成功");
		else request.setAttribute("prompt", "创建订单成功");
		return "/bike/init/showOrder.do";
	}

	//添加车辆信息
	@RequestMapping("bike/init/addBicycle.do")
	public String addBicycle(HttpServletRequest request, Bicycle_Order order){
		if(orderService.comfirmOrder(order)) request.setAttribute("prompt", "确认操作成功");
		else request.setAttribute("prompt", "确认操作失败");
		return "/bike/init/showOrder.do";
	}
	
	
	public Bicycle_OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(Bicycle_OrderService orderService) {
		this.orderService = orderService;
	}

	public SYUserService getUserService() {
		return userService;
	}

	public void setUserService(SYUserService userService) {
		this.userService = userService;
	}

	public IVenderService getVenderService() {
		return venderService;
	}

	public void setVenderService(IVenderService venderService) {
		this.venderService = venderService;
	}

	public IBicycle_CatagoryService getCatagoryService() {
		return catagoryService;
	}

	public void setCatagoryService(IBicycle_CatagoryService catagoryService) {
		this.catagoryService = catagoryService;
	}
	
}
