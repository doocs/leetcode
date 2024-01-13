# Write your MySQL query statement below
SELECT
    user_id AS buyer_id,
    join_date,
    IFNULL(SUM(YEAR(order_date) = 2019), 0) AS orders_in_2019
FROM
    Users AS u
    LEFT JOIN Orders AS o ON u.user_id = buyer_id
GROUP BY 1;
