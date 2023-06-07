# Write your MySQL query statement below
select
    seller_name
from
    seller s
    left join orders o on s.seller_id = o.seller_id
    and year(sale_date) = '2020'
where
    o.seller_id is null
order by
    seller_name