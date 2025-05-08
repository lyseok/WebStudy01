function FetchApi(baseUrl = "") {
	this.fetchJson = async () => {
		let resp = await fetch(`${baseUrl}/form`, {
			headers: {
				"accept": "application/json"
			}
		});
		
		if(resp.ok){
			return resp.json();	
		} else {
			throw new Error(resp.statusText);
		}
	}
	
	this.fetchHtml = async (url) => {
		let resp = await fetch(url);
		if(resp.ok){
			return resp.text();	
		} else {
			throw new Error(resp.statusText);
		}
	}
}





document.addEventListener("DOMContentLoaded", async () => {
	let fetchApi = new FetchApi("../../mbti");
	const selectEl = document.querySelector(`[name="mbtiType"]`);
	const formEl = selectEl.form;
	const contentAreaEl = document.querySelector('#content-area');
	
	selectEl.addEventListener('change', (e) => {
		e.target.form.requestSubmit();
	});
	
	formEl.addEventListener('submit', async (e) => {
		e.preventDefault();
		
		let params = new URLSearchParams(new FormData(formEl));
		params.set("layout", "none");
		
		let url = `${formEl.action}?${params}`;
		let content = await fetchApi.fetchHtml(url);
		
		contentAreaEl.replaceChildren("");
		contentAreaEl.innerHTML = content;	
	});
	
	let mbtiProps = await fetchApi.fetchJson();
	let array = Array.from(Object.entries(mbtiProps));
	let options = array.map(([k, v]) => `<option value="${k}">${v}</option>`).join('\n');
	selectEl.innerHTML = options;
		
		// for문에서 in 연산자는 반복의 대상이 객체이거나 배열일때
		// for문에서 of 키워드는 반복의 대상이 주로 배열일때
//		for(let p in mbtiProps){
//			console.log(p, mbtiProps[p]);
//		}

});