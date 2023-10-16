# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            id,
            month,
            SUM(salary) OVER (
                PARTITION BY id
                ORDER BY month
                RANGE 2 PRECEDING
            ) AS salary,
            RANK() OVER (
                PARTITION BY id
                ORDER BY month DESC
            ) AS rk
        FROM Employee
    )
SELECT id, month, salary
FROM T
WHERE rk > 1
ORDER BY 1, 2 DESC;
