# Write your MySQL query statement below
SELECT
    transaction_id
FROM
    (
        SELECT
            transaction_id,
            rank() OVER (
                PARTITION BY date_format(day, '%Y-%m-%d')
                ORDER BY amount DESC
            ) AS rk
        FROM Transactions
        ORDER BY transaction_id
    ) AS t
WHERE rk = 1;
