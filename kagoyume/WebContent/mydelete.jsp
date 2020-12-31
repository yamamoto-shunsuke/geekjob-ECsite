<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession"
		import="kagoyume.UserDataDTO" %>
<%
	HttpSession hs = request.getSession();
	UserDataDTO udd = (UserDataDTO)hs.getAttribute("udd");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user delete</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>
<table border=1>
	<tr>
		<th>名前：</th>
		<td><%=udd.getName() %></td>
	</tr>
	<tr>
		<th>パスワード：</th>
		<td><%=udd.getPassword() %></td>
	</tr>
	<tr>
		<th>メールアドレス：</th>
		<td><%=udd.getMail() %></td>
	</tr>
	<tr>
		<th>住所：</th>
		<td><%=udd.getAddress() %></td>
	</tr>
	<tr>
		<th>購入総額：</th>
		<td><%=udd.getTotal() %>円</td>
	</tr>
	<tr>
		<th>登録日時：</th>
		<td><%=udd.getNewDate() %></td>
	</tr>
</table>
<br>
以上のユーザーを本当に削除しますか？<br>
<form action="MyDelete" method="POST">
	<input type="hidden" name="check" value="true">
	<a href="#" onclick="this.parentNode.submit()">はい</a>
</form>
<a href="">いいえ</a>
</body>
</html>