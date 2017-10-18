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
				else location.href = "bike/fix/showPileIn.do?current=" + target;
			}
		}
		//翻页（快速）
		function jumpTo(){
			var target = $("#targetPage option:selected").html();
			jump(target - 1);
		}
		function fixin(id){
			$("#pileid").val(id);
			$("form").attr("action","bike/fix/FixIn.do");
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
	<div class="public-nav">您当前的位置：<a javascript:void(0)>车辆维修调入管理</a>><a javascript:void(0)>需要维修调入的自行车编号${BInfo.bicycle_code }</a>><a javascript:void(0)>维修调入的${BStation.station_name }车点的车桩信息</a></div>
    <div id="main" class="public-content">
  		<table border="1px solid #fff" cellspacing="0px"class="public-content-cont">
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
			<td></td>
			</c:if>
			<c:if test="${pile.status ==9 }">
			<td>已报废</td>
			<td></td>
			</c:if>
			<c:if test="${pile.status ==2 }">
			<td>无车</td>
			<td class="page"><input class="page-btn" style="width:80px;"  type="button"
			value="维修调入"
				onclick="fixin(${pile.pile_id })" />
			</td>
			</c:if>
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
		<form method="post">
		车辆序列号：<input value="${BInfo.bicycle_id }"" name="bicycle_id" readonly/><br/>
		 车桩序列号：<input name="pile_id" id="pileid" readonly/><br/>
		 用户编号：<input name="user_id" value="${user.user_id }" readonly/><br/>
		</form>
  </body>
  
</html>
