# Write your MySQL query statement below
select
    product_name,
    o.product_id,
    o.order_id,
    o.order_date
from
    Orders o
    join Products p on o.product_id = p.product_id
where
    (o.product_id, order_date) in (
        select
            product_id,
            max(order_date) order_date
        from
            Orders
        group by
            product_id
    )
order by
    product_name,
    o.product_id,
    o.order_id