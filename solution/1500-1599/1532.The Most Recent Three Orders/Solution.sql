# Write your MySQL query statement below
SELECT
    name AS customer_name,
    o.customer_id,
    order_id,
    order_date
FROM
    Customers AS c
    JOIN (
        SELECT
            customer_id,
            order_date,
            order_id,
            rank() OVER (
                PARTITION BY customer_id
                ORDER BY order_date DESC
            ) AS rk
        FROM orders
    ) AS o
        ON c.customer_id = o.customer_id
WHERE rk <= 3
ORDER BY name, o.customer_id, order_date DESC;
