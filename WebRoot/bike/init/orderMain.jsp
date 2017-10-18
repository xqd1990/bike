<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'addOrder.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link type="text/css" href="date/css/jquery-ui-1.8.17.custom.css" rel="stylesheet" />
	<link type="text/css" href="date/css/jquery-ui-timepicker-addon.css" rel="stylesheet" />
	<link rel="stylesheet" href="css/reset.css" />
	<link rel="stylesheet" href="css/content.css" />
	<style type="text/css">
		form {margin-top:50px; display:none;}
		table{width:98%; text-align:center; margin-left:1%; margin-top:10px;}
		image{width:12px; height:12px;}
		#chDiv{display:none;width:60%;}
	</style>
	<script src="js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="date/js/jquery-ui-1.8.17.custom.min.js"></script>
	<script type="text/javascript" src="date/js/jquery-ui-timepicker-addon.js"></script>
	<script type="text/javascript" src="date/js/jquery-ui-timepicker-zh-CN.js"></script>
	<script src="js/part1/orderMain.js"></script>
	<script type="text/javascript">
		$(function(){
			<c:if test="${not empty prompt}">
				alert("${prompt}");
			</c:if>
			init();
		});
		function init(){
			$(".ui_timepicker").datetimepicker({
	            showOn: "button",
	            buttonImage: "images/icon.png",
	            buttonImageOnly: true,
	            /* showSecond: true, */
	            timeFormat: ' ',
	            /* stepHour: 1,
	            stepMinute: 1,
	            stepSecond: 1 */
        	});
		}
		
		//提交
		function sub(){
			var buyNum = $("#buy_num").val();
			var buyDate = $("#buy_date").val();
			var invoiceNo = $("#invoice_no").val();
			var venderId = $("#vender_id").val();
			var personInCharge = $("#person_in_charge").val();
			if(parseInt(buyNum) > 0){
				if(buyDate != "" && invoiceNo != "" && venderId != "" && personInCharge != ""){
					var types = $("#t1 tr:gt(0)").find("input:eq(0)");
					var dates = $("#t1 tr:gt(0)").find("input:eq(1)");
					var prices = $("#t1 tr:gt(0)").find("input:eq(3)");
					for(var i = 0;i < types.length;i++){
						if(types.eq(i).val() == "" || dates.eq(i).val() == "" || parseFloat(prices.eq(i).val()) == 0){
							alert("请填写明细信息");
							return;
						}
					}
					$("#order_code").val(createDate());
					$("#operator_time").val(getFullDate());
					$("#user_id").val(${user.user_id});
					$("form").submit();
				}else{
					alert("请填写主单明细");
				}
			}else{
				alert("请添加车辆明细");
			}
		}
		
		//确认录入
		function sure(orderId,buyNum){
			if(confirm("确认录入吗？")){
				$("#buy_num").val(buyNum);
				$("#order_id").val(orderId);
				$("#user_id").val(${user.user_id});
				$("#operator_time").val(getFullDate());
				$("form").attr("action","bike/init/addBicycle.do");
				$("form").submit();
			}
		}
	</script>
  </head>
  
  <body onload="init()">
  	<div class="public-nav">您当前的位置：<a javascript:void(0)>车辆业务</a>><a javascript:void(0)>车辆购入</a></div>
  	<!-- 显示购买单 -->
	<div id="main" class="public-content">
		<div class="public-content-header"><h3>订单确认</h3></div>
		<table border="solid 1px" cellspacing="0px" class="public-content-cont">
			<tr>
				<th>订单编号</th>
				<th>供应商</th>
				<th>车辆数量</th>
				<th>购入金额</th>
				<th>购入负责人</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${orderList }" var="order">
				<tr>
					<td>${order.order_code }</td>
					<td>
						<c:forEach items="${venderList}" var="temp">
							<c:if test="${order.vender_id == temp.vender_id }">
								${temp.vender_name}
							</c:if>
						</c:forEach>
					</td>
					<td>${order.buy_num }</td>
					<td>${order.buy_price }</td>
					<td>
						<c:forEach items="${userList}" var="temp">
							<c:if test="${order.person_in_charge == temp.user_id }">
								${temp.username}
							</c:if>
						</c:forEach>
					</td>
					<td class="table-fun"><a javascript:void(0) onclick="sure(${order.order_id},${order.buy_num })">确认</a></td>
					<%-- <input type="button" value="确认" onclick="sure(${order.order_id},${order.buy_num })"/> --%>
				</tr>
			</c:forEach>
		</table>
		<div style="text-align:center; margin-top:10px;" class="page">
			<input type="button" value="新建订单" class="page-btn" style="width:100px;" onclick="buy()"/>
		</div>
	</div>
	
  	<!-- 新建购买单 -->
    <form method="post" action="bike/init/addOrder.do" class="public-content">
    	<div class="public-content-header"><h3>创建订单</h3></div>
    	<div style="margin-top:30px; margin-left:1%;">
    	供应商：<select name="vender_id">
    		<option></option>
    		<c:forEach items="${venderList }" var="temp">
    			<option value="${temp.vender_id }">${temp.vender_name }</option>
    		</c:forEach>
    	</select>&nbsp;&nbsp;&nbsp;
    	购入负责人：<select name="person_in_charge">
    		<option></option>
    		<c:forEach items="${userList }" var="temp">
    			<option value="${temp.user_id }">${temp.username }</option>
    		</c:forEach>
    	</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	
    	<input type="hidden" name="order_id" id="order_id"/>
    	<input type="hidden" name="order_code" id="order_code"/>
    	<input type="hidden" name="user_id" id="user_id"/>
    	<input type="hidden" name="operator_time" id="operator_time"/>
    	
    	购买日期：<input id="buy_date" name="buy_date" class="ui_timepicker" readonly="readonly"/>&nbsp;&nbsp;&nbsp;
    	购买数量：<input id="buy_num" name="buy_num" value=1 readonly="readonly"/>&nbsp;&nbsp;&nbsp;
    	订单总价：<input id="buy_price" name="buy_price" value=0 readonly="readonly"/>&nbsp;&nbsp;&nbsp;
    	发票号码：<input id="invoice_no" name="invoice_no"/><br/>
    	备&nbsp;注：<input id="remark" style="margin-top:5px;" name="remark"/><br/>
    	</div>
    	<table id="t1" border="solid 1px" cellspacing="0px" class="public-content-cont">
    		<tr>
    			<th>序号</th>
    			<th>类型编号</th>
    			<th>出厂日期</th>
    			<th>批次号</th>
    			<th>价格</th>
    			<th>备注</th>
    			<th>操作</th>
    		</tr>
    		<tr>
    			<td>1</td>
    			<td><input readOnly="readonly"/><img src="images/icon.png" onclick="showChoice(this)"/><input type="hidden" name="details[0].catagory_id"/></td>
    			<td><input name="details[0].create_date" class="ui_timepicker" readonly="readonly" onchange="createBatchNo(this,0)"/></td>
    			<td><input name="details[0].batch_no" readonly="readonly"/></td>
    			<td><input name="details[0].price" value=0 onblur="checkPrice(this)"/></td>
    			<td><input name="details[0].remark"/></td>
    			<td><img src="images/delete.gif" onclick="deleteRow(this)"/></td>
    		</tr>
    	</table><br/>
    	<div align="center" class="page">
    		<input type="button" value="录入" class="page-btn" onclick="sub()"/>
    		<input type="reset" class="page-btn" value="重置"/>
    		<input type="button" class="page-btn" style="background:#52afb7;width:70px;" value="添加明细" onclick="addRow()"/>
    		<input type="button" class="page-btn" style="background:#52afb7;width:70px;" value="返回列表" onclick="back()"/>
    	</div>
    </form>
    <div id="chDiv" class="public-content">
    	<div class="public-content-header"><h3>选择车辆</h3></div>
    	<table id="t2" width="100%" border="1px solid" cellspacing="0" class="public-content-cont">
			<tr>
				<th class="title1">选择</th>
				<th class="title1">车辆类型</th>
				<th class="title1">车高</th>
				<th>车长</th>
				<th class="title1">车重/kg</th>
			</tr>
			<c:forEach items="${catagoryList }" var="temp">
				<tr align="center" onclick="choiceBike(this, ${temp.catagory_id})">
					<td>&nbsp;</td>
					<td>${temp.catagory_name }</td>
					<td>${temp.height }</td>
					<td>${temp.length }</td>
					<td>${temp.weight }</td>
				</tr>
			</c:forEach>
		</table>
		<div class="page">
    		<input type="button" class="page-btn" value="确定" onclick="subChoice()"/>
   	 		<input type="button" class="page-btn" value="取消" onclick="hideChoice()"/>
   	 	</div>
	</div>
  </body>
</html>
