<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="js/common.js"></script>
<link rel="stylesheet" href="css/reaction.css">
<link rel="stylesheet" href="css/MyPage.css">
<link rel="stylesheet" href="css/common.css">
<title>Cloudy - マイポスト</title>
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
					<a class="skip-link" href="MainPage"><div class="skip-main">
						<img class="cloudy-logo" src="images/cloudylogo.jpg"></div></a>
					<a class="skip-link" href="MyPage"><div class="mypage-button skip-button">
						<img class="mypage-img skip-img" src="images/profile-skip.png"><p class="skip-mypage skip-text">MyPosts</p>
					</div></a>
					<div class="profile-area" id="profile">
						<img class="profile-img" src="images/profile_icon.png">
						<div class="user-info">
							<p class="profile-name">${account.name}</p>
							<p class="profile-id">@${account.userId}</p>
						</div>
						<div class="logout" title="ログアウト" onclick="logoutCheck()">
							<img class="logout-img" src="images/logout.png" ></a>
						</div>
					</div>
				</div>
			</aside>
			<!-- ---/left-side--- -->
			<article class="main-area">
			<div id="title" class="title-area"><a class="title-link" href="MainPage">←</a><p class="title-text">　　MyPost</p></div>
			<div class="tweet-area">
				<c:forEach var="map" items="${map}">
					<a class="main-article" href="threadpageservlet?e=${map.value.id}">
					<div class="side-time">
					<div class="profile-area-2">
					<img class="profile-img" src="images/profile_icon.png">
					<div class="user-info">
						<p class="userName">
						<c:out value="${account.name}"/>
						</p>
						<p class="userId">
						@<c:out value="${map.value.user_id}"/>
						</p>
					</div>
					</div>
					<p class="time">
						<c:out value="${map.value.time}"/>
					</p>
					</div>
						<p class="comment">
						${map.value.comment}
						</p>
						<p class="tag">
						<c:out value="${map.value.tag}"/>
						</p>
					</a>
					<hr>
				</c:forEach>
				</div>
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
			<div class="pagetop" id="js-pagetop">↑</div>
			</aside>
			<!----/right-aside---->
		</div>
	</div>
</body>
</html>