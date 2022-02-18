<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%--JSTL 1.1.2 core タグライブラリ--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	<p>${sb.name}</p>
</div>
	<p>${sb.text}</p>
	<p>${sb.time}</p>
	<p>${sb.tag}</p>
	<p>${sb.userid}</p>


	<form method='post' action="threadpageservlet">
	<div>
			<input type="text" name="text"> <input type="text" name="tag">
		</div>

		<p>
			<input type="submit" value="tweet">
		</p>



	</form>

	<c:forEach var="map" items="${map}">

	<p><c:out value="${map.value.text}"/></p>
	<p><c:out value="${map.value.time}"/></p>
	<p><c:out value="${map.value.tag}"/></p>
	<p><c:out value="${map.value.name}"/></p>
	<p><c:out value="${map.value.reply_id}"/></p>


	</c:forEach>
<body>

</body>
</html>