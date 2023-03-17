/*
    # Vanilla Javascript

    - 아무 라이브러리도 사용하지 않는 기본 자바스크립트

    # JQuery

    - 자바스크립트 문법 길이를 줄인 것
    - 한국 개발자들이 이상하게 아주 좋아한다.
    - 새로운 자기들만의 문법이 있어서 기본 자바스크립트 문법이 잘 안먹힌다.

    $('#ajax-out').click();
*/
const ajaxOut = document.getElementById('ajax-out');
const ajaxBtn1 = document.getElementById('ajax-get-btn1');
const ajaxBtn2 = document.getElementById('ajax-get-btn2');
const ajaxBtn3 = document.getElementById('ajax-get-btn3');

const ajaxPostBtn1 = document.getElementById('ajax-post-btn1');
const ajaxPostBtn2 = document.getElementById('ajax-post-btn2');

const ajaxPutBtn1 = document.getElementById('ajax-put-btn1');

//GET
ajaxBtn1.addEventListener('click', (e) => {
    // AJAX 요청 인스턴스 생성
    const xhttp = new XMLHttpRequest();

    // 응답이 왔을 때 동작을 설정
    xhttp.addEventListener('readystatechange', (e) => {
       
        // 준비도 다 되었고, httpStatus도 ok인 상황
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            // RestController에서 응답한 데이터(body)가 responseText에 들어있다.
            console.log('요청 성공!', xhttp.responseText);
            ajaxOut.innerText = xhttp.responseText;
        }
    });

    // 요청을 어떤 방식(method)으로, 어디로(url) 보낼지 설정
    xhttp.open('GET', '/springrest/restful/test1');

    // 설정이 모두 끝난 요청을 보낸다.
    xhttp.send();
});

ajaxBtn2.addEventListener('click', (e) => {
    const xhttp = new XMLHttpRequest();

    xhttp.addEventListener('readystatechange', (e) => {
        if (xhttp.readyState == 4) {
            if(xhttp.status == 200) {
                // 서버로부터 응답받은 JSON 형태의 문자열을 Javascript Object로 변환
                const obj = JSON.parse(xhttp.responseText);

                ajaxOut.innerHTML = `name: ${obj.name}<br>`;
                ajaxOut.innerHTML += `price: ${obj.price}<br>`;
                ajaxOut.innerHTML += `calories: ${obj.calories}<br>`;
            }
        }
    });
    xhttp.open('GET', 'restful/pizza2');
    xhttp.send();
});

ajaxBtn3.addEventListener('click', (e) => {
    const xhttp = new XMLHttpRequest();

    xhttp.addEventListener('readystatechange', (e) => {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
                // responseXML로 받은 XML Text는 document 타입 객체가 된다.
                const xdocument = xhttp.responseXML;

                ajaxOut.innerHTML = 'name: ' + xdocument.getElementsByTagName('name')[0].textContent +'<br>';
                ajaxOut.innerHTML += 'price: ' + xdocument.getElementsByTagName('price')[0].textContent +'<br>';
                ajaxOut.innerHTML += 'calories: ' + xdocument.getElementsByTagName('calories')[0].textContent +'<br>';
        }
    });
    // 현재 주소가  http://localhost:8888/springrest/이기 때문에 상대 경로 사용 가능
    xhttp.open('GET', 'restful/pizza3');
    xhttp.send();
});

//POST

ajaxPostBtn1.addEventListener('click', (e) => {
    const xhttp = new XMLHttpRequest();

    xhttp.addEventListener('readystatechange', (e) => {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            console.log(xhttp.responseText);
        }
    });
    xhttp.open('POST', 'restful/employee');

    // 실어 보낼 데이터가 어떤 타입인지 지정해줘야 한다.
    // 데이터를 form 형식으로 보낼 것이기 때문에 form을 보낼 때와 똑같이 설정한다.
    xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

    // send(body) : 데이터를 실어 보낼 수 있다.
    xhttp.send('first_name=Lily&last_name=James&salary=7000');
});

ajaxPostBtn2.addEventListener('click', (e) => {
    const xhttp = new XMLHttpRequest();

    xhttp.addEventListener('readystatechange', (e) => {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                ajaxOut.innerHTML = xhttp.responseText;
            }
    });
    xhttp.open('POST', 'restful/employee/json');

    xhttp.setRequestHeader('content-type', 'application/json');

    const obj = {
         first_name: 'Ron',
         last_name: 'Wisley',
         salary: 6800
    }
    xhttp.send(JSON.stringify(obj));
});

//PUT
ajaxPutBtn1.addEventListener('click', (e) => {
    const xhttp = new XMLHttpRequest();

    xhttp.addEventListener('readystatechange', (e) => {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            console.log(xhttp.responseXML);
        }
    });
    xhttp.open('PUT', 'restful/employee');
    
    //GET/POST 외의 방식은 'application/x-www-form-urlencoded'를 쓸 수 없다.
    xhttp.setRequestHeader('content-type', 'application/json');
    
    const obj = {
        first_name: 'Harry',
        last_name: 'Potter',
        salary: 8000
    }
    //JSON 형태의 문자열 데이터와 함께 RestController로 요청.
    xhttp.send(JSON.stringify(obj));
});


// Spring의 ResponseEntity타입으로 원하는 status를 선택해 응답할 수 있다.