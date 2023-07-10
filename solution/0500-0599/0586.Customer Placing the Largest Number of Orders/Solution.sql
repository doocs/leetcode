# Write your MySQL query statement below
SELECT customer_number
FROM Orders
GROUP BY 1
ORDER BY count(1) DESC
LIMIT 1;
