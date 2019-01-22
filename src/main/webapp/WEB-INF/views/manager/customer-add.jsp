<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
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
							href="${pageContext.request.contextPath }/admin/toIndex.do">客户管理</a>
						</li>
						<li class="active">新增客户</li>
					</ul>
					<!-- /.breadcrumb -->
				</div>
				<div class="page-content">
					<div class="widget-box transparent" id="widget-box-13">
						<div class="widget-header">
							<div class="widget-toolbar no-border">
								<ul class="nav nav-tabs" id="myTab2">
									<li class="active"><a data-toggle="tab" href="#">新增客户</a>
									</li>
								</ul>
							</div>
						</div>

						<form action="${pageContext.request.contextPath }/customer/addCustomer.do" method="post" id="customerID" onsubmit="return toVaild()">
							<input name="token" type="hidden" value="${sessionScope.token }"/>
							<input name="token.invoke" type="hidden" value="/customer/addCustomer.do"/>
							<table class="table table-bordered">
								<tr>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">客户姓名：</td>
									<td width="23%"><input type="text" class=span1-1
										" id="customer_name" name="customer_name" /><font color="red" style=“”>*必填项</font></td>

									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">业
										务 员：</td>
									<td width="23%" id="salesmanID"><c:choose>
											<c:when test="${sessionScope.admin_info.admin_id==1 ||sessionScope.admin_info.admin_id==3 }">
												<select name="staff_no" id="staff_noID" class=" span1-1">
													<c:forEach var="admin" items="${requestScope.admins }">
														<option value="${admin.admin_id}">${admin.admin_name}</option>
													</c:forEach>
												</select>
											</c:when>
											<c:otherwise>
											<select name="staff_no" id="staff_noID" class=" span1-1" readonly unselectable="on">
													<option value="${sessionScope.admin_info.admin_id}">${sessionScope.admin_info.admin_name}</option>
											</select>
											</c:otherwise>
										</c:choose></td>
								</tr>
								<tr>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">客户电话：</td>
									<td width="23%"><input type="text" class="span1-1
										" id="customer_phone" name="customer_phone" /><font color="red" >*必填项</font></td>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">电子邮箱：</td>
									<td width="23%"><input type="text" class="span1-1
										" id="customer_email" name="customer_email" /></td>
								</tr>
								<tr>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">常用区间：</td>
									<td width="23%"><select name="customer_destination"
										id="customer_destination" class=" span1-1">
											<option value="SPG" selected="selected">新加坡</option>
											<option value="MYS">马来西亚</option>
											<option value="IFI">冰火岛</option>
									</select></td>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">通讯地址：</td>
									<td width="23%"><input type="text" class="span1-1
										" id="customer_address" name="customer_address" /><font color="red" >*必填项</font></td>
								</tr>

								<tr>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">性别：</td>
									<td width="23%"><select name="customer_gender"
										id=" customer_gerder" class=" span1-1">
											<option value="1">男</option>
											<option value="2">女</option>
											<option value="0" selected="selected">保密</option>
									</select></td>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">身份证号：</td>
									<td width="23%"><input type="text" class="span1-1"
										name="customer_identity" id="customer_identity" /><font color="red" >*必填项</font></td>
								</tr>

								<tr>
									<td align="right" nowrap="nowrap" bgcolor="#f1f1f1">备注：</td>
									<td colspan="3" nowrap="nowrap"><textarea
											name="customer_remark" id="customer_remark" class="span10"
											style="width: 200px;"></textarea></td>
								</tr>
							</table>
							<h4>${customer_add_msg}</h4>
							<table class="margin-bottom-20  table no-border">
								<tr>
									<td class="text-right"><input type="submit" value="提交"
										class="btn btn-info  " style="width: 80px;" /></td>
									<td class="text-left">
									<%-- ${pageContext.request.contextPath }/admin/toIndex.do --%>
									<a href="${pageContext.request.contextPath }/order/toOrderAdd.do"><buttom
												class="btn btn-info  " style="width:80px;">Next</buttom></a></td>
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
		<script src="${pageContext.request.contextPath}/lib/ace-admin/js/jquery-2.1.4.min.js"></script>
		<script src="${pageContext.request.contextPath}/lib/ace-admin/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/lib/ace-admin/js/ace-elements.min.js"></script>
	
	<script>
		$(function() {
	
			/* //表单提交前验证
			$("#customerID").ajax({
				dataType : 'json',
				contentType : 'application/json;charset=UTF-8',
				beforeSubmit : function(arr, $form, options) {
					var uflag = true;
					 for (var i=0; i < arr.length; i++) {
					       if (!arr[i].value) {
					    	   alert("不能留空，请完善");
					           return false;
					        }
					   } 
					return uflag; 
				},
				success : function(data, statusText, xhr, $form) {
					console.log(data);
					if (data == 1) {
						layer.alert("添加成功");
					} else if (data == 0) {
						layer.alert("添加失败");
					}
				}
			}); */
			
		})
	</script>

</body>
</html>
