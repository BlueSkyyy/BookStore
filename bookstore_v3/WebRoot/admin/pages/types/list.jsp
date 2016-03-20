<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>显示所有图书分类</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<a href="${pageContext.request.contextPath }/admin/pages/types/edit.jsp">添加分类</a>
     <h3>所有图书分类</h3>
     <table border="1" width="500px" align="center">
     	<tr>
     		<th>编号</th>
     		<th>分类名称</th>
     		<th>操作</th>
     	</tr>
     	<c:forEach items="${requestScope.types }" var="type" varStatus="vs">
     	<tr>
     		<td>${vs.count }</td>
     		<td>${type.name }</td>
     		<td>
     		<a href="${pageContext.request.contextPath }/admin/types?action=queryOne&id=${type.id}">修改</a>
     		<a href="javascript:void" onclick="_remove('${type.id}')">删除</a>
     		</td>
     	</tr>
     	</c:forEach>
     </table>
     <script type="text/javascript">
     	function _remove(id){
     		//提示
     		if(window.confirm("确认是否删除此记录，一旦删除不能恢复！")){
	     		window.location.href="${pageContext.request.contextPath }/admin/types?action=remove&id="+id;
     		}
     	}
     </script>
  </body>
</html>
