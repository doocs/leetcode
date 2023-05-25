# Write your MySQL query statement below
SELECT
	b.book_id,
	b.NAME 
FROM
	books b
	LEFT JOIN orders o ON b.book_id = o.book_id 
WHERE
	b.available_from < '2019-05-23' 
GROUP BY
	b.book_id 
HAVING
	ifnull( sum( IF ( o.dispatch_date < '2018-06-23', 0, quantity )), 0 )< 10 
ORDER BY
	b.book_id