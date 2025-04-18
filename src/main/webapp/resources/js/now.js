/**
 * 
 */
const clientTime = document.querySelector('#client-time');
const serverTime = document.querySelector('#server-time');

function repeatJob(){
	now = new Date();
	clientTime.innerHTML = now.toString();

	fetch('/ws01/server-time')
		.then(res => {
			if(res.ok){
				return res.text();
			}else{
				throw new Error("응답 에러", {cause : resp});
			}
		})
		.then(res => {
			serverTime.innerHTML = res;
		})
		.catch(err => console.log(err));
}

function start(){
	jobId = setInterval(repeatJob, 1000);
}

function stop() {
	clearInterval(jobId);
}

document.addEventListener('DOMContentLoaded', () => {
	startBtn.addEventListener('click', start);
	stopBtn.addEventListener('click', stop);
});

