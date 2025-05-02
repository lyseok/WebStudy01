import { InputComponent, SelectComponent } from './inputComponents.js';

async function fetchMetaDatas(){
  let res = await fetch("../../calendar/uiMetaDatas",{
    headers: {
      "accept":"application/json"
    }
  });
  return res.json();
}

function createForm({ months, locales, zoneIds }) {
  let formEl = document.createElement("form");

  let {element:yearInput, setValue:setYear} = InputComponent({name:"year", type:"number"});
  let {element:monthInput, setValue:setMonth} = SelectComponent({name: "month", metaData: months});
  let {element:localeInput, setValue:setLocale} = SelectComponent({name: "locale", metaData: locales});
  let {element:zoneInput, setValue:setZone} = SelectComponent({name: "zone", metaData: zoneIds});


  formEl.append(yearInput, monthInput, localeInput, zoneInput);
  
  initForm({setYear, setMonth, setLocale, setZone});

  return formEl;
}

function createCalendarArea() {
	let calArea = document.createElement('div');
	calArea.id = "cal-area";
	return calArea;
}

function initForm({ setYear, setMonth, setLocale, setZone }){
  let today = new Date();
  setYear(today.getFullYear());
  setMonth(today.getMonth());
  setLocale(navigator.language);
}

document.addEventListener("DOMContentLoaded", async () => {
  let metaDatas = await fetchMetaDatas();
  
  let form = createForm(metaDatas);
  let calArea = createCalendarArea();
  
  form.addEventListener('submit', async (e) => {
	e.preventDefault();
	let url = '../../calendar';
	let formData = new FormData(form);
	let params = new URLSearchParams(formData);
	console.log(params);
	let res = await fetch(`${url}?${params}`, {
		headers: {
			"accept" : "text/html",
		}
	});
	let calHtml = await res.text();
	calArea.innerHTML = calHtml;
  });
  
  form.addEventListener("change", ()=>{
	form.requestSubmit();
  });
  
  document.body.append(form, calArea);
  
  // 최초의 달력 랜더링, form의 submit이벤트 발생이 필요
  form.requestSubmit();

});