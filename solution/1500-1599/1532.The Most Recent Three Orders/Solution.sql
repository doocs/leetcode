# Write your MySQL query statement below
select
    name as customer_name,
    o.customer_id,
    order_id,
    order_date
from
    Customers c
    join (
        select
            customer_id,
            order_date,
            order_id,
            rank() over(
                partition by customer_id
                order by
                    order_date desc
            ) rk
        from
            orders
    ) o on c.customer_id = o.customer_id
where
    rk <= 3
order by
    name,
    o.customer_id,
    order_date desc;