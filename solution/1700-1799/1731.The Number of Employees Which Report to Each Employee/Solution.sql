# Write your MySQL query statement below
SELECT
    e2.employee_id,
    e2.name,
    count(1) AS reports_count,
    round(avg(e1.age)) AS average_age
FROM
    Employees AS e1
    JOIN Employees AS e2 ON e1.reports_to = e2.employee_id
WHERE e2.employee_id IS NOT NULL
GROUP BY e2.employee_id
ORDER BY e2.employee_id;
