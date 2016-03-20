<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>顶部页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		body{
			background-color:aqua;
		}
	</style>
  </head>
  
  <body>
    <table width="100%">
    	<tr>
    		<td><h2>叮咚购物商城后台</h2></td>  
    		<%-- herf标签的target属性： 超链接的目标   
    			_self: 在当前页面
    			_blank: 在新窗口页面 
    			_parent：在当前页面的父页面
    			 --%>
    		<td align="right">${sessionScope.adminsLoginInfo.name },您好！   <a href="${pageContext.request.contextPath }/admins?action=logout" target="_parent">[注销]</a></td>
    	</tr>
    </table>
  </body>
</html>
