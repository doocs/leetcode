# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            d.name AS department,
            e.name AS employee,
            salary,
            RANK() OVER (
                PARTITION BY d.name
                ORDER BY salary DESC
            ) AS rk
        FROM
            Employee AS e
            JOIN Department AS d ON e.departmentId = d.id
    )
SELECT department, employee, salary
FROM T
WHERE rk = 1;
