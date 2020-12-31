<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"
		import="javax.servlet.http.HttpSession"
		import="kagoyume.ProductDataBean" %>
<%
//セッションのスタート
	HttpSession hs = request.getSession();
//セッションン位保存していたcart情報をセッションから持って来ている。
	ArrayList<ProductDataBean> cart = (ArrayList<ProductDataBean>)hs.getAttribute("cart");
//requestスコープで持ってきたsumをint型の変数sumに入れている。
	int sum = (int)request.getAttribute("sum");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--includeを用いて画面上部にログイン、買い物かご、Homeに戻るリンクを用意している。-->
<jsp:include page="/WEB-INF/header.jsp"/>
<!--border属性では値でborder(枠線)の太さを表しています-->
<table border=1>
	<tr>
	<!--th要素にalign=""を追加するとセル内の文字位置を指定することができる。-->
		<th align="center">画像</th>
		<th align="center">商品名</th>
		<th align="center">値段</th>
	</tr>
	<!--確認：拡張for文 for(型 変数名 : 式)↓cartに入っている情報ひとつずつpdbに入れて、入れたら一つずつprintの処理を順番に行っている。今回だと購入したい商品数分-->
	<% for(ProductDataBean pdb : cart){ %>
		<tr>
			<td><img src="<%= pdb.getThumbnail() %>"></td>
			<td><%= pdb.getName() %></td>
			<td><%= pdb.getPrice() %>円</td>
		<tr>
	<% } %>
</table>
<br>
<!--requestスコープからsum(購入したい商品の合計金額)の値をもってきているのでそれをここで表示している。-->
合計金額：<%= sum %>円
<br>
発送方法の選択
<form action="BuyComplete" method="POST">
<!--input type = radioでラジオボタンを用意している。-->
	<input type="radio" name="type" value="1" checked>宅急便<br>
	<input type="radio" name="type" value="2">ゆうパック<br>
	<input type="radio" name="type" value="3">その他<br>
	<!--hiddenでsum(購入したい商品購入額の合計をrequestスコープに送っている。)-->
	<input type="hidden" name="sum" value="<%= sum %>">
	<input type="submit" value="上記の内容で購入する"><br>
</form>
<a href="top.jsp">トップページ</a>
</body>
</html>