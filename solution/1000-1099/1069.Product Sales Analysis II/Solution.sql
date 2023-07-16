# Write your MySQL query statement below
SELECT product_id, sum(quantity) AS total_quantity
FROM Sales
GROUP BY 1;
