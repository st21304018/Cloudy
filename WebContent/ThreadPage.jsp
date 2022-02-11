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

	<p>${threadtext}</p>

	<form method='post' action="threadpageservlet">
		<div>
			<input type="text" name="text"> <input type="text" name="tag">
		</div>

		<p>
			<input type="submit" value="tweet">
		</p>



	</form>

	<c:forEach var="list" items="${list}">

	<p><c:out value="${list.text}"/></p>

	</c:forEach>
<body>

</body>
</html>