<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<table border="1px solid #fff" cellspacing="0px" class="public-content-cont">
			<tr>
				<th>编号</th>
				<th>角色名称</th>
				<th>角色说明</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${roleList}" var="role" varStatus="i">
				<tr
					ondblclick="listPermission(${role.role_id},'${role.role_name}','${role.role_describe}')">
					<td>${i.index + 1 + page.current*page.count}</td>
					<td>${role.role_name}</td>
					<td>${role.role_describe}</td>
					<td class="table-fun">
						<a javascript:void(0) onclick="deleteRole(${role.role_id})" >删除</a>
						<%-- <input type="button" value="删除" onclick="deleteRole(${role.role_id})" />  --%>
						<a javascript:void(0) onclick="listPermission(${role.role_id},'${role.role_name}','${role.role_describe}')" >修改</a>
						<%-- <input type="button" value="修改" onclick="listPermission(${role.role_id},'${role.role_name}','${role.role_describe}')" /> updateRole(${role.role_id},'${role.role_name}','${role.role_describe}') --%>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div class="page">
			<input type="button" class="page-btn" style="width:80px;" value="首页" onclick="search(0)" />  
			<input type="button" class="page-btn" style="width:80px;" value="上一页" onclick="search(${page.current-1<0?-1:page.current-1})" /> 
			<input type="button" class="page-btn" style="width:80px;" value="下一页" onclick="search(${page.current+1>=page.total-1?-1:page.current+1})" /> 
			<input type="button" class="page-btn" style="width:80px;" value="末页" onclick="search(${page.total - 1})" /><br />
			第<span style="color:red;font-weight:600">${page.current + 1 }</span>页 
			<select onchange="jumpTo()" id="targetPage">
			<option></option>
				<c:forEach begin="1" end="${page.total }" varStatus="i">
					<option>${i.index }</option>
				</c:forEach>
			</select>/<span style="color:red;font-weight:600">${page.total==0?1:page.total }</span>页 
					共<span style="color:red;font-weight:600">${page.size }</span>条
		</div>
