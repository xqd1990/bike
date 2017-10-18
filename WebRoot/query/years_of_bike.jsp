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
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="css/reset.css"/>
	<link rel="stylesheet" href="css/content.css"/>

	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript">
	$(function(){
	
			<c:if test="${not empty error}">
				alert("${error}");
			</c:if>
	})
   
		

	</script>
</head>
  
  
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">信息列表</a></div>
		<div class="public-content">
			<div class="public-content-header">
			<!-- <h5 style="display: inline-block;"><input type="button" value="返回" onclick="window.location.href='query/annualbicyclecost.jsp'"></h5>
			 -->	
		
				<div id="oper" class="public-content">
					<div class="page">
						<input class="page-btn" style="width:100px" type="button" onclick="window.location.href='query/annualbicyclecost.jsp'" value="返回">
					</div>
				</div>
			</div>
				<div class="clearfix"></div>
				 <div class="public-content-cont two-col">
					<table class="public-cont-table col-2">
					<tr>
		
						<th>年度/月度信息</th>
						<th>车辆数量</th>
						<th>借还次数</th>
						<th>修理次数</th>
						<th>平均借还时间(min)</th>						
						<th>总借还时间(min)</th>
						<th>平均借车收入费用</th>
						<th>总借车收入费用</th>
						<th>平均修理费用</th>
						<th>总修理费</th>
					</tr>
					
					
						<tr><td></td>
							<td>${bicycleNum}</td>
							<td>${frequencyByYear}</td>
							<td>${repairByYear}</td>
							<td>${avgUseTimeByYear}</td>
							<td>${sumUseTimeByYear}</td>
							<td>${avgMoneyByYear}</td>
							<td>${sumMoneyByYear}</td>
							<td>${avgRepairByYear}</td>
							<td>${sumRepairByYear}</td>
						</tr>
			
						
						
				
				</table>
				</div> 
		</div>
	</div>

</body>
</html>