package com.bookmanagement.book_manager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books", indexes = {
    @Index(name = "idx_book_title", columnList = "title", unique = false),
    @Index(name = "idx_book_author", columnList = "author", unique = false)
})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 255)
    private String author;

    @Column(nullable = false, length = 255)
    private String genre;

    @Column(name = "published_year", nullable = false)
    private Integer publishedYear;

    public Book() {}

    public Book(String title, String author, String genre, Integer publishedYear) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publishedYear = publishedYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", author='" + getAuthor() + "'" +
            ", genre='" + getGenre() + "'" +
            ", publishedYear='" + getPublishedYear() + "'" +
            "}";
    }

}
