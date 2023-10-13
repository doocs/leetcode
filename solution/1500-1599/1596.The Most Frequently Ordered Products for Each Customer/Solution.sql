# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            customer_id,
            product_id,
            rank() OVER (
                PARTITION BY customer_id
                ORDER BY count(1) DESC
            ) AS rk
        FROM Orders
        GROUP BY 1, 2
    )
SELECT customer_id, product_id, product_name
FROM
    T
    JOIN Products USING (product_id)
WHERE rk = 1;
