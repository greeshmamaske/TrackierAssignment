package com.bookmanagement.book_manager.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookmanagement.book_manager.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    
    Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);
    
    @Query(value = "SELECT * FROM books WHERE LOWER(title) LIKE LOWER(CONCAT('%', :title, '%'))", nativeQuery = true)
    List<Book> searchByTitle(@Param("title") String title);

    @Query(value = "SELECT * FROM books WHERE LOWER(author) LIKE LOWER(CONCAT('%', :author, '%'))", nativeQuery = true)
    List<Book> searchByAuthor(@Param("author") String author);
}
