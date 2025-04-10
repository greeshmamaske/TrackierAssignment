package com.bookmanagement.book_manager.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bookmanagement.book_manager.model.Book;
import com.bookmanagement.book_manager.model.BookTransaction;
import com.bookmanagement.book_manager.model.User;
import com.bookmanagement.book_manager.repository.BookRepository;
import com.bookmanagement.book_manager.repository.BookTransactionRepository;
import com.bookmanagement.book_manager.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class BookTransactionService {

    private final BookTransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public BookTransactionService(BookTransactionRepository transactionRepository,
            UserRepository userRepository,
            BookRepository bookRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public Map<Long, String> borrowBooks(List<Long> bookIds) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        Map<Long, String> response = new HashMap<>();

        for (Long bookId : bookIds) {
            Book book = bookRepository.findById(bookId).orElse(null);

            if (book == null) {
                response.put(bookId, "Book not found!");
                continue;
            }

            List<BookTransaction> activeTransactions = transactionRepository.isBookBorrowed(book.getId());
            if (!activeTransactions.isEmpty()) {
                response.put(bookId, "Book already borrowed!");
                continue;
            }

            BookTransaction transaction = new BookTransaction();
            transaction.setUser(user);
            transaction.setBook(book);
            transactionRepository.save(transaction);
            response.put(bookId, "Successfully borrowed.");
        }

        return response;
    }

    @Transactional
    public Map<Long, String> returnBooks(List<Long> bookIds) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        List<BookTransaction> transactions = transactionRepository.findUnreturnedBooksByUserId(user.getId());
        Map<Long, String> response = new HashMap<>();

        for (BookTransaction transaction : transactions) {
            if (bookIds.contains(transaction.getBook().getId())) {
                transaction.setReturnedAt(new Date());
                transactionRepository.save(transaction);
                response.put(transaction.getBook().getId(), "Successfully returned.");
            }
        }

        for (Long bookId : bookIds) {
            if (!response.containsKey(bookId)) {
                response.put(bookId, "Book is not borrowed by current user.");
            }
        }

        return response;
    }

    public List<Book> getBorrowedBooks(Long userId) {
        List<BookTransaction> transactions = transactionRepository.findUnreturnedBooksByUserId(userId);
        List<Book> response = transactions.stream()
                .map(BookTransaction::getBook)
                .collect(Collectors.toList());

        return response;
    }

    public List<Book> getMostBorrowedBooks(int limit) {
        return transactionRepository.findMostFrequentlyBorrowedBooks(limit);
    }
}
