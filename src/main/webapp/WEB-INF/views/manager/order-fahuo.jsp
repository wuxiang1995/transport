<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%-- <%@taglib prefix="s" uri="/struts-tags" %> --%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/lib/ace-admin/css/bootstrap-datepicker3.min.css" />
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
							href="${pageContext.request.contextPath }/admin/toIndex.do">业务管理</a>
						</li>
						<li class="active">订单发货</li>
					</ul>
					<!-- /.breadcrumb -->
				</div>
				<div class="page-content">
					<div class="widget-box transparent" id="widget-box-13">
						<div class="widget-header">
							<div class="widget-toolbar no-border">
								<ul class="nav nav-tabs" id="myTab2">
									<li class="active"><a data-toggle="tab" href="#">订单发货</a>
									</li>
								</ul>
							</div>
						</div>
	<form action="${pageContext.request.contextPath }/order/orderFahuo.do" method="post" id="OrderFeeFormId">
		<input name="token" type="hidden" value="${sessionScope.token }"/>
		<input name="token.invoke" type="hidden" value="/order/orderFahuo.do"/>
		<input name="order_id" type="hidden" value="${orderdd.order_id }"/>
		<table class="table table-bordered">
	         <tr>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">订单编号：</td>
		     	 <td width="23%">
		     	 	 ${orderdd.order_id }  
		     	 </td>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">客户：</td>
		     	 <td width="23%">
		     	 	 	${orderdd.customer.customer_name }
		     	 </td>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">寄件地址：</td>
		     	 <td width="23%">
		     	 		${orderdd.customer.customer_address }
		     	 	 </td>
		     	 	 
	     	 	   
	     	</tr>
	     	<tr>
	     	<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">寄件手机号：</td>
		     	 <td width="23%">
		     	 		${orderdd.customer.customer_phone }
		     	 	 </td>
	     	 	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">付款方式：</td>
			     <td width="23%"> 
			     	${orderdd.order_paytype }
	           	 </td>
	           	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">车辆选择：</td>
			     <td width="23%"> 
			     	<select name="car_id" id=""
										class=" span1-1">
										<c:forEach var="car" items="${orderdd.cars }">
											<option value="${car.id }">${car.car_no }</option>
										</c:forEach>
									</select>
	           	 </td>
	     	</tr>
	         <tr>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">收货地址：</td>
		     	 <td width="23%">
		     	 		${orderdd.order_recieveraddress }
		     	 	 </td>
			     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">收货人：</td>
			     <td> ${orderdd.order_recievername }</td>
			     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">联系电话：</td>
		     	 <td width="23%">
		     	 	${orderdd.order_recieverphone }
	     	 	 </td>
	     	</tr>
	   	  </table>
	      <table class="table table-bordered" >
	        <tr>
	           <td align="center" bgcolor="#f1f1f1"><strong>序号</strong></td>
	           <td align="center" bgcolor="#f1f1f1"><strong>货物名称</strong></td>
	           <td align="center" bgcolor="#f1f1f1"><strong>数量</strong></td>
	           <td align="center" bgcolor="#f1f1f1"><strong>单位</strong></td>
	           <td align="center" bgcolor="#f1f1f1"><strong>核算体积(立方)</strong></td>
	           <td align="center" bgcolor="#f1f1f1"><strong>核算重量(千克)</strong></td>
	         </tr>
	         <c:forEach items="${orderdd.orderItems }" var="orderItem" varStatus="s">
	         	 <tr >
	           <td align="center">${s.index+1 }</td>
	           <td align="center">${orderItem.item_name}</td>
	           <td align="center">${orderItem.item_num}</td>
	           <td align="center">${orderItem.item_unit}</td>
	           <td align="center">${orderItem.item_truesize}</td>
	           <td align="center">${orderItem.item_truesize}</td>
	          </tr> 
	         </c:forEach>
	       </table>
		     <table  class="margin-bottom-20  table no-border" >
		       <tr>
		     	<td class="text-right"><input type="submit" value="发货" class="btn btn-info  " style="width:80px;" /></td>
		     	<td class="text-left"><button class="btn btn-info  " style="width:80px;">打印</button></td>
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
	<script
		src="${pageContext.request.contextPath}/lib/ace-admin/js/jquery-2.1.4.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/lib/ace-admin/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/lib/ace-admin/js/ace-elements.min.js"></script>
    
<script
		src="${pageContext.request.contextPath}/lib/ace-admin/js/bootstrap-datepicker.min.js"></script>
<script
		src="${pageContext.request.contextPath}/lib/ace-admin/js/bootstrap-datepicker-zh-cn.js"></script>
<script>
/* !function(){
laydate.skin('molv');
laydate({elem: '#Calendar'});
}();
 */
$(function(){
	   //$('.input-daterange').datepicker({autoclose:true,});
		$('.date-picker').datepicker({
			autoclose : true,
			todayHighlight : true,
			format: 'yyyy-mm-dd',
			language:"zh-CN"
		})
		// show datepicker when clicking on the icon
			.next().on(ace.click_event, function() {
			$(this).prev().focus();
		});

	/* //获取焦点消除错误
	$("#password").focus(function(){
		$("#passworderror").html("");
	})
	$("#userno").focus(function(){
		$("#usernoerror").html("");
	})
	
	//表单提交前验证
	$("#OrderFeeFormId").ajaxForm({
		beforeSubmit : function(arr, $form, options) {
			console.log("-------");
			
			
			return true;
		},
		success : function(data, statusText, xhr, $form) {
			console.log(data);
			layer.alert(data);
		}
	}); */
});

</script> 

</body>
</html>
