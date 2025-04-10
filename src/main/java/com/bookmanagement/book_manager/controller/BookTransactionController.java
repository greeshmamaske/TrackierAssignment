package com.bookmanagement.book_manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmanagement.book_manager.model.Book;
import com.bookmanagement.book_manager.service.BookService;
import com.bookmanagement.book_manager.service.BookTransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class BookTransactionController {

    private final BookTransactionService transactionService;
    private final BookService bookService;

    @GetMapping("/search")
    public ResponseEntity<Page<Book>> getAllBooks(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(bookService.getAllBooks(page, size));
    }

    @GetMapping("/search/title/{title}")
    public ResponseEntity<Page<Book>> searchByTitle(@PathVariable String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(bookService.searchByTitle(title, page, size));
    }

    @GetMapping("/search/author/{author}")
    public ResponseEntity<Page<Book>> searchByAuthor(@PathVariable String author,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(bookService.searchByAuthor(author, page, size));
    }

    @PostMapping("/borrow")
    public ResponseEntity<Map<Long, String>> borrowBooks(@RequestBody List<Long> bookIds) {
        Map<Long, String> borrowedBooks = transactionService.borrowBooks(bookIds);
        return ResponseEntity.ok(borrowedBooks);
    }

    @PostMapping("/return")
    public ResponseEntity<Map<Long, String>> returnBooks(@RequestBody List<Long> bookIds) {
        Map<Long, String> returnedBooks = transactionService.returnBooks(bookIds);
        return ResponseEntity.ok(returnedBooks);
    }
}
