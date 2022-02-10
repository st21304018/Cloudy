<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
	function actionToggle() {
		var target = document.getElementById('like');
		target.classList.toggle('onHeart');
		target.classList.toggle('offHeart');
	}
</script>
<link rel="stylesheet" href="css/reaction.css">
<title>掲示板</title>
</head>
<body>
<form action="MainPage" method="post" name="form1" onSubmit="return check()">

<p>コメント:<br>
<textarea name="comment" rows="5" cols="40"></textarea>
</p>
<p><input type="submit" value="TWEET">
</p>
</form>

<c:forEach var="list" items="${listAttribute}">
<p><br>
<c:out value="${list.comment}"/></p>
<div class="likes-area">
	<form method="POST" action="like">
		<input type="hidden" name="threadID" value="${id}">
		<input type="hidden" name="check" value="${check}">
		<div id="like" class="input-wrapper offHeart">
			<c:if test="${!empty check}">
				<script>
					actionToggle();
				</script>
			</c:if>
			<input class="input-submit" type="submit" value="">
		</div>
		<p class="like_count">${list.likes}</p>
	</form>
</div>
</c:forEach>

</body>
</html>