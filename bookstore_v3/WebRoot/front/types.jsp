<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分类栏</title>
</head>
<body>
	<font color="red" size ="5">图书分类</font>
	<ul>
		<li><a href="${pageContext.request.contextPath }/index?typesId=0">所有分类</a></li>
		<c:forEach items="${requestScope.types }" var="type">
			<li><a href="${pageContext.request.contextPath }/index?typesId=${type.id}">${type.name }</a></li>
		</c:forEach>
	</ul>		
</body>
</html>