# Write your MySQL query statement below
select
    customer_id
from
    (
        select
            customer_id,
            year(order_date),
            sum(price) as total,
            year(order_date) - rank() over(
                partition by customer_id
                order by
                    sum(price)
            ) as rk
        from
            Orders
        group by
            customer_id,
            year(order_date)
    ) t
group by
    customer_id
having
    count(distinct rk) = 1;