# Write your MySQL query statement below
SELECT
    customer_id
FROM
    Customer
GROUP BY
    customer_id
HAVING
    COUNT(DISTINCT(product_key)) = (
        SELECT
            COUNT(1)
        FROM
            Product
    );