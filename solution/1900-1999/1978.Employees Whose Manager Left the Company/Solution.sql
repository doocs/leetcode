# Write your MySQL query statement below
SELECT a.employee_id
FROM
    Employees AS a
    LEFT JOIN Employees AS b ON a.manager_id = b.employee_id
WHERE b.employee_id IS NULL AND a.salary < 30000 AND a.manager_id IS NOT NULL
ORDER BY a.employee_id;
