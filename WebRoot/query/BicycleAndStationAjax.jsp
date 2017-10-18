<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table border="1px solid #fff" cellspacing="0px" class="public-content-cont">
			<tr>
				<th>编号</th>
				<th>车点编号</th>
				<th>车点名称</th>
				<th>车点地址</th>
				<th>车桩数量</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${stationList}" var="temp" varStatus="i">
				<tr
					ondblclick="showPile(${temp.station_id})">
					<td>${i.index + 1 + page.current*page.count}</td>
					<td>${temp.station_code}</td>
					<td>${temp.station_name}</td>
					<td>${temp.address}</td>
					<td>${temp.bicycle_pile_num}</td>
					<td class="table-fun"><a javascript:void(0) onclick="showPile(${temp.station_id})" >查看</a></td>
				</tr>
			</c:forEach>
		</table>
<div class="page">
	<input type="button" class="page-btn" style="width:80px;" value="首页" onclick="searchStation(0)" /> 
	<input type="button" class="page-btn" style="width:80px;" value="上一页" onclick="searchStation(${page.current-1})" />
	<input type="button" class="page-btn" style="width:80px;" value="下一页" onclick="searchStation(${page.current+1>(page.total - 1)?-1:page.current+1}})" /> 
	<input type="button" class="page-btn" style="width:80px;" value="末页" onclick="searchStation(${page.total - 1})" /><br/>
	第<span style="color:red;font-weight:600">${page.current + 1 }</span>页
	<select onchange="jumpTo()" id="targetPage">
		<option></option>
		<c:forEach begin="1" end="${page.total }" varStatus="i">
			<option>${i.index }</option>
		</c:forEach>
	</select>/<span style="color:red;font-weight:600">${page.total==0?1:page.total }<span style="color:red;font-weight:600">页 
					共<span style="color:red;font-weight:600">${page.size }</span>条
</div>

