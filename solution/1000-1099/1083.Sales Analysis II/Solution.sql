# Write your MySQL query statement below
SELECT buyer_id
FROM
    Sales
    JOIN Product USING (product_id)
GROUP BY 1
HAVING sum(product_name = 'S8') > 0 AND sum(product_name = 'iPhone') = 0;
