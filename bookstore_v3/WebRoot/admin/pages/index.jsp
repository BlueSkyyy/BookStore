<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>主页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <%--注意：frameset必须放在body的外面 --%>
  <frameset rows="10%,*" border="0">
  		<frame src="${pageContext.request.contextPath}/admin/pages/top.jsp">
  		<frameset cols="15%,*" border="0">
  			<frame src="${pageContext.request.contextPath}/admin/pages/menu.jsp">
  			<frame src="${pageContext.request.contextPath}/admin/pages/main.jsp" name="main">
  		</frameset>
  </frameset>
  <body>
  </body>
</html>
