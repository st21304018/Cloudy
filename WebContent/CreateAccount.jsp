<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title> Cloudy - アカウント作成</title>
		<link rel="stylesheet" href="css/create.css">
		<script>
			function on_offPass(){
				var target = document.getElementById("passWord");
				console.log(target.type);

				if(target.type == "password"){
					target.type="text";
					document.getElementById("passImg").src = "images/offEye.png";
					console.log(target.type);
				}else{
					target.type="password";
					document.getElementById("passImg").src = "images/onEye.png";
					console.log(target.type);
				}

			}
		</script>
	</head>
	<body>
		<div class="input_area">
			<div>
			<img class="cloudy-logo" src="images/cloudylogo.jpg" alt="クラウディのロゴ">
			</div>
			<div><h1 class="createTitle">アカウント作成</h1></div>
			<form method='post' action="createservlet" class="form">
				<div class="nameWrapper">
					<input class="inputValue inputName" type="text" name="name"
					placeholder="名前を入力してください"
					size="80" required>
					<p class="inputError nameError">名前を入力してください</p>
				</div>
				<br>
				<div class="idWrapper">
					<input id="id" class="inputValue inputID" type="text" name="id"
					placeholder="ユーザーIDを入力してください" size="80"
					pattern=[a-zA-Z\d]{0,8} required>
					<p class="inputError idError">8文字以内の半角英数字でユーザーIDを入力してください</p>
				</div>
				<br>
				<div class="passWrapper">
					<img id="passImg" class="passImg onPass"src="images/onEye.png" onclick="on_offPass()">
					<input id = "passWord" class="inputValue inputPass" type="password" name="pass"
					placeholder="4～8文字の半角英数字でパスワードを入力してください"
					pattern=(?=.*?[a-zA-Z])(?=.*?\d)[a-zA-Z\d]{4,8} size="80"required>
					<p class="inputError passError">4～8文字の半角英数字でパスワードを入力してください</p>
				</div>
				<br>
				<div class="create_submit_button">
					<input class="submit_button" type='submit' value='登録する'>
				</div>
			</form>
			<p class="error_mess">${error}</p>
		</div>
		<script src="js/createJS.js"></script>
	</body>
</html>