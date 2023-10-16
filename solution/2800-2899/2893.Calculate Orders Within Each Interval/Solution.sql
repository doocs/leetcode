# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            minute,
            SUM(order_count) OVER (
                ORDER BY minute
                ROWS 5 PRECEDING
            ) AS total_orders
        FROM Orders
    )
SELECT minute / 6 AS interval_no, total_orders
FROM T
WHERE minute % 6 = 0;
