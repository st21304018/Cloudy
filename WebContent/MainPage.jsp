<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
	function actionToggle(threadID) {
		var id = threadID + 'like';
		console.log(id);
		var target = document.getElementById(id);
		target.classList.toggle('onHeart');
		target.classList.toggle('offHeart');
	}

	function hideForm() {
		document.getElementById("box").style.display = "none";
		var target = document.getElementById("thread");
		target.style.backgroundColor = "rgba(255, 255, 255)";
		var top = document.getElementById("top");
		top.style.pointerEvents = "auto";
	}
	function showForm() {
		document.getElementById("box").style.display = "block";
		var target = document.getElementById("thread");
		target.style.backgroundColor = "rgba(0, 0, 0, 0.4)";
		var top = document.getElementById("top");
		top.style.pointerEvents = "none";
	}
</script>
<link rel="stylesheet" href="css/reaction.css">
<link rel="stylesheet" href="css/MainPage.css">
<title>掲示板</title>
</head>

<body>
	<div id="thread" class="thread_page">
		<!---- input-box ---->
		<div id="box" class="box_area" style="display: none">
			<div class="input_box">
				<div class="input_box_close" onclick="hideForm()">×</div>
				<div class="form_area">
					<form action="MainPage" method="post" name="form1"
						onSubmit="return check()">
						<div class="input_area">
							<textarea name="comment" class="input_text_area"
								placeholder="コメントを入力"></textarea>
							<div>
								<input type="submit" value="TWEET">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!---- /input-box ---->
		<div id="top" class="main-page">
			<aside class="left-aside">
				<!-- ---left-side--- -->
				<div class="left-wrapper">
					<a class="skip-link" href="#top"><div class="top-button skip-button">
						<p class="skip-top">Top</p>
					</div></a>
					<a class="skip-link" href="mypage"><div class="mypage-button skip-button">
						<p class="skip-mypage">MyPage</p>
					</div></a>
					<input type="button" class="putButton" onclick="showForm()"
						value="Comment">
					<div class="profile-area">
						<img class="profile-img" src="images/profile_icon.png">
						<div class="user-info">
							<p class="profile-name">${account.name}</p>
							<p class="profile-id">@${account.userId}</p>
						</div>
					</div>
				</div>
			</aside>
			<!-- ---/left-side--- -->
			<article class="main-area">
				<c:forEach var="map" items="${map}">
					<a href="threadpageservlet?e=${map.value.id}">
						<p><br>
						<c:out value="${map.value.user_name}"/>
						</p>
						<p><br>
						<c:out value="${map.value.comment}"/>
						</p>
						<p><br>
						<c:out value="${map.value.time}"/>
						</p>
					</a>
					<div class="likes-area">
						<!---- like-area ---->
						<div id="${map.value.id}like" class="input-wrapper offHeart">
							<c:if test="${not empty map.value.check}">
								<script>
									var threadID = ${map.value.id};
									actionToggle(threadID);
								</script>
							</c:if>
							<a href="like?e=${map.value.id}" class="input-submit"></a>
						</div>
						<p class="like_count">${map.value.likes}</p>
					</div>
					<!---- /like-area ---->
				</c:forEach>
			</article>
			<aside class="right-aside"></aside>
		</div>
	</div>
</body>


</html>