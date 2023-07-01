# Write your MySQL query statement below
WITH RECURSIVE
    S AS (
        SELECT 0 AS n
        UNION
        SELECT n + 1
        FROM S
        WHERE
            n < (
                SELECT max(cnt)
                FROM
                    (
                        SELECT count(1) AS cnt
                        FROM Transactions
                        GROUP BY user_id, transaction_date
                    ) AS t
            )
    ),
    T AS (
        SELECT v.user_id, visit_date, ifnull(cnt, 0) AS cnt
        FROM
            Visits AS v
            LEFT JOIN (
                SELECT user_id, transaction_date, count(1) AS cnt
                FROM Transactions
                GROUP BY 1, 2
            ) AS t
                ON v.user_id = t.user_id AND v.visit_date = t.transaction_date
    )
SELECT n AS transactions_count, count(user_id) AS visits_count
FROM
    S AS s
    LEFT JOIN T AS t ON s.n = t.cnt
GROUP BY n
ORDER BY n;
