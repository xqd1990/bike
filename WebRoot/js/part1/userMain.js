		//注销用户
		function romoveUser(id){
			if(window.confirm("确认注销吗？")) location.href="basicInfo/orgInfo/removeUser.do?user_id=" + id;
		}
		//修改用户
		function updateUser(user_id, role_id, login_name, username, password, office_phone, mobile_phone, email){
			clearForm();
			$("form h2").html("修改用户");
			$("#user_id").val(user_id);
			$("#login_name").val(login_name);
			$("#login_name").attr("readonly","readonly");
			$("#username").val(username);
			$("#password").val(password);
			$("#office_phone").val(office_phone);
			$("#mobile_phone").val(mobile_phone);
			$("#email").val(email);
			var list = $("form input[type='radio']");
			for(var i = 0;i < list.size();i++){
				if(list.eq(i).val() == role_id){
					list.eq(i).attr("checked","checked");
					break;
				}
			}
			$("form").show();
		}
		//增加用户
		function addUser(){
			clearForm();
			$("#user_id").val(-1);
			$("form h2").html("新增用户");
			$("form").show();
		}
		//取消表单
		function cancel(){
			clearForm();
			$("form").hide();
		}
		//提交表单
		function sub(){
			var title = $("h2").html();
			if(!($("#login_name").val() == "" || $("#username").val() == "" || $("#password").val() == "" || $("form span").html() == "X")){
				if(title == "新增用户") $("form").attr("action","basicInfo/orgInfo/addUser.do");
				else $("form").attr("action","basicInfo/orgInfo/updateUser.do");
				$("form").submit();
			}
			else alert("请填写必填项");
		}
		//模糊查询用户(AJAX)
		function search(target){
			if(target >= 0){
				var map = {"login_name":$("#sLogin_name").val(),"username":$("#sUsername").val(),"role_name":$("#sRole_name").val(),"current":target};
				$.post("basicInfo/orgInfo/searchUser.do",map,function(data){
					$("#main").html(data);
				});
			}
		}
		//清空表单
		function clearForm(){
			$("input[type='radio']").removeAttr("checked");
			$("form h2").html("");
			$("#login_name").val("");
			$("#username").val("");
			$("#password").val("");
			$("#office_phone").val("");
			$("#mobile_phone").val("");
			$("#email").val("");
			$("#prom").val("");
			$("#login_name").removeAttr("readonly","readonly");
		}
		
		//表单验证，账号是否重复
		function checkAccount(){
			var map = {"login_name":$("#login_name").val()};
			$.post("basicInfo/orgInfo/checkAccount.do",map,function(data){
				if(data == "true"){
					alert("用户名重复");
					$("#login_name").val("");
					$("#prom").html("X");
				}
				else{
					$("#prom").html("√");
				}
			});
		}