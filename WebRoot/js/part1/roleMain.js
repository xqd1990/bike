		//删除角色
		function deleteRole(id){
			if(window.confirm("确认删除吗？")) location.href="basicInfo/orgInfo/deleteRole.do?role_id=" + id;
		}
		//增加角色
		function addRole(){
			clearForm();
			$("form h2").html("新增");
			$("#id").val("-1");
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
			if(title == "新增") $("form").attr("action","basicInfo/orgInfo/addRole.do");
			else $("form").attr("action","basicInfo/orgInfo/updateRole.do");
			$("form").submit();
		}
		//模糊查询角色(AJAX)
		function search(target){
			var name = $("#search").val();
			if(desc != null && desc != "" && target >= 0){
				var map = {"role_name":name,"current":target};
				$.post("basicInfo/orgInfo/searchRole.do",map,function(data){
					$("#main").html(data);
				});
			}
		}
		//查询角色拥有的权限
		function listPermission(id, name, desc){
			clearForm();
			var map = {"roleId": id};
			$.post("basicInfo/orgInfo/listPermission.do",map,function(data){
				var permissionList = eval("(" + data + ")");
				var boxList = $("input[type='checkbox']");
				for(var i = 0;i < boxList.length;i++){
					for(var j = 0;j < permissionList.length;j++){
						if(boxList.eq(i).val() == permissionList[j].phaseId){
							boxList.eq(i).attr("checked","checked");
							break;
						}
					}
				}
				$("form h2").html("修改");
				$("#id").val(id);
				$("#name").val(name);
				$("#desc").val(desc);
				$("form").show();
			});
		}
		//清空表单
		function clearForm(){
			$("input[type='checkbox']").removeAttr("checked");
			$("form h2").html("");
			$("#id").val("");
			$("#name").val("");
			$("#desc").val("");
		}