/**
 * 
 */

const clientTime = document.querySelector('#client-time');

const serverTime = document.querySelector('#server-time');
const startBtnEl = document.querySelector('#startBtn');
const stopBtnEl = document.querySelector('#stopBtn');


startBtnEl.addEventListener('click', () => {
	serverTimeId = setTime();
});

stopBtnEl.addEventListener('click', () => {
	clearInterval(timeId);
});

function setTime(){
	return setInterval(()=>{
		now = new Date();
		clientTime.innerHTML = now.toString();
		
		fetch('/ws01/server-time',{
			method: 'get',
			success: function(res){
				console.log(res);
			},
		})
			.then(res => res.text())
			.then(res => {
				console.log(res)
				serverTime.innerHTML = res;
			})
			.catch(error => console.log(error));
	}, 100);
}

let serverTimeId = setTime();

