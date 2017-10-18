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
		});
		

    function turnoff(){
		$("#pluswindow").hide();
	} 

	function card(){
		var card_id = prompt("请输入您的卡号", "");
		$(".card").html(card_id);
	}
	
	function rentBicycle(station_id,bicycle_id,pile_id){
		 
		 card_code=$(".card").eq(0).html();
		/*  alert(card_code); */
		 window.location.href="bike/rent/rentBicycle.do?station_id="+station_id+"&bicycle_id="+bicycle_id+"&pile_id="+pile_id+"&card_code="+card_code;	
	}
	//页面翻页
	function gogogo(target) {
	var total = ${empty page.total?-1:page.total};
		var current = ${empty page.current?-1:page.current};
			if(target<0){}	
				if(target>=total){}
					if (target >= 0 && target < total) {
							location.href = "bike/rent/getStationNameRent.do?current="
						+ target;
		}
	}
		

		

	</script>
</head>
  
  
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">信息列表</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3 style="display: inline-block;">修改网站配置</h3>
	
			</div>
		
			
			<div class="clearfix"></div>
			<div class="public-content-cont two-col">
			
				<table class="public-cont-table col-2">
					
						
						<tr>
							<c:forEach items="${stationnameList}" var="temp">
								<th>车点名称</th>
							</c:forEach>
						
						</tr>
						<tr>
						<c:forEach items="${stationnameList}" var="temp">
							<td><a href="bike/rent/getallPileRent.do?station_id=${temp.station_id }">${temp.station_name}</a></td>
						</c:forEach>
						</tr>					
				</table>
				<br/>
				<br/>
							
				<c:if test="${not empty stationnameList }">				
					<div class="page">
					
						</select>第<span style="color:red;font-weight:600">${page.current + 1 }
						</span>/${page.total }页 共
						<span style="color:red;font-weight:600">${page.size }
						</span>条
						
						<input type="button" class="page-btn" style="width:80px;"  value="首页" onclick="gogogo(0)">
						<input type="button"  class="page-btn" style="width:80px;"value="上一页" onclick="gogogo(${page.current-1})">
						<input type="button" class="page-btn" style="width:80px;" value="下一页" onclick="gogogo(${page.current+1})">
						
					</div>
				</c:if>
				
	
				
				<table class="public-cont-table col-2" >
				
					<c:if test="${not empty rentList }">
					<div class="page">
						<a href="bike/rent/getStationNameRent.do"><input type="button" class="page-btn" style="width:80px;" value="返回" onclick="goback1()"></a>
					</div>
						<tr>
						
							<th>车桩</th>
							<th>车点名称</th>
							<th>自行车编号</th>
							<th>车桩编号</th>
							
							<th>操作</th>
							
				
						</tr>
					</c:if>
					
						<c:forEach items="${rentList}" var="temp">
							<tr>
								<td><input type="radio" onclick="card()"></td>
								
								<td><input type="hidden" name="station_name" value="${temp.station_id}"/>
								${temp.station_name}
								</td>
								
								<input type="hidden" name="station_id" value="${temp.station_id}"/>
								<input type="hidden" name="bicycle_id" value="${temp.bicycle_id}"/>
								<input type="hidden" name="pile_id" value="${temp.pile_id}"/>
								
								<td><input type="hidden" name="bicycle_code" value="${temp.bicycle_code}"/>
								${temp.bicycle_code}
								</td>
								<td>${temp.pile_code}</td>
								<span class="card" style="display:none"></span>
								
								<td>
									<div class="page">
										<input type="button" value="租用" class="page-btn" style="width:50px;" onclick="rentBicycle('${temp.station_id}','${temp.bicycle_id}','${temp.pile_id}')">
									</div>
								</td>
								
							
							</tr>
							
						</c:forEach>
		
				</table> 
		
		
			
				
			</div>
		</div>
	</div>
</body>
</html>