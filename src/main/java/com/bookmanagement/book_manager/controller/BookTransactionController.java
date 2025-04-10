package com.bookmanagement.book_manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmanagement.book_manager.service.BookTransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class BookTransactionController {

    private final BookTransactionService transactionService;

    @PostMapping("/borrow/{userId}")
    public ResponseEntity<Map<Long, String>> borrowBooks(@PathVariable Long userId, @RequestBody List<Long> bookIds) {
        Map<Long, String> borrowedBooks = transactionService.borrowBooks(userId, bookIds);
        return ResponseEntity.ok(borrowedBooks);
    }

    @PostMapping("/return/{userId}")
    public ResponseEntity<Map<Long, String>> returnBooks(@PathVariable Long userId, @RequestBody List<Long> bookIds) {
        Map<Long, String> returnedBooks = transactionService.returnBooks(userId, bookIds);
        return ResponseEntity.ok(returnedBooks);
    }
}
