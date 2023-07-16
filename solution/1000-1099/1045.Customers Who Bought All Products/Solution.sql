# Write your MySQL query statement below
SELECT customer_id
FROM Customer
GROUP BY 1
HAVING count(DISTINCT product_key) = (SELECT count(1) FROM Product);
