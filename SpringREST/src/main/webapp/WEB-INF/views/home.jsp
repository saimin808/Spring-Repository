<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

	<h3>Rest 요청 보내보기</h3>
	
	<ul>
		<li><a href="/springrest/restful/test1">Hello</a></li>
		<li><a href="/springrest/restful/pizza1">pizza1 (JSON, 수동)</a></li>
		<li><a href="/springrest/restful/pizza2">pizza2 (JSON, 자동)</a></li>
		<li><a href="/springrest/restful/pizza3">pizza3 (XML)</a></li>
	</ul>
	
	<h3># ResponseEntity로 응답 직접 생성하기</h3>
	
	<ul>
		<li><a href="/springrest/restful/ok1">ok1</a></li>
		<li><a href="/springrest/restful/ok2">ok2</a></li>
		<li><a href="/springrest/restful/ok3">ok3</a></li>
		<li><a href="/springrest/restful/status1">status1 (502 Bad Gateway)</a></li>
		<li><a href="/springrest/restful/status2">status2 (404 Not Found)</a></li>
	</ul>
	
	<h3># Ajax 요청 보내기 (GET)</h3>
	<c:url value="/resources/js/home.js" var="home_js" />
	
	<div id="ajax-out"></div>
	
	<button id="ajax-get-btn1">AJAX1(GET Plain Text)</button>
	<button id="ajax-get-btn2">AJAX2(GET JSON Text)</button>
	<button id="ajax-get-btn3">AJAX3(GET XML Text)</button>
	
	<h3># Ajax 요청 보내기 (POST)</h3>
	
	<button id="ajax-post-btn1">AJAX1(POST, Form)</button>
	<button id="ajax-post-btn2">AJAX2(POST, JSON)</button>
	
	<h3># Ajax 요청 보내기 (PUT)</h3>
	
	<button id="ajax-put-btn1">AJAX1(PUT, JSON)</button>
	
	<h3># Form 방식으로 요청 보내기</h3>
	
	<c:url value="/restful/employee" var="emp" />
	<h5>* Post(form)</h5>
	<form action="${emp}" method="POST">
		<input type="hidden" name="first_name" value="Mike" />
		<input type="hidden" name="last_name" value="Park" />
		<input type="hidden" name="salary" value="5500" />
		<input type="submit" value="보내기(POST)"/>
 	</form>
 	
	<h5>* Put(form?)</h5>
	<p><i>form으로는 GET과 POST방식 요청만 보낼 수 있다.</i></p>
	<form action="${emp}" method="PUT">
		<input type="hidden" name="first_name" value="Smith" />
		<input type="hidden" name="last_name" value="King" />
		<input type="hidden" name="salary" value="5500" />
		<input type="submit" value="보내기(POST)"/>
 	</form>
 	
 	<script src="${home_js}"></script>

</body>
</html>
