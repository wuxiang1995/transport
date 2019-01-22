<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<title>晶科物流管理系统</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/css.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery1.9.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/sdmenu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/laydate/laydate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/layer2.4/layer.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</head>

<body>

   
   <div class="title_right"><strong>财务审核</strong></div>  
<div style="width:900px;margin:auto;">
	<form action="orderFee_quoteOrder.action" method="post" id="OrderFeeFormId">
		<table class="table table-bordered">
	         <tr>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">订单编号：</td>
		     	 <td width="23%">
		     	 <input type="hidden"  name="orderFee.order_id" vaule="rrr" />
		     	 	 ${orderdd.order_id }  
		     	 </td>
			     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">业务员：</td>
			     <td width="23%"> 
			    	${orderdd.staff_no }
			     		
			     </td>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">客户：</td>
		     	 <td width="23%">
		     	 	 	${customer.customer_name }
		     	 </td>
	     	 	   
	     	</tr>
	         <tr>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">到达国家：</td>
		     	 <td width="23%">
		     	 	 ${orderdd.order_destination }
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
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">入库人：</td>
		     	 <td width="23%">
		     	 	  <input type="text" name="" id="" value="钱仓管" disabled="disabled" class="span1-1" />
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
		     	 	${totalSize*orderdd.orderFee.size_order_fee }
	     	 	 </td>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">总体积：</td>
		     	 <td width="23%">
		     	 	  ${totalSize }
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
		     	 		<c:when test="${totalSize *200 > totalWeight}">
		     	 			${totalSize * 200 * orderdd.orderFee.weight_order_fee}
		     	 		</c:when>
		     	 		<c:otherwise>
		     	 			${totalWeight* orderdd.orderFee.weight_order_fee}
		     	 		</c:otherwise>
		     	 	</c:choose>
	     	 	 </td>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">总重量：</td>
		     	 <td width="23%">
		     	 	  ${totalWeight }
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
		     	 	 		<fmt:formatNumber value="${totalWorth * 0.07 }" pattern="#.0" minFractionDigits='1' />
		     	 	 	</c:when>
		     	 	 	<c:otherwise>
		     	 	 		0.0
		     	 	 		
		     	 	 	</c:otherwise>
		     	 	 </c:choose>
	     	 	 </td>
		     	 <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">总价值：</td>
		     	 <td width="23%">
		     	 	  ${totalWorth }
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
		     	 	55100.0
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
	           <td align="center" bgcolor="#f1f1f1"><strong>长(mm)</strong></td>
	           <td align="center" bgcolor="#f1f1f1"><strong>宽(mm)</strong></td>
	           <td align="center" bgcolor="#f1f1f1"><strong>高(mm)</strong></td>
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
    
<!-- 底部 -->
<div id="footer">版权所有：晶科客流 &copy; 2015&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://www.mycodes.net/" target="_blank">源码之家</a></div>
    
    

 <script>
!function(){
laydate.skin('molv');
laydate({elem: '#Calendar'});
}();

$(function(){

	
});

</script>

</body>
</html>
