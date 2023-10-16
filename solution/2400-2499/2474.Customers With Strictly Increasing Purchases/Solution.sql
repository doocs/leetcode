# Write your MySQL query statement below
SELECT
    customer_id
FROM
    (
        SELECT
            customer_id,
            YEAR(order_date),
            SUM(price) AS total,
            YEAR(order_date) - RANK() OVER (
                PARTITION BY customer_id
                ORDER BY SUM(price)
            ) AS rk
        FROM Orders
        GROUP BY customer_id, YEAR(order_date)
    ) AS t
GROUP BY customer_id
HAVING COUNT(DISTINCT rk) = 1;
