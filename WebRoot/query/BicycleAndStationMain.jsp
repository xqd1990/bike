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
    
    <title>My JSP 'roleMain.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/reset.css" />
	<link rel="stylesheet" href="css/content.css" />
	<style type="text/css">
		#main{text-align:center; width:90%; margin-left:5%;}
		#main table,#pile table{width:100%; text-align:center; margin-top:10px;}
		#pile{display:none; text-align:center; width:90%; margin-left:5%;}
		#bike{display:none;}
	</style>
	<script src="js/jquery-1.8.1.min.js"></script>
	<script src="js/part1/BicycleAndStationMain.js"></script>
	<script type="text/javascript">
		$(function(){
			<c:if test="${not empty prompt}">
				alert("${prompt}");
			</c:if>
		});
	
		//翻页
		function jump(target){
			var total = ${page.total};
			var current = ${page.current};
			if(target >= 0 && target < total){
				if(current == target && (current == 0 || current == total - 1)){}
				else location.href = "basicInfo/orgInfo/showUsers.do?current=" + target;
			}
		}
		//翻页（快速）
		function jumpTo(){
			var target = $("#targetPage option:selected").html();
			jump(target - 1);
		}
	</script>
  </head>
  
  <body>
  	<div class="public-nav">您当前的位置：<a javascript:void(0)>查询统计</a>><a javascript:void(0)>车点信息</a></div>
  	<div id="sele" class="public-content">
  		<div class="public-content-header"><h3>选择车点车桩</h3></div>
  		<div class="page" style="text-align:left; margin-left:5%;">
	  		<input type="text" id="stationName" placeholder="车点名称"/>
	  		<input type="text" id="address" placeholder="车点地址"/>
	  		<input type="button" class="page-btn" value="查询" onclick="searchStation(0)"/>
	  		<input type="button" class="page-btn" value="取消" onclick="location.href='query/showStation.do'"/>
  		</div>
  	</div>
	<div id="main" class="public-content">
		<table border="1px solid #fff" cellspacing="0px" class="public-content-cont">
			<tr>
				<th>编号</th>
				<th>车点编号</th>
				<th>车点名称</th>
				<th>车点地址</th>
				<th>车桩数量</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${stationList}" var="temp" varStatus="i">
				<tr
					ondblclick="showPile(${temp.station_id})">
					<td>${i.index + 1 + page.current*page.count}</td>
					<td>${temp.station_code}</td>
					<td>${temp.station_name}</td>
					<td>${temp.address}</td>
					<td>${temp.bicycle_pile_num}</td>
					<td class="table-fun"><a javascript:void(0) onclick="showPile(${temp.station_id})" >查看</a></td>
				</tr>
			</c:forEach>
		</table>
		<div class="page">
			<input type="button" class="page-btn" style="width:80px;" value="首页" onclick="jump(0)" /> 
			<input type="button" class="page-btn" style="width:80px;" value="上一页" onclick="jump(${page.current-1})" />
			<input type="button" class="page-btn" style="width:80px;" value="下一页" onclick="jump(${page.current+1})" /> 
			<input type="button" class="page-btn" style="width:80px;" value="末页" onclick="jump(${page.total - 1})" /><br/>
			第<span style="color:red;font-weight:600">${page.current + 1 }</span>页
			<select onchange="jumpTo()" id="targetPage">
				<option></option>
				<c:forEach begin="1" end="${page.total }" varStatus="i">
					<option>${i.index }</option>
				</c:forEach>
			</select>/<span style="color:red;font-weight:600">${page.total==0?1:page.total }</span>页 
							共<span style="color:red;font-weight:600">${page.size }</span>条
		</div>
	</div>
	<div id="pile" class="public-content"></div>
	<div id="bicycle"></div>
  </body>
</html>

