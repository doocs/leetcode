# Write your MySQL query statement below
select
    u.user_id as seller_id,
    case
        when u.favorite_brand = i.item_brand then 'yes'
        else 'no'
    end as 2nd_item_fav_brand
from
    users u
    left join (
        select
            order_date,
            item_id,
            seller_id,
            rank() over(
                partition by seller_id
                order by
                    order_date
            ) as rk
        from
            orders
    ) o on u.user_id = o.seller_id
    and o.rk = 2
    left join items i on o.item_id = i.item_id;