# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            seller_id,
            SUM(price) AS tot,
            RANK() OVER (ORDER BY SUM(price) DESC) AS rk
        FROM Sales
        GROUP BY seller_id
    )
SELECT seller_id
FROM T
WHERE rk = 1;
