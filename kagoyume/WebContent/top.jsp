<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%
	String error = (String) request.getAttribute("searchError");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"
>
<title>かごゆめ</title>
</head>
<body>
	<!-- ↓指定したページにinclude(組み込む)する。 -->
	<jsp:include page="/WEB-INF/header.jsp" />
	<h1>かごゆめ</h1>
	<h3>好きな物を好きなだけ買い物ができる（気分になれる）webサイトです。</h3>
	<%
		if (error != null) {
	%>

		<%=error%>

	<%
		}
	%>
	<form action="Search" method="GET">
		<!--placeholderとはフォームにあらかじめ用意されている薄い色のテキストのことである。-->
		<input type="text" style="width: 350px;" name="word" placeholder="検索ワード">

		<br>
		<input type="submit"value="検索">
		<br>

	</form>
</body>
</html>