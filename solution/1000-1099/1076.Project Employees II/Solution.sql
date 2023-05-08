# Write your MySQL query statement below
SELECT  project_id
FROM Project p
GROUP BY  project_id
HAVING COUNT(employee_id) >= all(
SELECT  COUNT(employee_id)
FROM Project
GROUP BY  project_id )