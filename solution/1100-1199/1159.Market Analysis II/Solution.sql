# Write your MySQL query statement below
SELECT
    u.user_id AS seller_id,
    CASE
        WHEN u.favorite_brand = i.item_brand THEN 'yes'
        ELSE 'no'
    END AS 2nd_item_fav_brand
FROM
    users AS u
    LEFT JOIN (
        SELECT
            order_date,
            item_id,
            seller_id,
            RANK() OVER (
                PARTITION BY seller_id
                ORDER BY order_date
            ) AS rk
        FROM orders
    ) AS o
        ON u.user_id = o.seller_id AND o.rk = 2
    LEFT JOIN items AS i ON o.item_id = i.item_id;
