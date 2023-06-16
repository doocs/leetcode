# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            departmentId,
            name,
            salary,
            rank() OVER (
                PARTITION BY departmentId
                ORDER BY salary DESC
            ) AS rk
        FROM Employee
    )
SELECT
    d.name AS Department,
    t.name AS Employee,
    salary AS Salary
FROM
    t
    JOIN Department AS d ON t.departmentId = d.id
WHERE rk = 1;
