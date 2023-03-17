const form = document.getElementById("modify");

	form.addEventListener('submit', (e) => {
		// 해당 이벤트의 기본 동작을 막을 수 있다.
		e.preventDefault();
		
		// form태그에 이벤트를 붙인 것이므로 e.target은
		// submit 이벤트가 발생한 해당 폼 태그를 의미한다.
		
		// 폼 내부의 모든 요소들
		const form = e.target;
		const elements = e.target.elements;
		
		const pw_regex = /^(?=.*[a-zA-Z])(?=.*\d).{4,30}$/;
		const id_regex = /^[a-z]+(?=.*[a-zA-Z])(?=.*\d).{4,30}$/;
		
		for(j = 0; j < elements.length; ++j) {
			const element = elements[j];
			// name 속성이 존재하는 태그들만 불러온다. (submit 버튼은 체크할 필요 없다)
			/*if (element.name === '' || element.type === 'submit') {
				console.log('continue.');
				continue;
			}*/
			
			if (element.value == null || element.value == '') {
				window.alert(element.name + ' is empty!!');
				
				// focus() : 해당 요소로 커서를 옮긴다.
				element.focus();
				return;
			}
			
			if((element.name == ('writer') || element.name == ('comment_writer'))
			&& element.value.length <= 1) {
				window.alert('nick-name must be longer than 1');
				return;
			}
			
			// 비밀번호가 비어있지 않더라도 4글자 미만이라면 submit하지 않음
			if ((element.name == ('pw') || element.name == ('comment_pw'))
				&& element.value.length <= 4 ) {
					window.alert('password must be longer than 4.');
					return;
			} else if ((element.name == ('pw') || element.name == ('comment_pw'))
				&& element.value.length > 4) {
				const is_valid = pw_regex.test(element.value);
				if(is_valid == false) {
					window.alert('password must contains at least one letter and one number')
					return;
				}
			}
		}
		
		// 검사가 끝난 후 최종적으로 해당 form을 submit
		form.submit();
	});
