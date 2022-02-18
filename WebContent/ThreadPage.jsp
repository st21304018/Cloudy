<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%--JSTL 1.1.2 core タグライブラリ--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cloudy - リプライ</title>
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
<link rel="stylesheet" href="css/common.css">
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
							<textarea name="tag" class="input_tag_area"
							placeholder="タグを入力"></textarea>
							<div>
								<input class="submit-button" type="submit" value="comment">
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
						<p class="top-img skip-img">↑</p><p class="skip-top skip-text">Top</p>
					</div></a>
					<a class="skip-link" href="MyPage"><div class="mypage-button skip-button">
						<img class="mypage-img skip-img" src="images/profile-skip.png"><p class="skip-mypage skip-text">MyPage</p>
					</div></a>
					<a class="skip-link" href="logout"><div class="logout-button skip-button">
						<img class="logout-img skip-img" src="images/logout.png"><p class="skip-logout skip-text">Logout</p>
					</div></a>
					<input type="button" class="putButton" onclick="showForm()" value="Comment">
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
			<div class="title-area"><a class="title-link" href="#" onclick="window.history.back(); return false;">←</a><p class="title-text">　　Thread</p></div>
				<div>
					<p>${user_name}</p>
				</div>
				<p>${sb.text}</p>
				<p>${sb.time}</p>
				<p>${sb.tag}</p>
				<p>${sb.userid}</p>

				<c:forEach var="map" items="${map}">
					<p>
						<c:out value="${map.value.text}" />
					</p>
					<p>
						<c:out value="${map.value.time}" />
					</p>
					<p>
						<c:out value="${map.value.tag}" />
					</p>
					<p>
						<c:out value="${map.value.name}" />
					</p>
					<p>
						<c:out value="${map.value.reply_id}" />
					</p>
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
			<!----right-aside---->
			<aside class="right-aside">
				<div class="right-wrapper">
					<div class="serch-box">
						<img src="images/search.png" class="search-img">
						<form method="GET" action="search">
							<div><input class="search-input" type="text" name="tag" placeholder="タグを検索"></div>
						</form>
					</div>
				</div>
			</aside>
			<!----/right-aside---->
		</div>
	</div>
</body>
</html>