<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%-- <%@taglib prefix="s" uri="/struts-tags" %> --%>
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
							href="${pageContext.request.contextPath }/admin/toIndex.do">业务管理</a>
						</li>
						<li class="active">核对入库</li>
					</ul>
					<!-- /.breadcrumb -->
				</div>
				<div class="page-content">
					<div class="widget-box transparent" id="widget-box-13">
						<div class="widget-header">
							<div class="widget-toolbar no-border">
								<ul class="nav nav-tabs" id="myTab2">
									<li class="active"><a data-toggle="tab" href="#">核对入库</a>
									</li>
								</ul>
							</div>
						</div>
						<form action="${pageContext.request.contextPath }/order/orderInstore.do" method="post"
							id="OrderFeeFormId">
							<input name="token" type="hidden" value="${sessionScope.token }"/>
							<input name="token.invoke" type="hidden" value="/order/orderInstore.do"/>
							<input name="order_id" type="hidden" value="${orderdd.order_id }"/>
							<table class="table table-bordered">
								<tr>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">订单编号：</td>
									<td width="23%"> ${orderdd.order_id }
									</td>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">业务员：</td>
									<td width="23%">${orderdd.admin.admin_name }</td>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">客户：</td>
									<td width="23%">${orderdd.customer.customer_name }</td>

								</tr>
								<tr>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">到达国家：</td>
									<td width="23%">${orderdd.order_destination }</td>
										<td width="10%" align="right" nowrap="nowrap"
										bgcolor="#f1f1f1">收货地址：</td>
										<td width="23%">${orderdd.order_recieveraddress }</td>
										<td width="10%" align="right" nowrap="nowrap"
										bgcolor="#f1f1f1">收货人：</td>
										<td>${orderdd.order_recievername }</td>
								</tr>
								<tr>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">联系电话：</td>
									<td width="23%">${orderdd.order_recieverphone }</td>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">付款方式：</td>
									<td>${orderdd.order_paytype }</td>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">货运方式：</td>
									<td width="23%">${orderdd.order_deliverytype }</td>
								</tr>
								<tr>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">取件方式：</td>
									<td width="23%">${orderdd.orderPick.pick_type }</td>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">取件费用：</td>
									<td><c:choose>
											<c:when test="${orderdd.orderPick.pick_type != '上门取件' }">
												<input type="text" name="pick_realfee" value="0"
													id="" class="span1-1" disabled="disabled" />元
			     		
			     		</c:when>
											<c:otherwise>
												<input type="text" name="pick_realfee" id=""
													class="span1-1" />元
			     		</c:otherwise>
										</c:choose></td>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">入库人：</td>
									<td width="23%"><input type="text" name="" id=""
										value="${sessionScope.admin_info.admin_name }" disabled="disabled" class="span1-1" /></td>
								</tr>
								<tr>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">体积费率：</td>
									<td width="23%"><input type="text"
										name="size_check_fee" id="" class="span1-1" />元/方</td>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">重量费率：</td>
									<td><c:choose>
											<c:when test="${orderdd.order_deliverytype != '空运'}">
												<input type="text" name="weight_check_fee"
													value="0" disabled="disabled" class="span1-1" />元/千克	
			     		</c:when>
											<c:otherwise>
												<input type="text" name="weight_check_fee"
													class="span1-1"  />元/千克	
			     		</c:otherwise>
										</c:choose></td>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">入库选择：</td>
									<td width="23%"><select name="order_repo" id=""
										class=" span1-1">
											<option value="1号仓库">1号仓库</option>
											<option value="2号仓库">2号仓库</option>
											<option value="3号仓库">3号仓库</option>
									</select></td>
								</tr>
								<tr>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">站点选择：</td>
									<td width="23%"><select name="zhandian" id=""
										class=" span1-1">
											<option value="湛江">湛江收揽中心</option>
											<option value="上海">上海收揽中心</option>
											<option value="广州">广州收揽中心</option>
											<option value="北京">北京收揽中心</option>
									</select></td>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">订单出入库：</td>
									<td width="23%"><select name="detil_status" id=""
										class=" span1-1">
											<option value="收揽">收揽</option>
											<option value="发货">发货</option>
											<option value="派送">派送</option>
									</select></td>
									<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">订单备注信息：</td>
									<td width="23%"><input type="text" name="detil_remark"
													class="span1-1"  /></td>
								</tr>

							</table>
							<table class="table table-bordered">
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



								</tr>
								<c:forEach items="${orderdd.orderItems }" var="orderItem"
									varStatus="s">
									<tr>
										<td align="center">${s.index+1 }<input type="hidden"
											name="id${s.index }" value="${orderItem.id}" /></td>
										<td align="center">${orderItem.item_name}</td>
										<td align="center">${orderItem.item_num}</td>
										<td align="center">${orderItem.item_unit}</td>
										<td align="center"><input type="text"
											name="item_length${s.index }"
											class=" span1 text-center" /></td>
										<td align="center"><input type="text"
											name="item_width${s.index }"
											class=" span1 text-center" /></td>
										<td align="center"><input type="text"
											name="item_height${s.index }"
											class=" span1 text-center" /></td>
										<td align="center"><input type="text"
											name="item_truesize${s.index }"
											class=" span1 text-center" /></td>
										<td align="center"><c:choose>
												<c:when test="${orderdd.order_deliverytype != '空运'}">
													<input type="text"
														name='item_trueweight"${s.index }"' value="0"
														disabled="disabled" class=" span1 text-center" />元/千克	
			     		</c:when>
												<c:otherwise>
													<input type="text"
														name="item_trueweight${s.index }"
														class=" span1 text-center" />
												</c:otherwise>
											</c:choose></td>
									</tr>
								</c:forEach>

							</table>


							<table class="margin-bottom-20  table no-border">
								<tr>
									<td class="text-right"><input type="submit" value="确定"
										class="btn btn-info  " style="width: 80px;" /></td>
									<td class="text-left"><input type="reset" value="重置"
										class="btn btn-info  " style="width: 80px;" /></td>
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
	<script>
		/* !function() {
			laydate.skin('molv');
			laydate({
				elem : '#Calendar'
			});
		}();

		$(function() {
			//获取焦点消除错误
			$("#password").focus(function() {
				$("#passworderror").html("");
			})
			$("#userno").focus(function() {
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
			});
		}); */
	</script>
</body>
</html>
