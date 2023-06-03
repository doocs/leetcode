# Write your MySQL query statement below
select
    round(
        sum(
            if(
                t1.order_date = t1.customer_pref_delivery_date,
                1,
                0
            )
        ) / count(1) * 100,
        2
    ) as immediate_percentage
from
    Delivery t1
    right join (
        select
            customer_id,
            min(order_date) as order_date
        from
            Delivery
        group by
            customer_id
    ) t2 on t1.customer_id = t2.customer_id
    and t1.order_date = t2.order_date