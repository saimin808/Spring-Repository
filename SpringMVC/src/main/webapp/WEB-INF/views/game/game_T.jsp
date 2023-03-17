<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/resources/css/mygame.css" var="mygame_css"/>
<c:url value="/resources/js/mygame.js" var="mygame_js"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>����������</title>
<link rel="stylesheet" href="${mygame_css}" />
</head>
<body>

	<h3># ���������� ����</h3>
	
	<ul>
		<li>ó�� �����ϴ� ����ڶ�� ����� �̸��� ���� �Է��ؾ� �Ѵ�. (��Ű or localStorage)</li>
		<li>�̸��� �Է��ϰų� �̹� �����ϴ� �̸��� �ִٸ� ������������ ��� ������ �� �ִ�.</li>
		<li>������ ��� �����ǰ� �·��� ������ ���߿� ���͵� �����Ǿ�� �Ѵ�.</li>
	</ul>

	<hr>

	<c:url value="/mygame/index" var="index"/>

	<c:choose>
		<c:when test="${empty cookie.user.value}">
			<form action="${index}" method="POST">
				<h4>�̸��� �������ּ���</h4>
				<input type="text" name="user" />
				<input type="submit" value="����"/>
			</form>
		</c:when>
		<c:otherwise>
			<h3>${cookie.user.value}���� ����</h3>
			<div id="record_panel"></div>
			<div id="com_lbl" class="lbl">COM</div>
			<div id="me_lbl" class="lbl">ME</div> <br>
			<div id="com" class="box img"></div>
			<div id="vs" class="box">VS</div>
			<div id="me" class="box img"></div>
			<div id="message"></div>
			<div>
				<button class="decision" data-num="0">����</button>
				<button class="decision" data-num="1">����</button>
				<button class="decision" data-num="2">��</button>
				&nbsp; &nbsp; &nbsp;
				<button id="logout">�׸�</button>
				<button id="reset">����</button>
			</div>
		</c:otherwise>
	</c:choose>
	
	<c:if test="${not empty cookie.user.value}">
	<script>
		const contextPath = '<%= request.getContextPath() %>';
		const userName = '${cookie.user.value}';
	</script>
	<!-- Servlet-context���� Ȯ���� resources ��η� �������ش�. -->
	<script src="${mygame_js}"></script>
	</c:if>
</body>
</html>