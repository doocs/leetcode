# Write your MySQL query statement below
WITH
    t AS (
        SELECT * FROM Transactions
        UNION ALL
        SELECT id, country, 'chargeback' AS state, amount, cb.trans_date
        FROM
            Chargebacks AS cb
            LEFT JOIN Transactions AS tx ON cb.trans_id = tx.id
    )
SELECT
    date_format(trans_date, '%Y-%m') AS month,
    country,
    sum(state = 'approved') AS approved_count,
    sum(if(state = 'approved', amount, 0)) AS approved_amount,
    sum(state = 'chargeback') AS chargeback_count,
    sum(if(state = 'chargeback', amount, 0)) AS chargeback_amount
FROM t
GROUP BY 1, 2
HAVING approved_amount OR chargeback_amount;
