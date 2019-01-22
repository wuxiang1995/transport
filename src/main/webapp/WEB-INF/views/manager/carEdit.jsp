<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><!DOCTYPE html>
<html lang="en">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<head>
		<jsp:include page="../commons/header.jsp"></jsp:include>
	</head>

	<body class="no-skin">
	    <!-- 导航栏 start		-->
		 <jsp:include page="../commons/navbar.jsp"></jsp:include>
		<!-- 导航栏 end -->

		<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>
            <!--- 左边栏-菜单 start -->
			  <jsp:include page="../commons/sidebar.jsp"></jsp:include>
			<!--- 左边栏-菜单 end -->

			<!--- 内容主体 start -->
			<div class="main-content">
				<div class="main-content-inner">
				    <!-- 向导栏 start-->
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
								<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">首页</a>
							</li>

							<li>
								<a href="#">车辆管理</a>
							</li>
							<li class="active">车辆管理-列表</li>
						</ul><!-- /.breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="ace-icon fa fa-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- /.nav-search -->
					</div>
					<!-- 向导栏 end-->

					<!-- 内容页 start -->
					<div class="page-content">
				          			<div class="widget-box transparent" id="widget-box-13">
												<div class="widget-header">
													<h4 class="widget-title lighter">车辆管理</h4>

													<div class="widget-toolbar no-border">
														<ul class="nav nav-tabs" id="myTab2">
															<li  class="active">
																<a data-toggle="tab" href="#adminEdit">编辑</a>
															</li>

													

															<li>
																<a data-toggle="tab" href="javascript:void(0)" onclick="window.location.href='${pageContext.request.contextPath }/car/toCarList.do'">列表</a>
															</li>
														</ul>
													</div>
												</div>

												<div class="widget-body">
													<div class="widget-main padding-12 no-padding-left no-padding-right">
														<div class="tab-content padding-4">
														
															<div id="adminEdit" class="tab-pane in active ">
																<div class="scrollable-horizontal" data-size="800">
																    ${requestScope.car_edit_msg }
																	<form action="${pageContext.request.contextPath }/car/editCar.do" method="post" class="form-horizontal" role="form">
																	    <input name="car_id" type="hidden" value="${requestScope.car.car_id }">
																		<input name="token" type="hidden" value="${sessionScope.token }">
																		<input name="token.invoke" type="hidden" value="/car/toCarList.do">
																		<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 车牌号 </label>

																			<div class="col-sm-9">
																				<input name="car_no" type="text" id="form-field-1" value="${car.car_no }" class="col-xs-10 col-sm-5" />
																			</div>
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 车型 </label>

																			<div class="col-sm-9">
																				<input name="car_type" type="text" id="form-field-1" value="${car.car_type }" class="col-xs-10 col-sm-5" />
																			</div>
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 营业证号 </label>

																			<div class="col-sm-9">
																				<input name="car_license" type="text" id="form-field-1" value="${car.car_license }" class="col-xs-10 col-sm-5" />
																			</div>
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 吨位 </label>

																			<div class="col-sm-9">
																				<input name="car_dun" type="text" id="form-field-1" value="${car.car_dun }" class="col-xs-10 col-sm-5" />
																			</div>
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 车辆状态 </label>

																			<div class="col-sm-9">
																			<select name="car_status" id=""
																				class=" span1-1">
																					<option value="0">空闲</option>
																					<option value="1">发车</option>
																			</select>
																			</div>
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 站点 </label>

																			<div class="col-sm-9">
																			<select name="network_id" id=""
																				class=" span1-1">
																					<c:forEach var="net" items="${car.nets }">
																					<c:if test="${car.map2.network_id==net.id }"><option selected="selected"  value="${net.id }">${net.name }</option></c:if>
																						<option value="${net.id }">${net.name }</option>
																					</c:forEach>
																			</select>
																			</div>
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 备注</label>

																			<div class="col-sm-9">
																				<input name="car_remark" type="text" id="form-field-1" class="col-xs-10 col-sm-5" />
																			</div>
																			
																		</div>

																		
																		<div class="form-group">
																			
																			<div class="col-sm-7 text-right">
																				<button type="submit" class="btn btn-primary">编辑车辆</button>
																			</div>
																		</div>
																	</form>

																</div>
															</div>

												
														</div>
													</div>
												</div>
											</div>
					</div><!-- /.page-content -->
					<!-- 内容页 end -->
				</div>
			</div><!-- /.main-content -->
			<!--- 内容主体 end -->

			 <!--页尾 start -->
	 <jsp:include page="../commons/footer.jsp"></jsp:include>

		
			 <!--页尾 end -->
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="${pageContext.request.contextPath}/lib/ace-admin/js/jquery-2.1.4.min.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="${pageContext.request.contextPath}/lib/ace-admin/js/jquery-1.11.3.min.js"></script>
<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${pageContext.request.contextPath}/lib/ace-admin/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="${pageContext.request.contextPath}/lib/ace-admin/js/bootstrap.min.js"></script>

		<!-- page specific plugin scripts -->

		<!-- ace scripts -->
		<script src="${pageContext.request.contextPath}/lib/ace-admin/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/lib/ace-admin/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->
	</body>
</html>
