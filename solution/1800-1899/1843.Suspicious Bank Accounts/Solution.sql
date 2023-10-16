# Write your MySQL query statement below
WITH
    S AS (
        SELECT
            account_id,
            DATE_FORMAT(day, '%Y%m') AS yearmonth,
            transaction_id AS tx
        FROM
            Transactions
            JOIN Accounts USING (account_id)
        WHERE type = 'Creditor'
        GROUP BY account_id, yearmonth
        HAVING SUM(amount) > AVG(max_income)
    )
SELECT DISTINCT account_id
FROM S
WHERE (account_id, PERIOD_ADD(yearmonth, 1)) IN (SELECT account_id, yearmonth FROM S)
ORDER BY tx;
