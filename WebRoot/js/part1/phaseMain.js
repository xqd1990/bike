		//删除权限
		function deletePhase(phaseId){
			if(window.confirm("确认删除吗？")){
				$.post("basicInfo/orgInfo/isUsed.do",{"phaseId":phaseId},function(data){
					if(data == "true") alert("删除失败，权限正在使用中");
					else location.href="basicInfo/orgInfo/deletePhase.do?phaseId=" + phaseId;			
				});
			}
		}
		//修改权限
		function updatePhase(phaseId,url,description){
			$("form h2").html("修改");
			$("#phaseId").val(phaseId);
			$("#url").val(url);
			$("#description").val(description);
			$("form").show();
		}
		//增加权限
		function addPhase(){
			$("form h2").html("新增");
			$("#phaseId").val("-1");
			$("form").show();
		}
		//取消表单
		function cancel(){
			$("form h2").html("");
			$("#phaseId").val("");
			$("#url").val("");
			$("#description").val("");
			$("form").hide();
		}
		//提交表单
		function sub(){
			var title = $("h2").html();
			if(title == "新增") $("form").attr("action","basicInfo/orgInfo/addPhase.do");
			else $("form").attr("action","basicInfo/orgInfo/updatePhase.do");
			$("form").submit();
		}
		//模糊查询权限
		function search(target){
			var desc = $("#search").val();
			if(desc != null && desc != "" && target >= 0){
				var map = {"description":desc,"current":target};
				$.post("basicInfo/orgInfo/searchPhase.do",map,function(data){
					$("#main").html(data);
				});
			}
		}