# Write your MySQL query statement below
select
    p1.product_id product_id,
    ifnull(p2.price, 10) price
from
    (
        select
            distinct(product_id) product_id
        from
            Products
    ) p1
    left join (
        select
            t1.product_id,
            t1.new_price price
        from
            Products t1
            join (
                select
                    product_id,
                    max(change_date) change_date
                from
                    Products
                where
                    change_date <= '2019-08-16'
                group by
                    product_id
            ) t2 on t1.product_id = t2.product_id
            and t1.change_date = t2.change_date
    ) p2 on p1.product_id = p2.product_id;