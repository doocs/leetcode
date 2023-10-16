# Write your MySQL query statement below
SELECT book_id, name
FROM
    Books
    LEFT JOIN Orders USING (book_id)
WHERE available_from < '2019-05-23'
GROUP BY 1
HAVING SUM(IF(dispatch_date >= '2018-06-23', quantity, 0)) < 10;
