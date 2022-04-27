SELECT user_id AS buyer_id,
    join_date,
    COUNT(order_id) AS orders_in_2019
FROM users AS u
    LEFT JOIN orders AS o ON u.user_id = o.buyer_id
    AND YEAR(order_date) = 2019
GROUP BY user_id;