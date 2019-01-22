<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TMS物流管理系统</title>
<jsp:include page="../commons/header.jsp"></jsp:include>
</head>

<body class="no-skin">
	<!-- 导航栏 start		-->
	<jsp:include page="../commons/navbar.jsp"></jsp:include>
	<!-- 导航栏 end -->
	<div class="main-container ace-save-state" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.loadState('main-container')
			} catch (e) {
			}
		</script>

		<!--- 内容主体 start -->
		<div class="main-content">
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a
							href="${pageContext.request.contextPath }/admin/toIndex.do">首页</a>
						</li>

						<li><a
							href="${pageContext.request.contextPath }/admin/toIndex.do">订单管理</a>
						</li>
						<li class="active">新增订单</li>
					</ul>
					<!-- /.breadcrumb -->
				</div>
				<div class="page-content">
					<div class="widget-box transparent" id="widget-box-13">
						<div class="widget-header">
							<div class="widget-toolbar no-border">
								<ul class="nav nav-tabs" id="myTab2">
									<li class="active"><a data-toggle="tab" href="#">新增订单</a>
									</li>
								</ul>
							</div>
						</div>
						<form action="${pageContext.request.contextPath }/order/editOrder.do" method="post"  id="OrderID">
							<input name="token" type="hidden" value="${sessionScope.token }"/>
							<input name="token.invoke" type="hidden" value="/order/addOrder.do"/>
							<input name="order_id" type="hidden" value="${order.order_id }"/>
							<table class="table table-bordered">
								<tr>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">业务员：</td>
									<td id="salesmanID"><c:choose>
											<c:when
												test="${sessionScope.admin_info.admin_id==1 ||sessionScope.admin_info.admin_id==3 }">
												<select name="staff_no" id="staff_noID" class=" span1-1">
													<c:forEach var="admin" items="${requestScope.admins }">
														<option value="${admin.admin_id}">${admin.admin_name}</option>
													</c:forEach>
												</select>
											</c:when>
											<c:otherwise>
												<select name="staff_no" id="staff_noID" class=" span1-1"
													readonly unselectable="on">
													<option value="${sessionScope.admin_info.admin_id}">${sessionScope.admin_info.admin_name}</option>
												</select>
											</c:otherwise>
										</c:choose></td>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">客户：</td>
									<td id="customerID"><select name="customer_id"
										id="customer_id" class=" span1-1">
											<c:forEach var="customer" items="${order.customers }">
											<c:choose>
											<c:when test="${customer.customer_id==order.customer_id }">
												<option value="${customer.customer_id }" selected="selected">${customer.customer_name}</option>
											</c:when>
											<c:otherwise>
												<option value="${customer.customer_id }" selected="selected">${customer.customer_name}</option>
											</c:otherwise>						
											</c:choose>
												
											</c:forEach>

									</select></td>

								</tr>

								<tr>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">到达国家：</td>
									<td width="23%"><select name="order_destination"
										id="order_destination" class=" span1-1">
										<c:if test="${order.order_destination=='CHI' }">
											<option value="CHI" selected="selected">中国</option>
											<option value="MYS">马来西亚</option>
											<option value="IFI">冰火岛</option>
											<option value="SPG">新加坡</option></c:if>
											<c:if test="${order.order_destination=='MYS' }">
											<option value="CHI">中国</option>
											<option value="MYS" selected="selected">马来西亚</option>
											<option value="IFI">冰火岛</option>
											<option value="SPG">新加坡</option></c:if><c:if test="${order.order_destination=='IFI' }">
											<option value="CHI">中国</option>
											<option value="MYS">马来西亚</option>
											<option value="IFI" selected="selected">冰火岛</option>
											<option value="SPG">新加坡</option></c:if><c:if test="${order.order_destination=='SPG' }">
											<option value="CHI" ">中国</option>
											<option value="MYS">马来西亚</option>
											<option value="IFI">冰火岛</option>
											<option value="SPG" selected="selected">新加坡</option></c:if>
									</select></td>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">收货地址：</td>
									<td width="23%"><input type="text"
										name="order_recieveraddress" id="recieveraddress"
										style="width: 400px" value="${order.order_recieveraddress}"/><font color="red" >*必填项</font></td>
								</tr>

								<tr>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">收货人：</td>
									<td><input type="text" name="order_recievername"
										id="recievername" class=" span2" value="${order.order_recievername}"/><font color="red" >*必填项</font></td>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">联系电话：</td>
									<td width="23%"><input type="text"
										name="order_recieverphone" id="recieverphone" class=" span2" value="${order.order_recieverphone}"/><font color="red" >*必填项</font></td>
								</tr>
								<tr>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">付款方式：</td>
									<td><select name="order_paytype" id="order_paytype"
										class=" span1-1">
											<option value="预付" selected="selected">预付</option>
											<option value="到付">到付</option>
											<option value="部分预付部分到付">部分预付</option>
									</select></td>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">货运方式：</td>
									<td width="23%"><select name="order_deliverytype"
										id="order_deliverytype" class=" span1-1">
										<c:if test="${order.order_deliverytype=='海运' }">
											<option value="海运" selected="selected">海运</option>
											<option value="空运">空运</option>
											<option value="陆运">陆运</option></c:if>
											<c:if test="${order.order_deliverytype=='空运' }">
											<option value="海运">海运</option>
											<option value="空运" selected="selected">空运</option>
											<option value="陆运">陆运</option></c:if>
											<c:if test="${order.order_deliverytype=='陆运' }">
											<option value="海运">海运</option>
											<option value="空运">空运</option>
											<option value="陆运" selected="selected">陆运</option></c:if>
									</select></td>
								</tr>



							</table>
							<table class="table table-bordered" id="goodsId">
								<tr>
									<td align="center" bgcolor="#f1f1f1"><strong>货物名称</strong></td>
									<td align="center" bgcolor="#f1f1f1"><strong>数量</strong></td>
									<td align="center" bgcolor="#f1f1f1"><strong>单位</strong></td>
									<td align="center" bgcolor="#f1f1f1"><strong>单价</strong></td>
									<td align="center" bgcolor="#f1f1f1"><strong>总价值(计算关税)</strong></td>
									<td align="center" bgcolor="#f1f1f1"><strong>备注</strong></td>
									<td align="center" bgcolor="#f1f1f1"><img height="20"
										width="20" onclick="addRow()"
										src="${pageContext.request.contextPath }/lib/ace-admin/images/add.jpg" /></td>


								</tr>
								<c:forEach var="item" items="${order.items }" varStatus="a">
								<tr id='index"+${a.index }+"'>
									<td align="center"><input type="hidden"
										name="id${a.index }" id="item_name1" value="${item.id }" class=" span2" /><input type="text"
										name="item_name${a.index }" id="item_name1" value="${item.item_name }" class=" span2" /><font color="red" >*</font></td>
									<td align="center"><input type="text"
										name="item_num${a.index }" id="item_num1"
										class=" span1 text-center" value="${item.item_num }"/><font color="red" >*</font></td>
									<td align="center"><input type="text"
										name="item_unit${a.index }" id="item_unit1"
										class=" span1 text-center" value="${item.item_unit }"/><font color="red" >*</font></td>
									<td align="center"><input type="text"
										name="item_pruefee${a.index }" id="item_pruefee1"
										class=" span1 text-center" value="${item.item_pruefee }"/><font color="red" >*</font></td>
									<td align="center"><input type="text"
										name="item_totalfee${a.index }" id="item_totalfee1"
										class=" span1 text-center" value="${item.item_totalfee }"/><font color="red" >*</font></td>
									<td align="center"><input type="text"
										name="item_remark${a.index }" id="item_remark1"
										class=" span2" value="${item.item_remark }"/></td>
									<td align="center" bgcolor="#f1f1f1"><img height="20"
										width="20" onclick="deleteRow(0)"
										src="${pageContext.request.contextPath }/lib/ace-admin/images/delete.jpg" /></td>
								</tr>
									</c:forEach>
							</table>
							<table class="table table-bordered">
								<tr>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">取件方式：</td>
									<td width="23%"><select name="pick_type" id="pick_type"
										onchange="changePcikType(this)" class=" span1-1">
											<option value="客户送达">客户送达</option>
											<option value="快递邮递">快递邮递</option>
											<option value="上门取件">上门取件</option>
									</select></td>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">物流公司：</td>
									<td><input type="text" name="pick_transcompany"
										id="pick_transcompany" class="span1-1" disabled="disabled"
										value="${order.pick.pick_transcompany}" /></td>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">物流单号：</td>
									<td><input type="text" name="pick_transid"
										id="pick_transid" class="span1-1" disabled="disabled" value="${order.pick.pick_transid}" /></td>
								</tr>
								<!-- 快递、客户送达  收件地址等信息 -->
								<tr id="pickRecieverID">
									<td align="right" nowrap="nowrap" bgcolor="#f1f1f1">公司收件人：</td>
									<td><input type="text" name="input13" id="input13"
										class="span1-1" value="钱仓管" disabled="disabled" /></td>
									<td align="right" nowrap="nowrap" bgcolor="#f1f1f1">公司收件地址：</td>
									<td><input type="text" name="input12" id="input12"
										style="width: 300px" value="广州市白云区机场路15号" disabled="disabled" /></td>
									<td align="right" nowrap="nowrap" bgcolor="#f1f1f1">公司联系电话：</td>
									<td><input type="text" name="input16" id="input16"
										class="span1-1" value="18866668888" disabled="disabled" /></td>
								</tr>

								<!-- 上门取件 取件地址等信息 -->
								<tr id="pickID">
								</tr>

								<tr>
									<td align="right" nowrap="nowrap" bgcolor="#f1f1f1">订单备注：</td>
									<td colspan="5" nowrap="nowrap"><input type="text"
										name="remark" id="order_remark" class="span10" value="${order.remark}"/></td>
								</tr>
							</table>

							<table class="margin-bottom-20  table no-border">
								<tr>
									<td class="text-right"><input type="submit" value="确定"
										class="btn btn-info  " style="width: 80px;"  /></td>
									<td class="text-left"><a
										href="${pageContext.request.contextPath }/admin/toIndex.do"><buttom
												class="btn btn-info  " style="width:80px;">返回</buttom></a></td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!--页尾 start -->
		<jsp:include page="../commons/footer.jsp"></jsp:include>
	</div>
<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${pageContext.request.contextPath}/lib/ace-admin/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="${pageContext.request.contextPath}/lib/ace-admin/js/jquery-2.1.4.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/lib/ace-admin/js/layer2.4/layer.js"></script>
		<script src="${pageContext.request.contextPath}/lib/ace-admin/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/lib/ace-admin/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/lib/ace-admin/js/jquery.form.js"></script>
	<script>
		var start = 0;
		function addRow() {

			start++;
			var row = "<tr id=index"+ start+">"
					+ "<td align='center'><input type='text' name='item_name"+start+"' id='item_name"+start+"' class=' span2' /><font color='red' >*</font></td>"
					+ "<td align='center'><input type='text' name='item_num"+start+"' id='item_num"+start+"' class=' span1 text-center' /><font color='red' >*</font></td>"
					+ "<td align='center'><input type='text' name='item_unit"+start+"' id='item_unit"+start+"' class='span1 text-center' /><font color='red' >*</font></td>"
					+ "<td align='center'><input type='text' name='item_pruefee"+start+"' id='item_pruefee"+start+"' class='span1 text-center' /><font color='red' >*</font></td>"
					+ "<td align='center'><input type='text' name='item_totalfee"+start+"' id='item_totalfee"+start+"' class='span1 text-center' /><font color='red' >*</font></td>"
					+ "<td align='center'><input type='text' name='item_remark"+start+"' id='item_remark"+start+"' class=' span2' /></td>"

					+ "<td align='center' bgcolor='#f1f1f1'><img height='20' width='20'  onclick='deleteRow("
					+ start
					+ ")' src='${pageContext.request.contextPath }/lib/ace-admin/images/delete.jpg' /></td>"
					+ "</tr>";
			if(start>10){
				alert("超过10行，不能增加！！！")
			}else{
				$("#goodsId").append(row);
			}
		}

		function deleteRow(start) {
			$("#index" + start).remove();
		}

		var pickHtml = '<td align="right" nowrap="nowrap" bgcolor="#f1f1f1">取件地址：</td>'
				+ '<td><input type="text" name="pick_address" id="pick_address" style="width: 270px" class="span1-1" value="${order.pick.pick_address}"/><font color="red" >*</font></td>'
				+ '<td align="right" nowrap="nowrap" bgcolor="#f1f1f1">联系电话：</td>'
				+ '<td><input type="text" name="pick_phone" id="pick_phone"  class="span1-1" value="${order.pick.pick_phone}"/><font color="red" >*</font></td>'
				+ '<td align="right" nowrap="nowrap" bgcolor="#f1f1f1">取件联系人：</td>'
				+ '<td><input type="text" name="pick_name" id="pick_name" class="span1-1" value="${order.pick.pick_name}" /><font color="red" >*</font></td>';
		function changePcikType(obj) {
			//console.log(obj.value);
			if (obj.value == "快递邮递") {
				$("#pick_transcompany").removeAttr("disabled");
				$("#pick_transid").removeAttr("disabled");
				$("#pickID").html("");
			} else if (obj.value == "上门取件") {

				/* var pickHtml = '<td align="right" nowrap="nowrap" bgcolor="#f1f1f1">取件地址：</td>'
							+'<td><input type="text" name="pick_address" id="pick_address"  class="span1-1" /></td>'
							+'<td align="right" nowrap="nowrap" bgcolor="#f1f1f1">联系电话：</td>'
							+'<td><input type="text" name="pick_phone" id="pick_phone"  class="span1-1" /></td>'
							+'<td align="right" nowrap="nowrap" bgcolor="#f1f1f1">取件联系人：</td>'
							+'<td><input type="text" name="pick_name" id="pick_name" class="span1-1"  /></td>'; */
				$("#pickID").html(pickHtml);

				$("#pick_transcompany").attr("disabled", "disabled");
				$("#pick_transid").attr("disabled", "disabled");
				$("#pick_transcompany").val("");
				$("#pick_transid").val("");

			} else {
				$("#pickID").html("");
				$("#pick_transcompany").attr("disabled", "disabled");
				$("#pick_transid").attr("disabled", "disabled");
				$("#pick_transcompany").val("");
				$("#pick_transid").val("");
			}
		}
		
		/* $(function(){
			$.fn.serializeObject=function(){
				var o={};
				var a=this.serializeArray();
				$.each(a,function(){
					if(o[this.name]){
						if(!o[this.name].push){
							o[this.name]=[o[this.name]];
						}
						o[this.name].push(this.value||'');
					}else{
						o[this.name]=this.value||'';
					}
				});return o;
			};
		
		var v=$("#OrderID").serializeObject();
		
			$("#OrderID").ajaxForm({
				contentType:"application/json;charset=utf-8",
				url:"${pageContext.request.contextPath }/order/addOrder.do",
				dataType: "text",
				data:JSON.stringify(v),
				beforeSubmit : function(arr,form) {
					var uflag = true;
					 for (var i=0; i < arr.length-1; i++) {
					       if (!arr[i].value) {
					    	   alert("不能留空，请完善");
					    	   if($("form table tr td input[name='item_remark"+i+"']" ).val()){
						    		console.log($("form table tr td input[name='item_remark0']" ).val());
						    		return true;
						    	}
					           return false;
					        }
					    	
					   } 
					 console.log(v)
					return uflag; 
					//return true;
				},
				success : function(data) {
					console.log(data);
					if (1 == data.data) {
						layer.alert("提交成功");
					} else {
						layer.alert("提交失败");
					}
				}
			});
		});  */
	</script>

</body>
</html>
