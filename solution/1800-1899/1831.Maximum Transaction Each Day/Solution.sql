# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            transaction_id,
            rank() OVER (
                PARTITION BY day(day)
                ORDER BY amount DESC
            ) AS rk
        FROM Transactions
    )
SELECT transaction_id
FROM T
WHERE rk = 1
ORDER BY 1;
