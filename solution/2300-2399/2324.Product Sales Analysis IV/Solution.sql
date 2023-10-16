# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            user_id,
            product_id,
            RANK() OVER (
                PARTITION BY user_id
                ORDER BY SUM(quantity * price) DESC
            ) AS rk
        FROM
            Sales
            JOIN Product USING (product_id)
        GROUP BY 1, 2
    )
SELECT user_id, product_id
FROM T
WHERE rk = 1;
