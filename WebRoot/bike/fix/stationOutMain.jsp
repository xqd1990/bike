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
    
    <title>My JSP 'stationInMain.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/fix.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="css/reset.css" />
	<link rel="stylesheet" href="css/content.css" />
	<script src="js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript">
		//翻页
		function jump(target){
			var total = ${page.total};
			var current = ${page.current};
			if(target >= 0 && target < total){
				if(current == target && (current == 0 || current == total - 1)){}
				else {location.href = "bike/fix/showStatOut.do?current=" + target;}}}
		//翻页（快速）
		function jumpTo(){
			var target = $("#targetPage option:selected").html();
			jump(target - 1);}
		//模糊查询翻页（快速）
		function searchTo(){
			var target = $("#targetPage option:selected").html();
			search(target - 1);}
		//查看车桩表单
		function showpile(stid,num,name){
			$("#stid").val(stid);
			$("#num").val(num);
			$("#stationname").val(name);
			$("form").attr("action","bike/fix/showPileOut.do");
			$("form").submit();}
		//模糊查询
		function search(target){
			var name = $("#sname").val();
			var address=$("#saddress").val();
			var total = ${page.total};
			var current = ${page.current};
			if(target >= 0 && target < total){
				if(current == target && (current == 0 || current == total - 1)){}
				else{var map = {"station_name":name,"address":address,"current":target};
				$.post("bike/fix/showStatOutSearch.do",map,function(data){
					$("#main").html(data);
				});}}}
	//显示信息
		function showmessage(message){
			alert(message);}
	</script>
  </head>
  
  <c:if test="${message != null }">
  	<body onload="showmessage('${message}')">
  </c:if>
  <c:if test="${message == null }">
  	<body>
  </c:if>
	<div class="public-nav">您当前的位置：<a javascript:void(0)>车辆维修调出管理</a>><a javascript:void(0)>需要维修调出的车点列表</a></div>
    <div id="oper" class="public-content">
    <div class="page" style="text-align:left; margin-left:5%;">
  		<input type="text" id="sname" placeholder="车点名字"/>
  		<input type="text" id="saddress" placeholder="车点地址"/>
  		<input type="button" class="page-btn" value="搜索" onclick="search(0)"/>
  		<input type="button" class="page-btn" value="取消" onclick="location.href='bike/fix/showStatOut.do'"/>
  	</div>
  	</div>
  	<div id="main"class="public-content">
  		<table border="1px solid #fff" cellspacing="0px"class="public-content-cont">
	<tr>
		<th>编号</th>
		<th>车点编号</th>
		<th>车点名字</th>
		<th>车点地址</th>
		<th>车桩数量</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${stationList }" var="station" varStatus="i">
		<tr
			ondblclick="showpile(${station.station_id },${station.bicycle_pile_num },'${station.station_name }')">
			<td>${i.index + 1 + page.current*page.count}</td>
			<td>${station.station_code }</td>
			<td>${station.station_name }</td>
			<td>${station.address }</td>
			<td>${station.bicycle_pile_num }</td>
			<td class="page"><input class="page-btn" type="button" style="width:80px;"value="查看车桩"
				onclick="showpile(${station.station_id },${station.bicycle_pile_num },'${station.station_name }')" />
				
			</td>
		</tr>
	</c:forEach>
	</table>
  	<br/> <div class="page"><input class="page-btn" style="width:80px;" type="button" value="首页" onclick="jump(0)" /> <input
			class="page-btn" style="width:80px;" type="button" value="上一页" onclick="jump(${page.current-1})" /> <input
			class="page-btn" style="width:80px;" type="button" value="下一页" onclick="jump(${page.current+1})" /> <input
			class="page-btn" style="width:80px;" type="button" value="末页" onclick="jump(${page.total - 1})" /><br />
		<br /><select onchange="jumpTo()"
			id="targetPage">
			<option>${page.current + 1 }</option>
			<c:forEach begin="1" end="${page.total }" varStatus="i">
				<option>${i.index }</option>
			</c:forEach>
		</select>/<span style="color:red;font-weight:600">${page.total }</span>页 共<span style="color:red;font-weight:600">${page.size }</span>条
		</div>
		</div>
  </body>
  <form method="post">
		车点编号：<input id="stid" name="station_id" readonly/><br/>
		车点车桩数：<input id="num" name="bicycle_pile_num" readonly/><br/>
		车点名字：<input id="stationname" name="station_name" readonly/><br/>
	</form>
</html>
