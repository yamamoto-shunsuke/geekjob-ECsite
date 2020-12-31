<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="kagoyume.UserData"
		import ="java.util.ArrayList"
		import="javax.servlet.http.HttpSession" %>
<%
	HttpSession hs = request.getSession();
//ここでFormError変数を用意した理由：RegistrationCompleteで未入力項目が入ったformErrorをここで受け取ってどこが未入力なのかを表示するため。]
ArrayList<String> formError = (ArrayList<String>)request.getAttribute("formError");
UserData ud = (UserData)request.getAttribute("ud");
String name = ud != null ? ud.getName() : "";
String password = ud != null ? ud.getPassword() : "";
String mail = ud != null ? ud.getMail() : "";
String address = ud != null ? ud.getAddress() : "";

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>
<h2>ユーザー新規登録</h2>
<!--未入力項目があった場合は未入力項目数分getする。formErrorの中には〇〇が未入力ですという表示の処理も入っている。-->
<%if(formError != null){ %>
		<div class="alert alert-warning" role="alert">
		<% for(int i = 0; i < formError.size(); i++){ %>
			<%= formError.get(i) %><br>
		<% } %>
		</div>
	<% } %>
<form action="RegistrationConfirm" method="POST">
	ユーザー名：<br>
	<input type="text" name="name" value="<%=  name %>"><br>
	<br>
	パスワード：<br>
	<input type="text" name="password" value="<%= password %>"><br>
	<br>
	メールアドレス：<br>
	<input type="text" name="mail" value="<%= mail %>"><br>
	<br>
	住所：<br>
	<input type="text" name="address" value="<%= address %>"><br>

	<input type="submit" value="登録">

</form>
</body>
</html>