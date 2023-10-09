# Write your MySQL query statement below
SELECT
    customer_id,
    customer_name
FROM
    Orders
    JOIN Customers USING (customer_id)
GROUP BY 1
HAVING sum(product_name = 'A') > 0 AND sum(product_name = 'B') > 0 AND sum(product_name = 'C') = 0;
