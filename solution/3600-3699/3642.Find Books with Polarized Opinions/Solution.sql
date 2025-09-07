# Write your MySQL query statement below
SELECT
    book_id,
    title,
    author,
    genre,
    pages,
    (MAX(session_rating) - MIN(session_rating)) AS rating_spread,
    ROUND((SUM(session_rating <= 2) + SUM(session_rating >= 4)) / COUNT(1), 2) polarization_score
FROM
    books
    JOIN reading_sessions USING (book_id)
GROUP BY book_id
HAVING
    COUNT(1) >= 5
    AND MAX(session_rating) >= 4
    AND MIN(session_rating) <= 2
    AND polarization_score >= 0.6
ORDER BY polarization_score DESC, title DESC;
