		//选择车点车桩
		var i = -1;
		function selPile(id){
			i = id;
			$("#main").hide();
			$("#sele").show();
		}
		//返回显示页面
		function cancel(){
			$("#sele").hide();
			$("#main").show();
			$("#station").val("选择车点");
			$("#pile").attr("disabled","disabled");
		}
		
		//根据车点查询车桩(AJAX)
		function searchPile(obj){
			var map = {"station_id":$(obj).val()};
			$.post("bike/dispatch/searchPiles.do", map, function(data){
				$("#pile").html(data);
				if(data != ""){
					$("#pile").removeAttr("disabled");
				}
			});
		}
		
		//校验日期是否为单数
		function checkDate(time){
			if(/^\d$/.test(time)){
				return 0 + "" + time;
			}
			return time;
		}
		//计算当天日期yyyy-MM-dd hh:mm:ss
		function getFullDate(){
			var date = new Date();
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			var day = date.getDate();
			var hour = date.getHours();
			var min = date.getMinutes();
			var sec = date.getSeconds();
			return year + "-" + checkDate(month) + "-" + checkDate(day) + " " + checkDate(hour) + ":" + checkDate(min) + ":" + checkDate(sec);
		}
		
		