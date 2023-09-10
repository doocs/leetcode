# Write your MySQL query statement below
SELECT
    round(sum(order_date = customer_pref_delivery_date) * 100 / count(1), 2) AS immediate_percentage
FROM Delivery;
