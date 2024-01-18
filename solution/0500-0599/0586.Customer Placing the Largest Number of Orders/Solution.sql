# Write your MySQL query statement below
SELECT
    customer_number
FROM orders
GROUP BY customer_number
ORDER BY COUNT(1) DESC
LIMIT 1;
