# Write your MySQL query statement below
SELECT
    ROUND(SUM(order_date = customer_pref_delivery_date) / COUNT(1) * 100, 2) AS immediate_percentage
FROM Delivery
WHERE
    (customer_id, order_date) IN (
        SELECT customer_id, MIN(order_date)
        FROM Delivery
        GROUP BY 1
    );
