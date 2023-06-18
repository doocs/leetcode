# Write your MySQL query statement below
SELECT
    o.customer_id,
    c.customer_name
FROM
    orders AS o
    LEFT JOIN customers AS c ON o.customer_id = c.customer_id
GROUP BY customer_id
HAVING
    sum(if(product_name = 'A', 1, 0)) > 0
    AND sum(if(product_name = 'B', 1, 0)) > 0
    AND sum(if(product_name = 'C', 1, 0)) = 0;
