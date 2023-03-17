<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/resources/css/mygame.css" var="mygame_css"/>
<c:url value="/resources/js/mygame.js" var="mygame_js"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>가위바위보</title>
<link rel="stylesheet" href="${mygame_css}" />
</head>
<body>

	<h3># 가위바위보 게임</h3>
	
	<ul>
		<li>처음 접속하는 사용자라면 사용자 이름을 먼저 입력해야 한다. (쿠키 or localStorage)</li>
		<li>이름을 입력하거나 이미 존재하는 이름이 있다면 가위바위보를 계속 진행할 수 있다.</li>
		<li>전적은 계속 누적되고 승률도 나오고 나중에 들어와도 유지되어야 한다.</li>
	</ul>

	<hr>

	<c:url value="/mygame/index" var="index"/>

	<c:choose>
		<c:when test="${empty cookie.user.value}">
			<form action="${index}" method="POST">
				<h4>이름을 설정해주세요</h4>
				<input type="text" name="user" />
				<input type="submit" value="시작"/>
			</form>
		</c:when>
		<c:otherwise>
			<h3>${cookie.user.value}님의 전적</h3>
			<div id="record_panel"></div>
			<div id="com_lbl" class="lbl">COM</div>
			<div id="me_lbl" class="lbl">ME</div> <br>
			<div id="com" class="box img"></div>
			<div id="vs" class="box">VS</div>
			<div id="me" class="box img"></div>
			<div id="message"></div>
			<div>
				<button class="decision" data-num="0">가위</button>
				<button class="decision" data-num="1">바위</button>
				<button class="decision" data-num="2">보</button>
				&nbsp; &nbsp; &nbsp;
				<button id="logout">그만</button>
				<button id="reset">리셋</button>
			</div>
		</c:otherwise>
	</c:choose>
	
	<c:if test="${not empty cookie.user.value}">
	<script>
		const contextPath = '<%= request.getContextPath() %>';
		const userName = '${cookie.user.value}';
	</script>
	<!-- Servlet-context에서 확인한 resources 경로로 설정해준다. -->
	<script src="${mygame_js}"></script>
	</c:if>
</body>
</html>