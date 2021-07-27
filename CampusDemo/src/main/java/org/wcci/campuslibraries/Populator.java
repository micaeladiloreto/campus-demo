package org.wcci.campuslibraries;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wcci.campuslibraries.resources.Author;
import org.wcci.campuslibraries.resources.Book;
import org.wcci.campuslibraries.resources.Campus;
import org.wcci.campuslibraries.storage.AuthorRepository;
import org.wcci.campuslibraries.storage.BookRepository;


import org.wcci.campuslibraries.storage.CampusStorage;

@Component
public class Populator implements CommandLineRunner {

    private CampusStorage campusStorage;

    private BookRepository bookRepo;

    private AuthorRepository authorRepo;

    public Populator(CampusStorage campusStorage, BookRepository bookRepo, AuthorRepository authorRepo) {
        this.campusStorage = campusStorage;
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Campus columbus = new Campus("Columbus", "Java");
        campusStorage.saveCampus(columbus);
        Campus cleveland = new Campus("Cleveland", "C#");
        campusStorage.saveCampus(cleveland);
        Campus moon = new Campus("THE MOON", "Ruby");
        campusStorage.saveCampus(moon);

        Author donald = new Author("Donald Duck");
        authorRepo.save(donald);

        Author mickey = new Author("Mickey Mouse");
        authorRepo.save(mickey);

        //public Book(Campus campus, String title, boolean available, Author... authors){
        Book book1 = new Book(columbus, "Reading Rainbow", "LeVar Burton is a national treasure.", true, donald, mickey);
        bookRepo.save(book1);
        
        Book book2 = new Book(columbus, "Don't Tickle The Dinosaur", "Seriously, dont do it.", true, donald, mickey);
        bookRepo.save(book2);

}
}
