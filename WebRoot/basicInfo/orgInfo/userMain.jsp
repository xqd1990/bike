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
		form{display:none; width:400px; margin-left:5%; padding-left:20px; background:#AD78B0;}
		form h2{font-size:20px;}
	</style>
	<script src="js/jquery-1.8.1.min.js"></script>
	<script src="js/part1/userMain.js"></script>
	<script type="text/javascript">
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
		$(function(){
			<c:if test="${not empty prompt}">
				alert("${prompt}");
			</c:if>
		});
	</script>

  </head>
  
  <body>
  	<div class="public-nav">您当前的位置：<a javascript:void(0)>组织机构信息</a>><a javascript:void(0)>人员管理</a></div>
  	<div id="oper" class="public-content">
  		<div class="page" style="text-align:left; margin-left:5%;">
	  		<input type="text" id="sLogin_name" placeholder="账号名称"/>
	  		<input type="text" id="sUsername" placeholder="姓名名称"/>
	  		<select id="sRole_name">
	  			<option></option>
	  			<c:forEach items="${allRoles}" var="role">
	  				<option>${role.role_name }</option>
	  			</c:forEach>
	  		</select>
	  		<input type="button" class="page-btn" value="搜索" onclick="search(0)"/>
	  		<input type="button" class="page-btn" value="取消" onclick="location.href='basicInfo/orgInfo/showUsers.do'"/>
	  		<input type="button" class="page-btn" style="background:#AD78B0;width:80px;" id="addUser" value="新增角色" onclick="addUser()"/>
  		</div>
  	</div>
	<div id="main" class="public-content">
		<table border="1px solid #fff" cellspacing="0px" class="public-content-cont">
			<tr>
				<th>编号</th>
				<th>账号</th>
				<th>密码</th>
				<th>姓名</th>
				<th>角色</th>
				<th>固定电话</th>
				<th>移动电话</th>
				<th>电子邮件</th>
				<!-- <th>激活标志</th> -->
				<th>操作</th>
			</tr>
			<c:forEach items="${userList}" var="user" varStatus="i">
				<tr
					ondblclick="updateUser(${user.user_id},${user.role_id},'${user.login_name}','${user.username}','${user.password}',
											'${user.office_phone}','${user.mobile_phone}','${user.email}')">
					<td>${i.index + 1 + page.current*page.count}</td>
					<td>${user.login_name}</td>
					<td>${user.password}</td>
					<td>${user.username}</td>
					<td><c:forEach items="${allRoles }" var="role">
							<c:if test="${role.role_id == user.role_id }">${role.role_name }</c:if>
						</c:forEach></td>
					<td>${user.office_phone}</td>
					<td>${user.mobile_phone}</td>
					<td>${user.email}</td>
					<td class="table-fun">
						<a javascript:void(0) onclick="romoveUser(${user.user_id})" >注销</a>
						<a javascript:void(0) onclick="updateUser(${user.user_id},${user.role_id},'${user.login_name}','${user.username}','${user.password}',
											'${user.office_phone}','${user.mobile_phone}','${user.email}')" >修改</a>
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
		<%-- <br /> <input type="button" value="首页" onclick="jump(0)" /> <input
			type="button" value="上一页" onclick="jump(${page.current-1})" /> <input
			type="button" value="下一页" onclick="jump(${page.current+1})" /> <input
			type="button" value="末页" onclick="jump(${page.total - 1})" /><br />
		<br /> 第${page.current + 1 }页 <select onchange="jumpTo()"
			id="targetPage">
			<option></option>
			<c:forEach begin="1" end="${page.total }" varStatus="i">
				<option>${i.index }</option>
			</c:forEach>
		</select>/${page.total==0?1:page.total }页 共${page.size }条 --%>
	</div>
	<div class="public-content">
		<form method="post" class="page" style="text-align:left; margin-left:5%;">
			<h2></h2>
			账&nbsp;&nbsp;号：<input id="login_name" name="login_name" placeholder="必填项" onkeyup="checkAccount()"/><span id="prom"></span><br/>
			密&nbsp;&nbsp;码：<input id="password" name="password" placeholder="必填项"/><br/>
			姓&nbsp;&nbsp;名：<input id="username" name="username" placeholder="必填项"/><br/>
			固定电话：<input id="office_phone" name="office_phone"/><br/>
			移动电话：<input id="mobile_phone" name="mobile_phone"/><br/>
			电子邮件：<input id="email" name="email"/><br/>
			选择角色：
			<c:forEach items="${allRoles}" var="role" varStatus="i">
				<input type="radio" id="${role.role_id}" name="role_id" value="${role.role_id}"/>${role.role_name}&nbsp;
				<c:if test="${(i.index + 1)%3 == 0}"><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
			</c:forEach><br/>
			<input type="button" class="page-btn" value="确认" onclick="sub()"/>
			<input type="button" class="page-btn" value="取消" onclick="cancel()"/><br/>
			<input type="hidden" id="user_id" name="user_id"/>
		</form>
	</div>
  </body>
</html>
