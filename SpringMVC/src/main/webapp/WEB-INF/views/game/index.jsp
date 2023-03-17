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

	<h3># ���������� ����</h3>
	
	<ul>
		<li>ó�� �����ϴ� ����ڶ�� ����� �̸��� ���� �Է��ؾ� �Ѵ�. (��Ű or localStorage)</li>
		<li>�̸��� �Է��ϰų� �̹� �����ϴ� �̸��� �ִٸ� ������������ ��� ������ �� �ִ�.</li>
		<li>������ ��� �����ǰ� �·��� ������ ���߿� ���͵� �����Ǿ�� �Ѵ�.</li>
	</ul>

	<hr>
	
	<h3>����������</h3>
	
	<c:choose>
		<c:when test="${cookie.player.value == null}">
			<form action="./game_set" method="GET">
			����� : <input type="text" name="player"/>
			<input type="submit" value="����"/>
			</form>
		</c:when>
		<c:when test="${cookie.player.value != null}">
			<h3>${cookie.player.value}���� ���� (${applicationScope.player})</h3>
			<form action="./game_result" method="POST">
				<%-- <input type="hidden" name="player" value="${cookie.}"/> --%>
				<input type="radio" name="answer" value="1"/>����
				<input type="radio" name="answer" value="2"/>����
				<input type="radio" name="answer" value="3"/>��
				<input type="submit" value="����"/>
			</form>
			<form action="./logout" method="GET">
				<input type="submit" value="�׸�"/>
			</form>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${result eq 'win'}">
			<div>�¸�!</div>
		</c:when>
		<c:when test="${result eq 'draw'}">
			<div>���º�!</div>
		</c:when>
		<c:when test="${result eq 'lose'}">
			<div>�й�...</div>
		</c:when>
	</c:choose>
	
	<script>
		const player = document.getElementByName('player');
		
		
	
		/* if("${applicationScope.player}" == 'logout') {
			alert('���� ��.');
		} */
	</script>
</body>
</html>