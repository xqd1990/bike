<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<link rel="stylesheet" href="css/reset.css" />
<link rel="stylesheet" href="css/content.css" />
<script src="js/jquery-1.8.1.min.js"></script>
<link type="text/css" href="date/css/jquery-ui-1.8.17.custom.css"
	rel="stylesheet" />
<link type="text/css" href="date/css/jquery-ui-timepicker-addon.css"
	rel="stylesheet" />
<script type="text/javascript" src="date/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript"
	src="date/js/jquery-ui-1.8.17.custom.min.js"></script>
<script type="text/javascript"
	src="date/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript"
	src="date/js/jquery-ui-timepicker-zh-CN.js"></script>
<script type="text/javascript">
	$(function() {
		<c:if test="${not empty error}">
		alert("${error}");
		</c:if>
	})

	//分页
	function go(target) {
		var total = ${page.total};
		var current = ${page.current};
		if (target < 0) {

		}
		if (target >= total) {

		}
		if (target >= 0 && target < total) {
			location.href = "basicInfo/businessInfo/getAllVender.do?current="
					+ target;
		}
	}
	//翻页（快速）
	function jumpTo() {
		var target = $("#targetPage option:selected").html();
		go(target - 1);
	}
	//点击关闭，关闭当前页面
	function turnOff() {
		$("#pluswindow").hide();
	}

	//显示增加页面
	function add() {
		$("#title").text("供应商注册");
		$("#pluswindow").show();
		$("#submit").val("注册");
		$("#submit").attr("onclick", "venderAdd()");

	}

	//显示修改页面，通过ajax查询出选中的供应商的所有信息。
	function modify(obj) {
		$("#title").text("修改页面");
		$("#pluswindow").show();
		$("#submit").val("修改");
		var id = $(obj).parent().parent().parent().children().eq(0).html()
		$("#submit").attr("onclick", "venderModify(id)");
		var param = {
			"id" : id
		};
		$.post('basicInfo/businessInfo/getVender.do', param,
				function(data) {
					$("#vender_id").val(data.vender_id);
					$("#vender_code").val(data.vender_code);
					$("#vender_type").val(data.vender_type);
					$("#vender_name").val(data.vender_name);
					$("#address").val(data.address);
					$("#telphone").val(data.telphone);
					$("#contacts").val(data.contacts);
					$("#product_license").val(data.product_license);
					$("#bussiness_registration_no").val(
							data.bussiness_registration_no);
					$("#registered_capital").val(data.registered_capital);
					$("#user_id").val(data.user_id);
					$("#create_time").val(data.create_time);
					$("#zxbj").val(data.zxbj);
					$("#remark").val(data.remark);
				});
	}

	//点击删除，删除该条数据
	function logoutVender(obj) {
		var id = $(obj).parent().parent().parent().children().eq(0).html();
		if (window.confirm("确认删除吗？"))
			location.href = "basicInfo/businessInfo/logoutVender.do?id=" + id;
	}

	//点击注册，将数据保存到数据库
	function venderAdd() {
		$("#form").attr("action", "basicInfo/businessInfo/venderAdd.do");
		$("#form").submit();
	}

	//点击修改，将最新的信息同步至数据库
	function venderModify(id) {
		$("#form").attr("action",
				"basicInfo/businessInfo/venderModify.do?id=" + id);
		$("#form").submit();
	}

	//时间控件
	$(function() {
		$(".ui_timepicker").datetimepicker({
			showOn : "button",
			buttonImage : "date/css/images/icon_calendar.gif",
			buttonImageOnly : true,
			timeonly : true,
			showSecond : false,
			showMinute : false,
			showHour : false,
			timeFormat : ''
		})
	})
</script>
</head>

<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">
			您当前的位置：<a href="">管理首页</a>><a href="">信息列表</a>
		</div>
		<div id="oper" class="public-content">
			<div class="page">
				<input class="page-btn" style="width:100px" type="button"
					onclick="add()" value="新增供应商">
			</div>
		</div>
		<div class="public-content">
			<div class="public-content-header">
				<h3 style="display: inline-block;">修改网站配置</h3>
			</div>
			<div class="clearfix"></div>
			<div class="public-content-cont two-col">
				<table class="public-cont-table col-2">
					<tr>
						<th>ID</th>
						<th>编号</th>
						<th>类型</th>
						<th>名称</th>
						<th>地址</th>
						<th>电话</th>
						<th>联系人</th>
						<th>生产许可证</th>
						<th>工商注册号</th>
						<th>注册资金</th>
						<th>操作人</th>
						<th>操作日期</th>
						<th>注销标志</th>
						<th>备注</th>
						<th>操作</th>
					</tr>
					
					<c:forEach items="${venderList}" var="temp">
						<tr>
							<td>${temp.vender_id}</td>
							<td>${temp.vender_code}</td>
							<td>${temp.vender_type}</td>
							<td>${temp.vender_name}</td>
							<td>${temp.address}</td>
							<td>${temp.telphone}</td>
							<td>${temp.contacts}</td>
							<td>${temp.product_license}</td>
							<td>${temp.bussiness_registration_no}</td>
							<td>${temp.registered_capital}</td>
							<td>${temp.user_id}</td>
							<td>${temp.create_time}</td>
							<td>${temp.zxbj}</td>
							<td>${temp.remark}</td>
							<td>
								<div class="page">
									<input type="button" class="page-btn" style="width:35px;"
										onclick="modify(this)" value="修改"> <input
										type="button" class="page-btn" style="width:35px;"
										onclick="logoutVender(this)" value="注销">
								</div>
							</td>
						</tr>
					</c:forEach>
				</table>
				<br /> <br />
				<div class="page">
					第<span style="color:red;font-weight:600">${page.current + 1 }</span>页
					<select onchange="jumpTo()" id="targetPage">
						<option></option>
						<c:forEach begin="1" end="${page.total }" varStatus="i">
							<option>${i.index }</option>
						</c:forEach>
					</select><span style="color:red;font-weight:600">/${page.total }</span>页 共<span
						style="color:red;font-weight:600">${page.size }</span>条 <input
						type="button" class="page-btn" style="width:80px;" value="首页"
						onclick="go(0)"> <input type="button" class="page-btn"
						style="width:80px;" value="上一页" onclick="go(${page.current-1})">
					<input type="button" class="page-btn" style="width:80px;"
						value="下一页" onclick="go(${page.current+1})">
				</div>
				<div id="pluswindow" style="display:none;">
					<div class="public-cont-left">

						<div class="public-cont-title">
							<h3 id="title"></h3>
						</div>

						<form id="form" action="" method="post">
							<div class="form-group mt0" style="display:none;">
								<label for="">供应商Id</label> <input type="text" id="vender_id"
									name="vender_id" class="form-input-small" value="10001">
							</div>

							<div class="form-group mt0">
								<label for="">供应商编号</label> <input type="text" id="vender_code"
									name="vender_code" class="form-input-small">
							</div>

							<div class="form-group mt0">
								<label for="">供应商类型</label> <input type="text" id="vender_type"
									name="vender_type" class="form-input-small">
							</div>

							<div class="form-group mt0">
								<label for="">名称</label> <input type="text" id="vender_name"
									name="vender_name" class="form-input-small">
							</div>

							<div class="form-group mt0">
								<label for="">地址</label> <input type="text" id="address"
									name="address" class="form-input-small">
							</div>

							<div class="form-group mt0">
								<label for="">联系电话</label> <input type="text" id="telphone"
									name="telphone" class="form-input-small">
							</div>

							<div class="form-group mt0">
								<label for="">联系人</label> <input type="text" id="contacts"
									name="contacts" class="form-input-small">
							</div>

							<div class="form-group mt0">
								<label for="">生产许可证</label> <input type="text"
									id="product_license" name="product_license"
									class="form-input-small">
							</div>

							<div class="form-group mt0">
								<label for="">工商注册号</label> <input type="text"
									id="bussiness_registration_no" name="bussiness_registration_no"
									class="form-input-small">

							</div>

							<div class="form-group mt0">
								<label for="">注册资金</label> <input type="text"
									id="registered_capital" name="registered_capital"
									class="form-input-small">
							</div>

							<div class="form-group mt0">
								<label for="">操作人</label> <input type="text" id="user_id"
									name="user_id" class="form-input-small">
							</div>

							<div class="form-group mt0">
								<label for="">操作日期</label> <input type="text" id="create_time"
									name="create_time" class="ui_timepicker">
							</div>

							<div class="form-group mt0">
								<label for="">注销标志</label> <input type="text" id="zxbj"
									name="zxbj" class="form-input-small">
							</div>

							<div class="form-group mt0">
								<label for="">备注</label> <input type="text" id="remark"
									name="remark" class="form-input-small">
							</div>

							<div class="form-group mt0">
								<span></span>
							</div>
							<div class="form-group mt0"
								style="text-align:center;margin-top:15px;">
								<input type="submit" id="submit" class="sub-btn" value="">
								<input type="button" class="sub-btn" value="关  闭"
									onclick="turnOff()">
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
