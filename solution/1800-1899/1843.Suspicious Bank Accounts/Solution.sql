# Write your MySQL query statement below
WITH
    S AS (
        SELECT DISTINCT
            t.account_id,
            DATE_FORMAT(day, '%Y-%m-01') AS day,
            transaction_id AS tx,
            SUM(amount) OVER (
                PARTITION BY account_id, DATE_FORMAT(day, '%Y-%m-01')
            ) > max_income AS marked
        FROM
            Transactions AS t
            LEFT JOIN Accounts AS a ON t.account_id = a.account_id
        WHERE type = 'Creditor'
    )
SELECT DISTINCT s1.account_id
FROM
    S AS s1
    LEFT JOIN S AS s2 ON s1.account_id = s2.account_id AND TIMESTAMPDIFF(Month, s1.day, s2.day) = 1
WHERE s1.marked = 1 AND s2.marked = 1
ORDER BY s1.tx;
