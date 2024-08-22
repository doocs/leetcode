# Write your MySQL query statement below
WITH RECURSIVE
    T AS (
        SELECT
            employee_id,
            employee_name,
            0 AS hierarchy_level,
            manager_id,
            salary
        FROM Employees
        WHERE manager_id IS NULL
        UNION ALL
        SELECT
            e.employee_id,
            e.employee_name,
            hierarchy_level + 1 AS hierarchy_level,
            e.manager_id,
            e.salary
        FROM
            T t
            JOIN Employees e ON t.employee_id = e.manager_id
    ),
    P AS (
        SELECT salary
        FROM Employees
        WHERE manager_id IS NULL
    )
SELECT
    employee_id subordinate_id,
    employee_name subordinate_name,
    hierarchy_level,
    t.salary - p.salary salary_difference
FROM
    T t
    JOIN P p
WHERE hierarchy_level != 0
ORDER BY 3, 1;
