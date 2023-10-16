# Write your MySQL query statement below
SELECT product_id, product_name
FROM
    Sales
    JOIN Product USING (product_id)
GROUP BY 1
HAVING COUNT(1) = SUM(sale_date BETWEEN '2019-01-01' AND '2019-03-31');
