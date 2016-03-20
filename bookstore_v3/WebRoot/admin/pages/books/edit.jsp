<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%--设置两个变量  --%>
<c:set var="tagName" value=''></c:set>
<c:set var="actionURL" value=''></c:set>
<c:choose>
	<c:when test="${empty requestScope.book }">
		<c:set var="tagName" value='添加'></c:set>
		<c:set var="actionURL" value='${pageContext.request.contextPath }/admin/books?action=save'></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="tagName" value='修改'></c:set>
		<c:set var="actionURL" value='${pageContext.request.contextPath }/admin/books?action=update&id=${requestScope.book.id}'></c:set>
	</c:otherwise>
</c:choose>
<html>
  <head>
    <title>添加/修改图书</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<h3>${tagName}图书</h3>
     <form action="${actionURL}" method="post" enctype="multipart/form-data">
   	 ${requestScope.book.id}
     <%--如果是修改操作，则需要传递id到后台 --%>
  		<c:if test="${!empty requestScope.book }">
  			<input type="hidden" name="id" value="${requestScope.book.id}"/>
  		</c:if>
  	 <%--如果是修改操作，则需要传递id到后台 --%>
  		
     <table border="1" width="500px">
     	<tr>
     		<td width="20%">书名:</td>
     		<td><input type="text" name="name" value="${book.name }"/></td>
     	</tr>
     	<tr>
     		<td>分类:</td>
     		<td>
     			<select name="typesID">
     				<c:forEach items="${requestScope.types }" var="type">
     				<option value="${type.id }">${type.name }</option>
     				</c:forEach>
     			</select>
     		</td>
     	</tr>
     	<tr>
     		<td>封面:</td>
     		<td><input type="file" name="img" value="${book.covers }"/></td>
     	</tr>
     	<tr>
     		<td>作者:</td>
     		<td><input type="text" name="author" value="${book.author }"/></td>
     	</tr>
     	<tr>
     		<td>出版社:</td>
     		<td><input type="text" name="publisher" value="${book.publisher }"/></td>
     	</tr>
     	<tr>
     		<td>出版日期:</td>
     		<td><input type="text" name="publishtime" value="${book.publishtime }"/></td>
     	</tr>
     	<tr>
     		<td>原价:</td>
     		<td><input type="text" name="price" value="${book.price }"/></td>
     	</tr>
     	<tr>
     		<td>折扣:</td>
     		<td><input type="text" name="rebate" value="${book.rebate }"/></td>
     	</tr>
     	<tr>
     		<td>作者简介:</td>
     		<td>
     		<textarea rows="5" cols="30" name="authorbrief" value="${book.authorbrief }"></textarea>
     		</td>
     	</tr>
     	<tr>
     		<td>内容简介:</td>
     		<td>
     		<textarea rows="5" cols="30" name="contentbrief" value="${book.contentbrief }"></textarea>
     		</td>
     	</tr>
     	<tr>
     		<td colspan="2">
     		<input type="submit" value="保存"/>
     		</td>
       </tr>
     </table>
     </form>
  </body>
</html>
