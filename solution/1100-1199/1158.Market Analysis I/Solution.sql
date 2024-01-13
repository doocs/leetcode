# Write your MySQL query statement below
SELECT
    u.user_id AS buyer_id,
    u.join_date,
    COUNT(order_id) AS orders_in_2019
FROM
    Users AS u
    LEFT JOIN Orders AS o ON u.user_id = o.buyer_id AND YEAR(order_date) = 2019
GROUP BY user_id;
