<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
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
								<a href="#">角色管理</a>
							</li>
							<li class="active">角色-列表</li>
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
													<h4 class="widget-title lighter">角色管理</h4>

													<div class="widget-toolbar no-border">
														<ul class="nav nav-tabs" id="myTab2">
															<li  class="active">
																<a data-toggle="tab" href="#roleAdd">编辑</a>
															</li>

													

															<li>
																<a data-toggle="tab" href="javascript:void(0)" onclick="window.location.href='${pageContext.request.contextPath }/role/toRoleList.do'">列表</a>
															</li>
														</ul>
													</div>
												</div>

												<div class="widget-body">
													<div class="widget-main padding-12 no-padding-left no-padding-right">
														<div class="tab-content padding-4">
														
															<div id="roleAdd" class="tab-pane in active ">
																<div class="scrollable-horizontal" data-size="800">
																	 ${requestScope.role_edit_msg }
																	<form action="${pageContext.request.contextPath }/role/editRole.do" method="post" class="form-horizontal" role="form">
																	    <input name="role_id" type="hidden" value="${requestScope.role.role_id }">
																		<input name="token" type="hidden" value="${sessionScope.token }">
																		<input name="token.invoke" type="hidden" value="/role/toRoleList.do">
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 角色名 </label>

																			<div class="col-sm-9">
																				<input name="role_name" value="${requestScope.role.role_name }" type="text" id="form-field-1" placeholder="角色名" class="col-xs-10 col-sm-5" />
																			</div>
																		</div>

																														<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 全部权限 <input id="chkAllPowers"  type="checkbox" /></label>

																		</div>
																		
																		<c:forEach var="modular" items="${requestScope.modulars }">
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> ${modular.modular_name }<input onclick="chkModularPower(this)"  type="checkbox" /></label>
                                                                            <c:set var="count" value="0"></c:set>
																			<div class="col-sm-6">
																			    <table class="table">
																			      	<c:forEach var="power" items="${requestScope.powers }">
																			      	  <c:if test="${count%4==0 }">
																			        	<tr>
																			       	</c:if>
																			      	  <c:if test="${modular.modular_id==power.modular_id   }">
																			      	       <%--
																			      	            	问题：什么时候我们需要选中复选框
																			      	            	答：当前角色拥有的权限就选中
																			      	            	 第一步：获得当前角色的权限 ${requestScope.role.role_powers}
																			      	            	 第二步：判断当前角色的权限是否存在 ,通过循环判断
																			      	           
																			      	       --%>
																			      	       <c:set var="flag" value="false"></c:set>
																			      	       <%--
																			      	       forTokens:遍历一个字符串，使用逗号作为分割符
																			      	        --%>
																			      	       <c:forTokens var="powerId" items="${requestScope.role.role_powers}" delims=",">
																			      	             <c:if test="${fn:trim(powerId)==power.power_id }">
																			      	                 <c:set var="flag" value="true"></c:set>
																			      	                
																			      	             </c:if>
																			      	       </c:forTokens>
																			      	    <c:choose>
																			      	      <c:when test="${flag==true}">
																			      	            <td>${ power.power_name}<input checked="checked" name="rolePower" value="${power.power_id }"  type="checkbox" /></td>
																			      	      </c:when>
																			      	      <c:otherwise>
																			      	            <td>${ power.power_name}<input name="rolePower" value="${power.power_id }"  type="checkbox" /></td>
																			      	      </c:otherwise>
																			      	    </c:choose>
																				      	<c:set var="count" value="${count+1}"></c:set>
																				      </c:if>
																				    <c:if test="${count%4==0 }">
																					 </tr>
																					</c:if>
																				   	</c:forEach>
																				  
																				</table>
																				
																			</div>
																		</div>
																		</c:forEach>

																		
																		
																		<div class="form-group">
																			
																			<div class="col-sm-7 text-right">
																				<button type="submit" class="btn btn-primary">编辑角色</button>
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
		<script type="text/javascript">
		   //1.实现全选以及返回所有的权限
		   $("#chkAllPowers").click(function(){
			   if($(this).prop("checked")==true){
				   $("input[type='checkbox']").prop("checked",true);
			   }else{
				   $("input[type='checkbox']").prop("checked",false);
			   }
		   });
		   
		   //2.勾选模块，仅仅只选中模块的权限。
		   var chkModularPower=function(obj){
			  // alert("-test-"+$(obj));
			   //ojb是一个Document对象，如果转成jQuery对象,$(obj);
			  var parentDiv=  $(obj).parents(".form-group");
			 
			  var chkObject=parentDiv.find("input[type='checkbox']");
			  if($(obj).prop("checked")==true){
				  chkObject.prop("checked",true);
			  }else{
				  chkObject.prop("checked",false);
			  }
			  
		   }
		   
		   
		</script>
	</body>
</html>
