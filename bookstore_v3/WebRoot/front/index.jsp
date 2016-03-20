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
		<tr height="5%">
			<td colspan="2" valign="top"><%@include file="/front/head.jsp" %></td>
		</tr>
		<tr height="95%">	
			<td rowspan="3" width="20%" valign="top">
				<%--左边 --%>
				<%@include file="/front/types.jsp" %>
			</td>
			<td width="80%">
				<%--显示图书列表 --%>
				<table width="100%">
					<tr>
						<c:forEach items="${requestScope.pb.data}" var="book" varStatus="vs">
							<td>
								<%--其中一本书 --%>
								<img src="${pageContext.request.contextPath }${book.covers}" width="120px" height="150px"/><br>
								<a href="${pageContext.request.contextPath }/front/books?action=detail&id=${book.id}"> ${book.name }</a><br>
							 	原价:<span class="tips1">${book.price }</span>,折扣价:<span class="tips2"><fmt:formatNumber value="${book.price*book.rebate}" pattern="#.#"></fmt:formatNumber></span>
								<%--满三本跳到下一行 --%>
								<c:if test="${vs.count%3==0 }">
									</tr>
									<tr>
								</c:if>
						</c:forEach>
					</tr>
				</table>
				<table border="1" width="80%">
    				<tr>
    					<td align="center">
    						<a href="${pageContext.request.contextPath }/index?curPage=${pb.firstPage }">首页</a>&nbsp;
    						<a href="${pageContext.request.contextPath }/index?curPage=${pb.prePage }">上一页 </a>&nbsp;
    						<a href="${pageContext.request.contextPath }/index?curPage=${pb.nextPage }">下一页</a>&nbsp;
    						<a href="${pageContext.request.contextPath }/index?curPage=${pb.totalPage }">末页</a>&nbsp;
    						共${pb.totalPage }页，
    						跳转到第<input type="text" id="pageNo" name="pageNo" size="2" onblur="_changePage()" value="${pb.curPage }"/>页
    					</td>
    				</tr>
		    	</table>
			</td>
		</tr>
	</table>	
<script type="text/javascript">
	//切换页码
	function _changePage(){
		//获取填入的页码，只能是数字
		var pageSizeNo = document.getElementById("pageNo").value;
		var reg = /^[1-9][0-9]?$/;
		if(!reg.test(pageSizeNo)){
			alert("只能输入两位数字！");
			document.getElementById("pageNo").value="1";
			return;
		}
		
		//获取总页数，不能大于总页数,用正则表达式进行验证
		var totalPage = "${pb.totalPage}";
		if(pageSizeNo>totalPage){
			alert("不能超过总页数！");
			document.getElementById("pageNo").value="1";
			return;
		}
		
		//将数据发送到servlet进行处理
		window.location.href="${pageContext.request.contextPath}/index?curPage="+pageSizeNo;
			
	}
</script>
</body>

</html>