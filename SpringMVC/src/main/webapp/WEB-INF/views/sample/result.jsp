<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Result</title>
</head>
<body>

	<h1>Result.jsp</h1>

	<p>${employee}</p>

	<h3>하나씩 받기</h3>
	
	<ul>
		<li>${employee_id}</li>
		<li>${first_name}</li>
		<li>${last_name}</li>
	</ul>
</body>
</html>