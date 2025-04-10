-- ○ Fetching all books borrowed by a specific user.
SELECT * FROM book_transactions WHERE user_id = 2 AND returned_at IS NULL;

-- ○ Searching for books based on title or author.
SELECT * FROM books WHERE LOWER(title) LIKE LOWER(CONCAT('%', "The Great Gatsby" '%'));
SELECT * FROM books WHERE LOWER(author) LIKE LOWER(CONCAT('%', "F. Scott Fitzgerald", '%'));

-- ○ Identifying the most frequently borrowed books.
SELECT b.* FROM book b JOIN book_transactions bt ON b.id = bt.book_id GROUP BY b.id ORDER BY COUNT(bt.id) DESC LIMIT 5

-- ○ Optimizing queries using proper indexing.
CREATE INDEX idx_book_title ON books(title);
CREATE INDEX idx_book_author ON books(author);
CREATE INDEX idx_borrow_transaction_user ON book_transactions(user_id);
CREATE INDEX idx_borrow_transaction_book ON book_transactions(book_id);
