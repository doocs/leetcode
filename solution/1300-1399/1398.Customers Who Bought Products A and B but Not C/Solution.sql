# Write your MySQL query statement below
select
    o.customer_id,
    c.customer_name
from
    orders o
    left join customers c on o.customer_id = c.customer_id
group by
    customer_id
having
    sum(if(product_name = 'A', 1, 0)) > 0
    and sum(if(product_name = 'B', 1, 0)) > 0
    and sum(if(product_name = 'C', 1, 0)) = 0