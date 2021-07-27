package org.wcci.campuslibraries.resources;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Author {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    @ManyToMany(mappedBy = "authors")
    //@JsonIgnore
    private Collection<Book> books;
    
    public Author(String name){
        this.name = name;
    }

    protected Author(){}

    public long getId() {
        return id;
    }

    public String getName(){ return name; }





}
