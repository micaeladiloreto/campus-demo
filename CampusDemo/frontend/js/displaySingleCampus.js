import { displayHomeView } from "./displayHomeView.js";
import { allCampusesJson } from "./sampleAllCampuses.js";

const displaySingleCampus = function(campus){
    const mainContent = document.querySelector(".main-content");
    clearChildren(mainContent);
    
    // <section class="campus-library">
    const section = document.createElement("section");
    section.classList.add("campus-library");

    // <header class="campus-library-header">
    const header = document.createElement("header");
    header.classList.add("campus-library-header");   

    // <h2 class="campus-library-header__location">Campus Name</h2>
    const libraryLocation = document.createElement("h2");
    libraryLocation.classList.add("campus-library-header__location");
    libraryLocation.innerText = campus.location;
 
    // <h3 class="campus-library-header__tech-stack">Tech Stack</h3>
    const techStack = document.createElement("h3");
    techStack.classList.add("campus-library-header__tech-stack"); 
    techStack.innerText = campus.techStack;

    // <section class="campus-books"></section>
    const bookSection = document.createElement("section");
    bookSection.classList.add("campus-books");

    // <a class="back-navigation">back to campus listings</a>
    const backButton = document.createElement("a");
    backButton.classList.add("back-navigation");
    backButton.innerText = "back to campus listings";
    
    backButton.addEventListener("click", ()=>{
        //window.history.back();
        clearChildren(mainContent);
        mainContent.appendChild(displayHomeView(allCampusesJson));
        console.log("Clicked back button");
    });
    
    

    campus.books.forEach((book)=>{
        // <h3 class="book-title">Head First Java</h3>
        const bookElement = document.createElement("h3");
        bookElement.classList.add("book-title");
        bookElement.innerText = book.title;

        const deleteButton = document.createElement("button");
        deleteButton.innerText = "Delete";
        bookElement.appendChild(deleteButton);
        bookSection.appendChild(bookElement);

        deleteButton.addEventListener("click",()=>{
            fetch("http://localhost:8080/api/campuses/" + campus.id + "/books/"+ book.id,{
            method: 'DELETE',
            })
            .then(response => response.json())
            .then(campus => displaySingleCampus(campus))
            .catch(error => console.log(error))
            });

    })

    const newBookform = document.createElement("form");
    newBookform.innerHTML = `
      <input class="title-input" placeholder="Title" type="text">
      <input class="summary-input" placeholder="Summary" type="text">
      <label>Available</label>
      <input type="checkbox" class="availability">
      <input class="author-input" placeholder="Author" type="text">
      <button class="submit-new-book">Submit New Book</button>
      `

      newBookform.querySelector(".submit-new-book").addEventListener("click", (clickEvent)=>{
        clickEvent.preventDefault();
        clearChildren(mainContent);
        let availCheckbox = newBookform.querySelector(".availability");
        

        
        const bookJson = {
            "title": newBookform.querySelector(".title-input").value,
            "summary": newBookform.querySelector(".summary-input").value,
            "available" : availCheckbox.checked,
            "summary": newBookform.querySelector(".summary-input").value,
            "authors": [{"name" : newBookform.querySelector(".author-input").value}]
        }
        fetch("http://localhost:8080/api/campuses/" + campus.id + "/books",{
            method: 'PATCH',
            headers:{
              'Content-Type': 'application/json'
            },
            body: JSON.stringify(bookJson)
            })
            .then(response => response.json())
            .then(campus => displaySingleCampus(campus))
            .catch(error => console.log(error))
      })
      section.appendChild(newBookform);


    header.appendChild(libraryLocation);
    header.appendChild(techStack);
    section.appendChild(header);
    section.appendChild(bookSection);
    section.appendChild(backButton);
    mainContent.appendChild(section);

};

const clearChildren = function(element){
    while(element.firstChild){
        element.removeChild(element.lastChild);
    }


};

export{
    displaySingleCampus, clearChildren
}



/* <section class="campus-library">
        <header class="campus-library-header">
            <h2 class="campus-library-header__location">Columbus</h2>
            <h3 class="campus-library-header__tech-stack">Java</h3>
        </header>
        <section class="campus-books">
            <h3 class="book-title">Head First Java</h3>
            <h3 class="book-title">Head First Design Patterns</h3>
            <h3 class="book-title">Test Driven Development by Example</h3>
        </section>
        
        <a class="back-navigation">back to campus listings</a>
    </section> */