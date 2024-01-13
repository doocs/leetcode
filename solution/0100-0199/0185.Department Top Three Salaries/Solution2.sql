# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            DENSE_RANK() OVER (
                PARTITION BY departmentId
                ORDER BY salary DESC
            ) AS rk
        FROM Employee
    )
SELECT d.name AS Department, t.name AS Employee, salary AS Salary
FROM
    T AS t
    JOIN Department AS d ON t.departmentId = d.id
WHERE rk <= 3;
