# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            project_id,
            rank() OVER (ORDER BY count(employee_id) DESC) AS rk
        FROM Project
        GROUP BY 1
    )
SELECT project_id
FROM T
WHERE rk = 1;
