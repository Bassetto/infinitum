const datalistFull = document.getElementById("municipios_extenso").options;
const inputEl = document.getElementById("county");
const inputHiddenEl = document.getElementById("fullCounty");
const formEl = document.getElementById("form");
const infoEl = document.getElementById("info");
const dataEl = document.getElementById("mesAno");

function getValue() {
    inputHiddenEl.value = "";
    for (let i = 0; i < datalistFull.length; i++) {
        if (inputEl.value === datalistFull[i].label) {
            inputHiddenEl.value = datalistFull[i].value;
            inputEl.classList.remove("is-invalid");
            infoEl.classList.remove("invisible")
            return;
        }
    }
    inputEl.classList.add("is-invalid");
    infoEl.classList.add("invisible");
}

function validate(e) {
    for (let i = 0; i < datalistFull.length; i++) {
        if (inputEl.value === datalistFull[i].label) {
            inputHiddenEl.value = datalistFull[i].value;
            return;
        }
    }
    inputEl.classList.add("is-invalid");
    e.preventDefault();
    e.stopPropagation();
}

function getData() {
    const data = dataEl.value.split("-");
    document.getElementById("ano").value = data[0];
    document.getElementById("mes").value = data[1];
}

inputEl.addEventListener("input", getValue);
dataEl.addEventListener("input", getData)
formEl.addEventListener("submit", validate);