<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ezen.springmvc.model.Player" %>
<%-- <% String player = (String)application.getAttribute("player"); 
	Player record = null;
	if(player != null) {
	   record = (Player)application.getAttribute(player);
	}
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Game Index</title>
<style>
form {
display: inline-block;
}
</style>
</head>
<body>

	<h3># 가위바위보 게임</h3>
	
	<ul>
		<li>처음 접속하는 사용자라면 사용자 이름을 먼저 입력해야 한다. (쿠키 or localStorage)</li>
		<li>이름을 입력하거나 이미 존재하는 이름이 있다면 가위바위보를 계속 진행할 수 있다.</li>
		<li>전적은 계속 누적되고 승률도 나오고 나중에 들어와도 유지되어야 한다.</li>
	</ul>

	<hr>
	
	<h3>가위바위보</h3>
	
	<c:choose>
		<c:when test="${cookie.player.value == null}">
			<form action="./game_set" method="GET">
			사용자 : <input type="text" name="player"/>
			<input type="submit" value="시작"/>
			</form>
		</c:when>
		<c:when test="${cookie.player.value != null}">
			<h3>${cookie.player.value}님의 게임 (${applicationScope.player})</h3>
			<form action="./game_result" method="POST">
				<%-- <input type="hidden" name="player" value="${cookie.}"/> --%>
				<input type="radio" name="answer" value="1"/>가위
				<input type="radio" name="answer" value="2"/>바위
				<input type="radio" name="answer" value="3"/>보
				<input type="submit" value="내기"/>
			</form>
			<form action="./logout" method="GET">
				<input type="submit" value="그만"/>
			</form>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${result eq 'win'}">
			<div>승리!</div>
		</c:when>
		<c:when test="${result eq 'draw'}">
			<div>무승부!</div>
		</c:when>
		<c:when test="${result eq 'lose'}">
			<div>패배...</div>
		</c:when>
	</c:choose>
	
	<script>
		const player = document.getElementByName('player');
		
		
	
		/* if("${applicationScope.player}" == 'logout') {
			alert('게임 끝.');
		} */
	</script>
</body>
</html>