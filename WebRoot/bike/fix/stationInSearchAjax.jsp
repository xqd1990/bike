<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table border="1px solid #fff" cellspacing="0px" class="public-content-cont">
	<tr>
		<th>编号</th>
		<th>车点编号</th>
		<th>车点名字</th>
		<th>车点地址</th>
		<th>车桩数量</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${stationList }" var="station" varStatus="i">
		<tr
			ondblclick="showpile(${station.station_id },${station.bicycle_pile_num },'${station.station_name }')">
			<td>${i.index + 1 + page.current*page.count}</td>
			<td>${station.station_code }</td>
			<td>${station.station_name }</td>
			<td>${station.address }</td>
			<td>${station.bicycle_pile_num }</td>
			<td class="page"><input class="page-btn" style="width:80px;"  type="button" value="查看车桩"
				onclick="showpile(${station.station_id },${station.bicycle_pile_num },'${station.station_name }')" />
				
			</td>
		</tr>
	</c:forEach>
</table>
<br />
<div class="page"><input class="page-btn" style="width:80px;" type="button" value="首页" onclick="search(0)" /> <input
			class="page-btn" style="width:80px;" type="button" value="上一页" onclick="search(${page.current-1})" /> <input
			class="page-btn" style="width:80px;" type="button" value="下一页" onclick="search(${page.current+1})" /> <input
			class="page-btn" style="width:80px;" type="button" value="末页" onclick="search(${page.total - 1})" /><br />
		<br /><select onchange="searchTo()"
			id="targetPage">
			<option>${page.current + 1 }</option>
			<c:forEach begin="1" end="${page.total }" varStatus="i">
				<option>${i.index }</option>
			</c:forEach>
		</select>/<span style="color:red;font-weight:600">${page.total }</span>页 共<span style="color:red;font-weight:600">${page.size }</span>条
</div>
