<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@page import="java.util.ArrayList"
		import="javax.servlet.http.HttpSession"
		import="kagoyume.ProductDataBean"%>
<%
//ArrayList<ProductDataBean> cartをセッションから取り出すのでセッションをスタートさせる。
//ArrayList<ProductDataBean> cartにはAdd.javaでカートに入れた情報のすべてが入っている。
	HttpSession hs = request.getSession();
	ArrayList<ProductDataBean> cart = (ArrayList<ProductDataBean>)hs.getAttribute("cart");
	//↓inCartはカートの中身の個数が入っている。
	if(cart == null){
		cart = new ArrayList<>();
	}
	int inCart = cart.size();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>
<h2>買い物かご</h2>
現在<%= inCart %>個の商品があります。<br>
<!--カートの中身の要素数が0以上だったら-->
<%if(cart.size() > 0){ %>
<!--int sum = 0 はカートに入れた商品の合計金額を表示するために用意した。-->
<% int sum = 0; %>
<table border=1>
	<tr>
		<th align="center">画像</th>
		<th align="center">商品名</th>
		<th align="center">値段</th>
		<th align="center">削除</th>
	</tr>
		<!--0～カートの要素数分繰り返し、pdbに入っている-->
	<%for(int i = 0;i < cart.size(); i++){ %>
	<!--cartの中に入っている商品情報を要素数分取り出して、pdbに加えている。-->
		<%ProductDataBean pdb = cart.get(i);%>
		<%sum += pdb.getPrice(); %>
		<tr>
			<td><img src="<%= pdb.getThumbnail() %>"></td>
			<td><a href="Item?code=<%=pdb.getCode() %>"><%= pdb.getName() %></a></td>
			<td><%= pdb.getPrice() %>円</td>
			<td>
				<form action="DeleteCartItem" method="POST" name="form1">
				<!--↓pdb.getDateをcartDateという名前をつけてhiddenでrequestスコープに送っている-->
					<input type="hidden" name="cartNumber" value="<%=i%>">
					<a href="#" onclick="this.parentNode.submit()">削除</a>
				</form>
			</td>
		<tr>
	<% } %>
</table><br>
合計金額：<%= sum %>円<br>
<form action="BuyConfirm" method="POST">
<!--hiddenでsumをriquestスコープに送っている。-->
	<input type="hidden" name="sum" value="<%= sum %>">
	<input type="submit" class="btn btn-primary" value="購入する">
</form>
<% }%>
<a href="top.jsp">トップページ</a>
</body>
</html>