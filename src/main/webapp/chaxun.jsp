
<%@ page language="java" import="java.sql.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<%-- <jsp:include page="../header.jsp"></jsp:include> --%>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/ace-admin/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/ace-admin/font-awesome/4.5.0/css/font-awesome.min.css" />
<!-- page specific plugin styles -->

<!-- text fonts -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/ace-admin/css/fonts.googleapis.com.css" />

<!-- ace styles -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/ace-admin/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/ace-admin/css/ace-skins.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/ace-admin/css/ace-rtl.min.css" />
<link rel="icon" href="${pageContext.request.contextPath}/lib/ace-admin/images/avatars/bind.ico" type="image/x-icon"/>
<script src="${pageContext.request.contextPath}/lib/ace-admin/js/ace-extra.min.js"></script>

<title>快递查询</title>
</head>
<body bgcolor="33ccff">
<center>
<h1>查询结果</h1><br>
<hr> 
	 
	<table class="table table-striped table-bordered align-center ">
		<tr class="bolder" style="text-align:center;" >
			<td>订单编号</td>
			<td>订单日期</td>
			<td>发货地址</td>
			<td>收货地址</td>
		    <td>订单状态</td>
		    <td>备注</td>
		    <td>预计到付日期</td>
		</tr>
 	<%
 		String driverName = "com.mysql.jdbc.Driver";
 		String userName = "root";
 		String userPasswd = "root";
 		String dbName = "transport";
 		String tableName = "tb_order";
 		String tableName1 = "tb_pick";
 		String tableName2 = "tb_orderdetil";
 		String url = "jdbc:mysql://localhost:3306/" + dbName;
 		Connection con = null;
 		Connection con1 = null;
 		PreparedStatement ps=null;
 		//PreparedStatement ps1=null;
 		ResultSet rs = null;
 		ResultSet rs1 = null;
 		try {
 			Class.forName(driverName).newInstance();
 		} catch (ClassNotFoundException e) {
 			System.out.print("Error loading Driver,不能加载驱动程序");
 		}
 		try {
 			con = DriverManager.getConnection(url, "root", "root");
 			//con1 = DriverManager.getConnection(url, "root", "root");
 		} catch (SQLException er) {
 			System.out.print("Error getConnection,不能连接数据库");
 		}
 		try {
 			String number = request.getParameter("text");
 			String sql = "select * from " + tableName + " a join " + tableName1
 					+ " b on a.order_id=b.order_id where a.order_id = ? ";
 			ps=con.prepareStatement(sql);
 			ps.setString(1, number);
 			rs=ps.executeQuery();
 			if (rs.next()) {
 				out.println("<tr>");
 				out.println("<td>" + rs.getString("order_id") + "</td>");
 				out.println("<td>" + rs.getString("order_createdate") + "</td>");
 				out.println("<td>" + rs.getString("pick_address") + "</td>");
 				out.println("<td>" + rs.getString("order_recieveraddress")
 						+ "</td>");
 				if(rs.getInt("order_status")==0){
 					out.println("<td>已签收</td>");
 				}
 				if(rs.getInt("order_status")==1){
 					out.println("<td>取件中</td>");
 				}
 				if(rs.getInt("order_status")==2){
 					out.println("<td>入仓中</td>");
 				}
 				if(rs.getInt("order_status")==3){
 					out.println("<td>报价中</td>");
 				}
 				if(rs.getInt("order_status")==4){
 					out.println("<td>审核中</td>");
 				}
 				if(rs.getInt("order_status")==5){
 					out.println("<td>已发货</td>");
 				}
 				
 				out.println("<td>" + rs.getString("remark") + "</td>");
 				out.println("<td>" + rs.getString("order_postpaydate") + "</td>");
 				out.println("</tr>");
 			} else {  
 				out.print("<script language='javascript'>alert('没有该订单编号！');history.back();</script>");
 			}
 			out.println("</table>");
			String sql1 = "select * from " + tableName2 + " a join tb_network b on a.zhandian=b.id where a.order_id = ? ";
			ps=con.prepareStatement(sql1);
 			ps.setString(1, number); 
 			rs1=ps.executeQuery();
 			out.println("</br></br></br><center><table border='0' cellpadding='0' cellspacing='0'>");
 			while(rs1.next()) {
 				out.println("<tr><td><h4>"+ rs1.getString("detiltime").replaceAll(".0+?$", "")+"</h4></td><td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><h4>"+ rs1.getString("city")+"</h4></td><td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><h4>"+ rs1.getString("detil_remark")+"</h4></td></tr>");}
 			out.println("</table></center>");
 			rs.close();
 			ps.close();
 			con.close();
 		} catch (SQLException er) {
 			er.printStackTrace();
 			out.print("<script language='javascript'>alert('不能执行查询！');history.back();</script>");
 			System.out.println("Error executeQuery,不能执行查询！");
 		}
 	%>
</center>
</body>
</html>