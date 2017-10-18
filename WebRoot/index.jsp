<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/public.css">
<style type="text/css">
	.secLi {font-size:10px; display:none;}
	#currTime{color:#00f;}
</style>
<script src="js/jquery.min.js"></script>
<script>
	$().ready(function() {
		//一级列表的收缩
		var item = $(".public-ifame-item");
		for (var i = 0; i < item.length; i++) {
			$(item[i]).on('click', function() {
				$(".ifame-item-sub").hide();
				if ($(this.lastElementChild).css('display') == 'block') {
					$(this.lastElementChild).hide();
					$(".ifame-item-sub li").removeClass("active");
				} else {
					$(this.lastElementChild).show();
					$(".ifame-item-sub li").on('click', function() {
						$(".ifame-item-sub li").removeClass("active");
						$(this).addClass("active");
					});
				}
			});
		}
		addTime();
	});
	//显示当前时间
	function addTime(){
		var date = new Date();
		var time = date.getFullYear() + "-" + checkDate((date.getMonth() + 1)) + "-" + checkDate(date.getDate()) + 
					" " + checkDate(date.getHours()) + ":" + checkDate(date.getMinutes()) + ":" + checkDate(date.getSeconds());
		$("#currTime").html(time);
		window.setTimeout("addTime()",1000);
	}
	//校验日期是否为单数
	function checkDate(time){
		if(/^\d$/.test(time)){
			return 0 + "" + time;
		}
		return time;
	}

	var index;
	//二级列表的收缩
	function turn(i) {
		if (i == index) {
			$(".secLi").hide();
			index = 0;
		} else {
			switch (i) {
			case 1:
				$(".secLi:lt(3)").show();
				$(".secLi:gt(2)").hide();
				index = i;
				break;
			case 2:
				$(".secLi").show();
				$(".secLi:lt(3)").hide();
				$(".secLi:gt(4)").hide();
				index = i;
				break;
			case 3:
				$(".secLi:gt(4)").show();
				$(".secLi:lt(5)").hide();
				index = i;
				break;
			}
		}
	}
	//退出
	function quit(){
		if(confirm("确认退出？")){
			location.href="QuitServlet";
		}
	}
</script>

</head>

<body>
	<div class="public-header-warrp">
		<div class="public-header">
			<div class="content">
				<div class="public-header-logo" style="width:350px;">
					<image src="images/bike.jpg" style="width:70px;height:60px;"/> <h3>自行车租赁系统</h3>
				</div>
				<div class="public-header-admin fr">
					${empty sessionScope.user?'游客':sessionScope.user.username},您好！&nbsp;<span id="currTime"></span>
					<div class="public-header-fun fr">
						<c:if test="${not empty sessionScope.user }" >
							 <a javascript:void(0) class="public-header-loginout" onclick="quit()">退出</a>
						</c:if>
						<c:if test="${empty sessionScope.user }" >
							<a href="login.jsp" class="public-header-man">登录</a> 
						</c:if>
						
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="clearfix"></div>
	<!-- 内容展示 -->
	<div class="public-ifame mt20">
		<div class="content">
			<div class="clearfix"></div>
			<!-- 左侧导航栏 -->
			<div class="public-ifame-leftnav">
				<div class="public-title-warrp">
					<div class="public-ifame-title ">
						<a href="">首页</a>
					</div>
				</div>
				<ul class="left-nav-list">
					<li class="public-ifame-item"><a href="javascript:;">基础信息维护</a>
						<div class="ifame-item-sub">
							<ul>
								<li onclick="turn(1)"><a href="javascript:;">组织机构信息</a></li>
								<li class="secLi"><a href="basicInfo/orgInfo/showPhases.do"
									target="content">&nbsp;1.权限管理</a></li>
								<li class="secLi"><a href="basicInfo/orgInfo/showRoles.do"
									target="content">&nbsp;2.角色管理</a></li>
								<li class="secLi"><a href="basicInfo/orgInfo/showUsers.do"
									target="content">&nbsp;3.人员登记</a></li>
								<li onclick="turn(2)"><a href="javascript:;">业务基础信息</a></li>
								<li class="secLi"><a
									href="basicInfo/businessInfo/getAllVender.do" target="content">&nbsp;1.供应商维护</a></li>
								<li class="secLi"><a href="basicInfo/businessInfo/showCardUser.do" target="content">&nbsp;2.办卡</a></li>
								<li onclick="turn(3)"><a href="javascript:;">车辆信息</a></li>
					
								<li class="secLi"><a href="basicInfo/bikeInfo/queryStation.do" 
								target="content">&nbsp;1.车点信息</a></li>
								<li class="secLi"><a href="basicInfo/bikeInfo/queryPiles.do" 
								target="content">&nbsp;2.车桩信息</a></li>
								<li class="secLi"><a href="basicInfo/bikeInfo/showBC.do"
									target="content">&nbsp;3.车辆信息</a></li>
				
							</ul>
						</div></li>
					<li class="public-ifame-item"><a href="javascript:;">车辆业务</a>
						<div class="ifame-item-sub">
							<ul>
								<li><a href="bike/init/showOrder.do" target="content">车辆购入</a></li>
								<li><a href="bike/dispatch/showBicycleToPile.do"
									target="content">新车入桩</a></li>
								<li><a href="bike/rent/getStationNameRent.do" target="content">车辆租出</a></li>
								<li><a href="bike/rent/getStationNameReturn.do" target="content">车辆归还</a></li>
								<li><a href="bike/fix/showStatOut.do" target="content">车辆维修调出</a></li>
								<li><a href="bike/fix/showFixbikes.do" target="content">车辆维修</a></li>
								<li><a href="bike/fix/showFixssbikes.do" target="content">车辆维修调入</a></li>
								<li><a href="bike/diapatch/allbikeoutfind.do?current=0" target="content">车辆调出</a></li>
								<li><a href="bike/diapatch/BicycleCanInList.do?current=0" target="content">车辆调入</a></li>
								<li><a href="bike/fix/showScrapbikes.do" target="content">车辆报废</a></li>
							
								
								
							</ul>
						</div></li>
					<li class="public-ifame-item"><a href="javascript:;">卡业务</a>
						<div class="ifame-item-sub">
							<ul>
							<a href="card/cardfortopup.jsp" target="content">充值</a></li>
							</ul>
						</div></li>
					<li class="public-ifame-item"><a href="javascript:;">查询统计</a>
						<div class="ifame-item-sub">
							<ul>
								<li><a href="query/showStation.do" target="content">车点车辆联合查询</a></li>
								<li><a href="query/annualbicyclecost.jsp" target="content">月度年度车辆费用</a></li>
								<li><a href="query/showStat.do" target="content">车点使用率统计</a></li>
							<li><a href="query/statisticsAndShow.do" target="content">实时卡费用统计</a></li>
								<li><a href="query/showAllCard.do" target="content">月度年度卡费用统计</a></li>
							</ul>
						</div></li>
				</ul>
			</div>
			<!-- 右侧内容展示部分 -->
			<div class="public-ifame-content">
				<iframe name="content" src="main.html" frameborder="0"
					id="mainframe" scrolling="yes" marginheight="0" marginwidth="0"
					width="100%" style="height: 700px;" noresize="noresize" ></iframe>
			</div>
		</div>
	</div>
</body>
</html>
