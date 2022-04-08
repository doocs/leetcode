SELECT employee_id
FROM Employees AS e
WHERE e.employee_id NOT IN (
        SELECT employee_id
        FROM Salaries
    )
UNION
SELECT employee_id
FROM Salaries AS s
WHERE s.employee_id NOT IN (
        SELECT employee_id
        FROM Employees
    )
ORDER BY employee_id;