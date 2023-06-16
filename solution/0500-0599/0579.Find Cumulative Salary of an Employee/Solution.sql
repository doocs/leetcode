# Write your MySQL query statement below
SELECT
    id,
    month,
    sum(salary) OVER (
        PARTITION BY id
        ORDER BY month
        RANGE 2 PRECEDING
    ) AS Salary
FROM employee
WHERE
    (id, month) NOT IN (
        SELECT
            id,
            max(month)
        FROM Employee
        GROUP BY id
    )
ORDER BY id, month DESC;
