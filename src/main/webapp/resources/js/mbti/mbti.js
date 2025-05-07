const selectEl = document.querySelector('#mbtiSelect');
const resultEl = document.querySelector('#result');

const getOption = async () => {
  let res = await fetch("../../csr/mbti/form");
  let data = await res.json();

  Object.entries(data).forEach(([key, value]) => {
    const optionEl = document.createElement('option');
    optionEl.value = key;
    optionEl.text = value;
    selectEl.appendChild(optionEl);
  });
}

selectEl.addEventListener('change', async (e) => {
  console.log(e.target.value);
  let mbti = e.target.value;
  let res = await fetch(`../../csr/mbti?mbtiType=${mbti}`,{
    headers: {
      "accept" : "text/html",
    }
  });
  let html = await res.text();
  resultEl.innerHTML = html;
});

getOption();