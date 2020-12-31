<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--includeを用いてブラウザ上部に、Home、ログイン、買い物かごに飛べるリンクを用意している。(header.jspを用いて。)-->
<jsp:include page="/WEB-INF/header.jsp"/>
<h2>購入が完了しました。</h2>
<a href="top.jsp">トップページ</a>
</body>
</html>