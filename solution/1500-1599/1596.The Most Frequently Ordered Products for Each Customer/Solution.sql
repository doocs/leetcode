# Write your MySQL query statement below
select
    customer_id,
    p.product_id,
    p.product_name
from
    (
        select
            customer_id,
            product_id,
            rank() over(
                partition by customer_id
                order by
                    count(1) desc
            ) rk
        from
            Orders
        group by
            customer_id,
            product_id
    ) o
    join Products p on o.product_id = p.product_id
where
    rk = 1;