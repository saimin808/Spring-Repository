<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 확인</title>
</head>
<body>

	<h3>비밀번호 확인</h3>

	<c:choose>
		<c:when test="${status eq 'modify'}">	
			<div>수정하기 위해 비밀번호를 입력하세요</div>
			<form action="./modify" method="POST">
				<div>
					<input type="hidden" name="board_id" value="${board_id}"/>
					<input type="password" name="write_pw"/>
					<input type="submit" value="입력"/>
				</div>
			</form>
		</c:when>
		<c:when test="${status eq 'delete'}">
			<div>삭제하기 위해 비밀번호를 입력하세요</div>
			<form action="./delete/do" method="POST">
				<div>
					<input type="hidden" name="board_id" value="${board_id}"/>
					<input type="password" name="write_pw"/>
					<input type="submit" value="입력"/>
				</div>
			</form>
		</c:when>
	</c:choose>
	<div id="pw_check"></div>
	
	<script>
		const pw_check = document.getElementById("pw_check");
		if("<%=request.getParameter("pw_check")%>" == "wrong_password") {
			pw_check.innerHTML += "잘못된 비밀번호입니다.";
		}
	</script>
</body>
</html>