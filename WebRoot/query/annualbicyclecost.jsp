<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="css/reset.css"/>
	<link rel="stylesheet" href="css/content.css"/>
<style type="text/css">

</style>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript">

	$(function(){
	
			<c:if test="${not empty error}">
				alert("${error}");
			</c:if>
	})
	function queryYear(){
		$("#checkyear").show();
		$("#submityear").attr("onclick","queryYearInfo()");
	}
   
   function queryYearInfo(){
  		$("#formyear").attr("action","query/queryYearInfo.do");
		$("#formyear").submit;
   
   }
   
  	//显示查询车辆页面
	function queryBicycle(){
		$("#checkwindow").show();
		$("#submit").attr("onclick","queryBicycleInfo()");
	}
	
	//根据条件执行车辆查询（带模糊查询）
	function queryBicycleInfo(){
		$("#form").attr("action","query/queryAllAnnualBicycle.do");
		$("#form").submit;
	}
	
	function queryBicycleDetail(bicycle_id){
	
		window.location.href="query/queryBicycleDetail.do?bicycle_id="+bicycle_id;
		}
		
    
     
    //页面翻页
	function gogogo(target) {
		var total = ${empty page.total?-1:page.total};
		var current = ${empty page.current?-1:page.current};
			if(target<0){}	
				if(target>=total){}
					if (target >= 0 && target < total) {
							location.href = "query/queryAllAnnualBicycle.do?current="
						+ target;
		}
	} 
	
	function turnoffCheckwindow(){
	$("#checkwindow").hide();
	
	}
		
	function turnoffCheckyear(){
	$("#checkyear").hide();
	}
    
	
	
	
    
  
	
	
	</script>
</head>
  
  
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">信息列表</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<div id="oper" class="public-content">
					<div class="page">
						<input class="page-btn" style="width:100px" type="button" onclick="queryBicycle()" value="查询车辆">
						<input class="page-btn" style="width:100px" type="button" onclick="queryYear()"  value="年度/月度信息查询">
					</div>
				</div>
		
			
			</div>
			<div class="clearfix"></div>
			<div class="public-content-cont two-col">
				<table class="public-cont-table col-2">
					<tr>
		
						<th>ID</th>
						<th>自行车编号</th>
						<th>状态</th>
						<th>车桩id</th>
						<th>销毁日期</th>						
						<th>操作人</th>
						<th>操作时间</th>
						<th>卡ID</th>
						<th>备注</th>
						<th>操作</th>
					</tr>
					
					 <c:forEach items="${bicycle_infoList}" var="temp">
						<tr>
						<td>${temp.bicycle_id}</td>
						<td>${temp.bicycle_code}</td>
						<td>${temp.status}</td>
						<td>${temp.pile_id}</td>
						<td>${temp.destory_date}</td>
						<td>${temp.user_id}</td>
						<td>${temp.operator_time}</td>
						<td>${temp.card_id}</td>
						<td>${temp.remark}</td>

						<td>
							<div class="table-fun">
								<input type="button"  onclick="queryBicycleDetail('${temp.bicycle_id}')" value="查看详情">						
							</div>
						</td>
						</tr>
					</c:forEach>
			
				</table>
				
				<br />
				<br />
				<div class="page">
					</select>第<span style="color:red;font-weight:600">${page.current + 1 }</span>/${page.total }页 共
					<span style="color:red;font-weight:600">${page.size }</span>条
					<input type="button" class="page-btn" style="width:80px;"  value="首页" onclick="gogogo(0)">
					<input type="button"  class="page-btn" style="width:80px;"value="上一页" onclick="gogogo(${page.current-1})">
					<input type="button" class="page-btn" style="width:80px;" value="下一页" onclick="gogogo(${page.current+1})">
					
				</div>
					
					
				</div>
				
			<div   id="checkwindow"  style="display:none"   >
				<div class="public-cont-left">
					<div class="public-cont-title">
						<h3 ><span id="title">查询窗口</span></h3>
					</div>
					
					<form id="form">
					<div class="form-group mt0">
						<label for="">自行车编号</label>
						<input type="text" name="bicycle_code" class="form-input-small">
					</div>
				
					
					<div class="form-group mt0" style="text-align:center;margin-top:15px;">
						<input type="submit" id="submit" class="sub-btn" value="查询">
						<input type="button" class="sub-btn" value="关  闭" onclick="turnoffCheckwindow()">
					</div>
					</form>
				</div>
			</div>
			
				<div   id="checkyear"  style="display:none"   >
				<div class="public-cont-left">
					<div class="public-cont-title">
						<h3 ><span id="title">查询窗口</span></h3>
					</div>
					
					<form id="formyear">
					<div class="form-group mt0">
						年度:&nbsp;&nbsp;<select name="year" style="text-align: center;">
								<option>2012</option>
								<option>2013</option>
								<option>2014</option>
								<option>2015</option>
								<option>2016</option>
								<option>2017</option>
						
						</select>
					
						<!-- <label for="">年度</label>
						<input type="text" name="year" class="form-input-small"> -->
						
						
					</div>
					<div class="form-group mt0">
					
					月度:&nbsp;&nbsp;<select name="month" style="text-align: center;">
								<option value="01">1</option>
								<option value="02">2</option>
								<option value="03">3</option>
								<option value="04">4</option>
								<option value="05">5</option>
								<option value="06">6</option>
								<option value="07">7</option>
								<option value="08">8</option>
								<option value="09">9</option>
								<option>10</option>
								<option>11</option>
								<option>12</option>
					</select>
					
					
					</div>
					
					<div class="form-group mt0" style="text-align:center;margin-top:15px;">
						<input type="submit" id="submityear" class="sub-btn" value="查询">
						<input type="button" class="sub-btn" value="关  闭" onclick="turnoffCheckyear()">
					</div>
					</form>
				</div>
			</div>
				
				<div id="bicycle_detail" style="display:none">
					
				
				</div>
			
				
				
				
					
					
				</div>
			</div>
	
	</div>
</body>
</html>