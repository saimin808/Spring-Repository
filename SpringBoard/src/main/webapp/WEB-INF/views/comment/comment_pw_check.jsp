<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 확인</title>
</head>
<body>

	<h3>비밀번호 확인</h3>

	<c:choose>
		<c:when test="${status eq 'modify'}">	
			<div>수정하기 위해 비밀번호를 입력하세요</div>
			<form action="./modify" method="POST" id="modify">
				<div>
					<input type="hidden" name="comment_id" value="${comment_id}"/>
					<input type="password" name="comment_pw"/>
					<input type="submit" value="입력"/>
				</div>
			</form>
		</c:when>
		<c:when test="${status eq 'delete'}">
			<div>삭제하기 위해 비밀번호를 입력하세요</div>
			<form action="./delete/do" method="POST" id="delete">
				<div>
					<input type="hidden" name="comment_id" value="${comment_id}"/>
					<input type="hidden" name="board_id" value="${board_id}"/>
					<input type="password" name="comment_pw"/>
					<input type="submit" value="입력"/>
				</div>
			</form>
		</c:when>
	</c:choose>
	<div id="pw_check"></div>
	
	<script>
		const pw_check = document.getElementById("pw_check");
		if("<%=request.getParameter("pw_check")%>" == "fail") {
			pw_check.innerHTML += "잘못된 비밀번호입니다.";
		}
		
		const form = document.getElementByTagName("form");
		
		for (i = 0; i < forms.length; ++i) {
			// 각 form 태그에 onsubmit 이벤트를 붙인다.
			forms[i].addEventListener('submit', (e) => {
				// 해당 이벤트의 기본 동작을 막을 수 있다.
				e.preventDefault();
				
				// 폼 내부의 모든 요소들
				const form = e.target;
				const elements = e.target.elements;
				
				const pw_regex = /^(?=.*[a-zA-Z])(?=.*\d).{4,30}$/;
				
				for(j = 0; j < elements.length; ++j) {
					const element = elements[j];
					// name 속성이 존재하는 태그들만 불러온다. (submit 버튼은 체크할 필요 없다)
					/*if (element.name === '' || element.type === 'submit') {
						console.log('continue.');
						continue;
					}*/
					
					if (element.value == null || element.value == '') {
						window.alert(element.name + 'is empty!!');
						
						// focus() : 해당 요소로 커서를 옮긴다.
						element.focus();
						return;
					}
					
					// 비밀번호가 비어있지 않더라도 4글자 미만이라면 submit하지 않음
					if (element.name.includes('comment_pw'))
						&& element.value.length <= 4 ) {
							pw_check.innerHTML += "비밀번호는 4자리 이상이어야 합니다.";
							return;
					} else if (element.name.includes('comment_pw'))
						&& element.value.length > 4) {
						const is_valid = pw_regex.test(element.value);
						if(is_valid == false) {
							pw_check.innerHTML += "비밀번호는 최소 하나의 문자와 숫자를 포함해야 합니다.";
							return;
						}
					}
				}
				
				// 검사가 끝난 후 최종적으로 해당 form을 submit
				form.submit();
			});
		}
	</script>

</body>
</html>