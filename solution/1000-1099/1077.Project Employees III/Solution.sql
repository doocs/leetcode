# Write your MySQL query statement below
SELECT
    project_id,
    employee_id
FROM
    (
        SELECT
            p.project_id,
            p.employee_id,
            rank() OVER (
                PARTITION BY p.project_id
                ORDER BY e.experience_years DESC
            ) AS rk
        FROM
            Project AS p
            LEFT JOIN Employee AS e ON p.employee_id = e.employee_id
    ) AS t
WHERE rk = 1;
