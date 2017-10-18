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
    function turnoff(){
		$("#checkwindow").hide();
		$("#addwindow").hide();
		$("#addstationwindow").hide();
	} 

	function addstation(){
		$("#addstationwindow").show();
		

	}
   /*  function addpile(){
		$("#pluswindow").show();
		

	} */
	function query(){
		$("#checkwindow").show();
		$("#title").text("查询页面");
		$("#submit").val("查询");
		$("#submit").attr("onclick","queryStation()");
	}
	
	function queryStation(){
		$("#form").attr("action","basicInfo/bikeInfo/queryStation.do");
		$("#form").submit;
	}
	
	
	//分页
	function go1(target) {
		var total = ${page.total};
		var current = ${page.current};
		var station_code=${empty station.station_code?-1:station.station_code};
		var station_name=${empty station.station_name?-1:station.station_name};
		var person_in_charge=${empty station.person_in_charge?-1:station.person_in_charge};
		var address=${empty station.address?-1:station.address};
		
		
		if (current >= 0 && current <= total) {
			if (current == target && (current == 0 || current == total - 1)) {
			} else {
		window.location.href = "basicInfo/bikeInfo/queryStation.do?current="
							+ target+"&station_code="+station_code+"&station_name="+station_name+"&person_in_charge="+person_in_charge+"&address="+address;
			}
		}
	} 
	
	function queryPile(obj){
		var id=$(obj).parent().parent().parent().children().eq(0).html();
		
		location.href="basicInfo/bikeInfo/queryPile.do?id=" + id;
	}
	
	function bicycle_stationAdd(){
	
		$("#addform").attr("action","basicInfo/bikeInfo/bicycle_stationAdd.do");
		$("#addform").submit();	
	}
	function bicycle_pileAdd(){
		$("#pluswindow").show();
	}
	function bicycle_pileRegist(){
		$("#plusform").attr("action","basicInfo/bikeInfo/bicycle_pileRegist.do")
	}
	/* function modifyStation(station_code,station_name,longitude,latitude,bicycle_pile_num,address,person_in_charge,build_time,run_time,user_id,create_time,remark){			
	 */
	 function modifyStation(station_id,station_code,station_name,longitude,latitude,bicycle_pile_num,address,person_in_charge,build_time,run_time,user_id,create_time,zxbj,remark){			
	
		$("#showstationwindow").show();
		$("#tempb_station_id").val(station_id);
		$("#tempb_station_code").val(station_code);
		$("#tempb_station_name").val(station_name);
		$("#tempb_longitude").val(longitude);
		$("#tempb_latitude").val(latitude);
		$("#tempb_bicycle_pile_num").val(bicycle_pile_num);
		$("#tempb_address").val(address);
		$("#tempb_person_in_charge").val(person_in_charge);
		$("#tempb_build_time").val(build_time);
		$("#tempb_run_time").val(run_time);
		$("#tempb_user_id").val(user_id);
		$("#tempb_create_time").val(create_time);
		$("#tempb_zxbj").val(zxbj);
		$("#tempb_remark").val(remark);
		

		
		
	}
	//修改车点信息
	function bicycle_stationModify(){
		$("#bicycle_stationModifyFrom").attr("action","basicInfo/bikeInfo/bicycle_stationModify.do");
	
	
	}


	//注销车点
	function logoutStation(station_id){
	
		if(window.confirm("确认注销吗？")) location.href="basicInfo/bikeInfo/logoutStation.do?station_id=" + station_id;
		
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
	
	</script>
</head>
  
  
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">信息列表</a></div>
		<div class="public-content">
			<div class="public-content-header">
			
			<div id="oper" class="public-content"><div class="page">
			<input class="page-btn" style="width:80px" type="button" onclick="query()" value="查询">
			<input class="page-btn" style="width:100px" type="button" onclick="addstation()" value="新增车点">
	<!-- 		<input class="page-btn" style="width:100px" type="button" onclick="addpile()" value="新增车桩"> -->
	
			
			</div></div>
		
				

			</div>
	
			<div class="clearfix"></div>
			<div class="public-content-cont two-col">
				<table class="public-cont-table col-2">
					<tr>
		
						<th>ID</th>
						<th>车点编号</th>
						<th>车点名称</th>
						<th>车点经度</th>
						<th>车点纬度</th>						
						<th>车点车桩数量</th>
						<th>地址</th>
						<th>负责人</th>
						<th>新建日期</th>
						<th>运行日期</th>
						<th>操作人</th>
						<th>创建时间</th>
						<th>注销标记</th>
						<th>备注</th>
						<th width="10%">操作</th>
					</tr>
					
					 <c:forEach items="${bicycle_stationList}" var="temp">
						<tr>
						<td>${temp.station_id}</td>
						<td>${temp.station_code}</td>
						<td>${temp.station_name}</td>
						<td>${temp.longitude}</td>
						<td>${temp.latitude}</td>
						<td>${temp.bicycle_pile_num}</td>
						<td>${temp.address}</td>
						<td>${temp.person_in_charge}</td>
						<td>${temp.build_time}</td>
						<td>${temp.run_time}</td>
						<td>${temp.user_id}</td>
						<td>${temp.create_time}</td>
						<td>${temp.zxbj}</td>
						<td>${temp.remark}</td>
						<td>
							<div class="page">
								<input type="button" class="page-btn" style="width:40px;" onclick="logoutStation('${temp.station_id}')" value="注销">
								<input type="button" class="page-btn" style="width:40px;" onclick="modifyStation('${temp.station_id}','${temp.station_code}','${temp.station_name}','${temp.longitude}','${temp.latitude}','${temp.bicycle_pile_num}','${temp.address}','${temp.person_in_charge}','${temp.build_time}','${temp.run_time}','${temp.user_id}','${temp.create_time}','${temp.zxbj}','${temp.remark}')" value="修改">
							</div>
						</td>
						</tr>
					</c:forEach>
				</table>
					<div class="page">
					<input type="button" class="page-btn" style="width:80px;" value="首页" onclick="go1(0)">
					<input type="button" class="page-btn" style="width:80px;" value="上一页" onclick="go1('${page.current-1}')">
					<input type="button"  class="page-btn" style="width:80px;" value="下一页" onclick="go1('${page.current+1}')">
					 第${page.current + 1 }页
				
					</select>/${page.total }页 共${page.size}条
					</div>
			
				
				
				
				</table>
				
				
				
		
				<div   id="checkwindow"  style="display:none"   >
				<div class="public-cont-left">
					<div class="public-cont-title">
						<h3 ><span id="title">ttt</span></h3>
					</div>
					
					<form id="form">
					<div class="form-group mt0">
						<label for="">车点编号</label>
						<input type="text" name="station_code" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">车点名称</label>
						<input type="text" name="station_name" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">负责人</label>
						<input type="text" name="person_in_charge" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">地址</label>
						<input type="text" name="address" class="form-input-small">
					</div>
					
					
					<div class="form-group mt0" style="text-align:center;margin-top:15px;">
						<input type="submit" id="submit" class="sub-btn" value="">
						<input type="button" class="sub-btn" value="关  闭" onclick="turnoff()">
					</div>
					</form>
				</div>
			</div>
			
			
			<div   id="pluswindow"  style="display:none"   >
				<div class="public-cont-left">
					<div class="public-cont-title">
						<h3 ><span >添加车桩</span></h3>
					</div>
					
					<form id="plusform">
					<div class="form-group mt0">
						<label for="">车桩供应商</label>
						<input type="text" name="vender_id" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">车桩编号</label>
						<input type="text" name="pile_code" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">所属车点</label>
						<input type="text" name="station_id" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">状态</label>
						<input type="text" name="status" class="form-input-small">
					</div>
						<div class="form-group mt0">
						<label for="">安装日期</label>
						<input type="text" name="install_time" class="ui_timepicker">
					</div>
					
					<div class="form-group mt0">
						<label for="">拆解日期</label>
						<input type="text" name="disassembly_time" class="ui_timepicker">
					</div>
					
					<div class="form-group mt0">
						<label for="">操作人</label>
						<input type="text" name="user_id" class="form-input-small">
					</div>
					
					<div class="form-group mt0">
						<label for="">操作时间</label>
						<input type="text" name="operator_time" class="ui_timepicker">
					</div>
					
					<div class="form-group mt0">
						<label for="">所存车辆</label>
						<input type="text" name="bicycle_id" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">备注</label>
						<input type="text" name="remark" class="form-input-small">
					</div>
				
					
					
					<div class="form-group mt0" style="text-align:center;margin-top:15px;">

						<input type="submit" class="sub-btn" value="添加车桩" onclick="bicycle_pileRegist()">
						<input type="button" class="sub-btn" value="关  闭" onclick="turnoff()">
					</div>
					</form>
				</div>
			</div>
					
					
				<div   id="addstationwindow" style="display:none">
				<div class="public-cont-left">
					<div class="public-cont-title">
						<h3 ><span id="title">新增车点</span></h3>
					</div>
					<form id="addform">
					<div class="form-group mt0">
						<label for="">车点编号</label>
						<input type="text" name="station_code" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">车点名称</label>
						<input type="text" name="station_name" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">车点经度</label>
						<input type="text" name="longitude" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">车点纬度</label>
						<input type="text" name="latitude" class="form-input-small">
					</div>
						<div class="form-group mt0">
						<label for="">车点车桩数量</label>
						<input type="text" name="bicycle_pile_num" class="form-input-small">
					</div>
					
					<div class="form-group mt0">
						<label for="">地址</label>
						<input type="text" name="address" class="form-input-small">
					</div>
					
					<div class="form-group mt0">
						<label for="">负责人</label>
						<input type="text" name="person_in_charge" class="form-input-small">
					</div>
					
					<div class="form-group mt0">
						<label for="">新建日期</label>
						<input type="text" id="build_time" name="build_time" class="ui_timepicker">
					</div>
					
					<div class="form-group mt0">
						<label for="">运行日期</label>
						<input type="text" name="run_time" class="ui_timepicker">
					</div>
					<div class="form-group mt0">
						<label for="">操作人</label>
						<input type="text" name="user_id" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">创建时间</label>
						<input type="text" name="create_time" class="ui_timepicker">
					</div>
					
					<div class="form-group mt0">
						<label for="">注销标记</label>
						<input type="text" name="zxbj" class="form-input-small">
					</div>
					
					<div class="form-group mt0">
						<label for="">备注</label>
						<input type="text" name="remark" class="form-input-small">
					</div>
					
					
					
					<div class="form-group mt0" style="text-align:center;margin-top:15px;">
						<input type="submit" class="sub-btn" value="添加车点" onclick="bicycle_stationAdd()">
		、
						<input type="button" class="sub-btn" value="关  闭" onclick="turnoff()">
					</div>
					</form>
				</div>
			</div>
			
			<div   id="showstationwindow"  style="display:none"   >
				<div class="public-cont-left">
					<div class="public-cont-title">
						<h3 ><span >车点信息修改</span></h3>
					</div>
					
					<form id="bicycle_stationModifyFrom">
					
					<div class="form-group mt0" style="display:none">
						<label for="">车点ID</label>
						<input type="text" id="tempb_station_id" name="station_id" class="form-input-small">
					</div>
					
					<div class="form-group mt0">
						<label for="">车点编号</label>
						<input type="text" id="tempb_station_code" name="station_code" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">车点名称</label>
						<input type="text" id="tempb_station_name" name="station_name" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">车点经度</label>
						<input type="text" id="tempb_longitude"  name="longitude" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">车点纬度</label>
						<input type="text" id="tempb_latitude"  name="latitude" class="form-input-small">
					</div>
						<div class="form-group mt0">
						<label for="">车点车桩数量</label>
						<input type="text" id="tempb_bicycle_pile_num" name="bicycle_pile_num" class="form-input-small">
					</div>
					
					<div class="form-group mt0">
						<label for="">地址</label>
						<input type="text" id="tempb_address" name="address" class="form-input-small">
					</div>
					
					<div class="form-group mt0">
						<label for="">负责人</label>
						<input type="text" id="tempb_person_in_charge" name="person_in_charge" class="form-input-small">
					</div>
					
					<div class="form-group mt0">
						<label for="">新建日期</label>
						<input type="text" id="tempb_build_time" name="build_time" class="ui_timepicker">
					</div>
					
					<div class="form-group mt0">
						<label for="">运行日期</label>
						<input type="text" id="tempb_run_time" name="run_time" class="ui_timepicker">
					</div>
					
					<div class="form-group mt0">
						<label for="">操作人</label>
						<input type="text" id="tempb_user_id" name="user_id" class="form-input-small">
					</div>
					
					<div class="form-group mt0">
						<label for="">创建时间</label>
						<input type="text" id="tempb_create_time" name="create_time" class="ui_timepicker">
					</div>
					
					<div class="form-group mt0">
						<label for="">注销标记</label>
						<input type="text" id="tempb_zxbj" name="zxbj" class="form-input-small">
					</div>
				
					<div class="form-group mt0">
						<label for="">备注</label>
						<input type="text" id="tempb_remark" name="remark" class="form-input-small">
					</div>
				
					
					
					<div class="form-group mt0" style="text-align:center;margin-top:15px;">

						<input type="submit" class="sub-btn" value="修   改" onclick="bicycle_stationModify()">
						<input type="button" class="sub-btn" value="关  闭" onclick="turnoff()">
					</div>
					</form>
				</div>
			</div>
			
			
			
			
					
					
				</div>
			</div>
				
			</div>
		</div>
	</div>
</body>
</html>