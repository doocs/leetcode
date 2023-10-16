# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            transaction_id,
            RANK() OVER (
                PARTITION BY DAY(day)
                ORDER BY amount DESC
            ) AS rk
        FROM Transactions
    )
SELECT transaction_id
FROM T
WHERE rk = 1
ORDER BY 1;
