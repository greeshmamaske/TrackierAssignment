package com.bookmanagement.book_manager.repository;

import com.bookmanagement.book_manager.model.Book;
import com.bookmanagement.book_manager.model.BookTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookTransactionRepository extends JpaRepository<BookTransaction, Long> {
    @Query(value = "SELECT * FROM book_transactions WHERE user_id = :userId AND returned_at IS NULL", nativeQuery = true)
    List<BookTransaction> findUnreturnedBooksByUserId(@Param("userId") Long userId);

    @Query(value = """
            SELECT b.* FROM book b
            JOIN book_transactions bt ON b.id = bt.book_id
            GROUP BY b.id
            ORDER BY COUNT(bt.id) DESC
            LIMIT :limit
                    """, nativeQuery = true)
    List<Book> findMostFrequentlyBorrowedBooks(@Param("limit") int limit);

    @Query(value = "SELECT * FROM book_transactions WHERE book_id = :bookId AND returned_at IS NULL", nativeQuery = true)
    List<BookTransaction> isBookBorrowed(@Param("bookId") Long bookId);

    @Modifying
    @Query("DELETE FROM BookTransaction bt WHERE bt.book.id = :bookId")
    void deleteByBookId(@Param("bookId") Long bookId);

    boolean existsByBookIdAndReturnedAtIsNull(Long bookId);
}
