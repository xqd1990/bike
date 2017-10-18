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
    
    <title>My JSP 'BCMain.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- <link href="dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="dist/css/bootstrap-theme.min.css" rel="stylesheet"> -->
	
	<!-- <link href="css/fix.css" rel="stylesheet" type="text/css"> -->
	<link rel="stylesheet" href="css/reset.css" />
	<link rel="stylesheet" href="css/content.css" />
	<style type="text/css">
	body{overflow:scroll; overflow-x:hidden; }
	#main {text-align:center; width:90%; margin-left:5%;}
	#main table {width:100%; text-align:center;}
	form{display:none; width:300px; margin-left:5%; padding-left:20px; background:#AD78B0;border:1px solid #fff;}
	form h2{font-weight:40;font-size:20px;}
</style>
	<!-- <link href="css/theme.css" rel="stylesheet" type="text/css"> -->
	<script src="js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript">
		//修改车辆类型
		function updateBC(catagory_id,catagory_name,frame_type,trie_type,weight,height,length,ring_type,basket,backseat,bicycle_seat,handbrake,remark){
			$("form h2").html("修改");
			$("#catagory_id").val(catagory_id);
			$("#catagory_name").val(catagory_name);
			$("#frame_type").val(frame_type);
			$("#tire_type").val(trie_type);
			$("#weight").val(weight);
			$("#height").val(height);
			$("#length").val(length);
			$("#ring_type").val(ring_type);
			$("#backseat").val(backseat);
			$("#basket").val(basket);
			$("#bicycle_seat").val(bicycle_seat);
			$("#handbrake").val(handbrake);
			$("#remark").val(remark);
			$("form").show();
		}
		//删除类型
		function deleteBC(catagory_id){
			cancel();
			if(window.confirm("确认删除吗？"))
			{ location.href="basicInfo/bikeInfo/queryBC.do?catagory_id=" +catagory_id ;}			
		}
		//隐藏表单
		function cancel(){
			$("form").hide();
		}
		//新增车辆类型
		function addBC(){
			$("form h2").html("新增");
			$("#catagory_id").val("");
			$("#catagory_name").val("");
			$("#frame_type").val("");
			$("#tire_type").val("");
			$("#weight").val("");
			$("#height").val("");
			$("#length").val("");
			$("#ring_type").val("");
			$("#basket").val("");
			$("#backseat").val("");
			$("#bicycle_seat").val("");
			$("#handbrake").val("");
			$("#remark").val("");
			$("form").show();
		}
		//提交表单
		function sub(){
			if(window.confirm("确认提交吗？")){
			var title = $("h2").html();
			if(title == "新增") {$("form").attr("action","basicInfo/bikeInfo/addBC.do");}
			else {$("form").attr("action","basicInfo/bikeInfo/updateBC.do");}
			$("form").submit();
			}
		}
		//显示信息
		function showmessage(message){
			alert(message);
		}
		//修改数字时提醒
		function changeNum1(){
			var tel= $("#weight").val();
			var reg=/^\d{1,3}$/;
			if(!reg.test(tel)){
				alert("请填写四位以下的正整数");
				$("#weight").val("");
			}
		}
		function changeNum2(){
			var tel= $("#height").val();
			var reg=/^\d{1,3}$/;
			if(!reg.test(tel)){
				alert("请填写四位以下的正整数");
				$("#height").val("");
			}
		}
		function changeNum3(){
			var tel= $("#length").val();
			var reg=/^\d{1,3}$/;
			if(!reg.test(tel)){
				alert("请填写四位以下的正整数");
				$("#length").val("");
			}
		}
	</script>

  </head>
  <c:if test="${message != null }">
  	<body onload="showmessage('${message}')">
  </c:if>
  <c:if test="${message == null }">
  	<body>
  </c:if>
	<div class="public-nav">您当前的位置：<a javascript:void(0)>车辆基本类型信息管理</a>><a javascript:void(0)>车辆基本类型列表</a></div>
	<div id="oper" class="public-content">
		<div class="page" style="text-align:left; margin-left:5%;">
  			<input type="button" value="新增车辆类型" style="background:#AD78B0;width:120px;" onclick="addBC()" class="page-btn"/>
  </div>
  </div>
  <div id="main" class="public-content">
		<table class="public-content-cont" >
			<tr>
				<th>序号</th>
				<th>车辆类型名称</th>
				<th>车架类型</th>
				<th>车胎类型</th>
				<th>前篮</th>
				<th>手刹</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${catagoryList }" var="bc" varStatus="i">
				<tr>
					<td>${i.index + 1 + page.current*page.count}</td>
					<td>${bc.catagory_name  }</td>
					<td>${bc.frame_type  }</td>
					<td>${bc.tire_type  }</td>
					<td>${bc.basket  }</td>
					<td>${bc.handbrake  }</td>
				<td class="page"><input class="page-btn" type="button" value="修改"
					onclick="updateBC(${bc.catagory_id },'${bc.catagory_name  }','${bc.frame_type  }','${bc.tire_type  }',${bc.weight  },${bc.height  },${bc.length  },'${bc.ring_type  }','${bc.basket  }','${bc.backseat  }','${bc.bicycle_seat  }','${bc.handbrake  }','${bc.remark  }')" />
					<input type="button" class="page-btn" style="background:#FF0000" value="删除"onclick="deleteBC(${bc.catagory_id })"/>
				</td>
			</tr>
			</c:forEach>
			</table>
		<br />
	</div>
	<div class="public-content">
	<div class="page">
		<form method="post"  >
		<h2></h2>
		类型名称：<input id="catagory_name" type="text"  name="catagory_name" /><br/>
		车架类型：<input id="frame_type"  type="text"  name="frame_type" /><br/>
		车胎类型：<input name="tire_type" type="text"  id="tire_type" /><br/>
		车重：<input type="text"  name="weight" id="weight" onchange="changeNum1()" placeholder="四位一下的正整数，单位KG"/><br/>
		车高：<input id="height" type="text"  name="height" onchange="changeNum2()" placeholder="四位一下的正整数，单位m"/><br/>
		车长：<input type="text" name="length" id="length"  onchange="changeNum3()" placeholder="四位一下的正整数，单位m"/><br/>
		铃铛类型：<select  name="ring_type" id="ring_type">
		<option></option>
		<option value="1">拨动式</option>
		<option value="2">塑胶按压式</option>
		<option value="3">其他样式</option>
		</select><br/>
		前篮：<input type="text" name="basket" id="basket"/><br/>
		后座：<input type="text" name="backseat" id="backseat"/><br/>
		车座：<input type="text" name="bicycle_seat" id="bicycle_seat"/><br/>
		手刹：<input type="text" name="handbrake" id="handbrake"/><br/>
		备注：<input type="text" name="remark" id="remark"><br/>
		<input name="user_id" value="${user.user_id }"type="hidden"/>
		<input id="catagory_id" name="catagory_id" type="hidden" />
		<input type="button" class="page-btn" value="确定" onclick="sub()"/>
		<input type="button" style="background:#FF0000" class="page-btn" value="取消" onclick="cancel()"/><br/>
	</form>
	</div>
	</div>
  </body>
</html>
