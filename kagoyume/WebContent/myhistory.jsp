<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"
		import="java.util.Date"
		import="java.text.SimpleDateFormat"
		import="javax.servlet.http.HttpSession"
		import="kagoyume.ProductDataBean" %>
<%
	HttpSession hs = request.getSession();
	ArrayList<ProductDataBean> historyList = (ArrayList<ProductDataBean>)hs.getAttribute("historyList");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Purchase History</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>
<h2>購入履歴</h2>
<% if(historyList.size() == 0){ %>
	購入履歴はありません。
<% }else{ %>
	<table border=1>
		<tr>
			<th align="center">画像</th>
			<th align="center">商品名</th>
			<th align="center">値段</th>
			<th align="center">購入日</th>
		</tr>
		<%for(ProductDataBean pdb : historyList){ %>
			<tr>
				<td><img src="<%= pdb.getThumbnail() %>"></td>
				<td><a href="item?code=<%=pdb.getCode() %>"><%= pdb.getName() %></a></td>
				<td><%= pdb.getPrice() %>円</td>
				<td><%= sdf.format(new Date(pdb.getDate().getTime())) %></td>
			<tr>
		<% } %>
	</table>
<% } %>
</body>
</html>