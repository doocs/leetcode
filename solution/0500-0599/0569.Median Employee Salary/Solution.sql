# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            *,
            row_number() OVER (
                PARTITION BY company
                ORDER BY salary ASC
            ) AS rk,
            count(id) OVER (PARTITION BY company) AS n
        FROM Employee
    )
SELECT
    id,
    company,
    salary
FROM t
WHERE rk >= n / 2 AND rk <= n / 2 + 1;
