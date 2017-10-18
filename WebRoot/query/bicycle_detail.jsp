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
	
			<div class="page">
				<input class="page-btn" style="width:100px" type="button"
					 onclick="window.location.href='query/annualbicyclecost.jsp'" value="返回">
			</div>
			
				<div class="clearfix"></div>
				 <div class="public-content-cont two-col">
				<table class="public-cont-table col-2">
					<tr>
		
						<th style="width:9%">年度/月度信息</th>
						<th style="width:9%">车辆使用时间</th>
						
						<th style="width:9%">借还次数</th>
						<th style="width:9%">修理次数</th>
						<th style="width:9%">调度次数</th>
						<th style="width:9%">总借还时间</th>
						<th style="width:9%">平均借还时间</th>						
						<th style="width:9%">总借车收入费用</th>
						<th style="width:9%">平均借车收入费用</th>
						<th style="width:9%">平均修理费用</th>
						<th style="width:9%">总修理费</th>
					</tr>				
						<tr>
						<c:if test="${not empty BicycleInfo}">
							<td></td>
							<td>${BicycleInfo.BicycleAge}</td>
							
							<td>${BicycleInfo.BicycleUsetimes}</td>
							
							<td>${BicycleInfo.BicycleRepairtimes}</td>
							<td>${BicycleInfo.BicycleDeploytimes}</td>
							<td>${BicycleInfo.sumTime}</td>
							<td>${BicycleInfo.BicycleAvgUseTime}</td>
							<td>${BicycleInfo.BicycleSumFee}</td>
							<td>${BicycleInfo.BicycleAvgFee}</td>
							<td>${BicycleInfo.BicycleAvgRepairFee}</td>
							<td>${BicycleInfo.BicycleSumRepairFee}</td>
							</c:if>
						</tr>
			
						
						
				
				</table>
				</div> 
		</div>
	</div>
</body>
</html>