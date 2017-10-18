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
	
	<script type="text/javascript" src="date/js/jquery-ui-1.8.17.custom.min.js"></script>


    <script type="text/javascript" src="date/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="date/js/jquery-ui-1.8.17.custom.min.js"></script>
	<script type="text/javascript" src="date/js/jquery-ui-timepicker-addon.js"></script>
    <script type="text/javascript" src="date/js/jquery-ui-timepicker-zh-CN.js"></script>
  	<link type="text/css" href="date/css/jquery-ui-1.8.17.custom.css" rel="stylesheet" />
    <link type="text/css" href="date/css/jquery-ui-timepicker-addon.css" rel="stylesheet" />
	<script type="text/javascript">
$(function(){
					<c:if test="${not empty error}">
						alert("${error}");
					</c:if>
	})

   //显示修改车桩信息页面
	function modifyPile(pile_id,vender_id,pile_code,station_id,status,install_time,disassembly_time,user_id,operator_time,bicycle_id,remark){
		$("#showpilewindow").show();
		$("#tempc_pile_id").val(pile_id);
		$("#tempc_vender_id").val(vender_id);
		$("#tempc_pile_code").val(pile_code);
 		$("#tempc_station_id").val(station_id);
		$("#tempc_status").val(status);
		$("#tempc_install_time").val(install_time);
		$("#tempc_disassembly_time").val(disassembly_time);
		$("#tempc_user_id").val(user_id);
		$("#tempc_operator_time").val(operator_time);
		$("#tempc_bicycle_id").val(bicycle_id);

		$("#tempc_remark").val(remark);
	
	
	}
	
	//修改车桩信息
	function bicycle_pileModify(){
		$("#bicycle_pileModifyFrom").attr("action","basicInfo/bikeInfo/bicycle_pileModify.do");
		$("#bicycle_pileModifyFrom").submit;
	}
		
	$(function() {
        $(".ui_timepicker").datetimepicker({
            //showOn: "button",
            //buttonImage: "./css/images/icon_calendar.gif",
            //buttonImageOnly: true,
            showSecond: true,
            timeFormat: 'hh:mm:ss',
            stepHour: 1,
            stepMinute: 1,
            stepSecond: 1
        })
    })
    
   $(function () {
        $(".ui_timepicker1").datetimepicker({
            showOn: "button",
            buttonImage: "date/css/images/icon_calendar.gif",
            buttonImageOnly: true,
            timeonly:true,
            showSecond: false,
            showMinute:false,
            showHour:false,
           timeFormat:''
            })
    })
	
	</script>
</head>
  
  
<body marginwidth="0" marginheight="0">

	<div class="container">
		
		<div class="public-content">
			
			<div id="oper" class="public-content">
				<div class="page">
					<input class="page-btn" style="width:80px"  type="button" value="返回" onclick="window.location.href='basicInfo/bikeInfo/queryPiles.do'">
				</div>
			</div>
			
			<div class="public-content-cont two-col">
				<table class="public-cont-table col-2">
					<tr>
						<th>ID</th>
						<th>供应商ID</th>
						<th>车桩编号</th>
						<th>所属车点</th>
						<th>状态</th>
						<th>安装日期</th>
						<th>拆解日期</th>						
						<th>操作人</th>
						<th>操作时间</th>
						<th>所存车辆</th>
						<th>备注</th>
						<th>操作</th>
					</tr>				
						 <c:forEach items="${pileList}" var="temp">
						 <tr>
							
							<td>${temp.pile_id}</td>
							<td>${temp.vender_id}</td>
							<td>${temp.pile_code}</td>
							<td>${temp.station_id}</td>
							<td>${temp.status}</td>		
							<td>${temp.install_time}</td>
							<td>${temp.disassembly_time}</td>
							<td>${temp.user_id}</td>
							<td>${temp.operator_time}</td>
							<td>${temp.bicycle_id}</td>
							<td>${temp.remark}</td>
							<td>
								<div class="page">
									<input type="button" class="page-btn" style="width:50px;" value="修改" onclick="modifyPile('${temp.pile_id}','${temp.vender_id}','${temp.pile_code}','${temp.station_id}','${temp.status}','${temp.install_time}','${temp.disassembly_time}','${temp.user_id}','${temp.operator_time}','${temp.bicycle_id}','${temp.remark}')">
								</div>
							</td>
						</tr>
						</c:forEach>
				</table>
				</div> 		
			<div   id="showpilewindow"  style="display:none"   >
				<div class="public-cont-left">
					<div class="public-cont-title">
						<h3 ><span >车桩信息修改</span></h3>
					</div>
					
					<form id="bicycle_pileModifyFrom">
					
					<div class="form-group mt0" style="display:none">
						<label for="">车桩ID</label>
						<input type="text" id="tempc_pile_id" name="pile_id" class="form-input-small">
					</div>
					
					<div class="form-group mt0">
						<label for="">车桩供应商</label>
						<input type="text" id="tempc_vender_id" name="vender_id" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">车桩编号</label>
						<input type="text" id="tempc_pile_code" name="pile_code" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">所属车点</label>
						<input type="text" id="tempc_station_id"  name="station_id" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">状态</label>
						<input type="text" id="tempc_status"  name="status" class="form-input-small">
					</div>
						<div class="form-group mt0">
						<label for="">安装日期</label>
						<input type="text" id="tempc_install_time" name="install_time" class="ui_timepicker1">
					</div>
					
					<div class="form-group mt0">
						<label for="">拆解日期</label>
						<input type="text" id="tempc_disassembly_time" name="disassembly_time" class="ui_timepicker1">
					</div>
					
					<div class="form-group mt0">
						<label for="">操作人</label>
						<input type="text" id="tempc_user_id" name="user_id" class="form-input-small">
					</div>
					
					<div class="form-group mt0">
						<label for="">操作时间</label>
						<input type="text" id="tempc_operator_time" name="operator_time" class="ui_timepicker">
					</div>
					
					<div class="form-group mt0">
						<label for="">所存车辆</label>
						<input type="text" id="tempc_bicycle_id" name="bicycle_id" class="form-input-small">
					</div>
					
					<div class="form-group mt0">
						<label for="">备注</label>
						<input type="text" id="tempc_remark" name="remark" class="form-input-small">
					</div>
					
				
					
			
					
					
					<div class="form-group mt0" style="text-align:center;margin-top:15px;">

						<input type="submit" class="sub-btn" value="修   改" onclick="bicycle_pileModify()">
						<input type="button" class="sub-btn" value="关  闭" onclick="">
					</div>
					</form>
				</div>
			</div>
				
				
				
			</div>
		</div>
	</div>
</body>
</html>