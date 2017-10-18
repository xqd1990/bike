<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script language="javascript" type="text/javascript" src="js/jquery-1.8.1.min.js" ></script>
	<script language="javascript" type="text/javascript" src="js/basicinfo/updatecardmsg.js"></script>
	<link rel="stylesheet" type="text/css" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/content.css">
  </head>
  
  <body>
    <div class="container">
		<div class="public-nav">您当前的位置：<a href="">基础信息维护</a>><a href="showCardUser.do">办卡</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>修改和维护卡用户界面</h3>
			</div>
			<div class="public-content-cont">
			<form action="" method="post" >
				<div class="form-group">编&nbsp;&nbsp;&nbsp;号
				  <input class="form-input-txt" type="text" name="name" id="upcardid" value="${param.upcard_id}" readonly="readonly" />
				</div>
				<div class="form-group">卡&nbsp;&nbsp;&nbsp;号
				  <input class="form-input-txt" type="text" name="name" id="upcardcode" value="${param.upcard_code}" readonly="readonly"/>
				</div>
			<div >办卡类型&nbsp;
				 A卡<input type="radio" name="card_type" id="up1card" class="cardtype" value="1" <c:if test="${param.upcard_type == '1'}">checked="checked"</c:if>>&nbsp;
				 D卡<input type="radio" name="card_type" id="up2card" class="cardtype" value="2" <c:if test="${param.upcard_type == '2'}">checked="checked"</c:if>>&nbsp;
				 社保卡卡<input type="radio" name="card_type" id="up3card" class="cardtype" value="3" <c:if test="${param.upcard_type == '3'}">checked="checked"</c:if>>&nbsp;
				 员工卡<input type="radio" name="card_type" id="up4card" class="cardtype" value="4" <c:if test="${param.upcard_type == '4'}">checked="checked"</c:if>>&nbsp;
				 调度卡<input type="radio" name="card_type" id="up5card" class="cardtype" value="5" <c:if test="${param.upcard_type == '5'}">checked="checked"</c:if>>
				<input type="hidden" id="username" value="test"/>
				</div>
				<div class="form-group">姓&nbsp;&nbsp;&nbsp;名
				  <input class="form-input-txt" type="text" name="name" id="upcardname" value="${param.upcard_name}" />
				</div>
				<div class="form-group">身份证号码
				  <input class="form-input-txt" type="number" name="idcard" id="upcardidcard" placeholder="请输入身份证号码" value="${param.upidcard}"/>
				</div>
				<!-- 在选择是社保卡的时候在输入身份证号码的时候卡号也应该相应的改变 -->
				<div class="form-group">性&nbsp;&nbsp;&nbsp;别&nbsp;
				  女<input  type="radio" name="sex" class="upcardsex" value="1" <c:if test="${param.upcard_sex == '1'}">checked="checked"</c:if>/>&nbsp;
				  男<input  type="radio" name="sex" class="upcardsex" value="0" <c:if test="${param.upcard_sex == '0'}">checked="checked"</c:if> />
				</div>
				<div class="form-group">联系&nbsp;电话 
				 	 <input class="form-input-txt" type="number" name="telphone" id="upcardtelphone" placeholder="请输入常用电话号码" value="${param.upcard_telphone}" />
				</div>
				<div class="form-group">手机&nbsp;联系
				 	 <input class="form-input-txt" type="number" name="mobile" id="upcardmobile" placeholder="请输入手机电话号码" value="${param.upcard_mobile}"/>
				</div>
				<div class="form-group">邮&nbsp;&nbsp;&nbsp;箱
				 	 <input class="form-input-txt" type="text" name="email" id="upcardemail" placeholder="请输入邮箱地址" value="${param.upcard_email}"/>
				</div>
				<div class="form-group">住&nbsp;&nbsp;&nbsp;址
				 	 <input class="form-input-txt" type="text" name="upress" id="upcardaddress" placeholder="请输入办卡人住址" value="${param.upcard_address}"/>
				</div>
				<div class="form-group">工作单位&nbsp;
				 	 <input class="form-input-txt" type="text" name="work" id="upcardwork" placeholder="请输入工作单位" value="${param.upcard_work}"/>
				</div>
				<div class="form-group" >注销&nbsp;信息;
				 <input class="form-input-txt" type="text" name="upcard_zxbj" id="upcardzxbj" readonly="readonly" value="${param.upcard_zxbj}"/>	
				</div>
				<div class="form-group" id="mothly">月票金额&nbsp;
				 <input class="form-input-txt" type="number" name="monthly_money" id="upcardmonthly_money" placeholder="请输入月票金额"   min="0" step="0.01" value="${param.upcard_monthly_money}" readonly="readonly"/>	
				</div>
					<div class="form-group" id="frozen">冻结金额&nbsp;
				 <input class="form-input-txt" type="number" name="frozen_money" id="upcardfrozen_money" placeholder="请输入冻结金额" value="${param.upcard_frozen_money}" min="0" step="0.01" readonly="readonly"/>	
				</div>
					<div class="form-group" id="wallet">钱包金额&nbsp;
				 <input class="form-input-txt" type="number" name="wallet_money" id="upcardwallet_money" placeholder="请输入钱包金额" value="${param.upcard_wallet_money}" min="0" step="0.01" readonly="readonly" />	
				</div>
				<div class="form-group">当前卡状态
				  <input class="form-input-txt" type="text" name="upcard_status" id="upcard_status" readOnly="readonly" value="${param.upcard_status}"/>
				</div>
				<div class="form-group" style="margin-left:150px;">
					<input type="button" class="sub-btn" value="提  交" id="submitmsg" onclick="submitupdatemsg()"/>
					<input type="reset" class="sub-btn" value="重  置" />
				</div>
				</div>
				</form>
			</div>
		</div>
	</div>

  </body>
</html>
