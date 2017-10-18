<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>办卡信息填写</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="css/reset.css" />
	<link rel="stylesheet" href="css/content.css" />
	<script language="javascript" type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
	<script language="javascript" src="js/basicinfo/RegisterCard.js" type="text/javascript"></script>
  </head>
  
  <body>
    	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="showCardUser.do">办卡</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>修改网站配置</h3>
			</div>
			<div class="public-content-cont">
			<input type="hidden" id="username" value="test"/>
			<form action="" method="post" >
			<div >办卡类型&nbsp;
				 A卡<input type="radio" name="card_type" id="add1card" class="cardtype" value="1">&nbsp;
				 D卡<input type="radio" name="card_type" id="add2card" class="cardtype" value="2">&nbsp;
				 社保卡卡<input type="radio" name="card_type" id="add3card" class="cardtype" value="3">&nbsp;
				 员工卡<input type="radio" name="card_type" id="add4card" class="cardtype" value="4">&nbsp;
				 调度卡<input type="radio" name="card_type" id="add5card" class="cardtype" value="5">
				</div>
				<div class="form-group">姓&nbsp;&nbsp;&nbsp;名
				  <input class="form-input-txt" type="text" name="name" id="addcardname"  />
				</div>
				<div class="form-group">身份证号码
				  <input class="form-input-txt" type="number" name="idcard" id="addcardidcard" placeholder="请输入身份证号码" />
				</div>
				<div class="form-group">性&nbsp;&nbsp;&nbsp;别&nbsp;
				  女<input  type="radio" name="sex" class="addcardsex" value="1" />&nbsp;
				  男<input  type="radio" name="sex" class="addcardsex" value="0"  />
				</div>
				<div class="form-group">联系&nbsp;电话
				 	 <input class="form-input-txt" type="number" name="telphone" id="addcardtelphone" placeholder="请输入常用电话号码" />
				</div>
				<div class="form-group">手机&nbsp;联系
				 	 <input class="form-input-txt" type="number" name="mobile" id="addcardmobile" placeholder="请输入手机电话号码" />
				</div>
				<div class="form-group">邮&nbsp;&nbsp;&nbsp;箱
				 	 <input class="form-input-txt" type="text" name="email" id="addcardemail" placeholder="请输入邮箱地址" />
				</div>
				<div class="form-group">住&nbsp;&nbsp;&nbsp;址
				 	 <input class="form-input-txt" type="text" name="address" id="addcardaddress" placeholder="请输入办卡人住址" />
				</div>
				<div class="form-group">工作单位&nbsp;
				 	 <input class="form-input-txt" type="text" name="work" id="addcardwork" placeholder="请输入工作单位" />
				</div>
				<div class="form-group" id="mothly">月票金额&nbsp;
				 <input class="form-input-txt" type="number" name="monthly_money" id="addcardmonthly_money" placeholder="请输入月票金额" value="0"  min="0" step="0.01" readonly="readonly" />	
				</div>
					<div class="form-group" id="frozen">冻结金额&nbsp;
				 <input class="form-input-txt" type="number" name="frozen_money" id="addcardfrozen_money" placeholder="请输入冻结金额" value="0" min="0" step="0.01" readonly="readonly"/>	
				</div>
					<div class="form-group" id="wallet">钱包金额&nbsp;
				 <input class="form-input-txt" type="number" name="wallet_money" id="addcardwallet_money" placeholder="请输入钱包金额" value="0" min="0" step="0.01" readonly="readonly"/>	
				</div>
				<div class="form-group" style="margin-left:150px;">
					<input type="button" class="sub-btn" value="提  交" id="submitmsg" />
					<input type="reset" class="sub-btn" value="重  置" />
				</div>
				</form>
			</div>
		</div>
	</div>
<script src="../kingediter/kindeditor-all-min.js"></script>
<script>
	 KindEditor.ready(function(K) {
                window.editor = K.create('#editor_id');
        });
</script>
</body>
  </body>
</html>
