<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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

	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript">

    function turnoff(){
		$("#pluswindow").hide();
	} 

	function add(){
		$("#title").text("新增页面");
		$("#pluswindow").show();
	}
	function check(){
		$("#title").text("查询页面");
		$("#pluswindow").show();
	}
		

		

	</script>
</head>
  
  
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">信息列表</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3 style="display: inline-block;">修改网站配置</h3>
			<!-- 	<div class="public-content-right fr">
					<a href="" style="height: 24px; width: 60px;border: 1px solid #ccc;font-size: 12px;text-align:center">添加信息</a>
				</div> -->
			</div>
			<div class="clearfix"></div>
			<div class="public-content-cont two-col">
		
				<div   id="pluswindow"     >
				<div class="public-cont-left">
					<div class="public-cont-title">
						<h3 ><span id="title">ttt</span></h3>
					</div>
					<div class="form-group">
						<label for="">分类名称</label>
						<select name="" id="" class="form-select">
							<option value="1">一级分类</option>
						</select>
					</div>
					<div class="form-group mt0">
						<label for="">分类名称</label>
						<input type="text" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">排序编号</label>
						<input type="text" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">外链</label>
						<input type="text" class="form-input-small">
					</div>
					<div class="form-group">
						<label for="">缩略图</label>
						<input type="text" class="form-input-small">
						<div class="file"><input type="file" class="form-input-file" />选择文件</div>
						<div class="file"><input type="submit" class="form-input-file"/>上传</div>
					</div>
					<div class="clearfix"></div>
					<div class="form-group">
						<label for="">导航显示</label>
						<input type="checkbox" />显示
					</div>
					<div class="form-group mt0">
						<label for="">查看权限</label>
						<input type="checkbox" />会员
					</div>
					<div class="form-group mt0">
						<label for="">新栏目</label>
						<input type="checkbox" />显示
					</div>
					<div class="form-group mt0" style="text-align:center;margin-top:15px;">
						<input type="submit" class="sub-btn" value="提   交">
						<input type="button" class="sub-btn" value="关  闭" onclick="turnoff()">
					</div>
				</div>
			</div>
				
			</div>
		</div>
	</div>
</body>
</html>