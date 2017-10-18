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
		body{overflow:scroll; overflow-x:hidden; }
		#main{text-align:center; width:90%; margin-left:5%;}
		#main table{width:100%; text-align:center;}
		form{display:none; width:350px; padding-left:20px; background:#AD78B0;}
		form h2{font-size:20px;}
	</style>
	<script src="js/jquery-1.8.1.min.js"></script>
	<script src="js/part1/roleMain.js"></script>
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
				else location.href = "basicInfo/orgInfo/showRoles.do?current=" + target;
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
  	<div class="public-nav">您当前的位置：<a javascript:void(0)>组织机构信息</a>><a javascript:void(0)>角色管理</a></div>
  	<div id="oper" class="public-content">
  		<div class="page" style="text-align:left; margin-left:5%;">
	  		<input type="text" id="search" placeholder="角色名称"/>
	  		<input type="button" class="page-btn" value="搜索" onclick="search(0)"/>
	  		<input type="button" class="page-btn" value="取消" onclick="location.href='basicInfo/orgInfo/showRoles.do'"/>
	  		<input type="button" class="page-btn" style="background:#AD78B0;width:80px;" id="addRole" value="新增角色" onclick="addRole()"/>
  		</div>
  	</div>
	<div id="main" class="public-content">
		<table border="1px solid #fff" cellspacing="0px" class="public-content-cont">
			<tr>
				<th>编号</th>
				<th>角色名称</th>
				<th>角色说明</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${roleList}" var="role" varStatus="i">
				<tr
					ondblclick="listPermission(${role.role_id},'${role.role_name}','${role.role_describe}')">
					<td>${i.index + 1 + page.current*page.count}</td>
					<td>${role.role_name}</td>
					<td>${role.role_describe}</td>
					<td class="table-fun">
						<a javascript:void(0) onclick="deleteRole(${role.role_id})" >删除</a>
						<a javascript:void(0) onclick="listPermission(${role.role_id},'${role.role_name}','${role.role_describe}')" >修改</a>
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
			</select>/<span style="color:red;font-weight:600">${page.total==0?1:page.total }</span>页 
					共<span style="color:red;font-weight:600">${page.size }</span>条
		</div>
	</div>
	<div class="public-content">
		<form method="post" class="page" style="text-align:left; margin-left:5%;">
			<h2></h2>
			角色名称：<input id="name" name="role_name"/><br/>
			角色描述：<input id="desc" name="role_describe"/><br/>
			<c:forEach items="${allPhase}" var="phase" varStatus="i">
				<input type="checkbox" name="phaseIds" value="${phase.phaseId}"/>${phase.description}&nbsp;
				<c:if test="${(i.index + 1)%3 == 0}"><br/></c:if>
			</c:forEach><br/>
			<input type="button" class="page-btn" value="确认" onclick="sub()"/>
			<input type="button" class="page-btn" value="取消" onclick="cancel()"/><br/>
			<input id="id" name="role_id" type="hidden"/><br/>
		</form>
	</div>
  </body>
</html>
