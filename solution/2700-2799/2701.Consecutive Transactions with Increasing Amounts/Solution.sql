# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            t1.*,
            sum(
                CASE
                    WHEN t2.customer_id IS NULL THEN 1
                    ELSE 0
                END
            ) OVER (ORDER BY customer_id, transaction_date) AS s
        FROM
            Transactions AS t1
            LEFT JOIN Transactions AS t2
                ON t1.customer_id = t2.customer_id
                AND t1.amount > t2.amount
                AND datediff(t1.transaction_date, t2.transaction_date) = 1
    )
SELECT
    customer_id,
    min(transaction_date) AS consecutive_start,
    max(transaction_date) AS consecutive_end
FROM T
GROUP BY customer_id, s
HAVING count(1) >= 3
ORDER BY customer_id;
