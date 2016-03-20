<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>头部页面</title>
</head>
<body>
	<table width="100%" height="10%">
		<tr>
			<td align="left"><h1><font color="red" size ="8">叮咚购物商城</font></h1></td>
			<td align="right">
				请先<a href="">登录</a>或<a href="">注册</a>&nbsp;&nbsp;
				<a href="">购物车</a>&nbsp;&nbsp;
				<a href="">我的订单</a>
			</td>
		</tr>
	</table>
	 
	<table width="100%">
		<tr>
			<td colspan="3" align="center" >
				<input type="text"  name="name" id="context" size="40px"  /><input type="submit" value="搜索" onclick="_query()"/>
			</td>
	
		</tr>
	</table>
	<hr>
<script type="text/javascript">
function _query(){
	var context = document.getElementById("context");
	alert(context);
	window.location.href="${pageContext.request.contextPath }/front/books?action=detail&id=${book.id}";
}
</script>
</body>
</html>