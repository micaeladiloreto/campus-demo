import{
    createHeader
} from "./Header.js"
import{
    createFooter
}from "./Footer.js"
import {
    displayHomeView
} from "./displayHomeView.js"
// import {
//     allCampusesJson
// } from "./sampleAllCampuses.js"

// import {
//     displaySingleCampus
// } from "./displaySingleCampus.js"


const container = document.querySelector(".container");
container.append(createHeader());

const mainElement = document.createElement("main");
mainElement.classList.add("main-content");

container.appendChild(mainElement);
//container.append(displayHomeView(allCampusesJson));

fetch("http://localhost:8080/api/campuses")
    .then(response => response.json())
    .then(campuses => displayHomeView(campuses))
    .then(campusesElement => mainElement.appendChild(campusesElement))
    .catch(error => console.log(error));
    

container.append(createFooter());
