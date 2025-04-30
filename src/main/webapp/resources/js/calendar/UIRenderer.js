import { InputComponent, SelectComponent } from './inputComponents.js';

async function fetchMetaDatas(){
  let res = await fetch("../../calendar/uiMetaDatas",{
    headers: {
      "accept":"application/json"
    }
  });
  return res.json();
}

function createForm({ months, locales, timezone }) {
  let formEl = document.createElement("form");

  let {element:yearInput, setValue:setYear} = InputComponent({name:"year", type:"number"});
  let {element:monthInput, setValue:setMonth} = SelectComponent({name: "month", metaData: months});
  let {element:localeInput, setValue:setLocale} = SelectComponent({name: "locale", metaData: locales});
  let {element:zoneInput, setValue:setZone} = SelectComponent({name: "zone", metaData: timezone});

  formEl.append(yearInput, monthInput, localeInput, zoneInput);
  
  initForm({setYear, setMonth, setLocale, setZone});

  return formEl;
}

function initForm({ setYear, setMonth, setLocale, setZone }){
  let today = new Date();
  value => selectEl.value = value
  setYear(today.getFullYear());
  setMonth(today.getMonth());

}

document.addEventListener("DOMContentLoaded", async () => {
  let metaDatas = await fetchMetaDatas();
  
  let form = createForm(metaDatas);
  document.body.append(form);

});