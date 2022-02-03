<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title> 新規登録 - Cloudyアカウント</title>
	</head>
	<body>
		<form method='post' action="createservlet">
			<div class="create_name_aria">
				<input type="text" name="name" placeholder="名前" required>
			</div>
			<div class="create_userID_aria">
				<input type="text" name="id" placeholder="ユーザーID"
				pattern=[a-zA-Z\d]{0,8} title="8文字以内の半角英数字で入力してください">
			</div>
			<div class="create_pass_area">
				<input type="password" name="pass" placeholder="パスワード"
				pattern=(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\d)[a-zA-Z\d]{4,8}
				title="4～8文字の半角英数字で入力してください" required>
			</div>
			<div class="create_submit_button">
				<input type='submit' value='登録'>
			</div>
		</form>
		<p class="error_mess">${error}</p>
	</body>
</html>