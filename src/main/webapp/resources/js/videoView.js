/**
 * 1. 서버쪽으로 비동기 요청
 * 2. 동적타입 목록을 순수 데이터로 수신
 * 3. option 태그 생성 
 * 4. select 태그의 innerTHML로 설정
 */
document.addEventListener('DOMContentLoaded', () => {
  const select = document.querySelector('[name="video"]');

  select.addEventListener('change', (e)=>{
    let selected = e.target.value;
    resultArea.innerHTML = /* html */`
      <video src="../../03/Media?${select.name}=${selected}" 
        controls autoplay muted="muted"></video>
      `;
  })

  const resolve = res => {
    if(res.ok){
      return res.json();
    } else {
      throw new Error(`응답에러, 상태코드 : ${res.status}`);
    }
  }

  const resolveJson = fileList => {
    let options = fileList.map((fn) => `<option>${fn}</option>`).join("\n");
    select.innerHTML = options;
  }

  const reject = console.error;

  fetch("../../04/movieListData")
  .then(resolve)
  .then(resolveJson)
  .catch(reject)
  .finally(() => {
    console.log('비동기 요청 종료');
  });


});
// document.addEventListener('DOMContentLoaded', () => {
//   const selectEl = document.querySelector('select');
//   fetch("../../04/movieListData")
//   .then(res => res.json())
//   .then(res => {
//     let html = '';
//     res.forEach(e => {
//       html += /* html */`
//         <option>${e}</option>
//       `;
//     });
//     selectEl.innerHTML = html;
//   });

//   selectEl.addEventListener('change', (e) => {
//     console.log(e.target.value);
//     let path = `D:/00.medias/movies/${e.target.value}`;
//     console.log(path);

//     document.querySelector('video').setAttribute('src', path);
//   });

// });

