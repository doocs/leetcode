# Write your MySQL query statement below
SELECT
    id,
    month,
    SUM(salary) OVER (
        PARTITION BY id
        ORDER BY month
        RANGE 2 PRECEDING
    ) AS Salary
FROM employee
WHERE
    (id, month) NOT IN (
        SELECT
            id,
            MAX(month)
        FROM Employee
        GROUP BY id
    )
ORDER BY id, month DESC;
