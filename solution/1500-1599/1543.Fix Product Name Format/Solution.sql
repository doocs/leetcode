# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            lower(trim(product_name)) AS product_name,
            date_format(sale_date, '%Y-%m') AS sale_date
        FROM Sales
    )
SELECT product_name, sale_date, count(1) AS total
FROM t
GROUP BY 1, 2
ORDER BY 1, 2;
