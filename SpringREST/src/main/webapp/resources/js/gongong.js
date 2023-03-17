const sisul = document.getElementById('sisul');
const testBtn = document.getElementById('test-btn');
const sisulId = 3585;

const endPoint = 'https://apis.data.go.kr/3980000/grBiz';
const serviceKey = 'Fp5Ne%2FiEd08IgWaOdwAvCEHHEG91%2BcwhKpA5AaG3yVN28eLRrTzWOopgl9rO%2F9OK6Iq3pf3oBhTfD6qLKyOIaw%3D%3D';

testBtn.addEventListener('click', (e) => {
	const xhttp = new XMLHttpRequest();
	xhttp.addEventListener('readystatechange', (e) => {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			const obj = JSON.parse(xhttp.responseText);

			// 여기에 공공데이터
			console.log(obj);

			// 이후에는 공공데이터로 멋있게 잘 요소를 만들어서 화면에 추가한다.
		}
	});
	xhttp.open('GET', `${endPoint}/getBizItem?serviceKey=${serviceKey}`
		+ `&type=json&id=${sisulId}`);
	xhttp.send();
});