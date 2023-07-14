# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            project_id,
            employee_id,
            rank() OVER (
                PARTITION BY project_id
                ORDER BY experience_years DESC
            ) AS rk
        FROM
            Project
            JOIN Employee USING (employee_id)
    )
SELECT project_id, employee_id
FROM T
WHERE rk = 1;
