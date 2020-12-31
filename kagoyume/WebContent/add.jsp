<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>商品を追加しました。</h3>
<jsp:include page="/WEB-INF/header.jsp"/>
<form action="top.jsp" method="POST">
<input type="submit" name="no" value="トップへ戻る">
</form>
</body>
</html>