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
</script>
<link rel="stylesheet" href="css/reaction.css">
<link rel="stylesheet" href="css/MyPage.css">
<title>Cloudy - マイページ</title>
</head>
<body>

<div id="top" class="main-page">
			<aside class="left-aside">
				<!-- ---left-side--- -->
				<p>${name}さんのマイページ</p>
				<div class="left-wrapper">
					<a class="skip-link" href="#top"><div class="top-button skip-button">
						<p class="skip-top">Top</p>
					</div></a>
					<a class="skip-link" href="MainPage"><div class="mainpage-button skip-button">
						<p class="skip-mainpage">MainPage</p>
					</div></a>
					<a class="skip-link" href="MyPage"><div class="mypage-button skip-button">
						<p class="skip-mypage">MyPage</p>
					</div></a>
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
		<p>
			<br>
			<c:out value="${map.value.comment}" />
		</p>
		<div class="likes-area">
			<div id="${map.value.id}like" class="input-wrapper offHeart">
				<c:if test="${not empty map.value.check}">
					<script>
						var threadID = ${map.value.id	};
						actionToggle(threadID);
					</script>
				</c:if>
				<a href="like?e=${map.value.id}" class="input-submit"></a>
			</div>
			<p class="like_count">${map.value.likes}</p>
		</div>
	</c:forEach>
	</article>
	</div>

<aside class="right-aside"></aside>

</body>
</html>