		
		//--------订单明细处理-----------
		//添加一行明细
		var index = 1;
		function addRow(){
			$("#t1").append("<tr><td>" + (index+1) + "</td><td><input readOnly='true'/><img src='images/icon.png' onclick='showChoice(this)'/><input type='hidden' name='details[" + index + "].catagory_id' /></td>" + 
									"<td><input name='details[" + index + "].create_date' class='ui_timepicker' readonly='readonly' onchange='createBatchNo(this," + index + ")'/></td>" + 
									"<td><input name='details[" + index + "].batch_no' readonly='readonly'/></td>" + 
									"<td><input name='details[" + index + "].price' value=0 onblur='checkPrice(this)'/></td>" + 
									"<td><input name='details[" + index++ + "].remark'/></td>" + 
									"<td><img src='images/delete.gif' onclick='deleteRow(this)'/></td></tr>");
			init();
			countNum();
		}
		//删除一行明细,并修改每行表单的name属性
		function deleteRow(obj){
			$(obj).parent().parent().remove();
			var rows = $("#t1 tr:gt(0)");
			for(var i = 0;i < rows.length;i++){
				rows.eq(i).find("td").eq(0).html(i + 1);
				var row = rows.eq(i).find("input");
				row.eq(0).attr("name","details[" + i + "].catagory_id");
				row.eq(1).attr("name","details[" + i + "].create_date");
				row.eq(2).attr("name","details[" + i + "].batch_no");
				row.eq(3).attr("name","details[" + i + "].price");
				row.eq(4).attr("name","details[" + i + "].remark");
			}
			index = rows.length;
			countNum();
		}
		//校验单价是否数字并计算总价
		function checkPrice(obj){
			var num = $(obj).val();
			if(!(/^\d+\.?\d*$/.test(num))){
				alert("请输入数字");
				$(obj).val("0");
			}
			countPrice();
		}
		//根据生产日期创建批次号
		function createBatchNo(obj, index){
			var date = $(obj).val();
			date = date.substr(0,4) + date.substr(5,2) + date.substr(8,2);
			/*$("#t1 input").eq(index * 5 + 2).val(date);*/
			$(obj).parent().next("td").eq(0).find("input").val(date);
		}
		//计算购买总数
		function countNum(){
			$("#buy_num").val($("#t1 tr").length - 1);
		}
		//计算订单总价
		function countPrice(){
			var allPrice = 0;
			var prices = $("#t1 tr:gt(0)").find("td:eq(4)").find("input");
			for(var i = 0;i < prices.length;i++){
				allPrice += parseFloat(prices.eq(i).val());
			}
			$("#buy_price").val(allPrice);
		}
		
		
		
		//---------车辆类型选择---------
		//显示车辆类型选择框
		var target;	//明细单对象
		function showChoice(obj){
			target = obj;
			$("form").hide();
			$("#chDiv").show();
		}
		//隐藏车辆类型选择框
		function hideChoice(){
			$("form").show();
			$("#chDiv").hide();
			$("#chDiv tr").find("td:eq(0)").html("");
		}
		//选中车辆类型
		var catagory_id;
		function choiceBike(obj, id){
			catagory_id = id;
			var cell = $(obj).children().eq(0);
			if(cell.html() != "√"){
				$("#chDiv tr").find("td:eq(0)").html("");
				cell.html("√");
			}else{
				cell.html("");
			}
		}
		//提交车辆类型
		function subChoice(){
			var cells = $("#chDiv tr").find("td:eq(0)");
			for(var i = 0;i < cells.length;i++){
				var cell = cells.eq(i);
				if(cell.html() == "√"){
					hideChoice();
					$(target).prev().val(cell.next().eq(0).html());
					$(target).next().eq(0).val(catagory_id);
					break;
				}
			}
		}
		
		//计算当天日期yyyyMMddhhmmss
		function createDate(){
			var date = new Date();
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			var day = date.getDate();
			var hour = date.getHours();
			var min = date.getMinutes();
			var sec = date.getSeconds();
			var time = "" + year + checkDate(month) + checkDate(day) + checkDate(hour) + checkDate(min) + checkDate(sec);;
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
		//校验日期是否为单数
		function checkDate(time){
			if(/^\d$/.test(time)){
				return 0 + "" + time;
			}
			return time;
		}
		
		
		//返回显示界面
		function back(){
			$("form").hide();
			$("#main").show();
		}
		
		//查询界面操作
		function buy(){
			$("#main").hide();
			$("form").show();
		}
		