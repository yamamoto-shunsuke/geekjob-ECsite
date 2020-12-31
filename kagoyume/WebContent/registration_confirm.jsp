<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="kagoyume.UserData"
	import="javax.servlet.http.HttpSession"%>
<%
	HttpSession hs = request.getSession();
	UserData ud = (UserData) hs.getAttribute("ud");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Confirm</title>
</head>
<body>
	<jsp:include page="/WEB-INF/header.jsp" />

	<h2>登録確認</h2>
	ユーザー名：<%=ud.getName()%><br> パスワード：<%=ud.getPassword()%><br>
	メールアドレス：<%=ud.getMail()%><br> 住所：<%=ud.getAddress()%><br>

	上記内容で登録します。よろしいですか？
	<form action="RegistrationComplete" method="POST">
		<input type="submit" value="はい">
		</form>
	<form action="Registration">
	<!--udには新規登録のフォームで入力した情報が入っている。-->
		<input type="submit" formaction="Registration" value="いいえ">
	</form>
</body>
</html>