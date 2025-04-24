/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {
  // const facFormEl = document.querySelector('#fac-form');
  const facFormEl = window['fac-form'];
  facFormEl.addEventListener('submit', async (e)=>{
    e.preventDefault();
    let form = e.target;
    let url = form.action;
    let fd = new FormData(form);
    let queryString = new URLSearchParams(fd).toString();
    console.log(queryString);
    
    let res = await fetch(`${url}?${queryString}`)
    if(res.ok){
      let { result } = await res.json();
      resultArea.innerHTML = result;
    } else {

    }
  });
});