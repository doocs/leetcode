# Write your MySQL query statement below
WITH
    T AS (
        SELECT * FROM Transactions
        UNION
        SELECT id, country, 'chargeback', amount, c.trans_date
        FROM
            Transactions AS t
            JOIN Chargebacks AS c ON t.id = c.trans_id
    )
SELECT
    date_format(trans_date, '%Y-%m') AS month,
    country,
    sum(state = 'approved') AS approved_count,
    sum(if(state = 'approved', amount, 0)) AS approved_amount,
    sum(state = 'chargeback') AS chargeback_count,
    sum(if(state = 'chargeback', amount, 0)) AS chargeback_amount
FROM T
GROUP BY 1, 2
HAVING approved_amount OR chargeback_amount;
