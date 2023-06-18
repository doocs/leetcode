# Write your MySQL query statement below
SELECT
    customer_id,
    p.product_id,
    p.product_name
FROM
    (
        SELECT
            customer_id,
            product_id,
            rank() OVER (
                PARTITION BY customer_id
                ORDER BY count(1) DESC
            ) AS rk
        FROM Orders
        GROUP BY customer_id, product_id
    ) AS o
    JOIN Products AS p ON o.product_id = p.product_id
WHERE rk = 1;
