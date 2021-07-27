package org.wcci.campuslibraries.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Entity
public class Book {

    @Id
    @GeneratedValue
    private long id;
   
    @ManyToOne
    @JsonIgnore
    private Campus campus;
    private String title;
    @Lob
    private String summary;
    private boolean available;

    @ManyToMany
    private Collection<Author> authors;
    
    public Book(Campus campus, String title, String summary, boolean available, Author... authors){
        this.campus = campus;
        this.title = title;
        this.summary = summary;
        this.available = available;
        this.authors = Set.of(authors);
    }

    protected Book(){}

    public long getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public boolean isAvailable() {
        return available;
    }
    
    public Campus getCampus() {
        return campus;
    }

    public Iterable<Author> getAuthors(){
        return authors;
    }
    public void changeCampus(Campus campus){
        this.campus = campus;
    }
    
    
}
