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
<title>Cloudy - マイページ</title>
</head>
<body>

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

</body>
</html>