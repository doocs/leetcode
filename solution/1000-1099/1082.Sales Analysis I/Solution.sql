WITH
    T AS (
        SELECT
            seller_id,
            sum(price) AS tot,
            rank() OVER (ORDER BY sum(price) DESC) AS rk
        FROM Sales
        GROUP BY seller_id
    )
SELECT seller_id
FROM T
WHERE rk = 1;
