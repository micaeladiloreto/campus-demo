//import { displayHomeView } from "./displayHomeView.js";

const displaySingleCampus = function(campus){
    const mainContent = document.querySelector(".main-content");
    clearChildren(mainContent);
    const campusLibraryElement = document.createElement("section");
    const libraryHeader = document.createElement("header");
    const campusLocationElement = document.createElement("h2");
    const campusTechStack = document.createElement("h3");
    const booksElement = document.createElement("section");

    libraryHeader.classList.add("campus-library-header");
    campusLibraryElement.classList.add("campus-library");
    campusLocationElement.classList.add("campus-library-header__location");
    campusTechStack.classList.add("campus-library-header__tech-stack");
    booksElement.classList.add("campus-books");

    campusLocationElement.innerText = campus.location;
    campusTechStack.innerText =campus.techStack;

    campus.books.forEach((book)=>{
        let bookTitle = document.createElement("h3");
        bookTitle.classList.add("book-title");
        bookTitle.innerText = book.title;
        booksElement.appendChild(bookTitle);

    })
    campusLibraryElement.appendChild(campusLocationElement);
    campusLibraryElement.appendChild(campusTechStack);
    campusLibraryElement.appendChild(booksElement);
    mainContent.appendChild(campusLibraryElement);
    //const backToAllCampuses = document.createElement("a");
    return mainContent;
}

const clearChildren = function (element) {
    while (element.firstChild) {
      element.removeChild(element.lastChild);
    }
  }
export{
    displaySingleCampus
}




{/* <main class="main-content">
    <section class="campus-library">
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
    </section>
</main> */}