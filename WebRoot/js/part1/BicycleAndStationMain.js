		//返回
		function back(){
			$("#pile").hide();
			$("#main").show();
			$("#sele").show();
		}
		
		//根据车点查询车桩(AJAX)
		function showPile(id){
			var map = {"station_id":id};
			$.post("query/searchPile.do", map, function(data){
				$("#pile").html(data);
				$("#main").hide();
				$("#sele").hide();
				$("#pile").show();
			});
		}
		//根据车桩查询车辆(AJAX)
		function showBicycle(pileId){
			$.post("query/searchBicycle.do",{"pile_id":pileId},function(data){
				var bicycle = eval("(" + data + ")");
				$("#catagory_name").val(bicycle.catagory_name);
				$("#bicycle_code").val(bicycle.bicycle_code);
				$("#bike").show();
			});
		}
		//关闭车辆信息页面
		function cancel(){
			$("#bike").hide();
		}
		//联合查询指定的车点(AJAX)
		function searchStation(current){
			if(current >= 0){
				var map = {"station_name":$("#stationName").val(), "address":$("#address").val() , "current":current};
					$.post("query/searchStation.do", map, function(data){
					$("#main").html(data);
				});
			}
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