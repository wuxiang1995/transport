<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
<script type="text/javascript" src="js/common.js"></script>
</head>

<body>

   
   <div class="title_right"><strong>财务审核</strong></div>  
<div style="width:900px;margin:auto;">
	<form action="queryOrder.action" method="post" >
       
      <table>
        
        <tr>
          <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">订单编号：</td>
          <td colspan="5" nowrap="nowrap"><input type="text" name="order.order_id" id="order_id" class="span10"  /></td>
         </tr>
       </table>    
	     <table  class="margin-bottom-20  table no-border" >
	       <tr>
	       	<input type="hidden" name="flag" id="" value="2"/>
	     	<td class="text-center"><input type="submit" value="确定" class="btn btn-info  " style="width:80px;" /></td>
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
 
</script>

</body>
</html>
