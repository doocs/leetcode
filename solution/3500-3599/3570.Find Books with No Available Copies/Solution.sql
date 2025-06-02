# Write your MySQL query statement below
WITH
    T AS (
        SELECT book_id, COUNT(1) current_borrowers
        FROM borrowing_records
        WHERE return_date IS NULL
        GROUP BY 1
    )
SELECT book_id, title, author, genre, publication_year, current_borrowers
FROM
    library_books
    JOIN T USING (book_id)
WHERE current_borrowers = total_copies
ORDER BY 6 DESC, 2;
