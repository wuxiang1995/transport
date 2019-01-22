<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
							href="${pageContext.request.contextPath }/admin/toIndex.do">财务管理</a>
						</li>
						<li class="active">财务审核</li>
					</ul>
					<!-- /.breadcrumb -->
				</div>
				<div class="page-content">
					<div class="widget-box transparent" id="widget-box-13">
						<div class="widget-header">
							<div class="widget-toolbar no-border">
								<ul class="nav nav-tabs" id="myTab2">
									<li class="active"><a data-toggle="tab" href="#">财务审核</a>
									</li>
								</ul>
							</div>
						</div>
	<form action="${pageContext.request.contextPath }/order/orderReview.do" method="post" id="OrderFeeFormId">
		<input name="token" type="hidden" value="${sessionScope.token }"/>
		<input name="token.invoke" type="hidden" value="/order/orderReview.do"/>
		<input name="order_id" type="hidden" value="${orderdd.order_id }"/>
		<table class="table table-bordered">
		<thead>订单明细</thead>
	         <tr>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">订单编号：</td>
		     	 <td width="23%">
		     	 <input type="hidden"  name="orderFee.order_id" vaule="rrr" />
		     	 	 ${orderdd.order_id }  
		     	 </td>
			     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">业务员：</td>
			     <td width="23%"> 
			    	${orderdd.admin.admin_name }
			     		
			     </td>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">客户：</td>
		     	 <td width="23%">
		     	 	 ${orderdd.customer.customer_name }
		     	 </td>
	     	 	   
	     	</tr>
	         <tr>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">到达国家：</td>
		     	 <td width="23%">
		     	 	 ${orderdd.order_destination }</td>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">收货地址：</td>
		     	 <td width="23%">
		     	 		${orderdd.order_recieveraddress }
		     	 	 </td>
			     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">收货人：</td>
			     <td> ${orderdd.order_recievername }</td>
	     	</tr>
	     	<tr>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">联系电话：</td>
		     	 <td width="23%">
		     	 	${orderdd.order_recieverphone }
	     	 	 </td>
	     	 	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">付款方式：</td>
			     <td> 
			     	${orderdd.order_paytype }
	           	 </td>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">货运方式：</td>
		     	 <td width="23%">
		     	 	  ${orderdd.order_deliverytype }
	           	</td>
	     	</tr>
	     	<tr>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">取件方式：</td>
		     	 <td width="23%">
		     	 	${orderdd.orderPick.pick_type }
	     	 	 </td>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">预付金额：</td>
		     	 <td width="23%">
		     	 		${orderdd.order_prepay }元
		     	 </td>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">入库选择：</td>
		     	 <td width="23%">
		     	 	  ${orderdd.order_repo }
	           	</td>
	           	
	     	</tr>
	   	  </table>
	   	  
	   	  <table class="table table-bordered" >
	   	  	<thead>费用明细</thead>
	       <tr>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">体积收费：</td>
		     	 <td width="23%">
		     	 	<div id="tiji">${orderdd.totalSize*orderdd.orderFee.size_order_fee }</div>
	     	 	 </td>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">总体积：</td>
		     	 <td width="23%">
		     	 	  ${orderdd.totalSize }
	           	</td>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">体积费率：</td>
		     	 <td width="23%">
		     	 	 ${orderdd.orderFee.size_order_fee}元/方
	           	</td>
	     	</tr>
	       <tr>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">重量收费：</td>
		     	 <td width="23%">
		     	 	<c:choose>
		     	 		<c:when test="${orderdd.totalSize *200 > orderdd.totalWeight}">
		     	 			${orderdd.totalSize * 200 * orderdd.orderFee.weight_order_fee}
		     	 		</c:when>
		     	 		<c:otherwise>
		     	 			${orderdd.totalWeight* orderdd.orderFee.weight_order_fee}
		     	 		</c:otherwise>
		     	 	</c:choose>
	     	 	 </td>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">总重量：</td>
		     	 <td width="23%">
		     	 	  ${orderdd.totalWeight }
	           	</td>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">重量费率：</td>
		     	 <td width="23%">
		     	 	 ${orderdd.orderFee.weight_order_fee}元/方
	           	</td>
	     	</tr>
	       <tr>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">过关税费：</td>
		     	 <td width="23%">
		     	 	<c:choose>
		     	 	 	<c:when test="${orderdd.order_destination == 'SPG' and orderdd.order_deliverytype=='海运'}">
		     	 	 		<fmt:formatNumber value="${orderdd.totalWorth * 0.07 }" pattern="#.0" minFractionDigits='1' />
		     	 	 	</c:when>
		     	 	 	<c:otherwise>
		     	 	 		0.0
		     	 	 		
		     	 	 	</c:otherwise>
		     	 	 </c:choose>
	     	 	 </td>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">总价值：</td>
		     	 <td width="23%">
		     	 	  ${orderdd.totalWorth }
	           	</td>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">税率：</td>
		     	 <td width="23%">
		     	 	 <c:choose>
		     	 	 	<c:when test="${orderdd.order_destination == 'SPG' and orderdd.order_deliverytype=='海运' }">
		     	 	 		7%
		     	 	 	</c:when>
		     	 	 	<c:otherwise>
		     	 	 		0%
		     	 	 	</c:otherwise>
		     	 	 </c:choose>
	           	</td>
	     	</tr>
	       <tr>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">取件费用：</td>
		     	 <td width="23%" colspan="5">
		     	 	<c:choose>
			     		<c:when test="${orderdd.orderPick.pick_type == '上门取件' }">
					     	${orderdd.orderPick.pick_fee}
			     		</c:when>
			     		<c:otherwise>
			     			0
			     		</c:otherwise>
			     	</c:choose> 
	     	 	 </td>
		     	
	     	</tr>
	       <tr>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">其它费用：</td>
		     	 <td width="23%" colspan="5">
		     	 	${orderdd.orderFee.other_fee}
	     	 	 </td>
	     	</tr>
	       <tr>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">总费用：</td>
		     	 <td width="23%" colspan="5">
						     	  ${orderdd.totalfee} 
		     	 
	     	 	 </td>
	     	</tr>
	        
	        
	       </table>
	   	  
	   	  
	      <table class="table table-bordered" >
	      	<thead align="center">货物清单</thead>
	        <tr>
	           <td align="center" bgcolor="#f1f1f1"><strong>序号</strong></td>
	           <td align="center" bgcolor="#f1f1f1"><strong>货物名称</strong></td>
	           <td align="center" bgcolor="#f1f1f1"><strong>数量</strong></td>
	           <td align="center" bgcolor="#f1f1f1"><strong>单位</strong></td>
	           <td align="center" bgcolor="#f1f1f1"><strong>长(m)</strong></td>
	           <td align="center" bgcolor="#f1f1f1"><strong>宽(m)</strong></td>
	           <td align="center" bgcolor="#f1f1f1"><strong>高(m)</strong></td>
	           <td align="center" bgcolor="#f1f1f1"><strong>核算体积(立方)</strong></td>
	           <td align="center" bgcolor="#f1f1f1"><strong>核算重量(千克)</strong></td>
	           <td align="center" bgcolor="#f1f1f1"><strong>总价值(元)</strong></td>
	           
	           
	            
	         </tr>
	         <c:forEach items="${orderdd.orderItems }" var="orderItem" varStatus="s">
	         	 <tr >
	           <td align="center">${s.index+1 }</td>
	           <td align="center">${orderItem.item_name}</td>
	           <td align="center">${orderItem.item_num}</td>
	           <td align="center">${orderItem.item_unit}</td>
	           <td align="center">${orderItem.item_length}</td>
	           <td align="center">${orderItem.item_width}</td>
	           <td align="center">${orderItem.item_height}</td>
	           <td align="center">${orderItem.item_size}</td>
	           <td align="center">${orderItem.item_weight}</td>
	           <td align="center">${orderItem.item_totalfee}</td>
	          </tr> 
	         </c:forEach>
	        
	       </table>
	      
	       
		     <table  class="margin-bottom-20  table no-border" >
		       <tr>
		     	<td class="text-right"><input type="submit" value="导出" class="btn btn-info  " style="width:80px;" /></td>
		     	<td class="text-left"><input type="reset" value="返回" class="btn btn-info  " style="width:80px;" /></td>
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
		 <!-- <script type="text/javascript">
		$(function(){
			alert($(".tiji").getAttribute("value"));
			console.log($(".tiji").val())
		})
		</script>  -->
</body>
</html>
