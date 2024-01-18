# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            RANK() OVER (
                PARTITION BY customer_id
                ORDER BY order_date
            ) AS rk
        FROM Delivery
    )
SELECT
    ROUND(AVG(order_date = customer_pref_delivery_date) * 100, 2) AS immediate_percentage
FROM T
WHERE rk = 1;
