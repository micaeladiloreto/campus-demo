package org.wcci.campuslibraries.controllers;

import org.springframework.web.bind.annotation.*;
import org.wcci.campuslibraries.resources.Author;
import org.wcci.campuslibraries.resources.Book;
import org.wcci.campuslibraries.resources.Campus;
import org.wcci.campuslibraries.storage.AuthorRepository;
import org.wcci.campuslibraries.storage.BookRepository;
import org.wcci.campuslibraries.storage.CampusStorage;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


//import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class CampusController {

    private CampusStorage campusStorage;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public CampusController(CampusStorage campusStorage, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.campusStorage = campusStorage;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    //GET localhost:8080/api/campuses
    @GetMapping("/api/campuses")
    public Iterable<Campus> retrieveAllCampuses() {
        return campusStorage.retrieveAllCampuses();
    }

    //GET localhost:8080/api/campuses/1
    @GetMapping("/api/campuses/{id}")
    public Campus retrieveCampusById(@PathVariable Long id){
        return campusStorage.retrieveCampusById(id);
    }
    //DELETE http://localhost:8080/api/campuses/1

    @DeleteMapping("/api/campuses/{id}")
    public Iterable<Campus> deleteCampusById(@PathVariable Long id){
        campusStorage.deleteCampusById(id);
        return campusStorage.retrieveAllCampuses();
    }

    // POST http://localhost:8080/api/campuses
    // Content-Type: application/json

    // {"location":"Dayton","techStack": "C#"}
    @PostMapping("/api/campuses")
    public Iterable<Campus> addNewCampus(@RequestBody Campus campusToAdd){
        campusStorage.saveCampus(campusToAdd);
        return campusStorage.retrieveAllCampuses();
    }
    // PUT http://localhost:8080/api/campuses
    // Content-Type: application/json

    // {"id":1,"location": "remote","techStack": "Java"}
    @PutMapping("/api/campuses")
    public Iterable<Campus> modifyCampus(@RequestBody Campus campusToModify) {
        if(campusToModify.getId()!=null){
            campusStorage.saveCampus(campusToModify);
        }
        return campusStorage.retrieveAllCampuses();
    }

    //     ### Update a campus's location property.
    // PATCH http://localhost:8080/api/campuses/1/location
    // Clintonville

    @PatchMapping("/api/campuses/{id}/location")
    public Campus changeCampusLocation(@PathVariable Long id, @RequestBody String location){
        Campus campusToChange = campusStorage.retrieveCampusById(id);
        campusToChange.changeLocation(location);
        campusStorage.saveCampus(campusToChange);
        return campusToChange;
    }

    //     ### Add a new book resource to the campus's books
    // PATCH http://localhost:8080/api/campuses/1/books
    // Content-Type: application/json

    // {"title": "New Book","summary": "This is a sample book.","available": true,"authors": [{"name": "Sample Author"}, {"name": "Another Sample Author"}]}
    @PatchMapping("/api/campuses/{id}/books")
    public Campus addBookToCampus(@PathVariable Long id, @RequestBody Book bookToAdd){
        Campus campusToChange = campusStorage.retrieveCampusById(id);
        for(Author author: bookToAdd.getAuthors()){
            authorRepository.save(author);
        }
        bookToAdd.changeCampus(campusToChange); 
        bookRepository.save(bookToAdd);
        campusToChange.addBook(bookToAdd);
        campusStorage.saveCampus(campusToChange);
        return campusToChange;
    }

    @DeleteMapping("/api/campuses/{id}/books/{bookId}")
    public Campus deleteBookFromCampus(@PathVariable Long id, @PathVariable Long bookId){
        Campus campusToChange = campusStorage.retrieveCampusById(id);
        Book book = bookRepository.findById(bookId).get();
        campusToChange.removeBook(book);
        bookRepository.deleteById(bookId);
        campusStorage.saveCampus(campusToChange);
        return campusToChange;
    }

}
