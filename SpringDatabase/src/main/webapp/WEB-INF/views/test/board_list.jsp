<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록</title>
</head>
<body>

	<ul>
		<c:forEach items="${boards}" var="board">
			<div>${board}</div>
			<div><a href="./update?board_id=${board.board_id}&write_title=modify test&write_content=yes!!">수정</a></div>
			<div><a href="./delete?board_id=${board.board_id}">삭제</a></div>
		</c:forEach>
	</ul>

</body>
</html>