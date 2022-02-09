<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cloudy - ログイン</title>
</head>
<body>
<h1>Cloudyにログイン</h1>
<form action="aca-serch" method="post">
ユーザーID：<input type="text" name="userId" required><br>
パスワード：<input type="password" name="pass" required><br>
<input type="submit" value="ログイン"><br>
</form>
<p>
<br>
<a href="CreateAccount.jsp"><button>アカウント新規作成</button></a>
</p>
</body>
</html>