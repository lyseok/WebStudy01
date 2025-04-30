/**
 * 
 */
function InputComponent({name, type}) {
  const inputEl = document.createElement('input');
  inputEl.name = name;
  inputEl.type = type;

  return {
    element: inputEl,
    setValue:value => inputEl.value=value
  }
}

function SelectComponent({name, metaData}){
  // 1. select 엘리먼트 생성
  let selectEl = document.createElement('select');
  if(metaData){
    let options;
    if(Array.isArray(metaData)){
      options = metaData
        .map((el,idx) => /* html */`<option value=${idx+1}>${el}</option>`)
        .join('\n');
    } else {
      options = Object
                  .keys(metaData)
                  .map(k=>/* html */`<option value="${k}">${metaData[k]}</option>`)
                  .join('n');
    }
  
    selectEl.innerHTML = options;
  }
  // 2. name속성 결정
  selectEl.anme = name;

  // 3.{엘리먼트 반환, 엘리먼트의value 변경함수}
  return {
    element: selectEl,
    setValue:  value => el.value = value
    
  }
}

export { InputComponent, SelectComponent };