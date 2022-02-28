<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%--JSTL 1.1.2 core タグライブラリ--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cloudy - リプライ</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="js/common.js"></script>
<link rel="stylesheet" href="css/reaction.css">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/Reply.css">
</head>
<body>
	<div id="thread" class="thread_page">
		<!---- input-box ---->
		<div id="box" class="box_area" style="display: none">
			<div class="input_box">
				<div class="input_box_close" onclick="hideForm()">×</div>
				<div class="form_area">
					<form action="threadpageservlet" method="post" name="form1"
						onSubmit="return check()">
						<div class="input_area">
							<textarea id="input_comment" name="text" class="input_text_area"
							placeholder="コメントを入力" required></textarea><br>
							<textarea name="tag" class="input_tag_area" maxlength="9"
							placeholder="タグを入力"></textarea>
							<div class="commit-area">
								<div class="count-area"><span id="num">0</span><span>/200</span></div>
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
					<input type="button" class="putButton" onclick="showForm()" value="Comment">
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
			<div id="title" class="title-area"><a class="title-link" href="MainPage">←</a><p class="title-text">　　Thread</p></div>
				<div class="tweet-area">
				<div class="side-time">
					<div class="profile-area-2">
					<img class="profile-img" src="images/profile_icon.png">
					<div class="user-info">
					<p class="userName">${sb.name}</p>
					<p class="userId">@<c:out value="${sb.userid}" /></p></div></div>
					<p class="time">${sb.time}</p></div>
					<p class="comment">${sb.text}</p>
					<p class="tag">${sb.tag}</p>
					<hr>
				<c:forEach var="map" items="${map}">
				<div class="reply">
				<div class="side-time">
					<div class="profile-area-2">
					<img class="profile-img" src="images/profile_icon.png">
					<div class="user-info">
					<p class="userName">
						<c:out value="${map.value.name}" />
					</p>
					<p class="userId">
						@<c:out value="${map.value.userid}" />
					</p>
					</div>
					</div>
					<p class="time">
						<c:out value="${map.value.time}" />
					</p>
					</div>
					<p class="reply-mark">@${sb.userid}さんに返信</p>
					<p class="comment">
						${map.value.text}
					</p>
					<p class="tag">
						<c:out value="${map.value.tag}" />
					</p>
					<div class="likes-area">
						<!---- like-area ---->
						<div id="${map.value.reply_id}like" class="input-wrapper offHeart">
							<c:if test="${not empty map.value.check}">
								<script>actionToggle(${map.value.reply_id});</script>
							</c:if>
							<a href="likerep?e=${map.value.reply_id}" class="input-submit"></a>
						</div>
						<p class="like_count">${map.value.likes}</p>
					</div>
					</div>
					<hr>
					<!---- /like-area ---->
				</c:forEach>
				</div>
			</article>
			<!----right-aside---->
			<aside class="right-aside">
				<div class="right-wrapper">
					<div class="serch-box">
						<img src="images/search.png" class="search-img">
						<form method="GET" action="searchreply">
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