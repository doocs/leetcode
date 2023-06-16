# Write your MySQL query statement below
SELECT
    customer_id
FROM
    (
        SELECT
            customer_id,
            year(order_date),
            sum(price) AS total,
            year(order_date) - rank() OVER (
                PARTITION BY customer_id
                ORDER BY sum(price)
            ) AS rk
        FROM Orders
        GROUP BY customer_id, year(order_date)
    ) AS t
GROUP BY customer_id
HAVING count(DISTINCT rk) = 1;
