SELECT p.project_id,
         p.employee_id
FROM Project p
JOIN Employee e
    ON p.employee_id = e.employee_id
WHERE (p.project_id, e.experience_years) IN
    (SELECT p.project_id,
         max(e.experience_years) AS max_years
    FROM Project p
    JOIN Employee e
        ON p.employee_id = e.employee_id
    GROUP BY  p.project_id)
