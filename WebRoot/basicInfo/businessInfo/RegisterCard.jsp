<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'RegisterCard.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/content.css" />
	<script language="javascript" type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
	<script language="javascript" src="js/basicinfo/RegisterCard.js" type="text/javascript"></script>
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <div class="container">
		<div class="public-nav">您当前的位置：基础信息维护>业务基础信息><a href="showCardUser.do">办卡以及查询</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>办卡以及查询管理</h3>
			</div>
			<div class="public-content-cont">
			<input type="button" value="新增" id="inputnewCardMsg"/>
			<input type="button" value="查詢" id="qureyCardmsg"/>
			<input type="hidden" id="username" value="test"/>
				<table class="public-cont-table">
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
				<c:forEach items="${cardlist}" var="temp">
					<tr>
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
						<script type="text/javascript">
					<c:if test="${not empty logoutcardmsg}">
							window.alert("${logoutcardmsg}");
					</c:if>
					<c:if test="${not empty losscardmsg}">
							window.alert("${losscardmsg}");
					</c:if>
						</script>
				</table>
				<div class="page">
					<form action="" method="get">
						<a href="showCardUser.do?current=0">首页</a>
						<a href="showCardUser.do?current=${cardpage.current-1}">上一页</a>
						<a href="showCardUser.do?current=${cardpage.current+1}">下一页</a>
						第<span style="color:red;font-weight:600"><input type="text" class="page-input" id="current1" value="${cardpage.current+1}"></span>页
						共<span style="color:red;font-weight:600">${cardpage.total}</span>页
						<input type="button" class="page-btn" value="跳转" onclick="jumpTorcd()"/>
					</form>
				</div>
			</div>
		</div>
	</div>
	
  </body>
</html>
