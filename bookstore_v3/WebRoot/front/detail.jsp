<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>叮咚商城首页</title>
</head>
<style type="text/css">
		.tips1{
			/*中划线*/
			text-decoration:line-through;
		}
		.tips2{
			color: red;
		}
	</style>
<body>
	<%--首页 --%>
	<table width="100%" >
		<tr height="10%">
			<td colspan="2" valign="top"><%@include file="/front/head.jsp" %></td>
		</tr>
		<tr>	
			<td rowspan="3" width="20%">
				<%--左边 --%>
				<%@include file="/front/types.jsp" %>
			</td>
			<td width="80%">
				<%--显示图书详情 --%>
    			<table width="600px" align="center" border="1">
    				<tr>
    					<td  colspan="2" align="center" ><img src="${pageContext.request.contextPath}${book.covers }" width="200px" height="250px"/></td>
    				</tr>
    				<tr>
    					<td colspan="2" align="center"><input type="button" value="加入购物车"/></td>
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
    					<td>原价</td>
    					<td>${book.price }</td>
    				</tr>
    				<tr>
    					<td>现价</td>
    					<td><fmt:formatNumber value="${book.price*book.rebate}" pattern="#.#"></fmt:formatNumber></td>
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
    					<td>作者简介</td>
    					<td>${book.authorbrief }</td>
    				</tr>
    				<tr>
    					<td>内容简介</td>
    					<td>${book.contentbrief }</td>
    				</tr>
    			</table>
			</td>
		</tr>
	</table>	
</body>
</html>