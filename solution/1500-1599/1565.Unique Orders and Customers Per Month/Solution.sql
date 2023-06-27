# Write your MySQL query statement below
SELECT
    date_format(order_date, '%Y-%m') AS month,
    count(order_id) AS order_count,
    count(DISTINCT customer_id) AS customer_count
FROM Orders
WHERE invoice > 20
GROUP BY month;
