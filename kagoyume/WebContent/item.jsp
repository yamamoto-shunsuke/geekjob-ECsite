<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="kagoyume.ProductDataBean"
		import="javax.servlet.http.HttpSession" %>
<%
	HttpSession hs = request.getSession();
	ProductDataBean pdb = (ProductDataBean)hs.getAttribute("pdb");
	String userName = (String)hs.getAttribute("userName");
	String word = request.getAttribute("word") == null ? "" : (String)request.getAttribute("word");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Detail</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>
<%if(pdb == null){ %>
	<h3>この商品は存在しません。</h3>
<%}else{ %>
	<table border=1>
		<tr>
			<th align="center">商品名</th>
			<td><%= pdb.getName() %></td>
		</tr>
		<tr>
			<th align="center">画像</th>
			<td><img src="<%= pdb.getImgURL() %>"></td>
		</tr>
		<tr>
			<th align="center">値段</th>
			<td><%= pdb.getPrice() %>円</td>
		</tr>
		<tr>
			<th align="center">商品説明</th>
			<td><%= pdb.getDescription() %></td>
		</tr>
		<tr>
			<th align="center">商品評価</th>
			<td><%= pdb.getReview() %>/5.0         (評価人数：<%= pdb.getCount() %>)</td>
		</tr>
		<tr>
			<th align="center">販売ストア</th>
			<td><%= pdb.getStoreName() %></td>
		</tr>
		<tr>
			<th align="center">ストア評価</th>
			<td><%= pdb.getStoreReview() %>/5.0    (評価人数：<%= pdb.getStoreCount() %>)</td>
		</tr>
	</table>
	<br>
	<form action="Add" method="POST">
		<input type="hidden" name="ac" value="<%=hs.getAttribute("ac")%>">
		<input type="hidden" name="word" value="<%= word %>">
		<input type="submit" class="btn btn-primary" value="カートに入れる">
	</form>
	<br>
	<%if(request.getAttribute("word") != null){ %>
		<form action="search" method="GET" name="form1">
			<input type="hidden" name="word" value="<%= request.getAttribute("word") %>">
			<a href="#" onclick="this.parentNode.submit()">検索結果に戻る</a>
		</form>
	<% } %>
<% } %>
</body>
</html>