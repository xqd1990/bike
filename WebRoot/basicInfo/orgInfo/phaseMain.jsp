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

<title>My JSP 'phaseMain.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/reset.css" />
<link rel="stylesheet" href="css/content.css" />
<style type="text/css">
	body{overflow:scroll; overflow-x:hidden; }
	#main {text-align:center; width:90%; margin-left:5%;}
	#main table {width:100%; text-align:center;}
	form{display:none; width:300px; margin-left:5%; padding-left:20px; background:#AD78B0;}
	form h2{font-weight:40;font-size:20px;}
</style>
<script src="js/jquery-1.8.1.min.js"></script>
<script src="js/part1/phaseMain.js"></script>
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
				else location.href = "basicInfo/orgInfo/showPhases.do?current=" + target;
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
	<div class="public-nav">您当前的位置：<a javascript:void(0)>组织机构信息</a>><a javascript:void(0)>权限管理</a></div>
	<div id="oper" class="public-content">
		<div class="page" style="text-align:left; margin-left:5%;">
			<input type="text" id="search" placeholder="权限名称" /> 
			<input type="button" class="page-btn" value="搜索" onclick="search(0)" /> 
			<input type="button" class="page-btn" value="取消" onclick="location.href='basicInfo/orgInfo/showPhases.do'" />
			<input type="button" class="page-btn" style="background:#AD78B0;width:80px;" id="addPhase" value="新增权限" onclick="addPhase()" />
		</div>
	</div>
	<div id="main" class="public-content">
		<table border="1px solid #fff" cellspacing="0px" class="public-content-cont">
			<tr>
				<th>编号</th>
				<th>权限名称</th>
				<th>请求路径</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${phaseList }" var="phase" varStatus="i">
				<tr
					ondblclick="updatePhase(${phase.phaseId},'${phase.url }','${phase.description }')">
					<td>${i.index + 1 + page.current*page.count}</td>
					<td>${phase.description }</td>
					<td>${phase.url }</td>
					<td class="table-fun">
						<a javascript:void(0) onclick="deletePhase(${phase.phaseId})" >删除</a> 
						<a javascript:void(0) onclick="updatePhase(${phase.phaseId},'${phase.url }','${phase.description }')" >修改</a> 
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
				</select>/<span style="color:red;font-weight:600">${page.total }</span>页 
							共<span style="color:red;font-weight:600">${page.size }</span>条
		</div>
	</div>
	<div class="public-content">
		<form method="post" class="page" style="text-align:left; margin-left:5%;">
				<h2></h2>
				权限名称：<input id="description" name="description" /><br />
				请求路径：<input id="url" name="url" /><br />
				<input type="button" class="page-btn" value="确定" onclick="sub()" /> 
				<input type="button" class="page-btn" value="取消" onclick="cancel()" /><br /> 
				<input id="phaseId" name="phaseId" type="hidden" /><br />
		</form>
	</div>
</body>
</html>
