# Write your MySQL query statement below
SELECT MAX(s) - MIN(s) AS salary_difference
FROM
    (
        SELECT MAX(salary) AS s
        FROM Salaries
        GROUP BY department
    ) AS t;
