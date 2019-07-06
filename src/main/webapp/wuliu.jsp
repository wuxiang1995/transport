<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TMS物流官网</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/wuliu.css" />
<style type="text/css">
<!--
.STYLE1 {
	color: #CC0000
}

.STYLE4 {
	color: #0062C4
}
-->
</style>
<script type="text/javascript">
	function submit1() {
		var k = document.f1;
		var b= document.text;
		k.action ="chaxun.jsp?text="+b;
		k.submit();
	}
</script>
</head>

<body>
	<div id="container">
		<div id="globallink">
			<ul>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li><a href="#">首页</a></li>
				<li><a href="#">关于我们</a></li>
				<li><a href="#">公司新闻</a></li>
				<li><a href="#">网点查询</a></li>
				<li><a href="#">招聘信息</a></li>
				<li></li>
			</ul>
		</div>
		<div id="left">
			<div id="search"> 
				<img src="${pageContext.request.contextPath}/lib/global_images/search.gif" /><br> 请输入您要查询的<span
					class="STYLE1">订单号</span>：&nbsp;&nbsp;&nbsp;<br>
						<form  method="get" name="f1"><!-- action=chaxun.jsp -->
							<input type="text" name="text" /><br>
							  <a
								onclick="submit1()"><img
									src="${pageContext.request.contextPath}/lib/global_images/search.jpg" border="0"/></a> 
						</form>
			</div>
			<div id="photo">
				<img src="${pageContext.request.contextPath}/lib/global_images/left.JPG" />
			</div>
			<div id="contact">
				<div id="lianxi">
					&nbsp;<br> &nbsp;<br> &nbsp;<span class="STYLE1">客服TEL</span>：<span
							class="STYLE4">0757-88888888</span><br> &nbsp;<span
								class="STYLE1">公司邮箱</span>：<span class="STYLE4">wuxiang689@163.com
							</span>
				</div>
			</div>
		</div>
		<div id="main">
			<div id="title1">
				&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#"><img
					src="${pageContext.request.contextPath}/lib/global_images/more.gif" align="right" border="0" /></a>
			</div>
			<div id="dongtai">
				<ul>
					<li><a href="#">抓紧时间 认真贯彻 规范市场 科学发展<img
							src="${pageContext.request.contextPath}/lib/global_images/new.gif" border="0" /></a></li>
					<li><a href="#">省管局辛局长深入会员单位TMS物流司调研检查工作<img
							src="${pageContext.request.contextPath}/lib/global_images/new.gif" border="0" /></a></li>
					<li><a href="#">公司在杭州召开2008年TMS物流快递网络工作会议</a></li>
					<li><a href="#">《TMS人简报》第39期全文</a></li>
					<li><a href="#">TMS 物流公司律师郑重声明</a></li>
					<li><a href="#">国家邮政局市场监管司安全监督处处长徐永健来我公司检查调研</a></li>
					<li><a href="#">《TMS人简报》第38期全文</a></li>
				</ul>
			</div>
			<div id="title2">
				&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#">
					<img src="${pageContext.request.contextPath}/lib/global_images/more.gif" align="right" border="0" />
				</a>
			</div>
			<div id="news">
				<ul>
					<li><a href="#">上海市部分快递企业代表学习《邮政法》（修订草案）<img
							src="${pageContext.request.contextPath}/lib/global_images/new.gif" border="0" /></a></li>
					<li><a href="#">两岸&ldquo;大三通&rdquo;将迎来快递无限商机<img
							src="${pageContext.request.contextPath}/lib/global_images/new.gif" border="0" /></a></li>
					<li><a href="#">上海邮政业迎世博600天行动动员大会召开</a></li>
					<li><a href="#">国家邮政局启动《快递服务》标准达标工作</a></li>
					<li><a href="#">快递业等级评定制度将出台</a></li>
					<li><a href="#">上半年我国主要城市网购市场规模达162亿元</a></li>
					<li><a href="#">奥运圣火上海传递圆满结束 结束现场进行募捐赈灾</a></li>
				</ul>
			</div>
		</div>
		<div id="right">
			<div id="notice">
				&nbsp;<br />
				<marquee direction="up" behavior="scroll" loop="-1" scrollamount="3"
					height="200" width="138"> 上海市部分快递企业代表学习<br />
				上海邮政业迎世博600天行动动员大会召开<br />
				快递业等级评定制度将<br />
				上海市部分快递企业代表学习<br />
				上海邮政业迎世博600天行动动员大会召开<br />
				快递业等级评定制度将<br />
				</marquee>
			</div>
			<div id="web">
				&nbsp;<br> 
				<img src="${pageContext.request.contextPath}/lib/global_images/office.jpg"
							border="0" /><br>
						&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="http://wu.ngrok.xiaomiqiu.cn/transport/login.jsp"><img src="${pageContext.request.contextPath}/lib/global_images/in.jpg"
							border="0" onclick="" /></a>
			</div>
		</div>
		<div id="foot">
			<div id="map">
				<img src="${pageContext.request.contextPath}/lib/global_images/map.gif" width="180" height="140" />
			</div>
			<div id="map_right">

				<div id="website">
					<ul>
						<li><a href="${pageContext.request.contextPath}/lib/global/searchWebSit.html">北京市网点</a></li>
						<li><a href="#">上海市网点</a></li>
						<li><a href="#">天津市网点</a></li>
						<li><a href="#">重庆市网点</a></li>
						<li><a href="#">浙江省网点</a></li>
					</ul>
				</div>
				<div id="website">
					<ul>
						<li><a href="#">江苏省网点</a></li>
						<li><a href="#">福建省网点</a></li>
						<li><a href="#">湖北省网点</a></li>
						<li><a href="#">湖南省网点</a></li>
						<li><a href="#">山东省网点</a></li>
					</ul>
				</div>
				<div id="website">
					<ul>
						<li><a href="#">广东省网点</a></li>
						<li><a href="#">辽宁省网点</a></li>
						<li><a href="#">江西省网点</a></li>
						<li><a href="#">河北省网点</a></li>
						<li><a href="#">安徽省网点</a></li>
					</ul>
				</div>
				<div id="website">
					<ul>
						<li><a href="#">河南省网点</a></li>
						<li><a href="#">吉林省网点</a></li>
						<li><a href="#">四川省网点</a></li>
						<li><a href="#">山西省网点</a></li>
						<li><a href="#">陕西省网点</a></li>
					</ul>
				</div>
				<div id="website">
					<ul>
						<li><a href="#">甘肃省网点</a></li>
						<li><a href="#">青海省网点</a></li>
						<li><a href="#">贵州省网点</a></li>
						<li><a href="#">云南省网点</a></li>
						<li><a href="#">海南省网点</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
