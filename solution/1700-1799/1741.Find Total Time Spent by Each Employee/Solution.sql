# Write your MySQL query statement below
SELECT event_day AS day, emp_id, sum(out_time - in_time) AS total_time
FROM Employees
GROUP BY 1, 2;
