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
    
    <title>My JSP 'pileInMain.jsp' starting page</title>
    
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
				else location.href = "bike/fix/showPileOut.do" + target;
			}
		}
		//翻页（快速）
		function jumpTo(){
			var target = $("#targetPage option:selected").html();
			jump(target - 1);
		}
		function fixOut(bid,pid){
			$("#bicycleid").val(bid);
			$("#pid").val(pid);
			$("form").attr("action","bike/fix/FixOut.do");
			$("form").submit();
		}
	//显示信息
		function showmessage(message){
			alert(message);
		}
	</script>
  </head>
  
  <c:if test="${message != null }">
  	<body onload="showmessage('${message}')">
  </c:if>
  <c:if test="${message == null }">
  	<body>
  </c:if>
	<div class="public-nav">您当前的位置：<a javascript:void(0)>车辆维修调出管理</a>><a javascript:void(0)>需要维修调出的${BStation.station_name }车点的车桩信息</a></div>
    <div id="main" class="public-content">
  		<table border="1px solid #fff" cellspacing="0px" class="public-content-cont">
	<tr>
		<th>编号</th>
		<th>车桩编号</th>
		<th>车桩状态</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${pileList }" var="pile" varStatus="i">
		<tr>
			<td>${i.index + 1 + page.current*page.count}</td>
			<td>${pile.pile_code }</td>
			<c:if test="${pile.status ==1 }">
			<td>有车</td>
			<td class="page"><input class="page-btn" style="width:80px"type="button" value="维修调出"
				onclick="fixOut(${pile.bicycle_id },${pile.pile_id })" /></td>
			</c:if>
			<c:if test="${pile.status ==9 }">
			<td>已报废</td>
			<td></td>
			</c:if>
			<c:if test="${pile.status ==2 }">
			<td>无车</td>
			<td></td>
			</c:if>
		</tr>
	</c:forEach>
	</table>
  	<br/><div class="page"><input class="page-btn" style="width:80px;" type="button" value="首页" onclick="jump(0)" /> <input
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
		<form method="post">
		车辆序列号：<input id="bicycleid" name="bicycle_id" readonly/><br/>
		车桩序列号：<input name="pile_id" id="pid" readonly/><br/>
		用户编号：<input name="user_id" value="${user.user_id }" readonly/><br/>
		车点编号：<input  name="station_id" value="${BStation.station_id }" readonly/><br/>
		车点车桩数：<input  name="bicycle_pile_num" value="${BStation.bicycle_pile_num }" readonly/><br/>
		车点名字：<input  name="station_name" value="${BStation.station_name }" readonly/><br/>
	</form>
  </body>
</html>
