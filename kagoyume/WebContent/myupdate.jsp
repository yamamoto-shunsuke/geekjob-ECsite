<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession"
		import="java.util.ArrayList"
		import="kagoyume.UserDataDTO" %>
<%
	HttpSession hs = request.getSession();
	UserDataDTO udd = (UserDataDTO)hs.getAttribute("udd");
	ArrayList<String> formError = (ArrayList<String>) request.getAttribute("formError");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>
<h2>ユーザー情報更新</h2>
<% if(formError != null){ %>
	<% for(String error : formError){ %>
	<div class="alert alert-warning" role="alert">
		<%= error %>
	</div>
	<% } %><br>
<% } %>
<form action="MyUpdate" method="POST">
	ユーザー名：<br>
	<input type="text" name="name" value="<%= udd.getName() %>"><br><br>
	パスワード：<br>
	<input type="text" name="password" value="<%= udd.getPassword() %>"><br><br>
	メールアドレス：<br>
	<input type="text" name="mail" value="<%= udd.getMail() %>"><br><br>
	住所：<br>
	<input type="text" name="address" value="<%= udd.getAddress() %>"><br>
	<input type="hidden" name="check" value="true">
	<div class="mt-1">
		<input type="submit" class="btn btn-primary" value="更新">
	</div>
</form>
</body>
</html>