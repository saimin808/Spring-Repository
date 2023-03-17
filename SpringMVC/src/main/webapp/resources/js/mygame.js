const btns = document.getElementsByClassName('decision');
const recordPanel = document.getElementById('record_panel');
const computer = document.getElementById('com');
const me = document.getElementById('me');
const message = document.getElementById('message');
const logout = document.getElementById('logout');
const reset = document.getElementById('reset');


const loadRecord = () => {
	const recordStr = localStorage.getItem(userName);

	if (recordStr == null || recordStr == '') {
		return {
			win: 0,
			draw: 0,
			lose: 0
		};
	} else {
		// JSON 형태의 문자열 덩어리를 자바스크립트 오브젝트로 변환
		return JSON.parse(recordStr);
	}
	
};

const record = loadRecord();

const printRecord = () => {
	winRate = record.win / (record.win + record.draw + record.lose) * 100;
	winRate = Math.round(winRate * 100) / 100;

	recordPanel.innerText = `win: ${record.win} draw: ${record.draw} lose: ${record.lose} (${winRate}%)`;
};

const images = [contextPath + '/resources/images/scissors.png',
				contextPath + '/resources/images/rock.png',
				contextPath + '/resources/images/paper.png'];

// 0:가위, 1: 바위 , 2: 보
const getResult = (num) => {
	// 
	const com = parseInt(Math.random() * 3);
	
	// change computer image
	computer.style.backgroundImage = 'url(' + images[com] + ')';
	
	if (com == num) {
		return 'draw';
	} else if (com == (num + 1) % 3) {
		return 'lose';
	} else {
	 	return 'win';
	}
	
	//com == num + 1 % 3; // 사람이 짐
	//num == com + 1 % 3; // 사람이 이김
	//com == num // 비김
}

const clickAction = (e) => {
	console.log('I clicked:', e.target);
	
	const btn = e.target;
	
	console.log('this element got:', btn.dataset.num);
	
	me.style.backgroundImage = 'url(' + images[btn.dataset.num] + ')';
	
	const result = getResult(btn.dataset.num);
	
	console.log('Game result:', result);
	
	message.innerText = result.toUpperCase() + '!';
	
	record[result] += 1;
	
	console.log(record);
	
	printRecord();
	
	// 자바스크립트 오브젝트를 문자열로 변환하여 저장하기 (JSON)
	const record_json = JSON.stringify(record);
	
	localStorage.setItem(userName, record_json);
}

logout.addEventListener('click', () => {
	location.href = contextPath + '/mygame/logout';
});

const resetRecord = () => {
	return {
		win: 0,
		draw: 0,
		lose: 0
	};
};

reset.addEventListener('click', () => {
	const empty_record = resetRecord();
	
	const empty_record_json = JSON.stringify(empty_record);
	
	localStorage.setItem(userName, empty_record_json);
	
	location.href = contextPath + '/mygame/main_T';
});

for (i = 0; i < btns.length; i++) {
	console.log(btns[i]);
	
	btns[i].addEventListener('click', clickAction);
}

printRecord();