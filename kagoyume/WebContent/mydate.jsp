<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession"
		import="java.util.Date"
		import="java.text.SimpleDateFormat"
		import="kagoyume.UserDataDTO" %>
<%
	HttpSession hs = request.getSession();
	UserDataDTO udd = (UserDataDTO)hs.getAttribute("udd");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH時mm分ss秒");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyData</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>
<h3>登録情報</h3>
<table border=1>
	<tr>
	 	<th align="center">名前：</th>
	 	<td><%= udd.getName() %></td>
	</tr>
	<tr>
	 	<th align="center">パスワード：</th>
	 	<td><%= udd.getPassword() %></td>
	</tr>
	<tr>
	 	<th align="center">メールアドレス：</th>
	 	<td><%= udd.getMail() %></td>
	</tr>
	<tr>
	 	<th align="center">住所：</th>
	 	<td><%= udd.getAddress() %></td>
	</tr>
	<tr>
	 	<th align="center">購入総額：</th>
	 	<td><%= udd.getTotal() %>円</td>
	</tr>
	<tr>
	 	<th align="center">登録日時：</th>
	 	<td><%= sdf.format(new Date(udd.getNewDate().getTime())) %></td>
	</tr>
</table>
<a href="MyHistory">購入履歴</a>
<br>
登録情報を<a href="myupdate.jsp">更新する</a>
<br>
登録情報を<a href="mydelete.jsp">削除する</a>
</body>
</html>