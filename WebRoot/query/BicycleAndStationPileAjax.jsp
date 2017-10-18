<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="public-content-header"><h3>车桩信息</h3></div>
<table border="1px solid #fff" cellspacing="0px" class="public-content-cont">
	<tr>
		<th>编号</th>
		<th>车桩编号</th>
		<th>车桩状态</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${pileList}" var="temp" varStatus="i">
		<tr>
			<td>${i.index + 1 + page.current*page.count}</td>
			<td>${temp.pile_code}</td>
			<td>
				<c:if test="${temp.status == 1}">有车</c:if>
				<c:if test="${temp.status == 2}">无车</c:if>
				<c:if test="${temp.status == 9}">报废</c:if>
			</td>
			<td class="table-fun">
				<c:if test="${temp.status == 1}">
					<a javascript:void(0) onclick="showBicycle(${temp.pile_id})" style="width:80px;color:#52afb7;background:#D1E8FF;">查看车辆</a>
				</c:if>
				${temp.status==1?" ":"没有车辆"}
			</td>
		</tr>
	</c:forEach>
</table><br/>
<div id="bike">
车辆类型：<input id="catagory_name"/>&nbsp;
车辆编号：<input id="bicycle_code"/><br/>
<!-- <input type="button" value="取消" onclick="cancel()"/> -->
</div><br/>
<div class="page">
	<input type="button" class="page-btn" value="返回" onclick="back()" />
</div>

