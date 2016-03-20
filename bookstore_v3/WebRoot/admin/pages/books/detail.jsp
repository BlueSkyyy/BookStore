<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书详情页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h3>图书详情</h3>
    <table border="1" width="300px">
    	<tr>
    		<td>封面</td>
    		<td><img src="${pageContext.request.contextPath}${book.covers}" width="150" height="200"/></td>
    	</tr>
    	<tr>
    		<td>书名</td>
    		<td>${book.name }</td>
    	</tr>
    	<tr>
    		<td>所属分类</td>
    		<td>${book.types.name }</td>
    	</tr>
    	<tr>
    		<td>作者</td>
    		<td>${book.author }</td>
    	</tr>
    	<tr>
    		<td>出版社</td>
    		<td>${book.publisher }</td>
    	</tr>
    	<tr>
    		<td>出版时间</td>
    		<td>${book.publishtime }</td>
    	</tr>
    	<tr>
    		<td>原价</td>
    		<td>${book.price }</td>
    	</tr>
    	<tr>
    		<td>折扣</td>
    		<td>${book.rebate }</td>
    	</tr>
    	<tr>
    		<td>作者简介</td>
    		<td>${book.authorbrief }</td>
    	</tr>
    	<tr>
    		<td>内容简介</td>
    		<td>${book.contentbrief }</td>
    	</tr>
    </table>
  </body>
</html>
