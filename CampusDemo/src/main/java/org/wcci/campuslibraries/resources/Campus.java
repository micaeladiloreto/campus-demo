package org.wcci.campuslibraries.resources;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Campus {
    private String location;
    private String techStack;

    @Id
    @GeneratedValue
    private Long id;


    @OneToMany(mappedBy = "campus", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Book> books;
    
    protected Campus() {
    }

    public Campus(String location, String techStack) {
        this.location = location;
        this.techStack = techStack;
        //this.books = new ArrayList<Book>();
    }

    public Long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void changeLocation(String newLocation) {
        location = newLocation;
    }

    public String getTechStack() {
        return techStack;
    }

    public Iterable<Book> getBooks(){
        return books;
    }
    public void addBook(Book book){
        books.add(book);
    }
    public void removeBook(Book book){
        books.remove(book);
    }

}
