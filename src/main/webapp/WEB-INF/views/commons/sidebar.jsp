<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="sidebar" class="sidebar responsive ace-save-state">
	<script type="text/javascript">
		try{ace.settings.loadState('sidebar')}catch(e){}
	</script>
	<ul class="nav nav-list">
		<li class="">
			<a href="${pageContext.request.contextPath }/admin/toIndex.do">
				<i class="menu-icon fa fa-tachometer"></i>
				<span class="menu-text"> 系统首页 </span>
			</a>

			<b class="arrow"></b>
		</li>
                 
		<!--
                      open:表示菜单打开
			 active：表示菜单选中
		-->
		<c:forEach var="modular" items="${sessionScope.admin_info.modulars }"> 
		<li class="closed ">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-desktop"></i>
				<span class="menu-text">
					${modular.modular_name }
				</span>

				<b class="arrow fa fa-angle-down"></b>
			</a>

			<b class="arrow"></b>

			<ul class="submenu">
			   <c:forEach var="power" items="${sessionScope.admin_info.powers }">
			    <c:if test="${power.power_parent==0 and power.power_is_show==0 and power.modular_id==modular.modular_id }" >
			      <c:choose>
			       <%--
			           	1.为了解决空字符的问题，需要判空！！
			           	2.为了忽略空格的判断，去掉空格
			          --%>
			         <c:when test="${fn:contains(sessionScope.path,fn:trim(power.power_action)) and power.power_action!='' and power.power_action!=null }">
			             <li class="active" >
							<a href="${pageContext.request.contextPath }${power.power_action}">
							<i class="menu-icon fa fa-caret-right"></i>
							${power.power_name}
							</a>
	
					  		<b class="arrow"></b>
						</li>
			         </c:when>
			         <c:otherwise>
			            <li >
							<a href="${pageContext.request.contextPath }${power.power_action}">
							<i class="menu-icon fa fa-caret-right"></i>
							${power.power_name}
							</a>
	
					  		<b class="arrow"></b>
						</li>
			         </c:otherwise>
			      </c:choose>
				
				</c:if>
				</c:forEach>
			    


			</ul>
		</li>
		</c:forEach>

	</ul><!-- /.nav-list -->

	<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
		<i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
	</div>
</div>