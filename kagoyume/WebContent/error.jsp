<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String error = (String)request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Page</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>
<h2>エラーが発生しました。以下を確認してください。</h2>
<%= error %><br>
<br>
<a href="top.jsp">トップページへ</a>

</body>
</html>