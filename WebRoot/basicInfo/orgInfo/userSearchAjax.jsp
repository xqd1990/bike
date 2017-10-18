<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table border="1px solid #fff" cellspacing="0px" class="public-content-cont">
			<tr>
				<th>编号</th>
				<th>账号</th>
				<th>密码</th>
				<th>姓名</th>
				<th>角色</th>
				<th>固定电话</th>
				<th>移动电话</th>
				<th>电子邮件</th>
				<!-- <th>激活标志</th> -->
				<th>操作</th>
			</tr>
			<c:forEach items="${userList}" var="user" varStatus="i">
				<tr
					ondblclick="updateUser(${user.user_id},${user.role_id},'${user.login_name}','${user.username}','${user.password}',
											'${user.office_phone}','${user.mobile_phone}','${user.email}')">
					<td>${i.index + 1 + page.current*page.count}</td>
					<td>${user.login_name}</td>
					<td>${user.password}</td>
					<td>${user.username}</td>
					<td><c:forEach items="${allRoles }" var="role">
							<c:if test="${role.role_id == user.role_id }">${role.role_name }</c:if>
						</c:forEach></td>
					<td>${user.office_phone}</td>
					<td>${user.mobile_phone}</td>
					<td>${user.email}</td>
					<td class="table-fun">
						<a javascript:void(0) onclick="romoveUser(${user.user_id})" >注销</a>
						<a javascript:void(0) onclick="updateUser(${user.user_id},${user.role_id},'${user.login_name}','${user.username}','${user.password}',
											'${user.office_phone}','${user.mobile_phone}','${user.email}')" >修改</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div class="page">
			<input type="button" class="page-btn" style="width:80px;" value="首页" onclick="search(0)" />  
			<input type="button" class="page-btn" style="width:80px;" value="上一页" onclick="search(${page.current-1<0?-1:page.current-1})" /> 
			<input type="button" class="page-btn" style="width:80px;" value="下一页" onclick="search(${page.current+1>page.total-1?-1:page.current+1})" /> 
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