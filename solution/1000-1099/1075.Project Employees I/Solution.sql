# Write your MySQL query statement below
SELECT  project_id
       ,round(AVG(experience_years),2) AS average_years
FROM Project p
LEFT JOIN Employee e
ON p.employee_id = e.employee_id
GROUP BY  project_id