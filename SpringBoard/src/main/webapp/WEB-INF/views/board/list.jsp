<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<c:url value="/resources/css/button.css" var="btnCSS"/>
<meta charset="UTF-8">
<title>Board</title>
<link rel="stylesheet" href="${btnCSS}"/>
</head>
<body>

	<h1>게시판</h1>

	<table id="board" border='3'>
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>종류</th>
			<th>글쓴이</th>
			<th>내용</th>
			<th>작성일</th>
			<th>View</th>
			<th>추천</th>
			<th>비추천</th>
		</tr>
			<!-- c:forEach 사용 -->
			<c:forEach items="${boards}" var="board">
				<tr>
					<td>
						${board.board_id}
					</td>
					<td>
						<a>${board.write_title}</a>
					</td>
					<td>
						${board.write_type}
					</td>
					<td>
						<a>${board.writer}</a>
					</td>
					<td>
						<a href="./show?board_id=${board.board_id}">${board.write_content}</a>
					</td>
					<td>
						${board.creationDateTime} <!-- 오늘건 시간으로.. 어제 이전은 날짜로 -->
					</td>
					<td>
						${board.write_view}
					</td>
					<td>
						${board.write_recommand}
					</td>
					<td>
						${board.write_not_recommand}
					</td>					
				</tr>
			</c:forEach>
	</table>
	<div><a href="">&lt;&nbsp;</a>
		<c:forEach begin="${pagination_start}" end="${pagination_end}" var="i">
			<a href="./list?page=${i}">${i}</a>
		</c:forEach>
	<a href="">&nbsp;&gt;</a>&nbsp;<a class="btn" href="./write">글쓰기</a></div>
	
	<script>
		if("<%=request.getParameter("status")%>" == 'delete_complete') {
			alert("게시글 삭제 성공");	
		} else if("<%=request.getParameter("status")%>" == 'delete_failed') {
			alert("게시글 삭제 실패");
		} else if("<%=request.getParameter("status")%>" == 'modify_complete') {
			alert("게시글 수정 완료");
		} else if ("<%=request.getParameter("status")%>" == 'modify_failed') {
			alert("게시글 수정 실패");
		} else if ("<%=request.getParameter("status")%>" == 'write_complete') {
			alert("게시글 작성 완료");
		} else if ("<%=request.getParameter("status")%>" == 'view_error') {
			alert("조회수 오류!");
		}
	</script>
</body>
</html>