# Write your MySQL query statement
SELECT project_id, round(avg(experience_years), 2) AS average_years
FROM
    Project
    JOIN Employee USING (employee_id)
GROUP BY 1;
