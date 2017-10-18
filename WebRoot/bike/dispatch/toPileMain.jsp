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
		#sele{display:none;}
		#main{width:100%;}
		#main table{width:70%; text-align:center; margin-top:10px; margin-left:15%;}
	</style>
	<script src="js/jquery-1.8.1.min.js"></script>
	<script src="js/part1/toPileMain.js"></script>
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
				else location.href = "bike/dispatch/showBicycleToPile.do?current=" + target;
			}
		}
		//翻页（快速）
		function jumpTo(){
			var target = $("#targetPage option:selected").html();
			jump(target - 1);
		}
		
		//新车入桩
		function toPile(){
			if($("#station").val() != "选择车点" && $("#pole").val() != "选择车桩"){
				var stationId = $("#station").val();
				var pileId = $("#pile").val();
				location.href="bike/dispatch/toPile.do?bicycle_id=" + i + "&station_id=" + stationId + 
							  "&pile_id=" + pileId + "&user_id=" + ${sessionScope.user.user_id} + 
							  "&operator_time=" + getFullDate();
			}
			else
				alert("请先选择车点和车桩");
		}
		
	</script>
  </head>
  
  <body>
  	<div class="public-nav">您当前的位置：<a javascript:void(0)>车辆业务</a>><a javascript:void(0)>新车入桩</a></div>
  	<div id="sele" class="public-content">
  		<div class="public-content-header"><h3>选择车点车桩</h3></div>
  		<div class="page" style="margin-top:100px;">
	  		<select id="station" onchange="searchPile(this)">
	  			<option>选择车点</option>
	  			<c:forEach items="${stationList }" var="temp">
	  				<option value="${temp.station_id }">${temp.station_name }</option>
	  			</c:forEach>
	  		</select>
	  		<select id="pile" disabled="disabled">
	  			<option>选择车桩</option>
	  		</select>&nbsp;&nbsp;&nbsp;
  			<input type="button" class="page-btn" value="确认" onclick="toPile()"/>
  			<input type="button" class="page-btn" value="取消" onclick="cancel()"/>
  		</div>
  	</div>
	<div id="main" class="public-content">
		<div class="public-content-header"><h3>新车入桩</h3></div>
		<table border="1px solid #fff" cellspacing="0px" class="public-content-cont">
			<tr>
				<th>编号</th>
				<th>车辆编号</th>
				<th>车辆类型</th>
				<th>车辆状态</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${bicycleList}" var="temp" varStatus="i">
				<tr
					ondblclick="selPile(${temp.bicycle_id})">
					<td>${i.index + 1 + page.current*page.count}</td>
					<td>${temp.bicycle_code}</td>
					<td>${temp.catagory_name }
					<td>${temp.status==1?'购入未入桩':bicycle.status }</td>
					<td><input type="button" value="选择车桩"
						onclick="selPile(${temp.bicycle_id})" /> 
					</td>
				</tr>
			</c:forEach>
		</table>
		<div class="page">
			<input type="button" class="page-btn" style="width:80px;" value="首页" onclick="jump(0)" /> 
			<input type="button" class="page-btn" style="width:80px;" value="上一页" onclick="jump(${page.current-1})" /> 
			<input type="button" class="page-btn" style="width:80px;" value="下一页" onclick="jump(${page.current+1})" /> 
			<input type="button" class="page-btn" style="width:80px;" value="末页" onclick="jump(${page.total - 1})" /><br />
			第<span style="color:red;font-weight:600">${page.current + 1 }</span>页 
			<select onchange="jumpTo()" id="targetPage">
				<option></option>
				<c:forEach begin="1" end="${page.total }" varStatus="i">
					<option>${i.index }</option>
				</c:forEach>
			</select>/<span style="color:red;font-weight:600">${page.total==0?1:page.total}</span>页 
						共<span style="color:red;font-weight:600">${page.size}</span>条
		</div>
	</div>
  </body>
</html>

