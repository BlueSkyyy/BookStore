<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>系统菜单页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		body{
			background-color:lime;
		}
	</style>
  </head>
  
  <body>
     <ul>
     	<li><a href="${pageContext.request.contextPath}/admin/types?action=list" target="main">管理图书分类</a></li>
     	<li><a href="${pageContext.request.contextPath}/admin/books?action=preEdit" target="main">添加图书</a></li>
     	<li><a href="${pageContext.request.contextPath}/admin/books?action=list" target="main">管理图书</a></li>
     	<li>管理会员</li>
     </ul>
  </body>
</html>
