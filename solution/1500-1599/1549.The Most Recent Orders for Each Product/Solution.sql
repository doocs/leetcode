# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            RANK() OVER (
                PARTITION BY product_id
                ORDER BY order_date DESC
            ) AS rk
        FROM
            Orders
            JOIN Products USING (product_id)
    )
SELECT product_name, product_id, order_id, order_date
FROM T
WHERE rk = 1
ORDER BY 1, 2, 3;
