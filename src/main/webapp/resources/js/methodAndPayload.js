/**
 * 동기요청 vs 비동기요청 : lock의 사용 여부만 다름
 * 1. line : url, method
 * 2. header : header(name/value)...
 * 3. body : 클라이언트가 보낼 진짜 데이터(content-type 헤더에 따라 다른 형태)
 * 
 * fetch(url, {
 *   method: 'post',
 *   headers: {
 *     "accept": "application/json",
 *     "accept-language": "ko-KR",
 *     "content-type": "application/json"
 *   },
 *   body: FormData(Multipart/form-data), URLSearchParams(application/x-www-form-urlencoded)
 *         json페이로드나 xml 페이로드를 전송시에는 'content-type'헤더를 명시해야함함
 * })
 */
const defaultHeaders = {
  "accept": "text/html"
};

const options = {
  headers:defaultHeaders
}

function fnGet(form){
  let url = form.action;
  let params = new URLSearchParams(new FormData(form));

  fetch(`${url}?${params}`, {
    method: "get",
    headers: defaultHeaders
  })
    .then(res => res.text())
    .then(res => {
      console.log(res);
    })

}
async function fnPost(form){
  let url = form.action;
  let fd = new FormData(form);
  let params = new URLSearchParams(fd);
  let resp = await fetch(url, {
    method: 'post',
    headers: {
      ...defaultHeaders,
      "my-header": "headerValue"
    },
    body: params
  });
  let html = await resp.test();
  console.log(html);

  // const p1 = form.querySelector('[name=p1]').value;
  // const p2 = form.querySelector('[name=p2]').value
  // const data = {
  //   p1,
  //   p2
  // };
  
  // fetch(url, {
  //   method: "post",
  //   headers: {
  //     ...defaultHeaders,
  //     "content-type": "application/json"
  //   },
  //   body: JSON.stringify(data)
  // })
  //   .then(res => res.text())
  //   .then(res => {
  //     console.log(res);
  //   })

}
function fnPut(form){
  let url = form.action;
  let fd = new FormData(form); // FormData : form의 모든 입력 태그의 값을 이름과 값의(엔트리)로 관리하는 객체
  const obj = {p1:fd.get('p1'), p2:fd.get('p2')};

  fetch(url, {
    ...options,
    headers: {
      ...options.headers,
      "content-type": "application/json",
    },
    method:"put",
    body: JSON.stringify(obj)
  });
}
async function fnDelete(form){
  let url = form.action;
  let res = await fetch(url, {
    ...options,
    headers: {
      ...options.headers,
      "accept": "application/json"

    },
    method: 'delete'
  });
  let { success, message:msg } = await res.json();
  console.log(success);
  console.log(msg);
}

document.addEventListener('DOMContentLoaded', () => {
  // window['target-form'];
  const targetForm = document.querySelector('#target-form');

  document.addEventListener("click", (event) => {
    let method = event.target.dataset['method'];
    if(!method) return false;
    alert(`이벤트 버블링 구조로 처리, targer : ${method}`);
    switch(method){
      case 'GET':
        fnGet(targetForm);
        break;
      case 'POST':
        fnPost(targetForm);
        break;
      case 'PUT':
        fnPut(targetForm);
        break;
      case 'DELETE':
        fnDelete(targetForm);
        break;
      default:
    }
  });

  // document.querySelectorAll('button[data-method]').forEach((el, i) => {
  //   el.addEventListener('click', () => {
  //     let method = el.dataset['method'];
  //     alert(method);
  //   });
  // });
});