# Write your MySQL query statement below
select
    round(
        sum(if(order_date = customer_pref_delivery_date, 1, 0)) / count(1) * 100,
        2
    ) as immediate_percentage
from
    Delivery;