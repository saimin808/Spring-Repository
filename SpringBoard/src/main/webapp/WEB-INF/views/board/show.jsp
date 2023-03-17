<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물</title>
<style>
form {
	display: inline;
}

input.submitLink {
	background-color: transparent;
    text-decoration: underline;
    color: blue;
    border: none;
    cursor: pointer;
}
</style>
</head>
<body>

	<h3>게시물 보기</h3>

	<div id="board" class="container">
			<div id="board_id">
				<b>No:</b> ${board.board_id}
			</div>
			<div id="title">
				<b>제목:</b> ${board.write_title}
			</div>
			<div id="type">
				<b>주제:</b> ${board.write_type}
			</div>
			<div id="writer">
				<b>작성자:</b> ${board.writer}
			</div>
			<div id="content">
				<textarea cols="50" rows="10" readonly>${board.write_content}</textarea>
			</div>
			<div id="date">
				<b>쓴 날짜:</b> ${board.write_date}
			</div>
			<div id="views">
				<b>조회수:</b> ${board.write_view}
			</div>
			<div id="recommend">
				<form action="./recommend" method="GET">
					<b>추천:</b> ${board.write_recommand}
					<input type="hidden" name="board_id" value="${board.board_id}"/>
					<input type="submit" value="추천"/>
				</form>
			</div>
			<div id="not_recommand">
				<form action="./not_recommend" method="GET">			
					<b>비추천:</b> ${board.write_not_recommand}
					<input type="hidden" name="board_id" value="${board.board_id}"/>
					<input type="submit" value="비추천"/>
				</form>
			</div>
		<div id="buttons">
			<button onclick="location.href='./list?page=1'">글 목록</button>
			<form action="./modify_pw_check" method="GET" id="modify">
				<input type="hidden" form="modify" value="${board.board_id}" name="board_id" />
				<input type="hidden" form="modify" value="modify" name="msg"/>
				<input type="submit" form="modify" value="수정"/>
			</form>
			<form action="./delete_pw_check" method="GET" id="delete">
				<input type="hidden" form="delete" value="${board.board_id}" name="board_id" />
				<input type="hidden" form="delete" value="delete" name="msg"/>
				<input type="submit" form="delete" value="삭제"/>
			</form>
		</div>
	</div>
	
	<br>
	
	<jsp:include page="../comment/comment.jsp"/>
	
	<script>
		if("<%=request.getParameter("status")%>" == 'delete_complete') {
			alert("댓글 삭제 성공");	
		} else if("<%=request.getParameter("status")%>" == 'delete_failed') {
			alert("댓글 삭제 실패");
		} else if("<%=request.getParameter("status")%>" == 'modify_complete') {
			alert("댓글 수정 완료");
		} else if ("<%=request.getParameter("status")%>" == 'modify_failed') {
			alert("댓글 수정 실패");
		}
	</script>
</body>
</html>