# Write your MySQL query statement below
SELECT
    round(
        sum(if(t1.order_date = t1.customer_pref_delivery_date, 1, 0)) / count(1) * 100,
        2
    ) AS immediate_percentage
FROM
    Delivery AS t1
    RIGHT JOIN (
        SELECT
            customer_id,
            min(order_date) AS order_date
        FROM Delivery
        GROUP BY customer_id
    ) AS t2
        ON t1.customer_id = t2.customer_id AND t1.order_date = t2.order_date;
