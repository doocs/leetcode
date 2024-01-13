# Write your MySQL query statement below
SELECT
    a.visited_on,
    SUM(b.amount) AS amount,
    ROUND(SUM(b.amount) / 7, 2) AS average_amount
FROM
    (SELECT DISTINCT visited_on FROM customer) AS a
    JOIN customer AS b ON DATEDIFF(a.visited_on, b.visited_on) BETWEEN 0 AND 6
WHERE a.visited_on >= (SELECT MIN(visited_on) FROM customer) + 6
GROUP BY 1
ORDER BY 1;
