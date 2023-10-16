# Write your MySQL query statement below
SELECT buyer_id
FROM
    Sales
    JOIN Product USING (product_id)
GROUP BY 1
HAVING SUM(product_name = 'S8') > 0 AND SUM(product_name = 'iPhone') = 0;
