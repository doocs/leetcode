# Write your MySQL query statement below
SELECT
    sale_date AS SALE_DATE,
    sum(
        CASE WHEN fruit = 'oranges' THEN -sold_num ELSE sold_num END
    ) AS DIFF
FROM
    Sales
GROUP BY sale_date
ORDER BY sale_date;