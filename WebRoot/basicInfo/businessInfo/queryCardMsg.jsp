<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>卡信息查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script language="javascript" type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
	<script language="javascript" src="js/basicinfo/queryCardMsg.js" type="text/javascript"></script>
	<link rel="stylesheet" href="css/content.css" type="text/css"/>
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
  </head>
  
  <body>
   <div class="container">
		<div class="public-nav">您当前的位置：<a href="basicInfo/businessInfo/queryCardMsg.jsp">卡查询统计</a></div>
		<div class="public-content">
		<input type="hidden" id="username" value="test"/>
			<div class="public-content-header">
				<h3>卡信息查询</h3>
			</div>
			<table class="public-cont-table" >
			<tr><td>
			卡号：<input type="text" placeholder="请输入要查询的卡号" width="20" id="qcardcode"/>
			</td>
			<td>
			姓名：<input type="text" placeholder="请输入要查询的姓名" width="20" id="qcardname"/>
			</td>
			<td>
			身份证号：<input type="text" placeholder="请输入要查询的证件号" width="20" id="qcardidcard"/>
			</td>
			<td>
			<input type="button" value="查詢" id="qureyCardmsg"/>
			</td>
			</tr>
			</table>
			<div class="public-content-cont" id="qureytable">
				<table class="public-cont-table" id="querytebale1" >
					<tr>
						<th >编号</th>
						<th >卡号</th>
						<th >卡类型</th>
						<th style="width: 4%">姓名</th>
						<th >身份证号</th>
						<th >性别</th>
						<th >联系电话</th>
						<th >手机</th>
						<th >邮箱</th>
						<th >住址</th>
						<th >工作单位</th>
						<th >注销信息</th>
						<th >月票金额</th>
						<th >冻结金额</th>
						<th >钱包余额</th>
						<th >卡状态</th>
						<th >相关操作</th>
					</tr>
				<c:forEach items="${queryCardlist}" var="temp">
					<tr class="dbltr">
						<td>${temp.card_id}</td>
						<td>${temp.card_code}</td>
						<td>${temp.card_type_st}</td>
						<td>${temp.name}</td>
						<td>${temp.idcard}</td>
						<td>${temp.sex_st}</td>
						<td>${temp.telphone}</td>
						<td>${temp.mobile}</td>
						<td>${temp.email}</td>
						<td>${temp.address}</td>
						<td>${temp.work}</td>
						<td>${temp.zxbj_st}</td>
						<td>${temp.monthly_money}</td>
						<td>${temp.frozen_money}</td>
						<td>${temp.wallet_money}</td>
						<td>${temp.status_st}</td>
						<td>
							<div class="table-fun">
		        			<input type="button" value="修改" id="upcard${temp.card_id}"  onclick="updatecard(this)"/> 
							<input type="button" value="挂失" onclick="losscard(this)"/>
							<input type="button" value="注銷" onclick="logoutcard(this)"/> 	
							</div>
						</td>
						<c:if test="${temp.zxbj==1}">
								<script type="text/javascript">
									$("#upcard${temp.card_id}").attr("disabled","disabled");
								</script>
							</c:if>
					</tr>
					</c:forEach>
					
				</table>
				
			</div>
		</div>
	</div>
	
	
		<script  type="text/javascript">
		<c:if test="${ not empty queryCardlist}">
		$("#qureytable").show();
		</c:if>
		
	</script>
	
  </body>
</html>
