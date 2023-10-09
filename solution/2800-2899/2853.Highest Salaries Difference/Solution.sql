# Write your MySQL query statement below
SELECT max(s) - min(s) AS salary_difference
FROM
    (
        SELECT max(salary) AS s
        FROM Salaries
        GROUP BY department
    ) AS t;
