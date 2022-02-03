<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Boolean b = (Boolean) request.getAttribute("login");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cloudy - ログイン</title>
</head>
<body>
	<h1>Cloudyへログイン</h1>
	<form action="login" method="post">
		ユーザーID:<input type="text" name="name"> パスワード:<input
			type="password" name="password"> <input type="submit"
			value="login">
	</form>
	<%
	if (b) {
	%>
	<p>
		<font color="red">ログインに失敗しました。</font>
	</p>
	<%
	}
	%>
</body>
</html>

