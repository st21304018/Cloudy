<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title> アカウント作成 - Cloudyアカウント</title>
		<link rel="stylesheet" href="css/create.css">
	</head>
	<body>
		<div class="input_area">
			<div><img src="images/cloudylogo.jpeg" alt="クラウディのロゴ"></div>
			<div><h1 class="createTitle">アカウント作成</h1></div>
			<form method='post' action="createservlet" class="form">
				<div class="nameWrapper">
					<input class="inputValue inputName" type="text" name="name"
					placeholder="名前を入力してください"
					size="80" required>
					<p class="inputError nameError"></p>
				</div>
				<div class="idtWrapper">
					<input id="id" class="inputValue inputID" type="text" name="id"
					placeholder="ユーザーIDを入力してください" size="80"
					pattern=[a-zA-Z\d]{0,8} required>
					<p class="inputError idError"></p>
				</div>
				<div class="passWrapper">
					<input id = "passWord" class="inputValue inputPass" type="password" name="pass"
					placeholder="4～8文字の半角英数字でパスワードを入力してください"
					pattern=(?=.*?[a-zA-Z])(?=.*?\d)[a-zA-Z\d]{4,8} size="80"required>
					<p class="inputError passError"></p>
				</div>
				<div class="create_submit_button">
					<input class="submit_button" type='submit' value='登録する'>
				</div>
			</form>
			<p class="error_mess">${error}</p>
		</div>
		<script src="js/createJS.js"></script>
	</body>
</html>