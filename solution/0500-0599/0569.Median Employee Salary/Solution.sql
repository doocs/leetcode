# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            *,
            ROW_NUMBER() OVER (
                PARTITION BY company
                ORDER BY salary ASC
            ) AS rk,
            COUNT(id) OVER (PARTITION BY company) AS n
        FROM Employee
    )
SELECT
    id,
    company,
    salary
FROM t
WHERE rk >= n / 2 AND rk <= n / 2 + 1;
