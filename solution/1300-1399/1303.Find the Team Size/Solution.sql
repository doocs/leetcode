# Write your MySQL query statement below
SELECT e1.employee_id, COUNT(1) AS team_size
FROM
    Employee AS e1
    LEFT JOIN Employee AS e2 USING (team_id)
GROUP BY 1;
