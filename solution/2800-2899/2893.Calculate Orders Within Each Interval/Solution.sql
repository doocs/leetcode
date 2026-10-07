# Write your MySQL query statement below
SELECT
    (minute + 5) DIV 6 AS interval_no,
    SUM(order_count) AS total_orders
FROM Orders
GROUP BY 1
ORDER BY 1;
