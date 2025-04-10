package com.bookmanagement.book_manager.model;

import jakarta.persistence.*;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "book_transactions", indexes = {
    @Index(name = "idx_borrow_transaction_book", columnList = "book_id", unique = false),
    @Index(name = "idx_borrow_transaction_user", columnList = "user_id", unique = false)
})
public class BookTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date borrowedAt;

    private Date returnedAt;

    public BookTransaction() {
    }

    public BookTransaction(User user, Book book, Date borrowedAt) {
        this.user = user;
        this.book = book;
        this.borrowedAt = borrowedAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getBorrowedAt() {
        return this.borrowedAt;
    }

    public void setBorrowedAt(Date borrowedAt) {
        this.borrowedAt = borrowedAt;
    }

    public Date getReturnedAt() {
        return this.returnedAt;
    }

    public void setReturnedAt(Date returnedAt) {
        this.returnedAt = returnedAt;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser() + "'" +
            ", book='" + getBook() + "'" +
            ", borrowedAt='" + getBorrowedAt() + "'" +
            ", returnedAt='" + getReturnedAt() + "'" +
            "}";
    }
    
}
