<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>显示所有图书</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<a href="${pageContext.request.contextPath}/admin/books?action=preEdit">添加图书</a>
     <h3>所有图书信息</h3>
     <table border="1" width="800px" align="center">
     	<tr>
     		<th>编号</th>
     		<th>封面</th>
     		<th>书名</th>
     		<th>所属分类</th>
     		<th>作者</th>
     		<th>原价</th>
     		<th>折扣</th>
     		<th>出版社</th>
     		<th>操作</th>
     	</tr>
     	<c:forEach items="${requestScope.books }" var="book" varStatus="vs">
     	<tr>
     		<td>${vs.count }</td>
     		<td><img src="${pageContext.request.contextPath }${book.covers}" width="100" height="80"/></td>
     		<td><a href="${pageContext.request.contextPath }/admin/books?action=queryOne&id=${book.id}&tag=detail">${book.name }</a></td>
     		<td>${book.types.name }</td>
     		<td>${book.author }</td>
     		<td>${book.price }</td>
     		<td>${book.rebate }</td>
     		<td>${book.publisher }</td>
     		<td>
     		<a href="${pageContext.request.contextPath}/admin/books?action=queryOne&id=${book.id}&tag=update">修改</a>&nbsp;
     		<a href="javascript:void" onclick="_remove('${book.id}','${book.covers}')">删除</a>
     		</td>
     	</tr>
     	</c:forEach>
     </table>
      <script type="text/javascript">
     	function _remove(id,covers){
     		//提示
     		if(window.confirm("确认是否删除此记录，一旦删除不能恢复！")){
	     		window.location.href="${pageContext.request.contextPath }/admin/books?action=remove&id="+id+"&covers="+covers;
     		}
     	}
     </script>
  </body>
</html>
