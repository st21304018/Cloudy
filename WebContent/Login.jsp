<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cloudy - ログイン</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>
	<div class="input_area">
		<div>
			<img class="cloudy-logo" src="images/cloudylogo.jpg" alt="クラウディのロゴ">
		</div>
		<h1 class="loginTitle">Cloudyにログイン</h1>
		<form action="aca-serch" method="post">
			<input class="inputValue" type="text" name="userId"
				placeholder="ユーザーIDを入力してください" size="80" pattern=[a-zA-Z\d]{0,8}
				required><br> <input class="inputValue" type="password"
				name="pass" placeholder="パスワードを入力してください"
				pattern=(?=.*?[a-zA-Z])(?=.*?\d)[a-zA-Z\d]{4,8} size="80" required>
			<br>
			<br><input type="submit" value="ログイン" class="button"><br>
		</form>
		<p>
			<br> <a href="CreateAccount.jsp"><button class="button">アカウント新規作成</button></a>
		</p>
	</div>
</body>
</html>