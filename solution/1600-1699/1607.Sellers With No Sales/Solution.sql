# Write your MySQL query statement below
SELECT
    seller_name
FROM
    seller AS s
    LEFT JOIN orders AS o ON s.seller_id = o.seller_id AND year(sale_date) = '2020'
WHERE o.seller_id IS NULL
ORDER BY seller_name;
