<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<form action="/board/BoardServlet" method="post" name="form1" onSubmit="return check()">
<p>名前:<input type="text" name="name"></p>
<p>コメント:<br>
<textarea name="comment" rows="5" cols="40"></textarea>
</p>
<p><input type="submit" value="送信"><input type="reset" value="リセット">
</p>
</form>

<c:forEach var="list" items="${listAttribute}">
<p>ID:<c:out value="${list.id}"/> 名前:<c:out value="${list.name}"/> 日付:<c:out value="${list.time}"/><br>
<c:out value="${list.comment}"/></p>
</c:forEach>
<body>

</body>
</html>