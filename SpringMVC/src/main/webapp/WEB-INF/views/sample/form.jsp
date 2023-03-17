<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>@GetMapping test</title>
</head>
<body>
	
	<!-- JSP jstl의 맨 마지막 부분 복습 하세요 -->
	<c:url value="/sample/post" var="form1"/>
	<c:url value="/sample/post2" var="form2"/>
	<c:url value="/sample/post3" var="form3"/>
	
	<h3># Form Test</h3>
	
	<form action="${form1}" method="POST">
		ID: <input type="text" name="employee_id" value="1"/> <br>
		FIRST_NAME: <input type="text" name="first_name" value="Smith"/> <br>
		LAST_NAME: <input type="text" name="last_name" value="King"/> <br>
		<input type="submit" value="OK"/>
	</form>
	
	<h3># Form Test2</h3>
	
	<form action="${form2}" method="POST">
		ID: <input type="text" name="employee_id" value="1"/> <br>
		FIRST_NAME: <input type="text" name="first_name" value="Smith"/> <br>
		LAST_NAME: <input type="text" name="last_name" value="King"/> <br>
		<input type="submit" value="OK"/>
	</form>
	
	<h3># Form Test3</h3>
	
	<form action="${form3}" method="POST">
		ID: <input type="text" name="employee_id" value="1"/> <br>
		FIRST_NAME: <input type="text" name="first_name" value="John"/> <br>
		LAST_NAME: <input type="text" name="last_name" value="Doe"/> <br>
		<input type="submit" value="OK"/>
	</form>
	
</body>
</html>