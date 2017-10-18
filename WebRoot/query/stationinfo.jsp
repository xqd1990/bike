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
    
    <title>My JSP 'stationinfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/fix.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="css/reset.css" />
	<link rel="stylesheet" href="css/content.css" />
  </head>
  
  <body>
	<div class="public-nav">您当前的位置：<a javascript:void(0)>车点信息统计</a>><a javascript:void(0)>车点列表</a>><a javascript:void(0)>${BSSI.station_name}车点的信息统计</a></div>
    <div id="main" class="public-content">
  		<table border="1px solid #fff" cellspacing="0px"class="public-content-cont">
    	<tr>
    		<th>选项</th>
    		<th>信息</th>
    	</tr>
    	<tr><td>车点编号:</td><td>${BSSI.station_code}</td></tr>
		<tr><td>车点名字:</td><td>${BSSI.station_name}</td></tr>
		<tr><td>车点地址:</td><td>${BSSI.address}</td></tr>
		<tr><td>车点车桩数:</td><td>${BSSI.bicycle_pile_num }</td></tr>
		<tr><td>车点建立时间:</td><td>${BSSI.build_time }</td></tr>
		<tr><td>日均车辆还入数:</td><td>${BSSI.pin}</td></tr>
		<tr><td>日均车辆借出数:</td><td>${BSSI.pout }</td></tr>
		<tr><td>日均车辆调入数:</td><td>${BSSI.rentin }</td></tr>
		<tr><td>日均车辆调出数:</td><td>${BSSI.rentout}</td></tr>
		</table>
		<div  class="page"><input  class="page-btn" style="width:160px;"type="button" value="返回车点查看" onclick="location.href='query/showStat.do'" /></div>
	</div>
  </body>
</html>
