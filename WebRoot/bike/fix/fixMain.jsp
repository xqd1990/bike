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
    
    <title>My JSP 'fixMain.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link href="css/fix.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="css/reset.css" />
	<link rel="stylesheet" href="css/content.css" />
	<script src="js/jquery-1.8.1.min.js"></script>
	<link type="text/css" href="date/css/jquery-ui-1.8.17.custom.css" rel="stylesheet" />
     <link type="text/css" href="date/css/jquery-ui-timepicker-addon.css" rel="stylesheet" />
    <script type="text/javascript" src="date/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="date/js/jquery-ui-1.8.17.custom.min.js"></script>
	<script type="text/javascript" src="date/js/jquery-ui-timepicker-addon.js"></script>
    <script type="text/javascript" src="date/js/jquery-ui-timepicker-zh-CN.js"></script>
	<script type="text/javascript">
		//时间控件
		$(function () {
        $(".ui_timepicker").datetimepicker({
            showOn: "button",
            buttonImage: "date/css/images/icon_calendar.gif",
            buttonImageOnly: true,
            timeonly:true,
            showSecond: false,
            showMinute:false,
            showHour:false,
           timeFormat:''
            //stepHour: 1,
           // stepMinute: 1,
            //stepSecond: 1
        })
    	})
		//翻页
		function jump(target){
			var total = ${page.total};
			var current = ${page.current};
			if(target >= 0 && target < total){
				if(current == target && (current == 0 || current == total - 1)){}
				else location.href = "bike/fix/showFixbikes.do?current=" + target;
			}
		}
		//翻页（快速）
		function jumpTo(){
			var target = $("#targetPage option:selected").html();
			jump(target - 1);
		}
		//填写维修单
		function addfix(bicycle_id,bicycle_code){
			$("#sel1").val("");
			$("#sel2").val("");
			$("#part").val("");
			$("#date").val("");
			$("#fee").val("");
			$("#repairer").val("");
			$("#remark").val("");
			$("#bicyclecode").val(bicycle_code);
			$("#bicycleid").val(bicycle_id);
			$("form").show();
		}
		
		//取消表单
		function cancel(){
			$("form").hide();
		}
		//提交表单
		function sub(){
			var sel1=$("#sel1").val();
			var sel2=$("#sel2").val();
			var part=$("#part").val();
			var date=$("#date").val();
			var fee=$("#fee").val();
			var repairer=$("#repairer").val();
			if(sel1!=""&&sel2!=""&&part!=""&&date!=""&&fee!=""&&repairer!=""){
				$("form").attr("action","bike/fix/addFix.do");
				$("form").submit();
			}else{
				alert("有选项未填，不能提交！");
			}
			
		}
		//模糊查询权限
		function search(target){
			var desc = $("#search").val();
			if(desc != null && desc != "" && target >= 0){
				var map = {"description":desc,"current":target};
				$.post("basicInfo/orgInfo/searchPhase.do",map,function(data){
					$("#main").html(data);
				});
			}
		}
		//检查费用填写是否符合标准
		function changenum(){
			var tel=$("#fee").val();
			var reg=/^\d+(\.\d{1,3})?$/;
			if(!reg.test(tel)){
				$("#fee").val("");
				alert("费用填写不符合标准，请重新填写！");
			}
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
  	<!-- <table width="100%"  border="0" cellpadding="0" cellspacing="0">
  		<tr>
    		<td nowrap class="title1">您的位置：车辆维修－－需要维修的自行车列表</td>
  		</tr>
	</table> -->
	<div class="public-nav">您当前的位置：<a javascript:void(0)>车辆维修</a>><a javascript:void(0)>需要维修的自行车列表</a></div>
  	<!-- <div id="oper">
  		<input type="button" id="addPhase" value="新增权限" onclick="addPhase()"/>
  		<input type="text" id="search" placeholder="权限名称"/>
  		<input type="button" value="搜索" onclick="search(0)"/>
  		<input type="button" value="取消" onclick="location.href='basicInfo/orgInfo/showPhases.do'"/>
  	</div> -->
	<div id="main" class="public-content">
		<table border="1px solid #fff" cellspacing="0px" class="public-content-cont">
			<tr>
				<th>编号</th>
				<th>需要维修的自行车编号</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${fixList }" var="info" varStatus="i">
				<tr
					ondblclick="addfix(${info.bicycle_id }')">
					<td>${i.index + 1 + page.current*page.count}</td>
					<td>${info.bicycle_code }</td>
					<td  class="page"> <input class="page-btn" style="width:120px;"type="button"
						value="填写维修表单"
						onclick="addfix(${info.bicycle_id },'${info.bicycle_code }')" />
					</td>
				</tr>
			</c:forEach>
		</table>
		<br /> 
		<div class="page">
		<input class="page-btn" type="button" style="width:80px;"value="首页" onclick="jump(0)" /> <input
			class="page-btn" type="button" style="width:80px;"value="上一页" onclick="jump(${page.current-1})" /> <input
			class="page-btn" type="button" style="width:80px;"value="下一页" onclick="jump(${page.current+1})" /> <input
			class="page-btn" type="button" style="width:80px;"value="末页" onclick="jump(${page.total - 1})" /><br />
		<br /><select onchange="jumpTo()"
			id="targetPage">
			<option>${page.current + 1 }</option>
			<c:forEach begin="1" end="${page.total }" varStatus="i">
				<option>${i.index }</option>
			</c:forEach>
		</select>/<span style="color:red;font-weight:600">${page.total }</span>页 共<span style="color:red;font-weight:600">${page.size }</span>条
		</div>
	</div>
	<div class="public-content">
		<div class="page">
	<form method="post"  >
		<h2>车辆维修单(以下均为必填项)</h2>
		车辆编号：<input id="bicyclecode"" name="bicycle_code" readonly/><br/>
		维修类型：<select name="repair_type" id="sel1">
		<option></option>
		<option value="1">普修</option>
		<option value="2">更换零件</option>
		<option value="3">维修+更换零件</option>
		</select><br/>
		维修部位：<input name="repair_part" id="part"><br/>
		维修结果：<select name="repair_result" id="sel2">
		<option></option>
		<option value="1">修理成功</option>
		<option value="2">修理失败</option>
		</select><br/>
		维修日期：<input id="date" type="text"  class="ui_timepicker" name="repair_date" readonly/><br/>
		维修费用：<input name="fee" id="fee" onchange="changenum()" placeholder="最多三位小数点的数字"/><br/>
		维修人员：<input name="repairer" id="repairer"><br/>
		维修备注：<input name="remark" id="remark"><br/>
		<input name="user_id" value="${user.user_id }"type="hidden"/>
		<input id="bicycleid"" name="bicycle_id" type="hidden" readonly/>
		<input type="button"  class="page-btn"value="确定" onclick="sub()"/>
		<input type="button" style="background:#FF0000" class="page-btn" value="取消" onclick="cancel()"/><br/>
	</form>
	</div>
	</div>
  </body>
</html>
