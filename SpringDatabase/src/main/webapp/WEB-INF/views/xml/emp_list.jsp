<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee List</title>
</head>
<body>

	<h3>employee</h3>

	<ul>
		<c:forEach items="${employees}" var="employee">
			<li>${employee.first_name} ${employee.last_name}
				(${employee.salary})</li>
		</c:forEach>
	</ul>

</body>
</html>