<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="kagoyume.UserData"
		import="javax.servlet.http.HttpSession" %>
<%
	HttpSession hs = request.getSession();
	UserData ud = (UserData)request.getAttribute("ud");
	if(hs.getAttribute("userName") == null){
		request.getRequestDispatcher("login.jsp").forward(request, response);
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Complete</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>
名前：<%= ud.getName() %><br>
パスワード：<%= ud.getPassword() %><br>
メールアドレス：<%=ud.getMail() %><br>
住所：<%= ud.getAddress() %><br>
<br>
以上の内容で更新しました。<br>
<button type="button" class="btn btn-secondary" onclick="history.back()">戻る</button>
</body>
</html>