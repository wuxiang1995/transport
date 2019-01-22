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
						<li class="active">物流信息</li>
					</ul>
					<!-- /.breadcrumb -->
				</div>
				<div class="page-content">
					<div class="widget-box transparent" id="widget-box-13">
						<div class="widget-header">
							<div class="widget-toolbar no-border">
								<ul class="nav nav-tabs" id="myTab2">
									<li class="active"><a data-toggle="tab" href="#">物流添加</a>
									</li>
								</ul>
							</div>
						</div>
	<form action="${pageContext.request.contextPath }/order/toOrderInfo.do" method="post" >
       
      <table  class="table no-border">
        
        <tr >
          <td colspan="5" nowrap="nowrap"></td>
          <td colspan="5" nowrap="nowrap"></td>
          <td colspan="5" nowrap="nowrap"></td>
          <td colspan="5" nowrap="nowrap"></td>
          <td colspan="5" nowrap="nowrap"></td>
          <td colspan="5" nowrap="nowrap"></td>
          <td colspan="5" nowrap="nowrap"></td>
          <td align="right" >订单编号：</td>
          <td colspan="5" nowrap="nowrap"><input type="text"  style="width:300px;"name="order_id" id="order_id" class="span10"  /></td>
         </tr>
       </table>    
	     <table  class="margin-bottom-20  table no-border" >
	       <tr>
	     	<td class="text-center"><input type="submit" value="确定" class="btn btn-info  " style="width:80px;" /></td>
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
    

</body>
</html>
