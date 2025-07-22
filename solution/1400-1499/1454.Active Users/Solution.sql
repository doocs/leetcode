# Write your MySQL query statement below
WITH
    T AS (
        SELECT DISTINCT *
        FROM
            Logins
            JOIN Accounts USING (id)
    ),
    P AS (
        SELECT
            *,
            DATE_SUB(
                login_date,
                INTERVAL ROW_NUMBER() OVER (
                    PARTITION BY id
                    ORDER BY login_date
                ) DAY
            ) g
        FROM T
    )
SELECT DISTINCT id, name
FROM P
GROUP BY id, g
HAVING COUNT(*) >= 5
ORDER BY 1;
