<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession"
		import="kagoyume.UserData" %>
<%
	HttpSession hs = request.getSession();
	UserData ud = (UserData)hs.getAttribute("ud");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Complete</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>
<h2>登録完了</h2>
ユーザー名：<%= ud.getName() %><br>
パスワード：<%= ud.getPassword() %><br>
メールアドレス：<%= ud.getMail() %><br>
住所：<%= ud.getAddress() %><br>

以上の内容で登録しました。<br>
<a href="top.jsp">トップページ</a>
</body>
</html>