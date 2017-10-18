<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	<link rel="stylesheet" href="css/reset.css" />
	<link rel="stylesheet" href="css/login.css" />
	<style type="text/css">
		body{overflow:hidden; overflow-x:hidden;}
		#prompt{color:#f00; font-size:16px;}
	</style>
	<script type="text/javascript">
		$(function(){
			<c:if test="${not empty sessionScopt.user}">
				location.href="/index.jsp";
			</c:if>
		});
	</script>
  </head>
  <body>
    <div class="page">
	<div class="loginwarrp">
		<div class="logo">登陆</div>
        <div class="login_form">
			<form id="Login" name="Login" method="post" target="_top" action="LoginServlet">
				<li class="login-item">
					<span>用户名：</span>
					<input type="text" name="login_name" class="login_input">
				</li>
				<li class="login-item">
					<span>密　码：</span>
					<input type="password" name="password" class="login_input">
				</li>
				<div class="clearfix"></div>
				<li class="login-sub">
					<input type="submit" name="Submit" value="登录" />
				</li>                      
           </form><br/>
           <span id="prompt">${empty prompt?"":prompt}</span>
           <%-- <c:if test="">
           		${prompt }
           </c:if> --%>
		</div>
	</div>
    </div>
  </body>
</html>
