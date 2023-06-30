# Write your MySQL query statement below
WITH
    S AS (
        SELECT salary
        FROM Employees
        GROUP BY salary
        HAVING count(1) > 1
    ),
    T AS (
        SELECT salary, row_number() OVER (ORDER BY salary) AS team_id
        FROM S
    )
SELECT e.*, t.team_id
FROM
    Employees AS e
    JOIN T AS t ON e.salary = t.salary
ORDER BY 4, 1;
