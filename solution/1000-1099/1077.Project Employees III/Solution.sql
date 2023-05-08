# Write your MySQL query statement below
SELECT  project_id
       ,employee_id
FROM
(
	SELECT  p.project_id
	       ,p.employee_id
	       ,rank() over(partition by p.project_id ORDER BY e.experience_years desc) AS rk
	FROM Project p
	LEFT JOIN Employee e
	ON p.employee_id = e.employee_id
) t
WHERE rk = 1