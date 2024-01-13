# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            project_id,
            RANK() OVER (ORDER BY COUNT(employee_id) DESC) AS rk
        FROM Project
        GROUP BY 1
    )
SELECT project_id
FROM T
WHERE rk = 1;
