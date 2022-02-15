<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/MainPage.css">
	<script>
		function hideForm(){
			document.getElementById("box").style.display = "none";
			var target = document.getElementById("thread");
			target.style.backgroundColor = "rgba(255, 255, 255)";
		}
		function showForm() {
			document.getElementById("box").style.display = "block";
			var target = document.getElementById("thread");
			target.style.backgroundColor = "rgba(0, 0, 0, 0.4)";
			target.style.color = "rgba(0, 0, 0, 0.4)";
		}
	</script>
</head>

<body>
	<div id="thread" class="thread_page">
		<div id="box" class="box_area" style="display:none">
			<div class="input_box">
				<div class="input_box_close" onclick="hideForm()"> × </div>
				<div class="form_area">
					<form action="MainPage" method="post" name="form1" onSubmit="return check()">
						<div class="input_area">
							<textarea name="comment" class="input_text_area" placeholder="コメントを入力"></textarea>
							<div>
								<input type="submit" value="TWEET">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<input type="button" class="putButton" onclick="showForm()" value="Comment">
	</div>
</body>
</html>