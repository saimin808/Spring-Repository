<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Database</title>
</head>
<body>

	<h3># DB Test Index</h3>

	<ul>
		<li><a href="./employees">employees 보러가기</a></li>
		<li><a href="./employee2">employees 보러가기 (Mybatis)</a></li>
		<li><a href="./board/get?board_id=1">글 하나 보기 (Mybatis)</a></li>
		<li><a href="./board/list">글 전체 보기 (Mybatis)</a></li>
		<li><a href="./board/add?writer=mybatis&write_pw=4321&write_type=자유글&write_title=Mybatis Test&write_content=hello!!">글 쓰기 (Mybatis)</a></li>
		<li><a href="./board/update?board_id=3&write_content=updated!!">글 변경 (Mybatis)</a></li>
		<li><a href="./board/delete?board_id=1">글 삭제 (Mybatis)</a></li>
		<li></li>
	</ul>
	

</body>
</html>