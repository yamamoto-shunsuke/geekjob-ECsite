<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="kagoyume.ProductDataBean"
		import="java.util.ArrayList"
		import="javax.servlet.http.HttpSession" %>
<%
	HttpSession hs = request.getSession();
//検索する処理。
	ArrayList<ProductDataBean> pdbList = (ArrayList<ProductDataBean>)request.getAttribute("pdbList");
//検索ワード
	String requestQuery = (String)request.getAttribute("requestQuery");
	//↓もし検索ワードが何も入力されていなければトップページへ戻る処理
	/*if(requestQuery == null){
		request.getRequestDispatcher("top.jsp").forward(request, response);
		return;
	}*/
	//検索結果をサーバから持ってきている。
	//検索結果件数。
	int totalResult = (int)request.getAttribute("totalResult");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Result</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>
検索結果：<%= totalResult %>件 / 検索ワード：<%= requestQuery %><br>
<%if(pdbList.size() > 0){ %>
<table border=1>
	<tr>
	<!--th alignで文字位置を中央に指定する。-->
		<th align="center">画像</th>
		<th align="center">商品名</th>
		<th align="center">値段</th>
	</tr>
	<!--pdbリストに入っているすべての検索結果を一つ一つ表示する処理を行っている。-->
	<%for(ProductDataBean pdb : pdbList){ %>
		<tr>
			<td><img src="<%= pdb.getThumbnail() %>"></td>
			<td>
					<!--名前を表示する部分-->
					<form action="Item?code=<%=pdb.getCode() %>" method="POST" name="form1">
					<input type="hidden" name="word" value="<%= requestQuery %>">
					<a href="#" onclick="this.parentNode.submit()"><%= pdb.getName() %></a>
					</form>


			</td>
			<td><%= pdb.getPrice() %>円</td>

		<tr>
	<% } %>
</table>
<% }%>
</body>
</html>