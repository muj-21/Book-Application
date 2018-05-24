package com.example.mujta.bookapp;

/**
 * Created by mujta on 19/03/2018.
 */
//created a class for Book containing variables such as title, author etc.
public class Book {
    private String title;
    private String author;
    private String cover;
    private String isbn;
    private String year;

    public Book(String title, String author, String cover, String isbn, String year) {
        this.title = title;
        this.author = author;
        this.cover = cover;
        this.isbn = isbn;
        this.year = year;
    }

    //Allows to Set and Get the data for the Book class
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
