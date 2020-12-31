<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%
	HttpSession hs = request.getSession();
	String userName = (String) hs.getAttribute("userName");
%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>

		<a href="top.jsp">Home</a>
		<%
			if (userName != null) {
		%>
		ようこそ<a href="Mydate"><%=userName%></a>さん
		<a href="Cart">買い物かご</a> <a href="Logout">ログアウト</a>

		<%
			} else {
		%>


		<a href="Cart">買い物かご</a> <a href="ToLoginPage">ログイン</a>

		<%
			}
		%>

</body>
</html>