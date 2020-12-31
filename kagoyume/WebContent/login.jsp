<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession"
	import="java.util.ArrayList"%>
<%
	HttpSession hs = request.getSession();
	ArrayList<String> errors = (ArrayList<String>) request.getAttribute("errors");
	String userName = (String) hs.getAttribute("userName");
	//ログインしていたらログアウト処理
	if (userName != null) {
		request.getRequestDispatcher("logout").forward(request, response);
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
	<jsp:include page="/WEB-INF/header.jsp" />

	<h3>ログインページ</h3>
	<%
		if (errors != null) {
	%>
	<%
		for (String e : errors) {
	%>
	<%=e%>
	<%
		}
	%>
	<%
		}
	%>
	<form action="Login" method="POST">
		<!--fieldsetは入力項目をグループ化する。-->
		<fieldset>
			<!--placeholderplaceholderとはフォームなどの入力欄にあらかじめ記入されている薄い灰色のテキストのこと-->
			<input type="text" name="name" placeholder="名前を入力してください"><br>

			<input type="text" name="password" placeholder="パスワードを入力してください"><br>


			<input type="submit" value="ログイン"> <a href="Registration">新規登録はこちら</a>

		</fieldset>
	</form>
</body>
</html>