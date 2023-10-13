# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            row_number() OVER (
                PARTITION BY customer_id
                ORDER BY order_date DESC
            ) AS rk
        FROM
            Orders
            JOIN Customers USING (customer_id)
    )
SELECT name AS customer_name, customer_id, order_id, order_date
FROM T
WHERE rk <= 3
ORDER BY 1, 2, 4 DESC;
