# Write your MySQL query statement below
SELECT
    round(sum(order_date = customer_pref_delivery_date) / count(1) * 100, 2) AS immediate_percentage
FROM Delivery;
