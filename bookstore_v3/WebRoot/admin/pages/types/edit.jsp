<br><%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="title" value=""></c:set>
<c:set var="url" value=""></c:set>
<%-- 如果在request域中有type对象，则为修改 --%>
<c:choose>
	<c:when test="${!empty requestScope.type}">
		<c:set var="title" value="修改"></c:set>
		<c:set var="url" value="${pageContext.request.contextPath }/admin/types?action=update"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="title" value="添加"></c:set>
		<c:set var="url" value="${pageContext.request.contextPath }/admin/types?action=save"></c:set>
	</c:otherwise>
</c:choose>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>${title}分类页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<font color="red">${msg }</font>
  	<h3>${title}图书分类</h3>
     <form action="${url}" method="post">
     	<%--提交修改请求时，必须要携带一个id数据 --%>
     	<c:if test="${!empty requestScope.type}">
     		<input type="hidden" name="id" value="${type.id }"/>
     	</c:if>
     	分类名称:<input type="text" name="name" value="${type.name }"/><br/>
     	<input type="submit" value="保存"/>
     	<input type="button" value="返回" onclick="_back()"/>
     </form>
     
     <script type="text/javascript">
     	function _back(){
     		//返回上一页
     		window.history.go(-1);
     	}
     </script>
  </body>
</html>
