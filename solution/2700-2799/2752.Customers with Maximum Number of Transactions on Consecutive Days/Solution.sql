# Write your MySQL query statement below
WITH
    s AS (
        SELECT
            customer_id,
            date_sub(
                transaction_date,
                INTERVAL row_number() OVER (
                    PARTITION BY customer_id
                    ORDER BY transaction_date
                ) DAY
            ) AS transaction_date
        FROM Transactions
    ),
    t AS (
        SELECT customer_id, transaction_date, count(1) AS cnt
        FROM s
        GROUP BY 1, 2
    )
SELECT customer_id
FROM t
WHERE cnt = (SELECT max(cnt) FROM t)
ORDER BY customer_id;
